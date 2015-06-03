package week1

object SquareRootsV2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * Nested functions:
   *
   * It’s good functional programming style to split up a task into many small functions.
   * But the names of functions like sqrtIter, improve, and isGoodEnough matter only for the implementation of sqrt, not for its usage.
   * Normally we would not like users to access these functions directly.
   * We can achieve this and at the same time avoid “name-space pollution” by putting the auxciliary functions inside sqrt.
   */
   
  /*
   * Blocks in Scala:
   *
   *   A block is delimited by braces { ... }.
   *   It contains a sequence of definitions or expressions.
   *   The last element of a block is an expression that defines its value.
   *   This return expression can be preceded by auxiliary definitions.
   *   Blocks are themselves expressions; a block may appear everywhere an expression can.
   *
   * Blocks and Visibility:
   *
   *   The definitions inside a block are only visible from within the block.
   *   The definitions inside a block shadow definitions of the same names outside the block.
   */
 		
 	def sqrt(x: Double) = {
 	 
 	  def sqrtIter(guess: Double, x: Double): Double =
			if (isGoodEnough(guess, x)) guess
			else sqrtIter(improve(guess, x), x)
	
		def improve(guess: Double, x: Double) =
			(guess + x / guess) / 2

		def isGoodEnough(guess: Double, x: Double) =
			abs(guess * guess - x) < 0.001
		
		def abs(n: Double): Double =
 			if (n > 0) n else -n
 	
 		sqrtIter(1.0, x)
 	}                                         //> sqrt: (x: Double)Double
 	
 	sqrt(4)                                   //> res0: Double = 2.0000000929222947
 	
 	sqrt(9)                                   //> res1: Double = 3.00009155413138
 	
 	sqrt(0.001)                               //> res2: Double = 0.04124542607499115
 	
 	sqrt(0.1e-20)                             //> res3: Double = 0.03125
 	
 	//sqrt(1.0e20)
 	
 	//sqrt(1.0e50)

}