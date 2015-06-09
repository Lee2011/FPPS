package week3

object scratch {
	new Rational(3,4)                         //> res0: week3.Rational = 3/4
	
	def error(msg: String) = throw new Error(msg)
                                                  //> error: (msg: String)Nothing
	
	error("Test")                             //> java.lang.Error: Test
                                                  //| 	at week3.scratch$$anonfun$main$1.error$1(week3.scratch.scala:6)
                                                  //| 	at week3.scratch$$anonfun$main$1.apply$mcV$sp(week3.scratch.scala:8)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.scratch$.main(week3.scratch.scala:3)
                                                  //| 	at week3.scratch.main(week3.scratch.scala)
}