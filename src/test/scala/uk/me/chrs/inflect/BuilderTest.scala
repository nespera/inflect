package uk.me.chrs.inflect

import org.scalatest.FunSuite
import Inflect_EN._

class BuilderTest extends FunSuite {

  test("can do count and some") {
    assert("I see " + using(0).count("person")(" with ").some("opinion", zero="") === "I see 0 people with opinions")
    assert("I see " + using(1).count("person")(" with ").some("opinion", lone="a single") === "I see 1 person with a single opinion")
    assert("I see " + using(8).count("person")(" with ").some("opinion", many="some") === "I see 8 people with some opinions")
  }

  test("can do plural too") {
    assert("I see " + using(1).count("door")(" on your ").plural("house") === "I see 1 door on your house")
    assert("I see " + using(8).count("door")(" on your ").plural("house") === "I see 8 doors on your houses")
  }
}
