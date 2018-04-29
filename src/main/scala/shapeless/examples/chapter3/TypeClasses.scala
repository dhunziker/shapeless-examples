package shapeless.examples.chapter3

import shapeless.examples._
import shapeless.the

object TypeClasses extends App {

  // Concrete type class instances for anteaters and tapirs with a custom definition for booleans
  implicit def anteaterEnc(implicit booleanEnc: CsvEncoder[Boolean]): CsvEncoder[Anteater] = {
    (a: Anteater) => Seq(a.habitat, a.length.toString) ++ booleanEnc.encode(a.herbivore)
  }

  implicit def tapirEnc(implicit booleanEnc: CsvEncoder[Boolean]): CsvEncoder[Tapir] = {
    (a: Tapir) => Seq(a.origin, a.height.toString) ++ booleanEnc.encode(a.nocturnal)
  }

  implicit def tupleEnc[A, B](implicit
                              encA: CsvEncoder[A],
                              encB: CsvEncoder[B]): CsvEncoder[(A, B)] = {
    (a: (A, B)) => encA.encode(a._1) ++ encB.encode(a._2)
  }

  implicit val booleanEnc: CsvEncoder[Boolean] = CsvEncoder.pure((a: Boolean) => Seq(if (a) "ja" else "nein"))

  // Summon type class instances and encode values
  println(the[CsvEncoder[Anteater]].encode(anteater))
  println(the[CsvEncoder[Tapir]].encode(tapir))
  println(the[CsvEncoder[(Anteater, Tapir)]].encode((anteater, tapir)))
}
