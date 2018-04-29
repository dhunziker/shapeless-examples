package shapeless.examples.chapter3

import shapeless.examples.chapter3.TypeClasses.ConcreteInstances
import shapeless.examples.{Anteater, Tapir, anteater, tapir}
import shapeless.the

object ResolvingTuple extends App with ConcreteInstances {

  // Instance for Scala product, i.e. Tuple2
  implicit def tupleEnc[A, B](implicit
                              encA: CsvEncoder[A],
                              encB: CsvEncoder[B]
                             ): CsvEncoder[(A, B)] = CsvEncoder.pure {
    case (a, b) => encA.encode(a) ++ encB.encode(b)
  }

  // Summon type class instances and encode values
  println(the[CsvEncoder[Anteater]].encode(anteater))
  println(the[CsvEncoder[Tapir]].encode(tapir))
  println(the[CsvEncoder[(Anteater, Tapir)]].encode((anteater, tapir)))
}
