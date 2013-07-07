package uk.me.chrs.inflect;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static uk.me.chrs.inflect.Inflect_EN.*;

public class JavaCardinalTest {

  @Test
  public void cardinalsInJava() {
    assertEquals("seven", cardinal(7));
    assertEquals("one hundred and eighty-five", cardinal("185"));
    assertEquals("eight hundred and ninety", cardinal(890l));
  }

  @Test
  public void canOverrideOptions() {
    Inflector inflector = new CustomInflector(new Options("negative", ""));
    assertEquals("one hundred five", inflector.cardinal(105));
    assertEquals("two thousand nine", inflector.cardinal(2009));
    assertEquals("negative twenty-six", inflector.cardinal(-26));
  }

}
