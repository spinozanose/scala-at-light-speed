package com.rockthejvm

// Note that this extends App, turning it into a runnable application (App has a main method)
object ObjectOrientation extends App {

  // this is a class
  class Animal {
    // all fields and methods are PUBLIC unless marked as such (there is also a
    // private keyword)
    protected val isAlive = true
    //define fields
    val age:Int = 0
    //define methods
    def eat()= println("I'm eating")
  }
  //which we can instantiate
  val animal = new Animal

  // inheritance
  class Dog extends Animal // this is fine if we don't want to add anything
  val dog = new Dog

  class Cat(name: String) extends Animal // constructor argument
  val myCat = new Cat("Jasper")
  // constructor arguments are NOT fields. "name" not available.
  // myCat.name <-- this does not compile

  class Ferret(val name: String) extends Animal // this constructor argument IS a field
  val myFerret = new Ferret("Squirrel")
  myFerret.name // <-- but this does

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Ferret("Slinky")
  // if the eat method is overridden in the inheritance chain,
  // this will call the most derived method at runtime
  aDeclaredAnimal.eat()

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true

    def walk(): Unit // no implementation here, so extending class needs to implement

  }

  // and something like an interface (nothing implemented)
  trait Carnivore {
    def eat(animal:Animal):Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit //"?!" is a valid method name, and symbols can have uses in some frameworks
  }

  // like Java, there is only single inheritance, but multiple implementations ("multi-trait mixing")
  class Crocodile extends Animal with Carnivore with Philosopher {
    // notice the "override" keyword
    override def eat(animal: Animal): Unit = {
      println("Snap!")
      // animal.isAlive = false <--How would I do this in the Scala way?
    }

    def ?!(thought: String): Unit = println(s"I was not just thinking about eating you, I was also thinking $thought.")

    override def eat(): Unit = super.eat()
  }

  val aCrocodile = new Crocodile
  aCrocodile.eat(myCat) // usual way, but can also be written
  aCrocodile eat myCat // Cool!
  // This is "infix notation". Only available for methods with ONE argument

  aCrocodile ?! "I'd like to eat the ferret next!"
  // Notice that this looks like an operator? Well, operators in Scala are actually methods!

  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) //plus is a method on the Int class

  //anonymous classes
  val aDinosaur = new Carnivore {
    override def eat(animal:Animal) :Unit = {
      println("Chomp!")
      //animal.isAlive = false
    }
  }

  // singleton object
  object MySingleton { // <-- this can be the only instance of this type, and this line also instantiates it.
    def mySpecialMethod: Int = 345345

    // this is a special method in any class or object
    def apply(x: Int): Int = x + 1 // It can be any set of arguments, return, or implementation

  }

  MySingleton.mySpecialMethod // as expected
  MySingleton.apply(19) // and an apply method works the same way
  MySingleton(19) // but this also can be done. It calls the apply method

  //companion objects
  object Animal { // notice that this is the same name as the class! Can also be applied to Traits, btw
    // companions can access the other's private fields and methods
    // Warning: the singleton animal and the instances of Animal are different things
    // Normally, you do not use this pattern with instances of Animal, though
    val canLiveIndefinitely = false
  }
  // Look at this
  val animalsCanLiveForever = Animal.canLiveIndefinitely
  //doesn't this seem like a static in Java? That's what it is for.


  // case classes
  // a common pattern in Scala because they are a lightweight data structure
  /*
  When created, Scala creates
    - sensible equals() and hashcode() methods
    - serialization capability
    - a companion with apply()
    - pattern matching (more later)
  */
  case class Person(name: String, age: Int)
  // case classes can be instantiated with the "new" keyword
  // This is using the implicit companion's apply method
  val bob = Person("Bob", 12)
  // this is the same as val bob = Person.apply("Bob", 12)


  // Exceptions
  try {
    // throw an exception
    val x: String = null
    x.length
  } catch {
    case e: Exception => "Some faulty error message"
  } finally { // we also have finally if we need it
    println("In finally block")
  }

  // Generics
  abstract class MyList[T] { //Notice the type specification
    def head: T
    def tail: MyList[T]
  }

  // This is the standard List in Scala
  val aList: List[Int] = List(1,2,3)
  val first = aList.head
  val rest = aList.tail

  val aStringList = List("Hello", "Scala")

  // Final Notes:
  // 1. In Scala we operate with IMMUTABLE values and objects. So
  val reversedList = aList.reverse
  // returns a NEW list, leaving the original intact (immutable)
  // Any modification to an object should return a new object
  // Benefits:
  //  - works miracles in multithreaded and distributed environments
  //  - helps reasoning about the code
  // 2. Scala is the closest to the OO ideal because every bit of code is in an object

}