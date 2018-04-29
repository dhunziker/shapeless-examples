package shapeless.examples.chapter2

import shapeless.examples._
import shapeless.{:+:, CNil, Generic, Inl, Inr}

object Coproducts extends App {

  // Generic representation of an animal
  type Animal2 = Dog :+: Cat :+: CNil
  val dog: Animal2 = Inl(Dog())
  val cat: Animal2 = Inr(Inl(Cat()))

  // Using shapeless to generate generic representation for concrete instances
  val animalGen = Generic[Animal]
  println(animalGen.to(Dog()))
  println(animalGen.to(Cat()))
}
