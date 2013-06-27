package uk.me.chrs.inflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.me.chrs.inflect.Inflect.plural;

public class JavaPluralTest {

    @Test
    public void pluralsInJava() {
        assertEquals("aircraft", plural("aircraft"));
        assertEquals("postmen", plural("postman"));
        assertEquals("skies", plural("sky"));
        assertEquals("faxes", plural("fax"));
    }

}
