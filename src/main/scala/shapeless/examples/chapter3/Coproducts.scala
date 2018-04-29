package shapeless.examples.chapter3

import shapeless.examples._
import shapeless.examples.chapter3.TypeClasses.CoproductInstances
import shapeless.the

object Coproducts extends App with CoproductInstances {

  // Summons coproduct encoder and encodes values
  println(the[CsvEncoder[Security]].encode(bond))
  println(the[CsvEncoder[Security]].encode(equity))
}
