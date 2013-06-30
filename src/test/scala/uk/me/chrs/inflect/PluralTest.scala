package uk.me.chrs.inflect

import uk.me.chrs.inflect.Inflect._
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
      "giant squid" -> "giant squid")
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
      "crisis" -> "crises"
    )
    check(examples)
  }

  test("conditional plurals") {
    assert(plural("ox", 0) === "oxen")
    assert(plural("ox", 1) === "ox")
    assert(plural("ox", 2) === "oxen")
  }

  def check(examples: List[(String, String)]) {
    for ((s, p) <- examples) {
      assert(plural(s) === p)
    }
  }

}
