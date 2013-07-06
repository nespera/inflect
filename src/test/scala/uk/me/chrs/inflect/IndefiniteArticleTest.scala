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

  test("exceptions") {
    assert(a("european") === "a european")
    assert(an("ewe") === "a ewe")
    assert(a("heirloom") === "an heirloom")
    assert(an("honour") === "an honour")
    assert(a("hourglass") === "an hourglass")
    assert(a("honest man") === "an honest man")
    assert(an("unicorn") === "a unicorn")
    assert(an("unit") === "a unit")
    assert(an("university") === "a university")
    assert(a("user") === "a user")
    assert(an("uruguayan") === "a uruguayan")
    assert(a("one-off") === "a one-off")
  }

}
