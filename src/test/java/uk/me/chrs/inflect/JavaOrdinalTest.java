package uk.me.chrs.inflect;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.me.chrs.inflect.Inflect.*;

import uk.me.chrs.inflect.Inflector;

public class JavaOrdinalTest {

  @Test
  public void ordinalsInJava() {
    assertEquals("7th", ordinal(7));
    assertEquals("twenty-first", ordinal("twenty-one"));
  }


}
