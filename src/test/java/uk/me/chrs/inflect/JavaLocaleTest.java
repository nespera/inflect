package uk.me.chrs.inflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaLocaleTest {

    @Test
    public void canSpecifyLocale() {
        assertEquals("one thousand and seventeen", Inflect_EN_GB.cardinal(1017));
        assertEquals("one thousand seventeen", Inflect_EN_US.cardinal(1017));
    }

}
