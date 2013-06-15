package com.github.t02uk.bagger

case class BookData(
  asin: String,
  title: String,
  link: String,
  shop: String,
  price: BigDecimal,
  includeFreight: String,
  point: String
) {
  var hasRecordLow: Boolean = false
  def hasRecordLowStr: String = {
    if(hasRecordLow) "最安！"
    else ""
  }
  var recordHighRatio: BigDecimal = 0
  def recordHighRatioStr: String = "%.2f" format recordHighRatio.doubleValue

  def toCsv: String = {
    List(
      asin,
      title,
      link,
      shop,
      point,
      price,
      hasRecordLowStr,
      recordHighRatioStr,
      includeFreight
    ).map("\"" + _ + "\"").mkString(",")
  }
}

object BookData {
  def exportTitle: String = {
    List(
      "ASIN",
      "タイトル",
      "リンク",
      "ショップ",
      "ポイント",
      "価格",
      "最安",
      "最高値比率",
      "送料込み"
    ) mkString(",")
  }
}