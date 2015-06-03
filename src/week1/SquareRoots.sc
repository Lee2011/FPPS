package week1

object SquareRoots {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * 定义一个函数来计算 x 的平方根
   *   def sqrt(x: Double): Double = ...
   */
   
  def sqrtIter(guess: Double, x: Double): Double =
		if (isGoodEnough(guess, x)) guess
		else sqrtIter(improve(guess, x), x)
                                                  //> sqrtIter: (guess: Double, x: Double)Double
	def improve(guess: Double, x: Double) =
		(guess + x / guess) / 2           //> improve: (guess: Double, x: Double)Double

	def isGoodEnough(guess: Double, x: Double) =
		abs(guess * guess - x) < 0.001    //> isGoodEnough: (guess: Double, x: Double)Boolean
		
	def abs(n: Double): Double =
 		if (n > 0) n else -n              //> abs: (n: Double)Double
 		
 	def sqrt(x: Double) = sqrtIter(1.0, x)    //> sqrt: (x: Double)Double
 	
 	sqrt(4)                                   //> res0: Double = 2.0000000929222947
 	
 	sqrt(9)                                   //> res1: Double = 3.00009155413138
 	
 	sqrt(0.001)                               //> res2: Double = 0.04124542607499115
 	
 	sqrt(0.1e-20)                             //> res3: Double = 0.03125
 	
 	//sqrt(1.0e20)
 	
 	//sqrt(1.0e50)

}