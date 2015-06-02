package week1

object week1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def loop: Int = loop                            //> loop: => Int
  // 加 => 为 call by name，否则默认 call by value
  def constOne(x: Int, y: => Int) = 1             //> constOne: (x: Int, y: => Int)Int
  
  constOne(1+2, loop)                             //> res0: Int = 1
  // loop 是无限循环
  //constOne(loop, 1+2)
}