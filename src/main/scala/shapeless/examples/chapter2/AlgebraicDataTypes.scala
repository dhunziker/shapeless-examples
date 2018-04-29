package shapeless.examples.chapter2

import shapeless.examples.{Cat, Dog, _}

object AlgebraicDataTypes extends App {

  // Compiler knows whether or not our match is complete, i.e. covers all possible types
  def speak(animal: Animal): String = animal match {
    case Dog() => "woof"
    case Cat() => "meow"
  }

  println(speak(Dog()))
  println(speak(Cat()))
}
