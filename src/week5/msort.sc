package week5

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
      mergeV2(msort(fst), msort(snd))
    }
  }                                               //> msortV2: (xs: List[Int])List[Int]

  msortV2(nums)                                   //> res1: List[Int] = List(-3, 1, 4, 6)

}