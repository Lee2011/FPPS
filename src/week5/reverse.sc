package week5

object reverse {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def reverse[T](xs: List[T]): List[T] = {
    (xs foldLeft List[T]())((xs, x) => x :: xs)
  }                                               //> reverse: [T](xs: List[T])List[T]

  val fruits = List("pineapple", "orange", "apple", "banana")
                                                  //> fruits  : List[String] = List(pineapple, orange, apple, banana)

  reverse(fruits)                                 //> res0: List[String] = List(banana, apple, orange, pineapple)

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())((x, xs) => f(x) :: xs)
                                                  //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

  val nums = List(1, 2, 3, 4, 5, 6)               //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6)
  mapFun[Int, Int](nums, y => y * y )             //> res1: List[Int] = List(1, 4, 9, 16, 25, 36)
  
  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0 )((x, y) => 1 + y)            //> lengthFun: [T](xs: List[T])Int
    
  lengthFun(nums)                                 //> res2: Int = 6
  lengthFun(fruits)                               //> res3: Int = 4
}