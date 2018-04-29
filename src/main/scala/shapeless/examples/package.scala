package shapeless

package object examples {

  // Coproduct aka OR type, i.e. a security is either a Bond OR Equity
  sealed trait Security
  case class Bond(ticker: String, maturity: Int, faceValue: Int, rate: Double) extends Security
  case class Equity(ticker: String, price: Double) extends Security

  val bond = Bond("UST", 10, 1000, 0.06)
  val equity = Equity("IBM", 147.23)

  // Product aka AND type, i.e. an Anteater has a habitat AND length AND herbivore flag
  case class Anteater(habitat: String, length: Double, herbivore: Boolean)
  case class Tapir(origin: String, height: Double, nocturnal: Boolean)

  val anteater = Anteater("Savannah", 1.8, false)
  val tapir = Tapir("Brazil", 2.0, true)
}
