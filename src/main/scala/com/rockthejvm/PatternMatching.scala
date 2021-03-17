package com.rockthejvm

object PatternMatching extends App {

  //switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }

  println(order) // 55th

  // so pattern match (PM) is an expression, and it reduces to a value.

  case class Person(name: String, age: Int)

  val bob = new Person("Bob", 33)

  // pattern matching like this only available for case classes
  // (or if you implement lots of stuff yourself)
  val personGreeting = bob match {
    case Person(n, a) => s"Hi! My name is $n and I am $a years old" // This is a little like Javascript destructuring
    case _ => "I'm not sure how to say hello . . . :("
  }

  // also works for tuples
  val aTuple = ("Pink Floyd", "Dark Side of the Moon", 1973)
  val bandDescription = aTuple match {
    case (name, album, year) => s"$name's album $album was released in $year"
    case _ => "I have no description"
  }
  println(bandDescription)

  // decomposing lists
  val aList = List(1,2,3)
  val aListDescription = aList match {
    // case order matters -- the pattern check is in order from top to bottom
    case List(_, 2, _) => "List contains 2 in its second index"
    // It is not required to have a default, but if you do not have one the unmatched cases throw a match error
    case _ => "Unknown list"
  }

  println(aListDescription)
}
