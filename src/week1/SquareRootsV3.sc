package week1

object SquareRootsV3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * Lexical Scoping:
   *   Definitions of outer blocks are visible inside a block unless they are shadowed.
   *   Therefore, we can simplify sqrt by eliminating redundant occurrences of the x parameter, which means everywhere the same thing:
   */
  
  def sqrt(x: Double) = {
 	 
 	  def sqrtIter(guess: Double): Double =
			if (isGoodEnough(guess)) guess
			else sqrtIter(improve(guess))
	
		def improve(guess: Double) =
			(guess + x / guess) / 2

		def isGoodEnough(guess: Double) =
			abs(guess * guess - x) < 0.001
		
		def abs(n: Double): Double =
 			if (n > 0) n else -n
 	
 		sqrtIter(1.0)
 	}                                         //> sqrt: (x: Double)Double
 	
 	sqrt(4)                                   //> res0: Double = 2.0000000929222947
 	
 	sqrt(9)                                   //> res1: Double = 3.00009155413138
 	
 	sqrt(0.001)                               //> res2: Double = 0.04124542607499115
 	
 	sqrt(0.1e-20)                             //> res3: Double = 0.03125
 	
 	//sqrt(1.0e20)
 	
 	//sqrt(1.0e50)
  
}