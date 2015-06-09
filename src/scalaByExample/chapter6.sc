package scalaByExample

object chapter6 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  var i = 1                                       //> i  : Int = 1
  var x = new Rational(0, 1)                      //> x  : scalaByExample.Rational = 0/1

  while (i <= 10) {
    x += new Rational(1, i)
    i += 1
  }
  println("" + x.numer + "/" + x.denom)           //> 7381/2520
  println(x.toString())                           //> 7381/2520
  val r = new Rational(3, 4)                      //> r  : scalaByExample.Rational = 3/4
  r.square                                        //> res0: scalaByExample.Rational = 9/16

  //by replacing contains by its body in class EmptySet
  (new EmptySet).contains(7)                      //> res1: Boolean = false
  //by replacing contains by its body in class NonEmptySet
  new NonEmptySet(7, new EmptySet, new EmptySet).contains(1)
                                                  //> res2: Boolean = false
  //by rewriting the conditional
  if (1 < 7) new EmptySet contains 1
  else if (1 > 7) new EmptySet contains 1
  else true                                       //> res3: Boolean = false

  //by replacing contains by its body in class EmptySet
  new EmptySet contains 1                         //> res4: Boolean = false

  val t1 = new NonEmptySet(7, new EmptySet(), new EmptySet())
                                                  //> t1  : scalaByExample.NonEmptySet = {.7.}
  t1.contains(7)                                  //> res5: Boolean = true
  t1.contains(5)                                  //> res6: Boolean = false

  val t2 = t1.incl(5)                             //> t2  : scalaByExample.IntSet = {{.5.}7.}
  t2.contains(5)                                  //> res7: Boolean = true
  val t3 = t2 incl 9                              //> t3  : scalaByExample.IntSet = {{.5.}7{.9.}}
  
  val t4 = t2 incl 19                             //> t4  : scalaByExample.IntSet = {{.5.}7{.19.}}

  val t5 = t4 union t3                            //> t5  : scalaByExample.IntSet = {{.5.}7{.9{.19.}}}
}

/*
 * Scala does not have a built-in type of rational numbers, but it is easy to define one, using a class. Here’s a possible implementation.
 *
 * This defines Rational as a class which takes two constructor arguments n and d, containing the number’s numerator and denominator parts.
 * The class provides fields which return these parts as well as methods for arithmetic over rational numbers. Each arithmetic method takes as parameter
 * the right operand of the operation. The left operand of the operation is always the rational number of which the method is a member.
 *
 * Private members:
 *   The implementation of rational numbers defines a private method gcd which computes the greatest common denominator of two integers, as well as a
 *   private field g which contains the gcd of the constructor arguments. These members are inaccessible outside class Rational. They are used in the
 *   implementation of the class to eliminate common factors in the constructor arguments in order to ensure that numerator and denominator are always
 *   in normalized form.
 *
 * Inheritance and Overriding:
 *   Every class in Scala has a superclass which it ex- tends. If a class does not mention a superclass in its definition, the root type scala.AnyRef
 *   is implicitly assumed (for Java implementations, this type is an alias for java.lang.Object. For instance, class Rational could equivalently be
 *   defined as:
 *       class Rational(n: Int, d: Int) extends AnyRef {
 *         ... // as before
 *       }
 *
 *   A class inherits all members from its superclass. It may also redefine (or: override) some inherited members. For instance, class java.lang.Object
 *   defines a method toString which returns a representation of the object as a string. The implementation of toString in Object forms a string consisting
 *   of the object’s class name and a number. It makes sense to redefine this method for objects that are rational numbers.
 *
 *   If class A extends class B, then objects of type A may be used wherever objects of type B are expected. We say in this case that type A conforms to type B .
 *   For instance, Rational conforms to AnyRef, so it is legal to assign a Rational value to a variable of type AnyRef:
 *       var x: AnyRef = new Rational(1, 2)
 *
 * Parameterless Methods:
 *   Unlike in Java, methods in Scala do not necessarily take a parameter list. An example is the square method below. This method is invoked by simply
 *   mentioning its name.
 *
 *   That is, parameterless methods are accessed just as value fields such as numer are. The difference between values and parameterless methods lies in
 *   their definition. The right-hand side of a value is evaluated when the object is created, and the value does not change afterwards. A right-hand side of
 *   a parameterless method, on the other hand, is evaluated each time the method is called. The uniform access of fields and parameterless methods gives
 *   increased flexibility for the implementer of a class. Often, a field in one version of a class becomes a computed value in the next version. Uniform access
 *   ensures that clients do not have to be rewritten because of that change.
 */

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

  //Note that, unlike in Java, redefining definitions need to be preceded by an override modifier.
  override def toString = "" + numer + "/" + denom
  //Parameterless Methods
  def square = new Rational(numer * numer, denom * denom)
}

