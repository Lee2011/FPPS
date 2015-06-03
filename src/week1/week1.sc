package week1

/*
 * You will learn simple elements of functional programing in Scala.
 *   1. arithmetic and boolean expressions
 *   2. conditional expressions if-else
 *   3. functions with recursion
 *   4. nesting and lexical scope
 *
 *   5. You will learn the difference between the call-by-name and call-by-value evaluation strategies.
 *   6. You will learn a way to reason about program execution: reduce expressions using the substitution model.
 */

object week1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
   * In particular, functions in a FP language are first-class citizens.This means
   *   1. they can be defined anywhere, including inside other functions
   *   2. like any other value, they can be passed as parameters to functions and returned as results
   *   3. as for other values, there exists a set operators to compose functions
   */
  
  /*
   * Evaluation of Function Applications
   *   1. Evaluate all function arguments, from left to right
   *   2. Replace the function application by the function’s right-hand side, and, at the same time
   *   3. Replace the formal parameters of the function by the actual arguments
   *
   * This scheme of expression evaluation is called the substitution model. The idea underlying this model
   * is that all evaluation does isreducean expression to a value. It can be applied to all expressions,
   * as long as they have no sideeffects.
   *
   * The substitution model is formalized in the λ-calculus, which gives afoundation for functional programming.
   */
  
  // 不是所有的表达式都能被计算出结果，例如：
  def loop: Int = loop                            //> loop: => Int
  
  /*
   * 有两种求值的策略：
   *   1. Call-by-name
   *   2. call-by-value
   *
   * Both strategies reduce to the same final values as long as
   *   1. the reduced expression consists of pure functions, and
   *   2. both evaluations terminate.
   *
   * 两种方法优缺点比较：
   *   1. Call-by-value has the advantage that it evaluates every functionargument only once.
   *   2. Call-by-name has the advantage that a function argument is notevaluated if the corresponding parameter is
   *      unused in the evaluationof the function body.
   *
   * If CBV evaluation of an expression e terminates, then CBN evaluation of e terminates, too.
   * The other direction is not true.
   */
  
  // Scala 默认使用 call by value 方法。 但是在参数前加 '=>' 则对于该参数使用 call by name 方法
  def constOne(x: Int, y: => Int) = 1             //> constOne: (x: Int, y: => Int)Int
  
  constOne(1+2, loop)                             //> res0: Int = 1
  // loop 是无限循环
  //constOne(loop, 1+2)
  
  /*
   * Conditional Expressions:
   *   To express choosing between two alternatives, Scala has a conditional expression if-else.
   *   It looks like a if-else in Java, but is used for expressions, not statements.
   */
  def abs(x: Int) = if (x >= 0) x else -x         //> abs: (x: Int)Int
  
  /*
   * Value Definitions:
   *   We have seen that function parameters can be passed by value or be passed by name.
   *   The same distinction applies to definitions.
   *     1. The def form is “by-name”, its right hand side is evaluated on each use.
   *     2. There is also a val for, which is “by-value”. The right-hand side of a val definition is evaluated at
   *        the point of the definition itself. Afterwards, the name refers to the value.
   */
   
  // 下面的 def 是可行的
  def x = loop                                    //> x: => Int
    
  // 下面的 val 是可以的，会导致无限循环
  //val y = loop
  
  /*
   * Semicolons:
   *
   *   In Scala, semicolons at the end of lines are in most cases optional.
   *   On the other hand, if there are more than one statements on a line, they need to be separated by semicolons:
   */
   
  //val p = t + 1; p * p
  
  /*
   * Semicolons and infix operators:
   *
   *   One issue with Scala’s semicolon convention is how to write expressions that span several lines. For instance
   *       someLongExpression
   *       + someLongExpression
   *
   *   would be interpreted as two expressions:
   *       someLongExpression;
   *       + someLongExpression
   *
   *   There are two ways to overcome this problem.
   *     1. You could write the multi-line expression in parentheses, because semicolons are never inserted inside (...):
   *           (someLongExpression
   *            + someLongExpression)
   *     2. Or you could write the operator on the first line, because this tells the Scala compiler that the expression is not yet finished:
   *           someLongExpression +
   *           someLongExpression
   */
  
}