package week2

object week2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def gcd(a: Int, b: Int): Int =
    if ( b == 0 ) a else gcd(b, a % b )           //> gcd: (a: Int, b: Int)Int
    
  gcd(14, 21)                                     //> res0: Int = 7
  
  def factorial(n: Int): Int =
    if ( n == 0 ) 1 else n * factorial(n-1)       //> factorial: (n: Int)Int
  
  factorial(0)                                    //> res1: Int = 1
  
  def factorialtail(n: Int, m: Int = 1): Int =
    if ( n == 0 ) m * 1 else factorialtail(n-1, m * n)
                                                  //> factorialtail: (n: Int, m: Int)Int
  
  factorialtail(0)                                //> res2: Int = 1
}