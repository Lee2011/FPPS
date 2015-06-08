package scalaByExample

object chapter6 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  var i = 1                                       //> i  : Int = 1
  var x = new Rational(0, 1)                      //> x  : scalaByExample.Rational = scalaByExample.Rational@36d64342
  
  while (i <= 10) {
		x += new Rational(1, i)
		i += 1 }
	println("" + x.numer + "/" + x.denom)     //> 7381/2520
}

class Rational(n: Int, d: Int) {
  private def gcd(x: Int, y: Int): Int = {
    if (x == 0) y
    else if (x < 0) gcd(-x, y)
    else if (y < 0) -gcd(x, -y)
    else gcd(y % x, x)
  }

  private val g = gcd(n, d)

  val numer: Int = n / g
  val denom: Int = d / g

  def +(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def -(that: Rational) =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  def *(that: Rational) =
    new Rational(numer * that.numer, denom * that.denom)
  def /(that: Rational) =
    new Rational(numer * that.denom, denom * that.numer)
}