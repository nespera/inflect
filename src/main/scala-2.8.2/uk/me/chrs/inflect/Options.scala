package uk.me.chrs.inflect

import scala.reflect.BeanProperty

class Options(@BeanProperty val minusIndicator : String,
@BeanProperty val andSeparator: String)

object Options {
  def apply(minusIndicator : String = "minus", andSeparator: String = "and") = new Options(minusIndicator, andSeparator)
}