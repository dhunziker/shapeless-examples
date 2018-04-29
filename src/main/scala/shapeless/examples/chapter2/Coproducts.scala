package shapeless.examples.chapter2

import shapeless.examples._
import shapeless.{:+:, CNil, Generic, Inl, Inr}

object Coproducts extends App {

  // Generic representation of a security
  type Security2 = Bond :+: Equity :+: CNil
  val bond2: Security2 = Inl(bond)
  val equity2: Security2 = Inr(Inl(equity))

  // Using shapeless to generate generic representation for concrete instances
  val securityGen = Generic[Security]
  println(securityGen.to(bond))
  println(securityGen.to(equity))
}
