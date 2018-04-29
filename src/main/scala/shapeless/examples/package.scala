package shapeless

package object examples {

  // Coproduct aka OR type, i.e. an animal is either a Dog OR Cat
  sealed trait Animal
  case class Dog() extends Animal
  case class Cat() extends Animal

  // Product aka AND type, i.e. a Tapir has an origin AND height AND nocturnal flag
  case class Anteater(habitat: String, length: Double, herbivore: Boolean)
  case class Tapir(origin: String, height: Double, nocturnal: Boolean)

  val anteater = Anteater("Savannah", 1.8, false)
  val tapir = Tapir("Brazil", 2.0, true)
}
