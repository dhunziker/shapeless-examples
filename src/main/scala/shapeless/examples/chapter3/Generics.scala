package shapeless.examples.chapter3

import shapeless.examples.chapter3.TypeClasses.GenericInstances
import shapeless.{::, HNil, the}

object Generics extends App with GenericInstances {

  // Summon encoder and encode heterogeneous list
  println(the[CsvEncoder[String :: Double :: Boolean :: HNil]].encode("Brazil" :: 1.8 :: true :: HNil))
}
