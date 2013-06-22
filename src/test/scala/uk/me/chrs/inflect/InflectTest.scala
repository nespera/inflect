package uk.me.chrs.inflect

import org.scalatest.FunSuite

class InflectTest extends FunSuite {

  test("th gets added to most numbers") {
    assert(Inflect.ordinal(7) === "7th")
  }

  test("1 becomes 1st") {
    assert(Inflect.ordinal(1) === "1st")
  }

  test("2 becomes 2nd") {
    assert(Inflect.ordinal(2) === "2nd")
  }

  test("3 becomes 3rd") {
    assert(Inflect.ordinal(3) === "3rd")
  }

  test("101 becomes 101st") {
    assert(Inflect.ordinal(101) === "101st")
  }

  test("11 becomes 11th") {
    assert(Inflect.ordinal(11) === "11th")
  }

  test("62 becomes 62nd") {
    assert(Inflect.ordinal(62) === "62nd")
  }

  test("312 becomes 312th") {
    assert(Inflect.ordinal(312) === "312th")
  }


}
