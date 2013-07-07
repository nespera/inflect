package uk.me.chrs.inflect

trait Inflector {

  def options: Options = Options()

  private def loadSpecialCases: List[(String, String)] = {
    val stream = io.Source.fromInputStream(getClass.getResourceAsStream("special-plurals.txt"))
    stream.getLines().filterNot(_.startsWith("#")).toList.map((x) => x.split(",", 2)).map((x) => (x(0), x(1)))
  }

  val specialCases: List[(String, String)] = loadSpecialCases

  val rules: List[(String, String)] = List(
    "[ie]x" -> "ices",
    "ois" -> "ois",
    "ies" -> "ies",
    "is" -> "es",
    "quy" -> "quies",
    "([aeou]y)" -> "$1s",
    "y" -> "ies",
    "(ff|oo)" -> "$1s",
    "(o|s|x|ch|sh)" -> "$1es",
    "fe?" -> "ves",
    "z?z" -> "zzes")

  def plural (singular: String): String = {
    val replacements = specialCases ++ rules
    val replace = replacements.find(x => (x._1+"$").r.findFirstIn(singular).isDefined).getOrElse("$" -> "s")
    singular.replaceAll(replace._1+"$", replace._2)
  }

  def plural (count: Long, singular: String): String = {
    if (count == 1) singular else plural(singular)
  }

  /* Special case: herb (US only)
   */
  private def vowelSound(noun: String): Boolean = {
    val AllCapitals = "^([A-Z])[^a-z]*\\b.*".r
    val StartsWithNumber = "^([0-9]+).*".r
    val YSoundingU = ("^[uU](?:nicorn|niform|nilateral|nion|nique|" +
      "nison|nit\\b|unite|unity|nivers|s[aeu]|rol|rin|" +
      "kelele|ganda|krain|nanim|rethane|rugua|ter|tili).*").r
    val YSoundingE = "^[eE][uw].*".r
    val WSoundingO = "[oO]n(?:e|ce)\\b.*".r
    val SilentH = "^[hH](?:eir|ono|our|onest|ommage).*".r
    val Vowel = "^[aeiouAEIOU].*".r
    noun match {
      case AllCapitals(initial) => "AEFILMNORSX".contains(initial)
      case StartsWithNumber(n) => vowelSound(cardinal(n.toLong))
      case YSoundingU() => false
      case YSoundingE() => false
      case WSoundingO() => false
      case SilentH() => true
      case Vowel() => true
      case _ => false
    }
  }
  def one(noun: String): String =  {
    (if (vowelSound(noun)) "an" else "a") + " " + noun
  }

  def count(number: Long, singular: String) = "" + number + " " + plural(number, singular)

  def ordinal (number: Long) = {

    def digitSuffix(digit: Int): String = {
      digit match {
        case 1 => "st"
        case 2 => "nd"
        case 3 => "rd"
        case _ => "th"
      }
    }

    def suffix(number: Long): String = {
      if (number % 100 /10 == 1) "th" else digitSuffix((number % 10).toInt)
    }

    number + suffix(number)
  }

  def textOrdinal(number: Long) = ordinal(cardinal(number))

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

  def cardinal (number: Long): String = {
    val small = List("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
      "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = List("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    def ifNonZero(x: Long)(f: Long => String) = {
      if (x != 0) f(x) else ""
    }

    def andPrefix = if (options.andSeparator.isEmpty) " " else " " + options.andSeparator + " "

    def render(value: Long, withPrefix: Boolean = false): String = {
      def add(pre: String) = if (withPrefix) pre else ""

      def doPower(power: Int, name: String): String = {
        add(", ") + render(value / power) + " " + name + ifNonZero(value % power) {
          render(_, withPrefix = true)
        }
      }

      if (value < 20)
        add(andPrefix) + small(value.intValue())
      else if (value < 100)
        add(andPrefix) + tens(value.intValue() / 10) + ifNonZero(value % 10){x => "-" + small(x.intValue())}
      else if (value < 1000)
        doPower(100, "hundred")
      else if (value < 1000000)
        doPower(1000, "thousand")
      else if (value < 1000000000)
        doPower(1000000, "million")
      else
        doPower(1000000000, "billion")
    }

    if (number < 0) options.minusIndicator + " " + render(-number) else render(number)
  }

  def cardinal (number: String): String = cardinal(number.toLong)

}

object Inflect extends Inflector

class CustomInflector(override val options: Options) extends Inflector

//Java Interoperability
class Inflect {}
