package week3

object scratch {
	new Rational(3,4)                         //> res0: week3.Rational = 3/4
	
	def error(msg: String) = throw new Error(msg)
                                                  //> error: (msg: String)Nothing
	val x = null                              //> x  : Null = null
	val y: String = x                         //> y  : String = null
	
	//val z: Int = null
	
	if (true) 1 else false                    //> res1: AnyVal = 1
	
	def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)week3.Cons[T]
	
	val myList = singleton[Int](5)            //> myList  : week3.Cons[Int] = week3.Cons@39ba5a14
	singleton[Boolean](true)                  //> res2: week3.Cons[Boolean] = week3.Cons@511baa65
	
	def nth[T](n: Int, list: List[T]): T =
	  if (list.isEmpty) throw new IndexOutOfBoundsException("Index out of bounds")
	  else if ( n > 0 ) nth[T](n-1, list.tail)
	  else list.head                          //> nth: [T](n: Int, list: week3.List[T])T
	  
  nth(0, myList)                                  //> res3: Int = 5
  
  val myList2 = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> myList2  : week3.Cons[Int] = week3.Cons@340f438e
	nth(0, myList2)                           //> res4: Int = 1
  nth(1, myList2)                                 //> res5: Int = 2
  nth(2, myList2)                                 //> res6: Int = 3
  nth(-1, myList2)                                //> res7: Int = 1
  nth(3, myList2)                                 //> java.lang.IndexOutOfBoundsException: Index out of bounds
                                                  //| 	at week3.scratch$$anonfun$main$1.nth$1(week3.scratch.scala:20)
                                                  //| 	at week3.scratch$$anonfun$main$1.apply$mcV$sp(week3.scratch.scala:31)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.scratch$.main(week3.scratch.scala:3)
                                                  //| 	at week3.scratch.main(week3.scratch.scala)
}