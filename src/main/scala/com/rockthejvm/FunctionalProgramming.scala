package com.rockthejvm

object FunctionalProgramming extends App {

  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43) // The apply method is special because . . .
  bob(43) // this is the same as the above, but "invoking" the object as a function

  // So Scala is OO, and it runs on the JVM.
  // Which means there is no concept of a function as a first-class object.
  // But we want to be functional, which means we want to:
  /*
    1. Compose functions
    2. Pass functions as arguments
    3. Return functions as results
   */
  // So the Scala folks invented FunctionX

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  println(simpleIncrementer.apply(23)) // will return 24
  println(simpleIncrementer(23)) //same as the above
  // So essentially we have defined a function (or an object that works like one)

  // All Scala Functions are instances of the FunctionX type!
  // These are traits, and the name indicates the number of arguments.
  // One argument, Function1, two arguments, Function2, etc.
  // The maximum is Function22
  // The third constructor argument to the FunctionX constructor is the return type

  // Example:
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(stringConcatenator("I love ", "Scala")) // I love Scala

  // Syntactic Sugar
  val doubler: Function1[Int, Int] = (x: Int) => x * 2
  // looks like a Java8 Lambda, sort of
  println(doubler(4)) // returns 8
  // but can be shortened further as
  val doubler2: Int => Int = (x: Int) => x * 2
  println(doubler2(8)) // returns 16
  // But we can actually remove the type spec further, as the compiler can figure it out:
  val doubler3 = (x: Int) => x * 2
  println(doubler3(16)) // returns 32

  // Functions that take functions as arguments, or return them, are called Higher-order Functions (HOF)
  val aMappedList = List(1, 2, 3).map(doubler) // map takes a function as an argument, so it is a HOF
  println(aMappedList)
  val anotherMappedList = List(1, 2, 3).map((x => x + 2)) // can be defined in place with an anonymous function
  println(anotherMappedList)

  // another example -- flatmap concatenates lists of lists
  val aFlatMappedList = List(1, 2, 3).flatMap { x =>
    List(x, 2 * x)
  } // alternative syntax
  println(aFlatMappedList)

  // filtering
  val filteredList = List(1, 2, 3, 4, 5).filter(x => x <= 3)
  println(filteredList)

  // There is an even shorter syntax
  val filteredList2 = List(1, 2, 3, 4, 5).filter(_ <= 3)
  println(filteredList2)

  // Let's chain these together. Let's say we want all the pairs composed of 1, 2, 3 and a, b, c
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs)
  // This is what we do instead of iteration or looping

  // This can get hard to read, so we use 'for comprehensions'
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  println(alternativePairs)

  // COLLECTIONS
  // Lists
  val list = List(1, 2, 3, 4, 5)
  // A List has a head and a tail
  println(s"head: ${list.head}")
  println(s"tail: ${list.tail}")
  // Prepending -- notice syntax for these
  println(s"Prepended List: ${0 :: list}")
  println(s"Appended List: ${list :+ 6}")
  // these can be combined
  println(s"Prepended and Appended List: ${0 +: list :+ 6}")

  // Sequences
  val aSequence: Seq[Int] = Seq(1, 2, 3)
  // Sequences are more like arrays in that you can access by index
  val firstElement = aSequence(0)
  println(s"$aSequence: $firstElement")

  // Vectors are very fast sequences, good for large amounts of data
  val aVector = Vector(1, 2, 3, 4, 5)
  // Vectors have the same methods as Lists and Sequences

  // Sets are collections with no duplicates (like Java)
  val aSet = Set(1, 2, 3, 4, 1, 2, 3)
  println(aSet)
  val hasAFive = aSet.contains(5)
  println(s"Has a Five? $hasAFive")
  val addedSet = aSet + 5 // Like a Java Set, there are no order guarantees
  println(s"Has a Five? ${addedSet.contains(5)}")
  val minusedSet = aSet - 3 // minus works, too
  println(s"Has a Three? ${minusedSet.contains(3)}")

  // Ranges
  val aRange = 1 to 1000
  // The range does not contain the numbers 1 to 1000, but it can be processed as if it did.
  // numbers 2 .. 2000
  val twoByTwo = aRange.map(x => 2 * x).toList // toList method is a collection conversion (like Java)

  // Tuples
  val aTuple = ("Pink Floyd", "Dark Side of the Moon", 1973) // here the parentheses are key

  //Maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 1234567890), // takes tuples
    "Jane" -> 987654321 // alternative syntax for tuples
  )
}
