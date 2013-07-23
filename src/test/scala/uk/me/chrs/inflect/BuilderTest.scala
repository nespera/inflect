package uk.me.chrs.inflect

import org.scalatest.FunSuite
import Inflect_EN.Builder._

class BuilderTest extends FunSuite {

  test("builder can do count and some") {
    assert("I see " + using(0)(count("person"),q(" with "),some("opinion")) === "I see 0 people with no opinions")
    assert("I see " + using(1)(count("person"),q(" with "),some("opinion")) === "I see 1 person with an opinion")
    assert("I see " + using(8)(count("person"),q(" with "),some("opinion")) === "I see 8 people with opinions")
  }

  test("builder can do plural too") {
    assert("There " + using(0)(plural("is"),q(" "),count("door"),q(" on your "),plural("house")) ===
      "There are 0 doors on your houses")
    assert("There " + using(1)(plural("is"),q(" "),count("door"),q(" on your "),plural("house")) ===
      "There is 1 door on your house")
    assert("There " + using(8)(plural("is"),q(" "),count("door"),q(" on your "),plural("house")) ===
      "There are 8 doors on your houses")
  }
}
