package week2

object HigherOrder {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * Functional languages treat functions as first-class values.
   * This means that, like any other value, a function can be passed as a parameter and returned as a result.
   * This provides a flexible way to compose programs.
   * Functions that take other functions as parameters or that return functions as results are called higher order functions.
   */
   
  def sumInts(a: Int, b: Int): Int =
		if (a > b) 0 else a + sumInts(a + 1, b)
                                                  //> sumInts: (a: Int, b: Int)Int

  def cube(x: Int): Int = x * x * x               //> cube: (x: Int)Int
  
  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)//> sumCubes: (a: Int, b: Int)Int

  def factorial(n: Int): Int =
    if ( n == 0 ) 1 else n * factorial(n-1)       //> factorial: (n: Int)Int

	def sumFactorials(a: Int, b: Int): Int =
		if (a > b) 0 else factorial(a) + sumFactorials(a + 1, b)
                                                  //> sumFactorials: (a: Int, b: Int)Int

  /*
   * Summing with Higher-Order Functions:
   *   The type A => B is the type of a function that takes an argument of type A and returns a result of type B.
   *   So, Int => Int is the type of functions that map integers to integers.
   */

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)                  //> sum: (f: Int => Int, a: Int, b: Int)Int
    
  def sumIntsV2(a: Int, b: Int) = sum(id, a, b)   //> sumIntsV2: (a: Int, b: Int)Int
  def sumCubesV2(a: Int, b: Int) = sum(cube, a, b)//> sumCubesV2: (a: Int, b: Int)Int
  def sumFactorialsV2(a: Int, b: Int) = sum(fact, a, b)
                                                  //> sumFactorialsV2: (a: Int, b: Int)Int
  
  def id(x: Int): Int = x                         //> id: (x: Int)Int
  def fact(x: Int): Int = if (x == 0) 1 else fact(x - 1)
                                                  //> fact: (x: Int)Int
  
  /*
   * Anonymous Functions:
   *   Passing functions as parameters leads to the creation of many small functions.
   *   Sometimes it is tedious to have to define (and name) these functions using def.
   * Compare to strings: We do not need to define a string using def. Instead of
   *   def str = ”abc”; println(str)
   * We can directly writeßß
   *   println(”abc”)
   * because strings exist as literals. Analogously we would like function literals, which let us write a function without giving it a name.
   * These are called anonymous functions.
   */
  
  /*
   * Anonymous Function Syntax:
   *   Example: A function that raises its argument to a cube:
   */
  
  (x: Int) => x * x * x                           //> res0: Int => Int = <function1>
  
  /*
   * Here, (x: Int) is the parameter of the function, and x * x * x is it’s body.
   * The type of the parameter can be omitted if it can be inferred by the compiler from the context.
   * If there are several parameters, they are separated by commas:
   */
   
  (x: Int, y: Int) => x + y                       //> res1: (Int, Int) => Int = <function2>
  
  /*
   * Anonymous Functions are Syntactic Sugar:
   *  An anonymous function (x1 : T1, ..., xn : Tn) ⇒ E can always be expressed using def as follows:
   *     def f(x1 : T1,...,xn : Tn) = E;f
   * where f is an arbitrary, fresh name (that’s not yet used in the program).
   * One can therefore say that anonymous functions are syntactic sugar.
   */
   
  // Summation with Anonymous Functions
  
  def sumIntsV3(a: Int, b: Int) = sum(x => x, a, b)
                                                  //> sumIntsV3: (a: Int, b: Int)Int
  def sumCubesV3(a: Int, b: Int) = sum(x => x * x * x, a, b)
                                                  //> sumCubesV3: (a: Int, b: Int)Int
}