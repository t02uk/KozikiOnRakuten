概要
==============
Amazonの書籍アフィリエイト一覧より、楽天市場での売価をCSV出力する


必要なもの
=============
1. Scala Compielr
2. SBT


実行方法
============
``` sh
sbt run "対象URL [CSVファイル名] [エンコーディング]"
```

1. 対象URL : アフィリエイトの貼ってあるURL
2. CSVファイル名：解析結果を出力するCSVファイル名(default: out.csv)
3. エンコーディング：対象URLのエンコーディング。ミスるとエラー。(default: UTF-8)

ex :>
``` sh
sbt run 'http://imonoblog.com/archives/51664458.html 必ず読んでおきたい、今までで最もブックマークされたAmazonの本ベスト50.csv UTF-8'
```

=>
``` csv
ASIN  タイトル	リンク	ショップ	ポイント	価格	最安	最高値比率	送料込み																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4140814047	フリー~〈無料〉からお金を生みだす新戦略	http://item.rakuten.co.jp/bookfan/bk-4140814047/	オンライン書店　BOOKFAN	ポイント 2倍	1890	最安！	1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4140814047	フリー~〈無料〉からお金を生みだす新戦略	http://item.rakuten.co.jp/booxstore/bk-4140814047/	オンライン書店boox	ポイント 5倍	1890	最安！	1	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4274065979	ハッカーと画家 コンピュータ時代の創造者たち	http://item.rakuten.co.jp/bookfan/bk-4274065979/	オンライン書店　BOOKFAN	ポイント 2倍	2520	最安！	1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4274065979	ハッカーと画家 コンピュータ時代の創造者たち	http://item.rakuten.co.jp/booxstore/bk-4274065979/	オンライン書店boox	ポイント 5倍	2520	最安！	1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4479791779	自分の小さな「箱」から脱出する方法	http://item.rakuten.co.jp/bookfan/bk-4479791779/	オンライン書店　BOOKFAN	ポイント 2倍	1680	最安！	1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4479791779	自分の小さな「箱」から脱出する方法	http://item.rakuten.co.jp/booxstore/bk-4479791779/	オンライン書店boox	ポイント 5倍	1680	最安！	1	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4484101017	20歳のときに知っておきたかったこと スタンフォード大学集中講義	http://item.rakuten.co.jp/comicset/4484101017/	もったいない本舗　楽天市場店		747	最安！	0.51	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4484101017	20歳のときに知っておきたかったこと スタンフォード大学集中講義	http://item.rakuten.co.jp/bookfan/bk-4484101017/	オンライン書店　BOOKFAN	ポイント 2倍	1470		1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4484101017	20歳のときに知っておきたかったこと スタンフォード大学集中講義	http://item.rakuten.co.jp/booxstore/bk-4484101017/	オンライン書店boox	ポイント 5倍	1470		1	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4478012032	もし高校野球の女子マネージャーがドラッカーの『マネジメント』を読んだら	http://item.rakuten.co.jp/comicset/4478012032/	もったいない本舗　楽天市場店		294	最安！	0.18	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4478012032	もし高校野球の女子マネージャーがドラッカーの『マネジメント』を読んだら	http://item.rakuten.co.jp/bookfan/bk-4478012032/	オンライン書店　BOOKFAN	ポイント 2倍	1680		1	送料込																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
4478012032	もし高校野球の女子マネージャーがドラッカーの『マネジメント』を読んだら	http://item.rakuten.co.jp/booxstore/bk-4478012032/	オンライン書店boox	ポイント 5倍	1680		1	送料別																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
:
:
:
```
