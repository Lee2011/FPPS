package scalaByExample

object chapter5 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /* ********************************
   * First-Class Functions
   * ********************************/
   
  /*
   * A function in Scala is a “first-class value”. Like any other value, it may be passed as a parameter or returned as a result.
   * Functions which take other functions as pa- rameters or return them as results are called higher-order functions. This chapter
   * introduces higher-order functions and shows how they provide a flexible mecha- nism for program composition.
   *
   * As a motivating example, consider the following three related tasks:
   */
   
  // 1. Write a function to sum all integers between two given numbers a and b:
  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInts(a + 1, b)       //> sumInts: (a: Int, b: Int)Int
    
  // 2. Write a function to sum the squares of all integers between two given numbers a and b:
  def square(x: Int): Int = x * x                 //> square: (x: Int)Int
  def sumSquares(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquares(a + 1, b)
                                                  //> sumSquares: (a: Int, b: Int)Int
    
  // 3. Write a function to sum the powers 2n of all integers n between two given numbers a and b:
  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)
                                                  //> powerOfTwo: (x: Int)Int
  def sumPowersOfTwo(a: Int, b: Int): Int =
    if (a > b) 0 else powerOfTwo(a) + sumPowersOfTwo(a + 1, b)
                                                  //> sumPowersOfTwo: (a: Int, b: Int)Int
   
  /*
   * We can factor out the common pattern by defining a function sum:
   */
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)     //> sum: (f: Int => Int, a: Int, b: Int)Int
 
  /*
   * The type Int => Int is the type of functions that take arguments of type Int and return results of type Int. So sum is a function which
   * takes another function as a parameter. In other words, sum is a higher-order function. Using sum, we can formulate the three summing
   * functions as follows.
   */
   
  def sumIntsV2(a: Int, b: Int): Int = sum(id, a, b)
                                                  //> sumIntsV2: (a: Int, b: Int)Int
  def sumSquaresV2(a: Int, b: Int): Int = sum(square, a, b)
                                                  //> sumSquaresV2: (a: Int, b: Int)Int
  def sumPowersOfTwoV2(a: Int, b: Int): Int = sum(powerOfTwo, a, b)
                                                  //> sumPowersOfTwoV2: (a: Int, b: Int)Int
  
  // where
  
  def id(x: Int): Int = x                         //> id: (x: Int)Int
  
  /* ********************************
   * Anonymous Functions
   * ********************************/
   
  /*
   * Parameterization by functions tends to create many small functions. In the previous example, we defined id,
   * square and power as separate functions, so that they could be passed as arguments to sum.
   *
   * Instead of using named function definitions for these small argument functions, we can formulate them in a shorter
   * way as anonymous functions. An anonymous function is an expression that evaluates to a function; the function is
   * defined without giving it a name. As an example consider the anonymous square function:
   */
   
  (x: Int) => x * x                               //> res0: Int => Int = <function1>
   
  /*
   * The part before the arrow ‘=>’ are the parameters of the function, whereas the part following the ‘=>’ is its body.
   * For instance, here is an anonymous function which multiples its two arguments.
   */
   
  (x: Int, y: Int) => x * y                       //> res1: (Int, Int) => Int = <function2>
   
  /*
   * Using anonymous functions, we can reformulate the first two summation functions without named auxiliary functions:
   */
   
  def sumIntsV3(a: Int, b: Int): Int = sum((x: Int) => x, a, b)
                                                  //> sumIntsV3: (a: Int, b: Int)Int
  def sumSquaresV3(a: Int, b: Int): Int = sum((x: Int) => x * x, a, b)
                                                  //> sumSquaresV3: (a: Int, b: Int)Int
   
  /*
   * Often, the Scala compiler can deduce the parameter type(s) from the context of the anonymous function in which case they
   * can be omitted. For instance, in the case of sumInts or sumSquares, one knows from the type of sum that the first parameter
   * must be a function of type Int => Int. Hence, the parameter type Int is redundant and may be omitted. If there is a single
   * parameter without a type, we may also omit the parentheses around it:
   */
  
  def sumIntsV4(a: Int, b: Int): Int = sum(x => x, a, b)
                                                  //> sumIntsV4: (a: Int, b: Int)Int
  def sumSquaresV4(a: Int, b: Int): Int = sum(x => x * x, a, b)
                                                  //> sumSquaresV4: (a: Int, b: Int)Int
  
  /*
   * Generally, the Scala term (x1: T1, ..., xn: Tn) => E defines a function which maps its parameters x1, ..., xn to the result of
   * the expression E (where E may refer to x1, ..., xn). Anonymous functions are not essential language elements of Scala, as they
   * can always be expressed in terms of named functions. Indeed, the anonymous function
   *      (x1: T1, ..., xn: Tn) => E
   * is equivalent to the block
   *      { def f (x1: T1, ..., xn: Tn) = E ; f _ }
   * where f is fresh name which is used nowhere else in the program. We also say, anonymous functions are “syntactic sugar”.
   */
   
  /* ********************************
   * Currying
   * ********************************/
   
  /*
   * The latest formulation of the summing functions is already quite compact. But we can do even better. Note that a and b appear as parameters
   * and arguments of every function but they do not seem to take part in interesting combinations. Is there a way to get rid of them?
   *
   * Let’s try to rewrite sum so that it does not take the bounds a and b as parameters:
   */
   
  def sumV5(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    sumF
  }                                               //> sumV5: (f: Int => Int)(Int, Int) => Int
   
  /*
   * In this formulation, sum is a function which returns another function, namely the specialized summing function sumF. This latter function does
   * all the work; it takes the bounds a and b as parameters, applies sum’s function parameter f to all integers between them, and sums up the results.
   *
   * Using this new formulation of sum, we can now define:
   */
   
  def sumIntsV5 = sumV5(x => x)                   //> sumIntsV5: => (Int, Int) => Int
  def sumSquaresV5 = sumV5(x => x * x)            //> sumSquaresV5: => (Int, Int) => Int
  def sumPowersOfTwoV5 = sumV5(powerOfTwo)        //> sumPowersOfTwoV5: => (Int, Int) => Int
   
  // Or, equivalently, with value definitions:
  val sumIntsV6 = sumV5(x => x)                   //> sumIntsV6  : (Int, Int) => Int = <function2>
  val sumSquaresV6 = sumV5(x => x * x)            //> sumSquaresV6  : (Int, Int) => Int = <function2>
  val sumPowersOfTwoV6 = sumV5(powerOfTwo)        //> sumPowersOfTwoV6  : (Int, Int) => Int = <function2>
   
  // sumInts, sumSquares, and sumPowersOfTwo can be applied like any other function. For instance,
  sumSquares(1, 10) + sumPowersOfTwo(10, 20)      //> res2: Int = 2096513
  sumSquaresV5(1, 10) + sumPowersOfTwoV5(10, 20)  //> res3: Int = 2096513
  sumSquaresV6(1, 10) + sumPowersOfTwoV6(10, 20)  //> res4: Int = 2096513
  
  // How are function-returning functions applied? As an example, in the expression
  sumV5(x => x * x)(1, 10)                        //> res5: Int = 385
  // the function sum is applied to the squaring function (x => x * x). The resulting function is then applied to the second argument list,(1, 10).

  /*
   * This notation is possible because function application associates to the left. That is, if args1 and args2 are argument lists, then
   *
   *  f (args1)(args2) is equivalent to (f (args1))(args2)
   *
   * In our example, sum(x => x * x)(1, 10) is equivalent to the following expression:
   *
   *  (sum(x => x * x))(1, 10)
   */
   
  /*
   * The style of function-returning functions is so useful that Scala has special syntax for it. For instance, the next definition of
   * sum is equivalent to the previous one, but is shorter:
   */
   
  def sumV6(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sumV6(f)(a + 1, b)   //> sumV6: (f: Int => Int)(a: Int, b: Int)Int
   
  /*
   * Generally, a curried function definition
   *    def f (args1) ... (argsn) = E
   * where n > 1 expands to
   *    def f (args1) ... (argsn−1) = { def g (argsn) = E ; g }
   * where g is a fresh identifier. Or, shorter, using an anonymous function:
   *    def f (args1) ... (argsn−1) = ( argsn ) => E .
   * Performing this step n times yields that
   *    def f (args1) ... (argsn) = E
   * is equivalent to
   *    def f = (args1) => ... => (argsn) => E .
   * Or, equivalently, using a value definition:
   *    val f = (args1) => ... => (argsn) => E .
   *
   * This style of function definition and application is called currying after its promoter, Haskell B. Curry, a logician of the 20th century,
   * even though the idea goes back further to Moses Schönfinkel and Gottlob Frege.
   *
   * The type of a function-returning function is expressed analogously to its parameter list. Taking the last formulation of sum as an example,
   * the type of sum is (Int => Int) => (Int, Int) => Int. This is possible because function types associate to the right. I.e.
   *
   *    T1 => T2 => T3 is equivalent to T1 => (T2 => T3)
   */
   
  // The sum function uses a linear recursion. Can you write a tail-recursive one?
  def sumV7(f: Int => Int)(a: Int, b: Int): Int = {
    def iter(a: Int, result: Int): Int = {
			if (a > b) result
			else iter(a + 1, f(a) + result)
		}
		iter(a, 0)
	}                                         //> sumV7: (f: Int => Int)(a: Int, b: Int)Int
	
	sumV7(x => x * x)(1, 10)                  //> res6: Int = 385

  /* ********************************
   * Summary
   * ********************************/
   
  /*
   * We have seen in the previous chapter that functions are essential abstractions, because they permit us to introduce general methods of
   * computing as explicit, named elements in our programming language. The present chapter has shown that these abstractions can be combined by
   * higher-order functions to create further abstractions. As programmers, we should look out for opportunities to abstract and to reuse.
   * The highest possible level of abstraction is not always the best, but it is important to know abstraction techniques, so that one can
   * use abstractions where appropriate.
   */

}