package scalaByExample

object chapter2 {
  println("http://www.scala-lang.org/docu/files/ScalaByExample.pdf")
                                                  //> http://www.scala-lang.org/docu/files/ScalaByExample.pdf
  
  /*
   * 快速排序算法的一种实现
   */
  def sort(xs: Array[Int]) {
  	def swap(i: Int, j: Int) {
			val t = xs(i); xs(i) = xs(j); xs(j) = t
		}
		def sort1(l: Int, r: Int) {
			val pivot = xs((l + r) / 2)
			var i = l; var j = r
			while (i <= j) {
				while (xs(i) < pivot) i += 1
				while (xs(j) > pivot) j -= 1
				if (i <= j) {
					swap(i, j)
					i += 1
					j -= 1
				}
			}
			if (l < j) sort1(l, j)
			if (j < r) sort1(i, r)
		}
		sort1(0, xs.length - 1)
	}                                         //> sort: (xs: Array[Int])Unit
	
	/*
   * 快速排序算法的另一种实现 （用了更多的 scala 特性）
   */
	def sortScala(xs: Array[Int]): Array[Int] = {
		if (xs.length <= 1) xs
		else {
		  val pivot = xs(xs.length / 2)
		  Array.concat(
		    sortScala(xs filter (pivot >)),
		              xs filter (pivot ==),
		    sortScala(xs filter (pivot <)))
		}
	}                                         //> sortScala: (xs: Array[Int])Array[Int]
  
  val intArray = Array(11, 3, 99, 4, 0, 2, 5)     //> intArray  : Array[Int] = Array(11, 3, 99, 4, 0, 2, 5)
  sortScala(intArray)                             //> res0: Array[Int] = Array(0, 2, 3, 4, 5, 11, 99)
  var a = intArray                                //> a  : Array[Int] = Array(11, 3, 99, 4, 0, 2, 5)
}