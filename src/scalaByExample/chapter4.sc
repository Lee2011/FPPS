package scalaByExample

object chapter4 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /* ********************************
   * Expressions and Simple Functions
   * ********************************/

  /*
   * Definitions start with the reserved word def; they introduce a name which stands for the expression following the = sign.
   * The interpreter will answer with the intro- duced name and its type.
   * Executing a definition such as def x = e will not evaluate the expression e. Instead e is evaluated whenever x is used.
   *
   * Alternatively, Scala offers a value defini- tionval x = e,which does evaluate the right-hand-side e as part of the evaluation of the definition.
   * If x is then used subsequently, it is immediately replaced by the pre-computed value of e, so that the expression need not be evaluated again.
   */
   
  /*
   * How are expressions evaluated?
   *
   * An expression consisting of operators and operands is evaluated by repeatedly applying the following simplification steps.
   *   1. pick the left-most operation
   *   2. evaluate its operands
   *   3. apply the operator to the operand values
   *
   * A name defined by def is evaluated by replacing the name by the (unevaluated) definition’s right hand side.
   * A name defined by val is evaluated by replacing the name by the value of the definitions’s right-hand side.
   * The evaluation process stops once we have reached a value. A value is some data item such as a string, a number, an array, or a list.
   *
   * The process of stepwise simplification of expressions to values is called reduction.
   */
   
  // Here is an evaluation of an arithmetic expression
  
  def pi = 3.141592653589793                      //> pi: => Double
  def radius = 10                                 //> radius: => Int
  
  (2 * pi) * radius                               //> res0: Double = 62.83185307179586
  
  /*
   *      (2 * pi) * radius
   *    → (2 * 3.141592653589793) * radius
   *    → 6.283185307179586 * radius
   *    → 6.283185307179586 * 10
   *    → 62.83185307179586
   */
   
  /* ********************************
   * Parameters
   * ********************************/
   
  // Using def, one can also define functions with parameters. For example:
  
  def square(x: Double) = x * x                   //> square: (x: Double)Double
  
  square(2)                                       //> res1: Double = 4.0
  
  square(5 + 3)                                   //> res2: Double = 64.0
  
  square(square(4))                               //> res3: Double = 256.0
  
  def sumOfSquares(x: Double, y: Double) = square(x) + square(y)
                                                  //> sumOfSquares: (x: Double, y: Double)Double
  sumOfSquares(3, 2 + 2)                          //> res4: Double = 25.0
  
  /*
   * Function parameters follow the function name and are always enclosed in parentheses. Every parameter comes with a type,
   * which is indicated following the parameter name and a colon. At the present time, we only need basic numeric types such as
   * the type scala.Double of double precision numbers. Scala defines type aliases for some standard types, so we can write numeric
   * types as in Java. For instance double is a type alias of scala.Double and int is a type alias for scala.Int.
   */
   
  /*
   * How are functions evaluated?
   *
   * Functions with parameters are evaluated analogously to operators in expressions. First, the arguments of the function are
   * evaluated (in left-to-right order). Then, the function application is replaced by the function’s right hand side, and at the same
   * time all formal parameters of the function are replaced by their corresponding actual arguments.
   */
  
  // for example:
  sumOfSquares(3, 2+2)                            //> res5: Double = 25.0
  
  /*
   *   sumOfSquares(3, 2+2)
   * → sumOfSquares(3, 4)
   * → square(3) + square(4)
   * → 3 * 3 + square(4)
   * → 9 + square(4)
   * → 9 + 4 * 4
   * → 9 + 16
   * → 25
   */
   
  /*
   * The above example shows that the interpreter reduces function arguments to values be- fore rewriting the function application.
   * One could instead have chosen to apply the function to unreduced arguments. This would have yielded the following reduction sequence:
   *
   *   sumOfSquares(3, 2+2)
   * → square(3) + square(2+2)
   * → 3 * 3 + square(2+2)
   * → 9 + square(2+2)
   * → 9 + (2+2) * (2+2)
   * → 9 + 4 * (2+2)
   * → 9 + 4 * 4
   * → 9 + 16
   * → 25
   */
   
  /*
   * The second evaluation order is known as call-by-name, whereas the first one is known as call-by-value. For expressions that use only
   * pure functions and that therefore can be reduced with the substitution model, both schemes yield the same final values.
   *
   * Call-by-value has the advantage that it avoids repeated evaluation of arguments. Call-by-name has the advantage that it avoids evaluation of
   * arguments when the parameter is not used at all by the function. Call-by-value is usually more efficient than call-by-name, but a call-by-value
   * evaluation might loop where a call-by-name evaluation would terminate.
   *
   * Scala uses call-by-value by default, but it switches to call-by-name evaluation if the parameter type is preceded by =>.
   */
   
  /* ********************************
   * Conditional Expressions
   * ********************************/
   
  /*
   * Scala’s if-else lets one choose between two alternatives. Its syntax is like Java’s if-else. But where Java’s if-else can be used only as an
   * alternative of state- ments, Scala allows the same syntax to choose between two expressions. That’s why Scala’s if-else serves also as a
   * substitute for Java’s conditional expression ... ? ... : ....
   */
   
  // example:
  def abs(x: Double) = if (x >= 0) x else -x      //> abs: (x: Double)Double
  
  /*
   * Scala’s boolean expressions are similar to Java’s; they are formed from the constants true and false, comparison operators,
   * boolean negation ! and the boolean operators && and ||.
   */
  
  // 4.4 - 4.6 are omitted for now
}