package week5

object reverse {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def reverse[T](xs: List[T]): List[T] = {
    (xs foldLeft List[T]())((xs, x) => x :: xs)
  }                                               //> reverse: [T](xs: List[T])List[T]
  
  val fruits = List("pineapple", "orange", "apple", "banana")
                                                  //> fruits  : List[String] = List(pineapple, orange, apple, banana)
  
  reverse(fruits)                                 //> res0: List[String] = List(banana, apple, orange, pineapple)
}