package week5

import math.Ordering

object msort {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = {
        xs match {
          case Nil => ys
          case x :: xs1 =>
            ys match {
              case Nil => xs
              case y :: ys1 =>
                if (x < y) x :: merge(xs1, ys)
                else y :: merge(xs, ys1)
            }
        }
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

  val nums = List(1, -3, 4, 6)                    //> nums  : List[Int] = List(1, -3, 4, 6)

  msort(nums)                                     //> res0: List[Int] = List(-3, 1, 4, 6)

  def msortV2(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def mergeV2(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: mergeV2(xs1, ys)
          else y :: mergeV2(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      mergeV2(msortV2(fst), msortV2(snd))
    }
  }                                               //> msortV2: (xs: List[Int])List[Int]

  msortV2(nums)                                   //> res1: List[Int] = List(-3, 1, 4, 6)

  def msortV3[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def mergeV3(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: mergeV3(xs1, ys)
          else y :: mergeV3(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      mergeV3(msortV3(fst)(lt), msortV3(snd)(lt))
    }
  }                                               //> msortV3: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]

  msortV3(nums)((x: Int, y: Int) => x < y)        //> res2: List[Int] = List(-3, 1, 4, 6)

  val fruits = List("pineapple", "orange", "apple", "banana")
                                                  //> fruits  : List[String] = List(pineapple, orange, apple, banana)
  msortV3(fruits)((x: String, y: String) => x.compareTo(y) < 0)
                                                  //> res3: List[String] = List(apple, banana, orange, pineapple)

  def msortV4[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def mergeV4(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: mergeV4(xs1, ys)
          else y :: mergeV4(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      mergeV4(msortV4(fst)(ord), msortV4(snd)(ord))
    }
  }                                               //> msortV4: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]

  msortV4(nums)(Ordering.Int)                     //> res4: List[Int] = List(-3, 1, 4, 6)
  msortV4(fruits)(Ordering.String)                //> res5: List[String] = List(apple, banana, orange, pineapple)

  def msortV5[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def mergeV5(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: mergeV5(xs1, ys)
          else y :: mergeV5(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      mergeV5(msortV5(fst)(ord), msortV5(snd)(ord))
    }
  }                                               //> msortV5: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]

  msortV5(nums)                                   //> res6: List[Int] = List(-3, 1, 4, 6)
  msortV5(fruits)                                 //> res7: List[String] = List(apple, banana, orange, pineapple)

}