package com.rockthejvm

object Basics extends App {

  // variables (this is a constant)
  val meaningOfLife: Int = 42
  // Other types include Boolean, Char, Double, Float, String

  // implicit typing
  val aBoolean = false

  //Strings
  var sString = "I love Scala!"
  var composedString = "I" + " " + " love" + " " + "Scala!"
  var interpolatedString = s"The meaning of life is $meaningOfLife"

  // Expressions are structures that can be reduced to a value
  // All operations in Scala are expressions that can be reduced to a value

  // if-expression (in other languages this is similar to a ternary operator)
  val ifExpressionsValue = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpresion =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  // Code blocks are also expressions
  var aCodeBlock = { // Notice that the compiler knows this is an Int based on the return value
    //definitions
    val aLocalValue = 67 // Not visible outside the code block

    aLocalValue + 3 // The value of the block is the value of the last expression
  }

  // Functions
  def MyFunction(x: Int, y: String): String = y + " " + x

  def codeBlockFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // In practice, functions are usually recursive.
  // In Scala, we do recursion INSTEAD OF loops or iteration
  // There are loops and such available in Scala, but don't use them!
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  // The Unit type = no meaningful value. Kind of like void in other languages
  println("Are you loving Scala yet?") // Has no meaningful value, so "returns" Unit
  // Unit is the type of SIDE EFFECTS, which are those things that do not compute a value
  val theUnit = ()
}
