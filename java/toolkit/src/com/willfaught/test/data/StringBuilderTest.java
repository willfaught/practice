package com.willfaught.test.data;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.willfaught.data.StringBuilder;

public class StringBuilderTest
{
    @Test
    public void testBasic()
    {
        StringBuilder s = new StringBuilder();
        s.append("Hello");
        s.append(", ");
        s.append("world");
        s.append("!");
        assertEquals("basic", "Hello, world!", s.toString());
    }

    /*
     * @Test public void testStringBuilder() { fail("Not yet implemented"); }
     * @Test public void testStringBuilderInt() { fail("Not yet implemented"); }
     * @Test public void testAppend() { fail("Not yet implemented"); }
     * @Test public void testToString() { fail("Not yet implemented"); }
     */
}