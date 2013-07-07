package uk.me.chrs.inflect

import org.scalatest.FunSuite
import uk.me.chrs.inflect.Inflect_EN._

class IndefiniteArticleTest extends FunSuite {

  test("basic tests") {
    assert(one("box") === "a box")
    assert(one("egg") === "an egg")
  }

  test("exceptions") {
    assert(one("european") === "a european")
    assert(one("ewe") === "a ewe")
    assert(one("heirloom") === "an heirloom")
    assert(one("honour") === "an honour")
    assert(one("hourglass") === "an hourglass")
    assert(one("honest man") === "an honest man")
    assert(one("unicorn") === "a unicorn")
    assert(one("unit") === "a unit")
    assert(one("university") === "a university")
    assert(one("user") === "a user")
    assert(one("uruguayan") === "a uruguayan")
    assert(one("one-off") === "a one-off")
  }

  test("handle numbers") {
    assert(one("8-man crew") === "an 8-man crew")
    assert(one("6 legged beast") === "a 6 legged beast")
    assert(one("11-a-side tournament") === "an 11-a-side tournament")
    assert(one("1 man show") === "a 1 man show")
  }

  test("handle acronyms") {
    assert(one("NGO") === "an NGO")
    assert(one("XML document") === "an XML document")
    assert(one("US citizen") === "a US citizen")
  }

  test("handle capitalized words") {
    assert(one("Hourly show") === "an Hourly show")
    assert(one("Union member") === "a Union member")
    assert(one("Euro") === "a Euro")
    assert(one("One Way Ticket") === "a One Way Ticket")
    assert(one("Orangeman") === "an Orangeman")
  }

}
