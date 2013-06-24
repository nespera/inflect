package uk.me.chrs.inflect

import org.scalatest.FunSuite
import Inflect._

class OrdinalTest extends FunSuite {

  test("numeric ordinals") {
    assert(ordinal(0) === "0th")
    assert(ordinal(1) === "1st")
    assert(ordinal(2) === "2nd")
    assert(ordinal(3) === "3rd")
    assert(ordinal(4) === "4th")
    assert(ordinal(5) === "5th")
    assert(ordinal(6) === "6th")
    assert(ordinal(7) === "7th")
    assert(ordinal(8) === "8th")
    assert(ordinal(9) === "9th")
    assert(ordinal(10) === "10th")
    assert(ordinal(11) === "11th")
    assert(ordinal(12) === "12th")
    assert(ordinal(13) === "13th")
    assert(ordinal(14) === "14th")
    assert(ordinal(15) === "15th")
    assert(ordinal(16) === "16th")
    assert(ordinal(17) === "17th")
    assert(ordinal(18) === "18th")
    assert(ordinal(19) === "19th")
    assert(ordinal(20) === "20th")
    assert(ordinal(21) === "21st")
    assert(ordinal(22) === "22nd")
    assert(ordinal(23) === "23rd")
    assert(ordinal(24) === "24th")
    assert(ordinal(62) === "62nd")
    assert(ordinal(100) === "100th")
    assert(ordinal(101) === "101st")
    assert(ordinal(102) === "102nd")
    assert(ordinal(103) === "103rd")
    assert(ordinal(104) === "104th")
    assert(ordinal(312) === "312th")
    assert(ordinal(8014) === "8014th")
    assert(ordinal(10004) === "10004th")
  }

  test("Using the th method and implicit conversion for numeric ordinals") {
    import ImplicitConversions._
    assert(1.th == "1st")
  }

  test("string ordinals") {
    assert(ordinal("zero") === "zeroth")
    assert(ordinal("one") === "first")
    assert(ordinal("two") === "second")
    assert(ordinal("three") === "third")
    assert(ordinal("four") === "fourth")
    assert(ordinal("five") === "fifth")
    assert(ordinal("six") === "sixth")
    assert(ordinal("seven") === "seventh")
    assert(ordinal("eight") === "eighth")
    assert(ordinal("nine") === "ninth")
    assert(ordinal("ten") === "tenth")
    assert(ordinal("eleven") === "eleventh")
    assert(ordinal("twelve") === "twelfth")
    assert(ordinal("thirteen") === "thirteenth")
    assert(ordinal("fourteen") === "fourteenth")
    assert(ordinal("fifteen") === "fifteenth")
    assert(ordinal("sixteen") === "sixteenth")
    assert(ordinal("seventeen") === "seventeenth")
    assert(ordinal("eighteen") === "eighteenth")
    assert(ordinal("nineteen") === "nineteenth")
    assert(ordinal("twenty") === "twentieth")
    assert(ordinal("twenty-one") === "twenty-first")
    assert(ordinal("twenty-two") === "twenty-second")
    assert(ordinal("twenty-three") === "twenty-third")
    assert(ordinal("twenty-four") === "twenty-fourth")
    assert(ordinal("sixty") === "sixtieth")
    assert(ordinal("one hundred") === "one hundredth")
    assert(ordinal("one hundred and four") === "one hundred and fourth")
    assert(ordinal("one hundred and five") === "one hundred and fifth")
    assert(ordinal("one hundred and eight") === "one hundred and eighth")
    assert(ordinal("one hundred and three") === "one hundred and third")
  }

  test("Using the th method and implicit conversion for string ordinals") {
    import ImplicitConversions._
    assert("five".th == "fifth")
  }
}
