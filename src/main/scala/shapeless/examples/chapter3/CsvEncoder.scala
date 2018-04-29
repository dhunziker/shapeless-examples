package shapeless.examples.chapter3

trait CsvEncoder[A] {
  def encode(a: A): Seq[String]
}

object CsvEncoder {

  // Lifts function into CsvEncoder, i.e. creates a CsvEncoder
  def pure[A](f: A => Seq[String]): CsvEncoder[A] = (a: A) => f(a)
}
