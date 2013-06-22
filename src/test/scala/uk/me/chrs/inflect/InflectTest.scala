package uk.me.chrs.inflect

import org.scalatest.FunSuite

class InflectTest extends FunSuite {

  test("th gets added to most numbers") {
    assert(Inflect.ordinal(7) === "7th")
  }

}
