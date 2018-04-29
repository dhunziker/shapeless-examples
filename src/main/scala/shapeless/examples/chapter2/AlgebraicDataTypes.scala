package shapeless.examples.chapter2

import shapeless.examples._

object AlgebraicDataTypes extends App {

  // Compiler knows whether or not our match is complete, i.e. covers all possible types
  def price(security: Security): Double = security match {
    case Bond(_, maturity, faceValue, rate) => faceValue / Math.pow(1 + rate, maturity)
    case Equity(_, price) => price
  }

  println(price(bond))
  println(price(equity))
}
