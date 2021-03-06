package week5

object reduction {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val data = List(1, 2, 3, 4, 5, 6)               //> data  : List[Int] = List(1, 2, 3, 4, 5, 6)

  def sum(xs: List[Int]) =
    (0 :: xs) reduceLeft ((x, y) => x + y)        //> sum: (xs: List[Int])Int

  def product(xs: List[Int]) =
    (1 :: xs) reduceLeft ((x, y) => x * y)        //> product: (xs: List[Int])Int

  sum(data)                                       //> res0: Int = 21
  product(data)                                   //> res1: Int = 720
  
/*
 *      Every _ represents a new parameter, going from left to right.
 */

  def sumV2(xs: List[Int]) =
    (0 :: xs) reduceLeft (_ + _)                  //> sumV2: (xs: List[Int])Int

  def productV2(xs: List[Int]) =
    (1 :: xs) reduceLeft (_ * _)                  //> productV2: (xs: List[Int])Int
    
  sumV2(data)                                     //> res2: Int = 21
  productV2(data)                                 //> res3: Int = 720
    
  
  def sumV3(xs: List[Int]) =
    (xs foldLeft 0)(_ + _)                        //> sumV3: (xs: List[Int])Int
    
  def productV3(xs: List[Int]) =
    (xs foldLeft 1)(_ * _)                        //> productV3: (xs: List[Int])Int

  sumV3(data)                                     //> res4: Int = 21
  productV3(data)                                 //> res5: Int = 720
  
/*
 * For operators that are associative and commutative, foldLeft and foldRight are equivalent(even though they may be a difference in efficiency)
 * But sometimes, only one of the two operators is appropiate
 */
  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys)(_ :: _)                     //> concat: [T](xs: List[T], ys: List[T])List[T]
}