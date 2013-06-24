package uk.me.chrs.inflect

import org.scalatest.FunSuite
import Inflect._

class CardinalTest extends FunSuite {

  test("numeric cardinals") {
    assert(cardinal(-1) === "minus one")
    assert(cardinal(0) === "zero")
    assert(cardinal(1) === "one")
    assert(cardinal(2) === "two")
    assert(cardinal(3) === "three")
    assert(cardinal(4) === "four")
    assert(cardinal(5) === "five")
    assert(cardinal(6) === "six")
    assert(cardinal(7) === "seven")
    assert(cardinal(8) === "eight")
    assert(cardinal(9) === "nine")
    assert(cardinal(10) === "ten")
    assert(cardinal(11) === "eleven")
    assert(cardinal(12) === "twelve")
    assert(cardinal(13) === "thirteen")
    assert(cardinal(14) === "fourteen")
    assert(cardinal(15) === "fifteen")
    assert(cardinal(16) === "sixteen")
    assert(cardinal(17) === "seventeen")
    assert(cardinal(18) === "eighteen")
    assert(cardinal(19) === "nineteen")
    assert(cardinal(20) === "twenty")
    assert(cardinal(21) === "twenty-one")
    assert(cardinal(30) === "thirty")
    assert(cardinal(45) === "forty-five")
    assert(cardinal(99) === "ninety-nine")
    assert(cardinal(100) === "one hundred")
    assert(cardinal(216) === "two hundred and sixteen")
    assert(cardinal(555) === "five hundred and fifty-five")
    assert(cardinal(1000) === "one thousand")
    assert(cardinal(2067) === "two thousand and sixty-seven")
    assert(cardinal(231094) === "two hundred and thirty-one thousand and ninety-four")
  }

  test("can override the word for minus") {
    object MyInflect extends Inflector {
      override val MINUS: String = "negative"
    }

    assert(MyInflect.cardinal(-7) === "negative seven")
    assert(MyInflect.cardinal(8) === "eight")
  }
}
