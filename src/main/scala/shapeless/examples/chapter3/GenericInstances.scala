package shapeless.examples.chapter3

import shapeless.{::, HList, HNil, the}

object GenericInstances extends App {

  // Encoders for standard language types
  implicit val stringEnc: CsvEncoder[String] = CsvEncoder.pure((a: String) => Seq(a))

  implicit val doubleEnc: CsvEncoder[Double] = CsvEncoder.pure((a: Double) => Seq(a.toString))

  implicit val booleanEnc: CsvEncoder[Boolean] = CsvEncoder.pure((a: Boolean) => Seq(if (a) "ja" else "nein"))

  // Generic encoders for shapeless types
  implicit val hnilEnc: CsvEncoder[HNil] = CsvEncoder.pure(_ => Nil)

  implicit def hlistEnc[A, B <: HList](implicit
                                       headEnc: CsvEncoder[A],
                                       tailEnc: CsvEncoder[B]): CsvEncoder[A :: B] = CsvEncoder.pure {
    case head :: tail => headEnc.encode(head) ++ tailEnc.encode(tail)
  }

  // Summon encoder and encode heterogeneous list
  println(the[CsvEncoder[String :: Double :: Boolean :: HNil]].encode("Brazil" :: 1.8 :: true :: HNil))
}
