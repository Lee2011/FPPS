package week2

object week2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * Tail Recursion:
   *   Implementation Consideration: If a function calls itself as its last action, the function’s stack frame can be reused.
   *   This is called tail recursion.
   *
   * Tail recursive functions are iterative processes.
   *
   * In general, if the last action of a function consists of calling a function (which may be the same), one stack frame would
   * be sufficient for both functions. Such calls are called tail-calls.
   *
   * In Scala, only directly recursive calls to the current function are optimized.
   * One can require that a function is tail-recursive using a @tailrec annotation:
   *   @tailrec
   *   def gcd(a: Int, b: Int): Int = ...
   *
   * If the annotation is given, and the implementation of gcd were not tail recursive, an error would be issued.
   */
  
  def gcd(a: Int, b: Int): Int =
    if ( b == 0 ) a else gcd(b, a % b )           //> gcd: (a: Int, b: Int)Int
    
  gcd(14, 21)                                     //> res0: Int = 7
  
  def factorial(n: Int): Int =
    if ( n == 0 ) 1 else n * factorial(n-1)       //> factorial: (n: Int)Int
  
  factorial(0)                                    //> res1: Int = 1
  
  // Design a tail recursive version of factorial.
  def factorialtail(n: Int, m: Int = 1): Int =
    if ( n == 0 ) m * 1 else factorialtail(n-1, m * n)
                                                  //> factorialtail: (n: Int, m: Int)Int
  
  factorialtail(0)                                //> res2: Int = 1
}