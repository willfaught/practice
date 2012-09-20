package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Comparator;

public class ArraySearchTest
{
    private interface ArraySearcher
    {
        int search(Integer[] a, Integer i);
    }

    private static class IntegerComparator implements Comparator<Integer>
    {
        public static final IntegerComparator instance = new IntegerComparator();

        @Override
        public int compare(Integer a, Integer b)
        {
            return a.compareTo(b);
        }
    }

    private static void test(ArraySearcher s)
    {
        try
        {
            s.search(null, 1);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
        Integer[] a = new Integer[0];
        try
        {
            s.search(a, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }

        assertExpected("empty", -1, s.search(a, 1));
        a = new Integer[] { 1 };
        assertExpected("1 ? 1", 0, s.search(a, 1));
        assertExpected("1 ? 2", -1, s.search(a, 2));
        a = new Integer[] { 1, 2 };
        assertExpected("1 2 ? 1", 0, s.search(a, 1));
        assertExpected("1 2 ? 2", 1, s.search(a, 2));
        assertExpected("1 2 ? 3", -1, s.search(a, 3));
        a = new Integer[] { 1, 1 };
        assertExpected("1 1 ? 1", 1, a[s.search(a, 1)]);
        assertExpected("1 1 ? 2", -1, s.search(a, 2));
        a = new Integer[] { 1, 2, 3 };
        assertExpected("1 2 3 ? 1", 0, s.search(a, 1));
        assertExpected("1 2 3 ? 2", 1, s.search(a, 2));
        assertExpected("1 2 3 ? 3", 2, s.search(a, 3));
        assertExpected("1 2 3 ? 4", -1, s.search(a, 4));
        a = new Integer[] { 1, 1, 2 };
        assertExpected("1 1 2 ? 1", 1, a[s.search(a, 1)]);
        assertExpected("1 1 2 ? 2", 2, s.search(a, 2));
        assertExpected("1 1 2 ? 3", -1, s.search(a, 3));
        a = new Integer[] { 1, 2, 2 };
        assertExpected("1 2 2 ? 1", 0, s.search(a, 1));
        assertExpected("1 2 2 ? 2", 2, a[s.search(a, 2)]);
        assertExpected("1 2 2 ? 3", -1, s.search(a, 3));
        a = new Integer[] { 1, 1, 1 };
        assertExpected("1 1 1 ? 1", 1, a[s.search(a, 1)]);
        assertExpected("1 1 1 ? 2", -1, s.search(a, 2));

        int n = 1000;
        a = new Integer[n];
        a[0] = 0;
        for (int i = 1; i < n; ++i)
        {
            a[i] = a[i - 1] + (int)(Math.random() * 100);
        }
        for (int i = 0; i < n; ++i)
        {
            assertExpected("many match " + i, a[i], a[s.search(a, a[i])]);
        }
        assertExpected("many not found", -1, s.search(a, -2));
    }

    @Test
    public void binary()
    {
        test(new ArraySearcher()
        {
            public int search(Integer[] a, Integer i)
            {
                return ArraySearch.binary(a, i, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void linear()
    {
        test(new ArraySearcher()
        {
            public int search(Integer[] a, Integer i)
            {
                return ArraySearch.linear(a, i, IntegerComparator.instance);
            }
        });
    }
}