package shapeless.examples.chapter2

import shapeless.Generic
import shapeless.examples._

object Products extends App {

  // Convert anteater to its generic representation
  val anteaterGen = Generic[Anteater]
  val anteaterRepr = anteaterGen.to(anteater)
  println(anteaterRepr)

  // Convert tapir into generic representation and back into an anteater
  val tapirGen = Generic[Tapir]
  val anteater2 = anteaterGen.from(tapirGen.to(tapir))
  println(anteater2)
}
