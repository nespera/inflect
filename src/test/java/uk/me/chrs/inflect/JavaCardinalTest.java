package uk.me.chrs.inflect;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static uk.me.chrs.inflect.Inflect.cardinal;

public class JavaCardinalTest {

  @Test
  public void cardinalsInJava() {
    assertEquals("seven", cardinal(7));
    assertEquals("one hundred and eighty-five", cardinal("185"));
  }


}
