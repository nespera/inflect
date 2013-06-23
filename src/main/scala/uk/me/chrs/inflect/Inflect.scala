package uk.me.chrs.inflect

import scala.language.implicitConversions

object Inflect {

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

  implicit def int2ord(n: Int) = {
    class Ordinalizer(n: Int) {
      def th: String = ordinal(n)
    }
    new Ordinalizer(n)
  }

}
