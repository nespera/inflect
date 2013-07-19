package uk.me.chrs.inflect

class Options(val minusIndicator : String,
              val andSeparator: String,
              val nonePrefix: String,
              val onePrefix: String,
              val somePrefix: String)

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
