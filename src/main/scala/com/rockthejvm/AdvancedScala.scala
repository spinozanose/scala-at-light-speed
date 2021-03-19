package com.rockthejvm

import scala.concurrent.Future
import scala.util.Try
import scala.util.Failure
import scala.util.Success
// this is used for asynchronous programming, below.
import scala.concurrent.ExecutionContext.Implicits.global

object AdvancedScala extends App {

  /**
    * Lazy Evaluation
    */
  // expression is not evaluated until its first use at runtime
  lazy val aLazyValue = 2
  lazy val aLazyUnreferencedValueWithSideEffect = {
    // this will not output, because it never gets evaluated
    println("I am lazy, and I have a side effect, but I'm not referenced!")
    42
  }
  lazy val aLazyReferencedValueWithSideEffect = {
    println("I am lazy, and I have a side effect, and I am referenced!")
    42
  }

  // Even though the above is lazily initialized, referencing it in a non-lazy context will force evaluation
  val eagerValue = aLazyReferencedValueWithSideEffect + 1

  // lazy initialization is very useful for infinite collections

  /**
    * "Pseudo-collections": Option and Try
    */
  def methodThatCanReturnNull(): String = "Hello, Scala!"

  // We can use an Option instead of a defensive null-check on the return
  val anOption = Option(methodThatCanReturnNull())
  // This above evaluates and now is a single-element "collection", kinda, that contains
  // Some(value), which now has "Hello, Scala!", or
  // None,

  val theString = anOption match {
    case Some(string) => s"I obtained the valid String '$string'"
    case None => "I got nothing!"
  }
  println(theString)

  def methodThatThrowsAnException(): String = throw new RuntimeException("I meant to do this!")
  // Instead of using the usual try/catch blocks, we can do this:
  val aTry = Try(methodThatThrowsAnException())
  // The Try object is like a single-element exception that holds one object, either
  //  an Exception
  //  an Object
  // Right now aTry will hold an Exception.
  val theStringAfterHandlingTheException = aTry match {
    case Success(validValue) => s"I obtained a valid value: '$validValue'"
    case Failure(ex) => s"I got nothing but an exception! It looks like this:\n $ex"
  }

  println(theStringAfterHandlingTheException)

  /**
    * Asynchronous Programming
    * How to evaluate something on another thread?
    *  We use Futures.
    */

  // You will notice that the compiler requires the "Execution Context" import above,
  // otherwise the context would have to be specified. The Execution Context defines
  // the Thread Pool
  val aFuture = Future({
    // This expression will be evaluated in another thread
    println("Loading . . .")
    Thread.sleep(1000)
    println("I've finished!")
    67
  })
  Thread.sleep(2000) // this will allow the thread above time to print out "I've finished!"

  // Note that we can eliminate the parentheses:
  val anotherFuture = Future {
    42
  }
  // You can think of the Future as a "collection" that contains a value when the expression is evaluated.
  // A Future is composable with map, flatmap, and filter

  // These pseudo-collections are known as MONADS

  /**
   * Implicits basics
   */

  // There are two common use cases for implicits
  // 1. implicit arguments
  def adderWithImplicitArg(implicit arg: Int) = arg + 1
  implicit val anImplicitValue = 46
  println(adderWithImplicitArg) // Note: no arguments!
  // 2. Implicit conversions
  implicit class MyRicherInteger(n: Int) {
    def isEven() = n % 2 == 0
  }
  println("23 is even? " + 23.isEven()) // Pretty magical. And dangerous.

}