概要
==============
Amazonの書籍アフィリエイト一覧より、楽天市場の売価をCSV出力する


必要なもの
=============
1.scala
2.sbt


実行方法
============
sbt run "対象URL [CSVファイル名] [エンコーディング]"

1.対象URL : アフィリエイトの貼ってあるURL
2.CSVファイル名：解析結果を出力するCSVファイル名(default: out.csv)
3.エンコーディング：対象URLのエンコーディング。ミスするとエラー。(default: UTF-8)

ex)
sbt run 'http://imonoblog.com/archives/51664458.html 必ず読んでおきたい、今までで最もブックマークされたAmazonの本ベスト50.csv UTF-8'

