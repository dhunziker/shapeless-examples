package shapeless.examples.chapter3

import shapeless.examples.chapter3.TypeClasses.ProductInstances
import shapeless.examples.{Anteater, Tapir, anteater, tapir}
import shapeless.the

object Products extends App with ProductInstances {

  // Works for existing case classes
  println(the[CsvEncoder[Anteater]].encode(anteater))
  println(the[CsvEncoder[Tapir]].encode(tapir))

  // Works for any case class for which all fields can be encoded
  case class Foo(bar: String, baz: Double)
  println(the[CsvEncoder[Foo]].encode(Foo("answer", 42.0)))
}
