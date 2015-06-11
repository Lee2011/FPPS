package week4

object ExprPatternMatching {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def evalV3(e: ExprV3): Int = e match {
  	case NumberV3(n) => n
  	case SumV3(e1, e2) => evalV3(e1) + evalV3(e2)
  }                                               //> evalV3: (e: week4.ExprV3)Int
  
  def showV3(e: ExprV3): String = e match {
  	case NumberV3(n) => n.toString
  	case SumV3(e1, e2) => showV3(e1) + " + " + showV3(e2)
  }                                               //> showV3: (e: week4.ExprV3)String
  
  val m = evalV3(new SumV3(new NumberV3(5), new NumberV3(6)))
                                                  //> m  : Int = 11
  showV3(new SumV3((new SumV3(new NumberV3(5), new NumberV3(6))), new NumberV3(5)))
                                                  //> res0: String = 5 + 6 + 5
}

trait ExprV3
case class NumberV3(n: Int) extends ExprV3
case class SumV3(e1: ExprV3, e2: ExprV3) extends ExprV3