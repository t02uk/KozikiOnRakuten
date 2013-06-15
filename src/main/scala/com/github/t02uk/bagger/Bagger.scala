package com.github.t02uk.bagger

import scala.io.Source
import scala.xml.parsing.XhtmlParser
import nu.validator.htmlparser.sax.HtmlParser
import nu.validator.htmlparser.common.XmlViolationPolicy
import scala.xml.parsing.NoBindingFactoryAdapter
import org.xml.sax.InputSource
import java.io.StringReader
import scala.xml.Text
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import java.net.URL
import cn.orz.pascal.mechanize.Mechanize
import com.gargoylesoftware.htmlunit.BrowserVersion
import java.io.InputStream
import scala.xml.NamespaceBinding
import scala.xml.NamespaceBinding
import scala.xml.NodeSeq
import java.math.BigDecimal
import java.io.PrintWriter
import scala.io.Codec

/**
 * This class has EntryPoint!
 */
object Bagger {

  private[this] lazy val hp = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)
    hp
  }

  /**
   * A tiny NodeSeq wrapper for filter attribute of NodeSeq without dull process
   */
  implicit class NodeSeqExt(self: NodeSeq) {
    type Selector = (NodeSeq, String) => NodeSeq
    // 
    def has(selector: (NodeSeq, String) => NodeSeq)(attribute: String)(value: String): NodeSeq = {
      self filter (x => selector(x, "@" + attribute) flatMap(_.toString.split("""\s""")) contains value)
    }
    // x \ y
    def yen1Quote: Selector = ((x, y) => x \ y)
    // .class selector
    def \* = has(yen1Quote)("class") _
    // #hoge selector
    def \# = has(yen1Quote)("id") _
    // name = "name"
    def named = has(yen1Quote)("name") _
  }
  
  /**
   * sleep
   */
  private[this] def sleep(millis: Long) {
    Thread.sleep(millis)
  }

  //
  def main(args: Array[String]) {
    
    // throw error if mandatory parameter is not given
    if(args.size < 1) {
      error("""wrong paramter size""")
      return
    }
    
    // url from args
    val url = args(0)
    // outputFileName from argument (default name is out.csv)
    val outputFileName = {
      if(args.size < 2) "out.csv"
      else args(1)
    }
    // file encoding (default encoding is UTF-8)
    val codec = {
      if(args.size < 3) "UTF-8"
      else args(2)
    }

    
    val records = detectAmazonLink(url, codec).flatMap { x =>
      val (asin, title) = x
      println("analyze > %s" format title)
      val bookDatas = searchByRakuten(asin, title)
      sleep(1000)
      
      val prices = bookDatas.map(_.price)
      if(!prices.isEmpty) {
        // get min/max price
        val recordLow = prices.min
        val recordHigh = prices.max
        
        // fill extra information
        for(x <- bookDatas) {
          x.hasRecordLow = (recordLow - x.price).abs < 1
          x.recordHighRatio = x.price / recordHigh
        }
        
        // 
        bookDatas.map(_.toCsv)
      } else {
        Nil
      }
    }

    // write file
    val output = new PrintWriter(outputFileName)
    output.print(records.mkString(BookData.exportTitle + "\n", "\n", ""))
    output.close()
    
    println("complete!")
    println("you got result in " + outputFileName)
  }
  
  /**
   * obtain amazon link from URL
   */
  private[this] def detectAmazonLink(url: String, codec: String): Seq[(String, String)] = {
    val re = """<a\s+href="http://www.amazon.co.jp/exec/obidos/ASIN/(\w+?)/\S+".+?>(.+?)</a>""".r
//                                                                  |> \1 -> asin  |
//                                                                                 |> \2 -> title

    val source = Source.fromURL(url)(codec).mkString
    for((asin, title) <- re.findAllMatchIn(source).map(x => (x.group(1), x.group(2))).toSeq.distinct
      // refuce <img> tag and Amazon.co.jpで詳細を・・・
      if !title.startsWith("<img") && !title.startsWith("Amazon.co.jp")
    ) yield (asin, title)
  }

  /**
   * Search books on Rakuten (key is ASIN)
   */
  private[this] def searchByRakuten(asin: String, title: String): Seq[BookData] = {
    // search by rakuten
    val searchResult = Source.fromURL(s"""http://search.rakuten.co.jp/search/mall/${asin}/-/""").mkString
  
    // and parse
    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(new StringReader(searchResult)))

    // get book list
    val box = saxer.rootElem \ "body" \ "div" \ "div" \ "div" \ "div" \ "div" \ "div" \ "div" \# "rsrSectInbox"
    val books = box \ "div" \* "rsrSResultSect"

    // parse and return object
    books.map { book =>
//      val title = book \"div" \* "rsrSResultItemTxt" \ "h2" \ "a" text
      val link = book \"div" \* "rsrSResultItemTxt" \ "h2" \ "a" \ "@href" text
      val shop = book \\ "span" \* "txtIconShopName" text
      val priceStr = ("""^\d+""".r).findFirstIn(book \\ "p" \* "price" \ "a" text)
      val price = priceStr.map(x => new BigDecimal(x)).getOrElse(null)
      val includeFreight = book \\ "p" \* "price" \ "span" \* "priceAssistText" text
      val point = book \\ "p" \* "point" text

      BookData(asin, title, link, shop, price, includeFreight, point)
    }
  }
}