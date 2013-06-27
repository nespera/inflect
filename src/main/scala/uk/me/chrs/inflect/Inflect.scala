package uk.me.chrs.inflect

trait Inflector {

  def options: Options = Options()

  val specialCases: List[(String, String)] = List(
    "bison" -> "bison",
    "buffalo" -> "buffalo",
    "deer" -> "deer",
    ".*fish" -> "$0",
    "moose" -> "moose",
    "pike" -> "pike",
    "sheep" -> "sheep",
    "salmon" -> "salmon",
    "trout" -> "trout",
    "swine" -> "swine",
    "plankton" -> "plankton",
    "squid" -> "squid",
    "aircraft" -> "aircraft",
    "watercraft" -> "watercraft",
    "spacecraft" -> "spacecraft",
    "hovercraft" -> "hovercraft")

  val rules: List[(String, String)] = List(
    "quy$" -> "quies",
    "([aeou]y)$" -> "$1s",
    "y$" -> "ies",
    "(ff|oo)$" -> "$1s",
    "ies$" -> "ies",
    "(o|s|x|ch|sh)$" -> "$1es",
    "fe?$" -> "ves",
    "z?z$" -> "zzes")

  def plural (singular: String): String = {
    val replacements = specialCases ++ rules
    val replace = replacements.find(x => x._1.r.findFirstIn(singular).isDefined).getOrElse("$" -> "s")
    singular.replaceAll(replace._1, replace._2)
  }

  def ordinal (number: BigInt) = {

    def digitSuffix(digit: Int): String = {
      digit match {
        case 1 => "st"
        case 2 => "nd"
        case 3 => "rd"
        case _ => "th"
      }
    }

    def suffix(number: BigInt): String = {
      if (number % 100 /10 == 1) "th" else digitSuffix((number % 10).toInt)
    }

    number + suffix(number)
  }

  //To keep life easy for Java
  def ordinal (number: Int): String = ordinal(BigInt(number))

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

  //To keep life easy for Java
  def cardinal (number: Int): String = cardinal(BigInt(number))

  def cardinal (number: BigInt): String = {
    val small = List("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
      "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = List("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    def ifNonZero(x: BigInt)(f: BigInt => String) = {
      if (x != 0) f(x) else ""
    }

    def andPrefix = if (options.andSeparator.isEmpty) " " else " " + options.andSeparator + " "

    def render(value: BigInt, withPrefix: Boolean = false): String = {
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

  def cardinal (number: String): String = cardinal(BigInt(number))

}

object Inflect extends Inflector

class CustomInflector(override val options: Options) extends Inflector

//Java Interoperability Classes
class Inflect {}
