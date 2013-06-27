package uk.me.chrs.inflect

import uk.me.chrs.inflect.Inflect._
import org.scalatest.FunSuite

class PluralTest extends FunSuite {

  test("basic rule based plurals") {
    assert(plural("dog") === "dogs")
    assert(plural("ferry") === "ferries")
    assert(plural("fox") === "foxes")
    assert(plural("shelf") === "shelves")
    assert(plural("quiz") === "quizzes")
    assert(plural("fizz") === "fizzes")
    assert(plural("church") === "churches")
    assert(plural("wish") === "wishes")
    assert(plural("bonus") === "bonuses")
    assert(plural("domino") === "dominoes")
    assert(plural("life") === "lives")
    assert(plural("cuff") === "cuffs")
    assert(plural("zoo") === "zoos")
    assert(plural("boy") === "boys")
    assert(plural("storey") === "storeys")
    assert(plural("play") === "plays")
    assert(plural("guy") === "guys")
    assert(plural("soliloquy") === "soliloquies")
  }

}
