package scalaByExample

import scala.math

object ch5FixedPoint {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  	
	/* ********************************
   * Example: Finding Fixed Points of Functions
   * ********************************/
   
  /*
   * A number x is called a fixed point of a function f if
   *   f(x) = x
   * For some functions f we can locate the fixed point by beginning with an initial guess and then applying f repeatedly,
   * until the value does not change anymore (or the change is within a small tolerance). This is possible if the sequence
   *   x, f(x), f(f(x)), f(f(f(x))), ...
   * converges to fixed point of f . This idea is captured in the following “fixed-point finding function”:
   */
   
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  def abs(x: Double) = if (x >= 0) x else -x      //> abs: (x: Double)Double
  def isCloseEnough(x: Double, y: Double) = abs((x - y) / x) < tolerance
                                                  //> isCloseEnough: (x: Double, y: Double)Boolean
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
		def iterate(guess: Double): Double = {
			val next = f(guess)
			println(next)					// print statement which keeps track of the current guess value
			if (isCloseEnough(guess, next)) next
			else iterate(next)
		}
		iterate(firstGuess)
	}                                         //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
	
	/*
	 * We now apply this idea in a reformulation of the square root function. Let’s start with a specification of sqrt:
	 *
	 * sqrt(x) = the y such that y * y = x
	 *         = the y such that y = x / y
	 *
	 * Hence, sqrt(x) is a fixed point of the function y => x / y. This suggests that sqrt(x) can be computed by fixed point iteration:
	 */
	def sqrt(x: Double) = fixedPoint(y => x / y)(1.0)
                                                  //> sqrt: (x: Double)Double
	
	/*
	 * But if we try this, we find that the computation does not converge. Let’s instrument the fixed point function with a print
	 * statement which keeps track of the current guess value, Then, sqrt(2) yields:
	 *
	 *     	2.0
	 *			1.0
	 *     	2.0
	 *			1.0
	 *     	2.0
	 *			1.0
	 * 			...
	 */
	 
	/*
	 * One way to control such oscillations is to prevent the guess from changing too much. This can be achieved by averaging
	 * successive values of the original sequence:
	 */
	 
	def sqrtV2(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)
                                                  //> sqrtV2: (x: Double)Double
	
	sqrtV2(2)                                 //> 1.5
                                                  //| 1.4166666666666665
                                                  //| 1.4142156862745097
                                                  //| 1.4142135623746899
                                                  //| res0: Double = 1.4142135623746899
	
	/*
	 * The previous examples showed that the expressive power of a language is considerably enhanced if functions can be
	 * passed as arguments. The next example shows that functions which return functions can also be very useful.
	 *
	 * Consider again fixed point iterations. We started with the observation that 2^(x) is a fixed point of the function y => x / y.
	 * Then we made the iteration converge by averaging successive values. This technique of average damping is so general that it
	 * can be wrapped in another function.
	 */
	
	def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
	
	// Using averageDamp, we can reformulate the square root function as follows. This expresses the elements of the algorithm as clearly as possible.
	
	def sqrtV3(x: Double) = fixedPoint(averageDamp(y => x/y))(1.0)
                                                  //> sqrtV3: (x: Double)Double
	
	sqrtV3(2)                                 //> 1.5
                                                  //| 1.4166666666666665
                                                  //| 1.4142156862745097
                                                  //| 1.4142135623746899
                                                  //| res1: Double = 1.4142135623746899
}