package uk.me.chrs.inflect

import org.scalatest.FunSuite
import uk.me.chrs.inflect.Inflect._

class IndefiniteArticleTest extends FunSuite {

  test("basic tests") {
    assert(a("box") === "a box")
    assert(a("egg") === "an egg")
    assert(an("box") === "a box")
    assert(an("egg") === "an egg")
  }

}
