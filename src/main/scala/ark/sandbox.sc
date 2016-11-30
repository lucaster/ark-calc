package ark

object sandbox {

	println("");                              //> 

	import scala.io._
	
	Source.fromFile("TrapList.csv", "UTF-8")  //> java.io.FileNotFoundException: TrapList.csv (Impossibile trovare il file spe
                                                  //| cificato)
                                                  //| 	at java.io.FileInputStream.open0(Native Method)
                                                  //| 	at java.io.FileInputStream.open(Unknown Source)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:60)
                                                  //| 	at ark.sandbox$$anonfun$main$1.apply$mcV$sp(ark.sandbox.scala:9)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at ark.sandbox$.main(ark.sandbox.scala:3)
                                                  //| 	at ark.sandbox.main(ark.sandbox.scala)
  
  

}