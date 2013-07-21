package uk.me.chrs.inflect

import org.scalatest.FunSuite
import Inflect_EN.Builder._

class BuilderTest extends FunSuite {

  test("can do count and some") {
    assert("I see " + using(0)(count("person"),q(" with "),some("opinion")) === "I see 0 people with no opinions")
    assert("I see " + using(1)(count("person"),q(" with "),some("opinion")) === "I see 1 person with an opinion")
    assert("I see " + using(8)(count("person"),q(" with "),some("opinion")) === "I see 8 people with opinions")
  }

  test("can do plural too") {
    assert("I see " + using(1)(count("door"),q(" on your "),plural("house")) === "I see 1 door on your house")
    assert("I see " + using(8)(count("door"),q(" on your "),plural("house")) === "I see 8 doors on your houses")
  }
}
