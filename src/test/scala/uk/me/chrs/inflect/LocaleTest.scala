package uk.me.chrs.inflect

import org.scalatest.FunSuite

class LocaleTest extends FunSuite {

  test("specify gb") {
    import uk.me.chrs.inflect.Inflect_EN_GB._
    assert(cardinal(107) === "one hundred and seven")
  }

  test("specify us") {
    import uk.me.chrs.inflect.Inflect_EN_US._
    assert(cardinal(107) === "one hundred seven")
  }
}
