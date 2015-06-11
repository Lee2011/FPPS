package week4

object week4 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Function1[A, B] {
    def apply(x: A): B
  }
  
  (x: Int) => x * x                               //> res0: Int => Int = <function1>
  
  val y = new Function1[Int, Int]{
  	def apply(x: Int) = x * x
  }                                               //> y  : week4.week4.Function1[Int,Int] = week4.week4$$anonfun$main$1$$anon$1@57
                                                  //| 829d67
  
  y(5)                                            //> res1: Int = 25
  
  
  
}