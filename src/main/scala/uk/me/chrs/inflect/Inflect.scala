package uk.me.chrs.inflect

import ch.epfl.lamp.compiler.msil.emit.ILPrinterVisitor

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

  def cardinal (number: BigInt): String = {
    val small = List("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
      "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = List("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    def ifNonZero(x: BigInt)(f: BigInt => String) = {
      if (x != 0) f(x) else ""
    }

    def positive(value: BigInt, prefix: Boolean = false): String = {
      def add(pre:String) = if (prefix) pre else ""

      def doPower(power: Int, name: String): String = {
        add(", ") + positive(value / power) + " " + name + ifNonZero(value % power) {
          positive(_, prefix = true)
        }
      }

      if (value < 20)
        add(" and ") + small(value.intValue())
      else if (value < 100)
        add(" and ") + tens(value.intValue() / 10) + ifNonZero(value % 10){x => "-" + small(x.intValue())}
      else if (value < 1000)
        doPower(100, "hundred")
      else if (value < 1000000)
        doPower(1000, "thousand")
      else if (value < 1000000000)
        doPower(1000000, "million")
      else
        doPower(1000000000, "billion")
    }

    if (number < 0) MINUS + " " + positive(-number) else positive(number)
  }
}

object Inflect extends Inflector{}
