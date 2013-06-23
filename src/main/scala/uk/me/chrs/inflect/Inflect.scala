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

    def suffix(number: Int): String = {
      if (number % 100 /10 == 1) "th" else digitSuffix(number % 10)
    }

    number + suffix(number)
  }

  def ordinal (number: String) = {

    def ends(end: String) = {
      ("(.*)" + end + "$").r
    }

    val patterns = List("one" -> "first", "two" -> "second", "three" -> "third",
                        "ve" -> "fth", "eight" -> "eighth", "nine" -> "ninth",
                        "ty" -> "tieth", "" -> "th")

    var answer: String = ""
    for (pattern <-  patterns) {
      val theMatch = ends(pattern._1).findFirstMatchIn(number)
      if (theMatch.isDefined && answer.isEmpty) {
        answer = theMatch.get.group(1) + pattern._2
      }
    }
    answer
  }
}
