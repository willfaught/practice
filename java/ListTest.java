package com.willfaught;

import static com.willfaught.Assert.*;

public class ListTest
{
    @Test
    public void arrayList()
    {
        list(new ArrayList<Integer>(), new ArrayList<Integer>());
    }
    
    @Test
    public void linkedList()
    {
        list(new LinkedList<Integer>(), new LinkedList<Integer>());
    }
    
    private static void list(List<Integer> list1, List<Integer> list2)
    {
        begin("init");
        assertTrue("empty", list1.empty());
        assertEqual("size", 0, list1.size());
        end();

        begin("empty clear");
        list1.clear();
        assertTrue("empty", list1.empty());
        assertEqual("size", 0, list1.size());
        end();
        
        begin("add");
        list1.add(1);
        list1.add(2);
        list1.add(3);
        assertFalse("empty", list1.empty());
        assertEqual("size", 3, list1.size());
        assertFalse("contains 0", list1.contains(0));
        assertTrue("contains 1", list1.contains(1));
        assertTrue("contains 2", list1.contains(2));
        assertTrue("contains 3", list1.contains(3));
        assertEqual("index 0", -1, list1.index(0));
        assertEqual("index 1", 0, list1.index(1));
        assertEqual("index 2", 1, list1.index(2));
        assertEqual("index 3", 2, list1.index(3));
        assertEqual("get 0", 1, list1.get(0));
        assertEqual("get 1", 2, list1.get(1));
        assertEqual("get 2", 3, list1.get(2));
        try
        {
            list1.get(-1);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        try
        {
            list1.get(3);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        end();
        
        begin("full clear");
        list1.clear();
        assertTrue("empty", list1.empty());
        assertEqual("size", 0, list1.size());
        end();
        
        begin("add index");
        list1.add(0, 2);
        list1.add(0, 1);
        list1.add(2, 3);
        try
        {
            list1.add(-1, 5);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        try
        {
            list1.add(4, 5);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        assertFalse("empty", list1.empty());
        assertEqual("size", 3, list1.size());
        assertTrue("contains 1", list1.contains(1));
        assertTrue("contains 2", list1.contains(2));
        assertTrue("contains 3", list1.contains(3));
        assertEqual("index 1", 0, list1.index(1));
        assertEqual("index 2", 1, list1.index(2));
        assertEqual("index 3", 2, list1.index(3));
        assertEqual("get 0", 1, list1.get(0));
        assertEqual("get 1", 2, list1.get(1));
        assertEqual("get 2", 3, list1.get(2));
        end();
        
        begin("set");
        assertEqual("set 0", 1, list1.set(0, 4));
        assertEqual("set 1", 2, list1.set(1, 5));
        assertEqual("set 2", 3, list1.set(2, 6));
        try
        {
            list1.set(-1, 7);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        try
        {
            list1.set(3, 7);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        assertFalse("empty", list1.empty());
        assertEqual("size", 3, list1.size());
        assertFalse("contains 1", list1.contains(1));
        assertFalse("contains 2", list1.contains(2));
        assertFalse("contains 3", list1.contains(3));
        assertTrue("contains 4", list1.contains(4));
        assertTrue("contains 5", list1.contains(5));
        assertTrue("contains 6", list1.contains(6));
        assertEqual("index 1", -1, list1.index(1));
        assertEqual("index 2", -1, list1.index(2));
        assertEqual("index 3", -1, list1.index(3));
        assertEqual("index 4", 0, list1.index(4));
        assertEqual("index 5", 1, list1.index(5));
        assertEqual("index 6", 2, list1.index(6));
        assertEqual("get 0", 4, list1.get(0));
        assertEqual("get 1", 5, list1.get(1));
        assertEqual("get 2", 6, list1.get(2));
        end();
        
        begin("remove");
        assertEqual("remove 2", 6, list1.remove(2).intValue());
        assertEqual("remove 1", 5, list1.remove(1).intValue());
        try
        {
            list1.remove(-1);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        assertEqual("remove 0", 4, list1.remove(0).intValue());
        try
        {
            list1.remove(0);
            fail(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (Exception e)
        {
            fail(IndexOutOfBoundsException.class, e);
        }
        assertTrue("empty", list1.empty());
        assertEqual("size", 0, list1.size());
        assertFalse("contains 4", list1.contains(4));
        assertFalse("contains 5", list1.contains(5));
        assertFalse("contains 6", list1.contains(6));
        assertEqual("index 4", -1, list1.index(4));
        assertEqual("index 5", -1, list1.index(5));
        assertEqual("index 6", -1, list1.index(6));
        end();
        
        for (int i = 0; i < 50; ++i)
        {
            begin("many add one " + i);
            list1.add(i);
            assertEqual("size", i + 1, list1.size());
            assertEqual("get", i, list1.get(i));
            assertEqual("index", i, list1.index(i));
            assertTrue("contains", list1.contains(i));
            assertFalse("empty", list1.empty());
            end();
        }
        
        for (int i = 49; i >= 0; --i)
        {
            begin("many removeLast " + i);
            int getValue = list1.get(i).intValue();
            int removeValue = list1.remove(i).intValue();
            assertEqual("remove == get", getValue, removeValue);
            assertEqual("size", i, list1.size());
            assertEqual("value", i, removeValue);
            assertEqual("index", -1, list1.index(removeValue));
            assertFalse("contains", list1.contains(removeValue));
            if (i > 0)
            {
                assertFalse("empty", list1.empty());
            }
            else
            {
                assertTrue("empty", list1.empty());
            }
            end();
        }

        begin("equals");
        list1.clear();
        list2.clear();
        assertTrue("zero zero", list1.equals(list2));
        list1.add(1);
        assertFalse("one zero", list1.equals(list2));
        list2.add(1);
        assertTrue("one one", list1.equals(list2));
        list1.add(2);
        assertFalse("two one", list1.equals(list2));
        list2.add(2);
        assertTrue("two two", list1.equals(list2));
        list2.set(1, 3);
        assertFalse("two two not equal", list1.equals(list2));
        end();
        
        begin("hashCode");
        list1.clear();
        list2.clear();
        assertEqual("zero zero", list1.hashCode(), list2.hashCode());
        list1.add(1);
        list2.add(1);
        assertEqual("one one equal", list1.hashCode(), list2.hashCode());
        list1.add(2);
        list2.add(2);
        assertEqual("two two equal", list1.hashCode(), list2.hashCode());
        list1.add(3);
        list2.add(3);
        assertEqual("three three equal", list1.hashCode(), list2.hashCode());
        list1.set(1, 4);
        assertTrue("three three not equal", list1.hashCode() != list2.hashCode());
        end();
        
        begin("toString");
        list1.clear();
        assertEqual("zero", "[]", list1.toString());
        list1.add(1);
        assertEqual("one", "[1]", list1.toString());
        list1.add(2);
        assertEqual("two", "[1, 2]", list1.toString());
        list1.add(3);
        assertEqual("three", "[1, 2, 3]", list1.toString());
        list1.remove(1);
        assertEqual("three remove", "[1, 3]", list1.toString());
        end();
    }
}