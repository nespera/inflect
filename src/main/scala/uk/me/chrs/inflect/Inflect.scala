package uk.me.chrs.inflect

trait Inflector {

  def options: Options = Options()

  private def loadSpecialCases: List[(String, String)] = {
    val stream = io.Source.fromInputStream(getClass.getResourceAsStream("special-noun-plurals.txt"))
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

  def many (singular: String): String = {
    val replacements = specialCases ++ rules
    val replace = replacements.find(x => (x._1+"$").r.findFirstIn(singular).isDefined).getOrElse("$" -> "s")
    singular.replaceAll(replace._1+"$", replace._2)
  }

  def plural (singular: String)(count: Long): String = plural(count, singular)

  def plural (count: Long, singular: String): String = {
    if (count == 1) singular else many(singular)
  }

  def count(number: Long, singular: String) = join("" + number, plural(number, singular))
  def count(singular:String)(number: Long): String = count(number, singular)

  def some(singular: String)(number: Long) : String =
    some(number, singular)

  def some(number: Long, singular: String) = {
    number match {
      case 0 => join(options.nonePrefix, many(singular))
      case 1 => if (options.onePrefix == Options.defaultOne) one(singular) else join(options.onePrefix, singular)
      case _ => join(options.somePrefix, many(singular))
    }
  }

  def and(quote: String)(n: Long) = quote

  private def join(words: String*) = {
    words.filter(_.nonEmpty).mkString(" ")
  }

  protected def vowelSound(noun: String): Boolean = {
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
    join(if (vowelSound(noun)) "an" else "a", noun)
  }

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

    def splitOffLastWord(words: String): (String, String) = {
      val Split = "(.*)\\b(\\w+)$".r
      words match {
        case Split(h, t) => (h, t)
        case _ => ("", words)
      }
    }

    val patterns = List("one$" -> "first", "two$" -> "second", "three$" -> "third",
                        "([a-z]*)ve$" -> "$1fth", "eight$" -> "eighth",
                        "nine$" -> "ninth", "([a-z]*)ty$" -> "$1tieth")

    def replaceSuffix(word: String, p: List[(String, String)] = patterns): String = {
      p match {
        case x :: xs => if (word.toLowerCase.matches(x._1))
            x._1.r.replaceFirstIn(word.toLowerCase, x._2)
          else replaceSuffix(word, xs)
        case _ => word + "th"
      }
    }

    def matchCase(original: String, replaced: String): String = {
      val AllCaps = "^[A-Z]+$".r
      val Capitalized = "^[A-Z][a-z]*$".r
      original match {
        case AllCaps() => replaced.toUpperCase
        case Capitalized() => replaced.capitalize
        case _ => replaced
      }
    }

    val (head, tail) = splitOffLastWord(number)
    head + matchCase(tail, replaceSuffix(tail))
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

    if (number < 0) join(options.minusIndicator, render(-number)) else render(number)
  }

  def cardinal (number: String): String = cardinal(number.toLong)

}

object using {
  def apply(num: Long) = new InflectionBuilder(num)
}

class InflectionBuilder(num: Long) {
  def apply(num2Str: (Long => String)*) = {
    num2Str.foldLeft("")((s,f) => s + f(num))
  }
}

class CustomInflector(override val options: Options) extends Inflector

object Inflect_EN extends CustomInflector(Options())
object Inflect_EN_US extends CustomInflector(Options(and = "")) {
  override def vowelSound(noun: String): Boolean = {
    if (noun.matches("^[Hh]erb(?:\\b|al).*")) true else super.vowelSound(noun)
  }
}
object Inflect_EN_GB extends CustomInflector(Options())

//Java Interoperability
class Inflect_EN {}
class Inflect_EN_US {}
class Inflect_EN_GB {}
