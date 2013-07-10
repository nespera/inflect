package uk.me.chrs.inflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.me.chrs.inflect.Inflect_EN.*;

public class JavaBuilderTest {

    @Test
    public void canUseBuilderFromJava() {
        assertEquals("We've 0 cups and saucers", "We've " + using(0).count("cup").and(" and ").plural("saucer"));
        assertEquals("We've 1 cup and saucer", "We've " + using(1).count("cup").and(" and ").plural("saucer"));
        assertEquals("We've 8 cups and saucers", "We've " + using(8).count("cup").and(" and ").plural("saucer"));
        assertEquals("We've no cups and saucers", "We've " + using(0).some("cup").and(" and ").plural("saucer"));
        assertEquals("We've a cup and saucer", "We've " + using(1).some("cup").and(" and ").plural("saucer"));
        assertEquals("We've cups and saucers", "We've " + using(6).some("cup").and(" and ").plural("saucer"));
    }

}
