package uk.me.chrs.inflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static uk.me.chrs.inflect.Inflect.one;

public class JavaIndefiniteArticleTest {

    @Test
    public void indefiniteArticles() {
        assertEquals("an echo", one("echo"));
    }
}
