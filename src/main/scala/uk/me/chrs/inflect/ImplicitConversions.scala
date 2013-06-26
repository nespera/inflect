package uk.me.chrs.inflect

import scala.language.implicitConversions

object ImplicitConversions {

  implicit def int2ord(n: Int) = {
    class Ordinalizer(n: Int) {
      def th: String = Inflect.ordinal(n)
    }
    new Ordinalizer(n)
  }

  implicit def int2ord(n: String) = {
    class Ordinalizer(n: String) {
      def th: String = Inflect.ordinal(n)
    }
    new Ordinalizer(n)
  }

  implicit def int2card(n: Int) = {
    class Cardinalizer(n: Int) {
      def inWords: String = Inflect.cardinal(n)
    }
    new Cardinalizer(n)
  }
}
