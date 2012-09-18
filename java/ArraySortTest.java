package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Comparator;

public class ArraySortTest
{
    private interface ArraySorter
    {
        void sort(Integer[] a);
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

    private static void test(ArraySorter s)
    {
        Integer[] a = new Integer[0];
        s.sort(a);

        a = new Integer[] { 1 };
        s.sort(a);
        assertExpected("one", 1, a[0]);

        a = new Integer[] { 1, 2 };
        s.sort(a);
        assertExpected("two asc", new Integer[] { 1, 2 }, a);

        a = new Integer[] { 2, 1 };
        s.sort(a);
        assertExpected("two desc", new Integer[] { 1, 2 }, a);

        a = new Integer[] { 1, 1 };
        s.sort(a);
        assertExpected("two same", new Integer[] { 1, 1 }, a);

        a = new Integer[] { 1, 2, 3 };
        s.sort(a);
        assertExpected("three asc", new Integer[] { 1, 2, 3 }, a);

        a = new Integer[] { 3, 2, 1 };
        s.sort(a);
        assertExpected("three desc", new Integer[] { 1, 2, 3 }, a);

        a = new Integer[] { 1, 1, 1 };
        s.sort(a);
        assertExpected("three same", new Integer[] { 1, 1, 1 }, a);

        a = new Integer[] { 1, 2, 3, 4, 5 };
        s.sort(a);
        assertExpected("five asc", new Integer[] { 1, 2, 3, 4, 5 }, a);

        a = new Integer[] { 5, 4, 3, 2, 1 };
        s.sort(a);
        assertExpected("five desc", new Integer[] { 1, 2, 3, 4, 5 }, a);

        a = new Integer[] { 1, 1, 1, 1, 1 };
        s.sort(a);
        assertExpected("five same", new Integer[] { 1, 1, 1, 1, 1 }, a);

        a = new Integer[101];
        Integer[] a2 = new Integer[101];
        for (int i = 0; i < a.length; ++i)
        {
            a[i] = a2[i] = i + 1;
        }
        s.sort(a);
        assertExpected("odd many asc", a2, a);

        for (int i = 0; i < a.length; ++i)
        {
            a[i] = a.length - i;
        }
        s.sort(a);
        assertExpected("odd many desc", a2, a);

        a = new Integer[100];
        a2 = new Integer[100];
        for (int i = 0; i < a.length; ++i)
        {
            a[i] = a2[i] = i + 1;
        }
        s.sort(a);
        assertExpected("even many asc", a2, a);

        for (int i = 0; i < a.length; ++i)
        {
            a[i] = a.length - i;
        }
        s.sort(a);
        assertExpected("even many desc", a2, a);

        a = new Integer[101];
        boolean sorted = true;
        do
        {
            for (int i = 0; i < a.length; ++i)
            {
                a[i] = (int)(Math.random() * Integer.MAX_VALUE);
            }
            for (int i = 1; i < a.length; ++i)
            {
                if (a[i - 1] > a[i])
                {
                    sorted = false;
                }
            }
        }
        while (sorted);
        s.sort(a);
        for (int i = 1; i < a.length; ++i)
        {
            boolean condition = a[i - 1] <= a[i];
            assertTrue("random random: indices=" + (i - 1) + ", " + i + "; values=" + a[i - 1] + ", " + a[i], condition);
            if (!condition)
            {
                break;
            }
        }
    }

    @Test
    public void bubble()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.bubble(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void heap()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.heap(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void insertion()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.insertion(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void mergeBottomUp()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.mergeBottomUp(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void mergeTopDown()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.mergeTopDown(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void quick()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.quick(a, IntegerComparator.instance);
            }
        });
    }

    @Test
    public void selection()
    {
        test(new ArraySorter()
        {
            @Override
            public void sort(Integer[] a)
            {
                ArraySort.selection(a, IntegerComparator.instance);
            }
        });
    }
}