/*
 * Abstract Classes:
 *
 *   Consider the task of writing a class for sets of integer numbers with two operations, incl and contains. (s incl x) should return a new set which contains the
 *   element x together with all the elements of set s. (s contains x) should return true if the set s contains the element x, and should return false otherwise.
 *   The interface of such sets is given by below.
 *
 *   IntSet is labeled as an abstract class. This has two consequences:
 *     1. First, abstract classes may have deferred members which are declared but which do not have an implementation.
 *     2. Second, because an abstract class might have unimplemented members, no objects of that class may be created using new.
 *   By contrast, an abstract class may be used as a base class of some other class, which implements the deferred members.
 */

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

/*
 * Traits:
 *
 *   Instead of abstract class one also often uses the keyword trait in Scala. Traits are abstract classes that are meant to be added to some other class.
 *   This might be because a trait adds some methods or fields to an unknown parent class. For instance, a trait Bordered might be used to add a border
 *   to a various graphical components. Another usage scenario is where the trait collects signatures of some functionality provided by different classes,
 *   much in the way a Java interface would work. Since IntSet falls in this category, one can alternatively define it as a trait:
 */

trait IntSetTrait {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

/*
 * Implementing Abstract Classes:
 *
 *   Let’s say, we plan to implement sets as binary trees. There are two possible forms of trees. A tree for the empty set, and a tree consisting of
 *   an integer and two subtrees. Here are their implementations.
 */

class EmptySet extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
  def union(other: IntSet): IntSet = other
  override def toString = "."
}

class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x else true
    
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmptySet(elem, left incl x, right)
    else if (x > elem) new NonEmptySet(elem, left, right incl x) else this

  def union(other: IntSet): IntSet =
  	((left union right) union other) incl elem

  override def toString = "{" + left + elem + right + "}"
}

/*
 * Dynamic Binding:
 *
 *   Object-oriented languages (Scala included) use dynamic dis- patch for method invocations. That is, the code invoked for a method call depends on the
 *   run-time type of the object which contains the method. For example, consider the expression s contains 7 where s is a value of declared type s: IntSet.
 *   Which code for contains is executed depends on the type of value of s at run-time. If it is an EmptySet value, it is the implementation of contains
 *   in class EmptySet that is executed, and analogously for NonEmptySet values. This behavior is a direct consequence of our substitution model of
 *   evaluation.
 *
 *   Dynamic method dispatch is analogous to higher-order function calls. In both cases, the identity of code to be executed is known only at run-time.
 *   This similarity is not just superficial. Indeed, Scala represents every function value as an object.
 */

/*
 * Objects:
 *
 *   In the previous implementation of integer sets, empty sets were ex- pressed with new EmptySet; so a new object was created every time an empty set
 *   value was required. We could have avoided unnecessary object creations by defin- ing a value empty once and then using this value instead of every
 *   occurrence of new EmptySet. For example:
 *      val EmptySetVal = new EmptySet
 *
 *   One problem with this approach is that a value definition such as the one above is not a legal top-level definition in Scala; it has to be part of
 *   another class or object. Also, the definition of class EmptySet now seems a bit of an overkill – why define a class of objects, if we are only
 *   interested in a single object of this class? A more direct approach is to use an object definition. Here is a more streamlined alternative
 *   definition of the empty set:
 */
 
//  object EmptySetO extends IntSet {
//    def contains(x: Int): Boolean = false
//    def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
//  }

/*
 * The syntax of an object definition follows the syntax of a class definition; it has an optional extends clause as well as an optional body.
 * As is the case for classes, the extends clause defines inherited members of the object whereas the body de- fines overriding or new members.
 * However, an object definition defines a single object only it is not possible to create other objects with the same structure using new.
 * Therefore, object definitions also lack constructor parameters, which might be present in class definitions.
 *
 * Object definitions can appear anywhere in a Scala program; including at top-level. Since there is no fixed execution order of top-level
 * entities in Scala, one might ask exactly when the object defined by an object definition is created and initialized. The answer is that the
 * object is created the first time one of its members is accessed. This strategy is called lazy evaluation.
 */
 