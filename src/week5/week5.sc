package week5

object week5 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /*
   * The List is a fundamental data structure in functional programming
   * A list having x1, ......, xn as elements is written List(x1, ......, xn)
   */

  val fruit = List("apples", "oranges", "pears")  //> fruit  : List[String] = List(apples, oranges, pears)
  val nums = List(1, 2, 3, 4)                     //> nums  : List[Int] = List(1, 2, 3, 4)
  val diag3 = List(List(0, 1, 2), List(3, 2, 2), List(1, 0, 1))
                                                  //> diag3  : List[List[Int]] = List(List(0, 1, 2), List(3, 2, 2), List(1, 0, 1))
                                                  //| 
  val empty = List()                              //> empty  : List[Nothing] = List()

  val fruit2 = "apples" :: ("oranges" :: ("pears" :: Nil))
                                                  //> fruit2  : List[String] = List(apples, oranges, pears)
  val nums2 = 1 :: (2 :: (3 :: (4 :: Nil)))       //> nums2  : List[Int] = List(1, 2, 3, 4)
  val empty2 = Nil                                //> empty2  : scala.collection.immutable.Nil.type = List()

  val nums3 = Nil.::(4).::(3).::(2).::(1)         //> nums3  : List[Int] = List(1, 2, 3, 4)

  fruit.head                                      //> res0: String = apples
  fruit.length                                    //> res1: Int = 3
  fruit.last                                      //> res2: String = pears
  fruit.init                                      //> res3: List[String] = List(apples, oranges)
  fruit take 2                                    //> res4: List[String] = List(apples, oranges)
  fruit drop 1                                    //> res5: List[String] = List(oranges, pears)
  fruit(1)                                        //> res6: String = oranges
  fruit.reverse                                   //> res7: List[String] = List(pears, oranges, apples)
  fruit.updated(1, "bananna")                     //> res8: List[String] = List(apples, bananna, pears)
  fruit ++ fruit.reverse                          //> res9: List[String] = List(apples, oranges, pears, pears, oranges, apples)
  fruit indexOf "apples"                          //> res10: Int = 0
  fruit contains "pears"                          //> res11: Boolean = true
  fruit.tail.head                                 //> res12: String = oranges
  diag3.head                                      //> res13: List[Int] = List(0, 1, 2)
  //empty.head

  /*
  * It is also possiable to decompose lists with pattern matching:
  *
  *   Nil 	-		The Nil constant
  * 	p :: ps 	- 	a pattern that matches a list with a head matching p and a tail matching ps
  *		List(p1, ......, pn) same as p1 :: p2 :: ..... :: pn :: Nil
  */

  /*
   * Suppose we want to sort a list of number in ascending order:
   *		1. One way to sort the list List(7, 3, 9, 2) is to sort the tail List(3, 9, 2) to obtain List(2, 3, 9)
   * 		2. The next step is to insert the head 7 in the right place to obtian the result List(2, 3, 7, 9)
   */
   
  def isort(xs: List[Int]): List[Int] = xs match {
    case List()  => List()
    case y :: ys => insert(y, isort(ys))
  }                                               //> isort: (xs: List[Int])List[Int]
  
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  	case List() => List(x)
  	case y :: ys => if (x<=y) x :: xs else y :: insert(x, ys)
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]
  
  val sortNums = List(7, 3, 9, 2)                 //> sortNums  : List[Int] = List(7, 3, 9, 2)
  isort(sortNums)                                 //> res14: List[Int] = List(2, 3, 7, 9)
}