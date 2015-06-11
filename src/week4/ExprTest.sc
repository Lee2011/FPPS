package week4

object ExprTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unknown expression " + e)
  }                                               //> eval: (e: week4.Expr)Int
  
  eval(new Number(5))                             //> res0: Int = 5
  eval(new Sum(new Number(4), new Number(7)))     //> res1: Int = 11
}

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber = true
  def isSum = false
  def numValue = n
  def leftOp = throw new Error("Number.leftOp")
  def rightOp = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber = false
  def isSum = true
  def numValue = throw new Error("Sum.numValue")
  def leftOp = e1
  def rightOp = e2
}