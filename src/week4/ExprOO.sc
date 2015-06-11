package week4

object ExprOO {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = new NumberV2(5).eval                    //> x  : Int = 5
  
  val y = new SumV2(new NumberV2(4), new NumberV2(7)).eval
                                                  //> y  : Int = 11
  
}

trait ExprV2 {
	def eval: Int
}

class NumberV2(num: Int) extends ExprV2 {
	def eval: Int = num
}

class SumV2(e1: ExprV2, e2: ExprV2) extends ExprV2 {
	def eval: Int = e1.eval + e2.eval
}