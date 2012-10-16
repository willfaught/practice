package com.willfaught;

import static com.willfaught.Assert.*;

public class CircularArrayTest
{
    private static void assertOrdered(String name, CircularArray<Integer> c, int first)
    {
        begin(name);

        int length = c.length();
        int expected = first;
        for (int i = 0; i < length; ++i)
        {
            assertExpected("for " + expected, expected, c.get(i));
            expected = (expected + 1) % length;
        }
        expected = first;
        for (Integer i : c)
        {
            assertExpected("iterator " + expected, expected, i);
            expected = (expected + 1) % length;
        }

        end();
    }

    @Test
    public void set()
    {
        CircularArray<Integer> c = new CircularArray<Integer>(5);
        int length = c.length();
        for (int i = 0; i < length; ++i)
        {
            begin("" + i);

            assertExpected("get null", null, c.get(i));
            assertExpected("set", null, c.set(i, i));
            assertExpected("get int", i, c.get(i));

            end();
        }

        for (int i = 0; i < length; ++i)
        {
            c.rotateLeft();
            assertOrdered("rotateLeft " + i, c, (i + 1) % length);
        }

        for (int i = length - 1; i >= 0; --i)
        {
            c.rotateRight();
            assertOrdered("rotateRight " + i, c, i);
        }
    }
}