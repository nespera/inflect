package uk.me.chrs.inflect

class Options(minus : String,
              and: String,
              none: String,
              one: String,
              some: String) {

  val minusIndicator : String = minus
  val andSeparator: String = and
  val nonePrefix: String = none
  val onePrefix: String  = one
  val somePrefix: String = some
}

object Options {

  val defaultMinus = "minus"
  val defaultAnd = "and"
  val defaultNone = "no"
  val defaultOne = "a"

  def apply(minus : String = defaultMinus,
            and: String = defaultAnd,
            none: String = defaultNone,
            one: String = defaultOne,
            some: String = "") = new Options(minus, and, none, one, some)
}
