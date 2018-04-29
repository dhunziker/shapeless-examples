package shapeless.examples.chapter3

import shapeless.examples._
import shapeless.{:+:, ::, CNil, Coproduct, Generic, HList, HNil, Inl, Inr, Lazy}

object TypeClasses {

  // Encoders for standard language types
  trait StandardInstances {
    implicit val stringEnc: CsvEncoder[String] = CsvEncoder.pure((a: String) => Seq(a))
    implicit val intEnc: CsvEncoder[Int] = CsvEncoder.pure((a: Int) => Seq(a.toString))
    implicit val doubleEnc: CsvEncoder[Double] = CsvEncoder.pure((a: Double) => Seq(a.toString))
    implicit val booleanEnc: CsvEncoder[Boolean] = CsvEncoder.pure((a: Boolean) => Seq(if (a) "ja" else "nein"))
  }

  // Concrete encoders anteaters and tapirs
  trait ConcreteInstances extends StandardInstances {
    implicit def anteaterEnc(implicit
                             stringEnc: CsvEncoder[String],
                             doubleEnc: CsvEncoder[Double],
                             booleanEnc: CsvEncoder[Boolean]
                            ): CsvEncoder[Anteater] = CsvEncoder.pure {
      (a: Anteater) => stringEnc.encode(a.habitat) ++ doubleEnc.encode(a.length) ++ booleanEnc.encode(a.herbivore)
    }

    implicit def tapirEnc(implicit
                          stringEnc: CsvEncoder[String],
                          doubleEnc: CsvEncoder[Double],
                          booleanEnc: CsvEncoder[Boolean]
                         ): CsvEncoder[Tapir] = CsvEncoder.pure {
      (a: Tapir) => stringEnc.encode(a.origin) ++ doubleEnc.encode(a.height) ++ booleanEnc.encode(a.nocturnal)
    }
  }

  // Generic encoders for shapeless types
  trait GenericInstances extends StandardInstances {
    implicit val hnilEnc: CsvEncoder[HNil] = CsvEncoder.pure(_ => Nil)

    implicit def hlistEnc[A, B <: HList](implicit
                                         headEnc: CsvEncoder[A],
                                         tailEnc: CsvEncoder[B]
                                        ): CsvEncoder[A :: B] = CsvEncoder.pure {
      case head :: tail => headEnc.encode(head) ++ tailEnc.encode(tail)
    }
  }

  trait ProductInstances extends GenericInstances {
    implicit def productEnc[A, R](implicit
                                  gen: Generic.Aux[A, R],
                                  enc: Lazy[CsvEncoder[R]] // Avoid implicit divergence
                                 ): CsvEncoder[A] = {
      (a: A) => enc.value.encode(gen.to(a))
    }
  }

  trait CoproductInstances extends ProductInstances {
    implicit val cnilEnc: CsvEncoder[CNil] = CsvEncoder.pure((_: CNil) => throw new RuntimeException())

    implicit def coproductEnc[A, B <: Coproduct](implicit
                                                 leftEnc: Lazy[CsvEncoder[A]], // Avoid implicit divergence
                                                 rightEnc: CsvEncoder[B]
                                                ): CsvEncoder[A :+: B] = CsvEncoder.pure {
      case Inl(left) => leftEnc.value.encode(left)
      case Inr(right) => rightEnc.encode(right)
    }
  }
}
