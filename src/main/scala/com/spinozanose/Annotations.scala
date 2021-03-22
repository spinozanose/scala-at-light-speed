package com.spinozanose

import scala.annotation.tailrec

// https://docs.scala-lang.org/tour/annotations.html
// https://www.youtube.com/watch?v=GGEtaxly3IE
object Annotations extends App{

  /** Annotations can be used for meta-information */
  // function deprecation
  // can have the message defined, or not
  @deprecated("deprecation message", "Don't use this any more!")
  def oldFunction() = {
    "hello"
  }
  println(oldFunction())

  @deprecated
  def oldFunction2() = {
    "hola"
  }
  println(oldFunction2())

  /** Annotations can be used to ensure correctness and force compilation to fail */

  // @tailRec ensures a method is "tail recursive" (https://en.wikipedia.org/wiki/Tail_call)
  // Tail recursion is where the last call of the function is the recursive call.
  // Tail recursion is useful for "tail call optimization", which allows recursion to have the
  // same stack overhead as loops. TCO is usually guaranteed by functional languages.
  // Consider a factorial method:
  def factorial(x: Int): Int = {

    @tailrec
    def factorialInner(x: Int, accumulator: Int): Int = {
      if (x == 1) accumulator else factorialInner(x - 1, accumulator * x) // recursive call is the last call
    }
    factorialInner(x, 1)
  }

  println("The factorial of 1 is " + factorial(1))
  println("The factorial of 2 is " + factorial(2))
  println("The factorial of 3 is " + factorial(3))

  // but this would fail if the @tailrec were uncommented:
  def factorial2(x: Int): Int = {
    //@tailrec
    def factorialInner(x: Int): Int = {
      // here the last call is the multiplication after the recursive call returns
      if (x == 1) 1 else x * factorialInner(x - 1)
    }
    factorialInner(x)
  }

  println("The factorial of 1 is " + factorial2(1))
  println("The factorial of 2 is " + factorial2(2))
  println("The factorial of 3 is " + factorial2(3))

  /** Annotations can be used to affect code generation */
  // For example, @inline recommends inlining the call for larger but faster execution

}
