package uk.me.chrs.inflect

trait Inflector {

  val MINUS: String = "minus"

  def ordinal (number: Int) = {

    def digitSuffix(digit: Int): String = {
      digit match {
        case 1 => "st"
        case 2 => "nd"
        case 3 => "rd"
        case _ => "th"
      }
    }

    def suffix(number: Int): String = {
      if (number % 100 /10 == 1) "th" else digitSuffix(number % 10)
    }

    number + suffix(number)
  }

  def ordinal (number: String) = {

    val patterns = List("one$" -> "first", "two$" -> "second", "three$" -> "third",
                        "ve$" -> "fth", "eight$" -> "eighth", "nine$" -> "ninth", "ty$" -> "tieth")

    def replaceSuffix(p: List[(String, String)]): String = {
      p match {
        case x :: xs => if (number.matches(".*" + x._1))
            x._1.r.replaceFirstIn(number, x._2)
          else replaceSuffix(xs)
        case _ => number + "th"
      }
    }

    replaceSuffix(patterns)
  }

  def cardinal (number: Int): String = {
    val small = List("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
      "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = List("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    def ifNonZero(x: Int)(f: Int => String) = {
      if (x != 0) f(x) else ""
    }

    def positive(value: Int): String = {
      if (value < 20)
        small(value)
      else if (value < 100)
        tens(value / 10) + ifNonZero(value % 10){"-" + small(_)}
      else
        positive(value / 100) + " hundred" + ifNonZero(value % 100) {" and " + positive(_)}
    }

    if (number < 0) MINUS + " " + positive(-number) else positive(number)
  }
}

object Inflect extends Inflector{}
