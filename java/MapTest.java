package com.willfaught;

import static com.willfaught.Assert.*;

public class MapTest
{
    private static void testInteger(Map<Integer, Integer> m)
    {
        m.clear();

        try
        {
            m.add(null, 1);
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
            m.add(1, null);
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

        assertExpected("get", null, m.get(1));
        assertExpected("remove", null, m.remove(1));

        end();

        begin("one");

        begin("add");
        assertExpected("add", null, m.add(1, 2));
        assertTrue("contains", m.contains(1));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 2, m.get(1));

        begin("replace");
        assertExpected("add", 2, m.add(1, 3));
        assertTrue("contains", m.contains(1));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 3, m.get(1));
        end();

        begin("remove");
        assertExpected("remove", 3, m.remove(1));
        assertFalse("contains", m.contains(1));
        assertTrue("empty", m.empty());
        assertExpected("size", 0, m.size());
        assertExpected("get", null, m.get(1));
        end();

        end();

        begin("add many");
        m.clear();
        for (int i = 1; i <= 1000; ++i)
        {
            begin("add " + i + "," + (i + 1));
            assertExpected("add", null, m.add(i, i + 1));
            assertTrue("contains", m.contains(i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 1, m.get(i));
            end();

            begin("replace " + i + "," + (i + 2));
            assertExpected("add", i + 1, m.add(i, i + 2));
            assertTrue("contains", m.contains(i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 2, m.get(i));
            end();

            begin("remove " + i + "," + (i + 2));
            assertExpected("remove", i + 2, m.remove(i));
            assertFalse("contains", m.contains(i));
            assertEqual("empty", i == 1, m.empty());
            assertExpected("size", i - 1, m.size());
            assertExpected("get", null, m.get(i));
            end();

            begin("add again " + i + "," + (i + 3));
            assertExpected("add", null, m.add(i, i + 3));
            assertTrue("contains", m.contains(i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 3, m.get(i));
            end();
        }
        end();

        for (int i = 1; i <= 1000; ++i)
        {
            begin("check many " + i + "," + (i + 3));
            assertTrue("contains", m.contains(i));
            assertExpected("get", i + 3, m.get(i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            begin("remove many asc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m.remove(i));
            assertFalse("contains", m.contains(i));
            assertEqual("empty", i == 1000, m.empty());
            assertExpected("size", 1000 - i, m.size());
            assertExpected("get", null, m.get(i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            m.add(i, i + 3);
        }

        for (int i = 1000; i >= 1; --i)
        {
            begin("remove many desc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m.remove(i));
            assertFalse("contains", m.contains(i));
            assertEqual("empty", i == 1, m.empty());
            assertExpected("size", i - 1, m.size());
            assertExpected("get", null, m.get(i));
            end();
        }

        end();

        begin("add one remove many");
        m.clear();
        m.add(1, 2);
        for (int i = 1; i <= 50; ++i)
        {
            m.remove(2);
        }
        assertTrue("contains", m.contains(1));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 2, m.get(1));
        end();
    }

    private static void testString(Map<String, Integer> m)
    {
        m.clear();

        try
        {
            m.add(null, 1);
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
            m.add("1", null);
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

        assertExpected("get", null, m.get("1"));
        assertExpected("remove", null, m.remove("1"));

        end();

        begin("one");

        begin("add");
        assertExpected("add", null, m.add("1", 2));
        assertTrue("contains", m.contains("1"));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 2, m.get("1"));

        begin("replace");
        assertExpected("add", 2, m.add("1", 3));
        assertTrue("contains", m.contains("1"));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 3, m.get("1"));
        end();

        begin("remove");
        assertExpected("remove", 3, m.remove("1"));
        assertFalse("contains", m.contains("1"));
        assertTrue("empty", m.empty());
        assertExpected("size", 0, m.size());
        assertExpected("get", null, m.get("1"));
        end();

        end();

        begin("add many");
        m.clear();
        for (int i = 1; i <= 1000; ++i)
        {
            begin("add " + i + "," + (i + 1));
            assertExpected("add", null, m.add("" + i, i + 1));
            assertTrue("contains", m.contains("" + i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 1, m.get("" + i));
            end();

            begin("replace " + i + "," + (i + 2));
            assertExpected("add", i + 1, m.add("" + i, i + 2));
            assertTrue("contains", m.contains("" + i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 2, m.get("" + i));
            end();

            begin("remove " + i + "," + (i + 2));
            assertExpected("remove", i + 2, m.remove("" + i));
            assertFalse("contains", m.contains("" + i));
            assertEqual("empty", i == 1, m.empty());
            assertExpected("size", i - 1, m.size());
            assertExpected("get", null, m.get("" + i));
            end();

            begin("add again " + i + "," + (i + 3));
            assertExpected("add", null, m.add("" + i, i + 3));
            assertTrue("contains", m.contains("" + i));
            assertFalse("empty", m.empty());
            assertExpected("size", i, m.size());
            assertExpected("get", i + 3, m.get("" + i));
            end();
        }
        end();

        for (int i = 1; i <= 1000; ++i)
        {
            begin("check many " + i + "," + (i + 3));
            assertTrue("contains", m.contains("" + i));
            assertExpected("get", i + 3, m.get("" + i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            begin("remove many asc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m.remove("" + i));
            assertFalse("contains", m.contains("" + i));
            assertEqual("empty", i == 1000, m.empty());
            assertExpected("size", 1000 - i, m.size());
            assertExpected("get", null, m.get("" + i));
            end();
        }

        for (int i = 1; i <= 1000; ++i)
        {
            m.add("" + i, i + 3);
        }

        for (int i = 1000; i >= 1; --i)
        {
            begin("remove many desc " + i + "," + (i + 3));
            assertExpected("remove", i + 3, m.remove("" + i));
            assertFalse("contains", m.contains("" + i));
            assertEqual("empty", i == 1, m.empty());
            assertExpected("size", i - 1, m.size());
            assertExpected("get", null, m.get("" + i));
            end();
        }

        end();

        begin("add one remove many");
        m.clear();
        m.add("" + 1, 2);
        for (int i = 1; i <= 50; ++i)
        {
            m.remove("" + 2);
        }
        assertTrue("contains", m.contains("" + 1));
        assertFalse("empty", m.empty());
        assertExpected("size", 1, m.size());
        assertExpected("get", 2, m.get("" + 1));
        end();
    }

    @Test
    public void linearProbingHashTable()
    {
        testInteger(new LinearProbingHashTable<Integer, Integer>());
        testInteger(new LinearProbingHashTable<Integer, Integer>(10));
    }

    @Test
    public void linearSinglyLinkedMap()
    {
        testInteger(new LinearSinglyLinkedMap<Integer, Integer>());
    }

    @Test
    public void separateChainingHashTable()
    {
        testInteger(new SeparateChainingHashTable<Integer, Integer>());
        testInteger(new SeparateChainingHashTable<Integer, Integer>(10));
    }

    @Test
    public void trie()
    {
        testString(new Trie<Integer>());
    }
}