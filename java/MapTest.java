package com.willfaught;

import static com.willfaught.Assert.*;

public class MapTest
{
    @Test
    public void linearProbingHashTable()
    {
        map(new LinearProbingHashTable<Integer, Integer>(), new LinearProbingHashTable<Integer, Integer>());
        map(new LinearProbingHashTable<Integer, Integer>(10), new LinearProbingHashTable<Integer, Integer>(10));
    }

    @Test
    public void linearSinglyLinkedMap()
    {
        map(new LinearSinglyLinkedMap<Integer, Integer>(), new LinearSinglyLinkedMap<Integer, Integer>());
    }

    @Test
    public void separateChainingHashTable()
    {
        map(new SeparateChainingHashTable<Integer, Integer>(), new SeparateChainingHashTable<Integer, Integer>());
        map(new SeparateChainingHashTable<Integer, Integer>(10), new SeparateChainingHashTable<Integer, Integer>(10));
    }

    private static void map(Map<Integer, Integer> m1, Map<Integer, Integer> m2)
    {
        m1.clear();
        m2.clear();

        try
        {
            m1.add(null, 1);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }

        try
        {
            m1.add(1, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }

        begin("zero");

        assertExpected("get", null, m1.get(1));
        assertExpected("remove", null, m1.remove(1));

        end();

        begin("one");

        begin("add");
        assertExpected("add", null, m1.add(1, 2));
        assertTrue("contains", m1.contains(1));
        assertFalse("empty", m1.empty());
        assertExpected("size", 1, m1.size());
        assertExpected("get", 2, m1.get(1));

        begin("replace");
        assertExpected("add", 2, m1.add(1, 3));
        assertTrue("contains", m1.contains(1));
        assertFalse("empty", m1.empty());
        assertExpected("size", 1, m1.size());
        assertExpected("get", 3, m1.get(1));
        end();

        begin("remove");
        assertExpected("remove", 3, m1.remove(1));
        assertFalse("contains", m1.contains(1));
        assertTrue("empty", m1.empty());
        assertExpected("size", 0, m1.size());
        assertExpected("get", null, m1.get(1));
        end();

        end();

        begin("add many");
        m1.clear();
        for (int i = 1; i <= 1000; ++i)
        {
            begin("add " + i + "," + (i + 1));
            assertExpected("add", null, m1.add(i, i + 1));
            assertTrue("contains", m1.contains(i));
            assertFalse("empty", m1.empty());
            assertExpected("size", i, m1.size());
            assertExpected("get", i + 1, m1.get(i));
            end();

            begin("replace " + i + "," + (i + 2));
            assertExpected("add", i + 1, m1.add(i, i + 2));
            assertTrue("contains", m1.contains(i));
            assertFalse("empty", m1.empty());
            assertExpected("size", i, m1.size());
            assertExpected("get", i + 2, m1.get(i));
            end();

            begin("remove " + i + "," + (i + 2));
            assertExpected("remove", i + 2, m1.remove(i));
            assertFalse("contains", m1.contains(i));
            assertEqual("empty", i == 1, m1.empty());
            assertExpected("size", i - 1, m1.size());
            assertExpected("get", null, m1.get(i));
            end();

            begin("add again " + i + "," + (i + 3));
            assertExpected("add", null, m1.add(i, i + 3));
            assertTrue("contains", m1.contains(i));
            assertFalse("empty", m1.empty());
            assertExpected("size", i, m1.size());
            assertExpected("get", i + 3, m1.get(i));
            end();
        }
        end();

        for (int i = 1; i <= 1000; ++i)
        {
            begin("check many " + i + "," + (i + 3));
            assertTrue("contains", m1.contains(i));
            assertExpected("get", i + 3, m1.get(i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            begin("remove many asc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m1.remove(i));
            assertFalse("contains", m1.contains(i));
            assertEqual("empty", i == 1000, m1.empty());
            assertExpected("size", 1000 - i, m1.size());
            assertExpected("get", null, m1.get(i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            m1.add(i, i + 3);
        }

        for (int i = 1000; i >= 1; --i)
        {
            begin("remove many desc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m1.remove(i));
            assertFalse("contains", m1.contains(i));
            assertEqual("empty", i == 1, m1.empty());
            assertExpected("size", i - 1, m1.size());
            assertExpected("get", null, m1.get(i));
            end();
        }

        end();

        begin("add one remove many");
        m1.clear();
        m1.add(1, 2);
        for (int i = 1; i <= 50; ++i)
        {
            m1.remove(2);
        }
        assertTrue("contains", m1.contains(1));
        assertFalse("empty", m1.empty());
        assertExpected("size", 1, m1.size());
        assertExpected("get", 2, m1.get(1));
        end();
    }
}