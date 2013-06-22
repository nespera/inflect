package uk.me.chrs.inflect

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

    def ordinalSuffix(number: Int): String = {
      if (number % 100 /10 == 1) "th" else digitSuffix(number % 10)
    }

    number + ordinalSuffix(number)
  }

}
