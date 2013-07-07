package uk.me.chrs.inflect

import uk.me.chrs.inflect.Inflect_EN._
import org.scalatest.FunSuite

class PluralTest extends FunSuite {

  test("basic rule based plurals") {
    val examples = List(
      "dog" -> "dogs",
      "ferry" -> "ferries",
      "fox" -> "foxes",
      "shelf" -> "shelves",
      "quiz" -> "quizzes",
      "fizz" -> "fizzes",
      "church" -> "churches",
      "wish" -> "wishes",
      "bonus" -> "bonuses",
      "domino" -> "dominoes",
      "life" -> "lives",
      "cuff" -> "cuffs",
      "zoo" -> "zoos",
      "boy" -> "boys",
      "storey" -> "storeys",
      "play" -> "plays",
      "guy" -> "guys",
      "soliloquy" -> "soliloquies",
      "series" -> "series",
      "spike" -> "spikes")
    check(examples)
  }

  test("words that don't change in plural") {
    val examples = List(
      "bison" -> "bison",
      "fish" -> "fish",
      "crayfish" -> "crayfish",
      "swine" -> "swine",
      "plankton" -> "plankton",
      "squid" -> "squid",
      "aircraft" -> "aircraft",
      "hovercraft" -> "hovercraft",
      "giant squid" -> "giant squid",
      "chamois" -> "chamois",
      "waterfowl" -> "waterfowl",
      "haggis" -> "haggis",
      "sea-bass" -> "sea-bass",
      "cod" -> "cod",
      "portuguese" -> "portuguese",
      "small pox" -> "small pox",
      "samurai" -> "samurai")
    check(examples)
  }

  test("special plurals") {
    val examples = List(
      "man" -> "men",
      "woman" -> "women",
      "human" -> "humans",
      "fisherman" -> "fishermen",
      "human being" -> "human beings",
      "ox" -> "oxen",
      "child" -> "children",
      "grandchild" -> "grandchildren",
      "childminder" -> "childminders",
      "foot" -> "feet",
      "footballer" -> "footballers",
      "mouse" -> "mice",
      "dormouse" -> "dormice",
      "mousehole" -> "mouseholes",
      "louse" -> "lice",
      "blouse" -> "blouses",
      "woodlouse" -> "woodlice",
      "goose" -> "geese",
      "mongoose" -> "mongooses",
      "tooth" -> "teeth",
      "toothbrush" -> "toothbrushes",
      "person" -> "people",
      "personality" -> "personalities",
      "vertex" -> "vertices",
      "matrix" -> "matrices",
      "crisis" -> "crises",
      "prima donna" -> "prima donnas",
      "stimulus" -> "stimuli",
      "radius" -> "radii",
      "lowlife" -> "lowlifes",
      "topaz" -> "topazes",
      "vertebra" -> "vertebrae",
      "stratum" -> "strata",
      "criterion" -> "criteria",
      "rhino" -> "rhinos",
      "stomach" -> "stomachs",
      "german" -> "germans",
      "savoury" -> "savouries")
    check(examples)
  }

  test("conditional plurals") {
    assert(plural(0, "ox") === "oxen")
    assert(plural(1, "ox") === "ox")
    assert(plural(2, "ox") === "oxen")
  }

  test("count plurals") {
    assert(count(0, "box") === "0 boxes")
    assert(count(1, "box") === "1 box")
    assert(count(7, "box") === "7 boxes")
  }

  def check(examples: List[(String, String)]) {
    for ((s, p) <- examples) {
      assert(plural(s) === p)
    }
  }

}
