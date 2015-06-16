package week5

object listfun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val fruits = List("pineapple", "orange", "apple", "banana")
                                                  //> fruits  : List[String] = List(pineapple, orange, apple, banana)
  val nums = List(1, -3, 4, 6)                    //> nums  : List[Int] = List(1, -3, 4, 6)

  nums filter (x => x > 0)                        //> res0: List[Int] = List(1, 4, 6)
  nums filterNot (x => x > 0)                     //> res1: List[Int] = List(-3)
  nums partition (x => x > 0)                     //> res2: (List[Int], List[Int]) = (List(1, 4, 6),List(-3))

  nums takeWhile (x => x > 0)                     //> res3: List[Int] = List(1)
  nums dropWhile (x => x > 0)                     //> res4: List[Int] = List(-3, 4, 6)
  nums span (x => x > 0)                          //> res5: (List[Int], List[Int]) = (List(1),List(-3, 4, 6))

  val data = List("a", "a", "a", "a", "b", "b", "c")
                                                  //> data  : List[String] = List(a, a, a, a, b, b, c)

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil      => Nil
    case x :: xs1 => x * x :: squareList(xs1)
  }                                               //> squareList: (xs: List[Int])List[Int]

  squareList(nums)                                //> res6: List[Int] = List(1, 9, 16, 36)

  def squareListMap(xs: List[Int]): List[Int] =
    xs map (x => x * x)                           //> squareListMap: (xs: List[Int])List[Int]

  squareListMap(nums)                             //> res7: List[Int] = List(1, 9, 16, 36)

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil      => Nil
    case x :: xs1 => if (x > 0) x :: posElems(xs1) else posElems(xs1)
  }                                               //> posElems: (xs: List[Int])List[Int]

  posElems(nums)                                  //> res8: List[Int] = List(1, 4, 6)

  def posElemsFilter(xs: List[Int]): List[Int] =
    xs filter (x => x > 0)                        //> posElemsFilter: (xs: List[Int])List[Int]
    
  posElemsFilter(nums)                            //> res9: List[Int] = List(1, 4, 6)
  
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => {
    	val (first, rest) = xs span (y => y == x )
    	  first :: pack(rest)
    }
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  pack(data)                                      //> res10: List[List[String]] = List(List(a, a, a, a), List(b, b), List(c))
  
  def encode[T](xs: List[T]): List[(T, Int)] = {
  	pack(xs) map (ys => (ys.head, ys.length))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]
  
  encode(data)                                    //> res11: List[(String, Int)] = List((a,4), (b,2), (c,1))

}