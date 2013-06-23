package uk.me.chrs.inflect

import scala.language.implicitConversions

object ImplicitConversions {

  implicit def int2ord(n: Int) = {
    class Ordinalizer(n: Int) {
      def th: String = Inflect.ordinal(n)
    }
    new Ordinalizer(n)
  }
}
