package com.willfaught.test.data;

import static org.junit.Assert.*;
import org.junit.Test;
import com.willfaught.data.LinkedList;

public class LinkedListTest
{
    @Test
    public void testLots()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        assertTrue("init empty", ll.empty());
        for (int i = 0; i < 10; ++i)
        {
            ll.addLast(i);
            assertTrue("size", ll.size() == i + 1);
            assertTrue("get", ll.get(i) == i);
            assertTrue("index", ll.index(i) == i);
            assertTrue("contains", ll.contains(i));
            assertTrue("not empty", !ll.empty());
        }
        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.addLast(ll);
        assertTrue("equals", ll.equals(ll2));
        ll.clear();
        assertTrue("clear size", ll.size() == 0);
        assertTrue("clear empty", ll.empty());
        ll = null;
        for (int i = 9; i >= 0; --i)
        {
            //int j = ll2.removeLast(i).intValue();
            int j = 0;
            assertTrue("removeLast size", ll2.size() == i);
            assertTrue("removeLast value", j == i);
            assertTrue("removeLast index", ll2.index(j) == -1);
            assertTrue("removeLast contains", !ll2.contains(j));
            if (i > 0)
            {
                assertTrue("removeLast not empty", !ll2.empty());
            }
            else
            {
                assertTrue("removeLast empty", ll2.empty());
            }
        }
        ll2.clear();
        assertTrue("clear 2 size", ll2.size() != 0);
        assertTrue("clear 2 empty", ll2.empty());
    }
    
    //@Test
    public void testHashCode()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddAfterIntE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddAfterIntLinkedListOfE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddBeforeIntE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddBeforeIntLinkedListOfE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddFirstE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddFirstLinkedListOfE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddLastE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testAddLastLinkedListOfE()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testClear()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testContains()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testEmpty()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testEqualsObject()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testGet()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testGetFirst()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testGetLast()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testIndex()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testRemove()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testRemoveFirst()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testRemoveLast()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testSet()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testSetFirst()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testSetLast()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testSize()
    {
        fail("Not yet implemented");
    }

    //@Test
    public void testToString()
    {
        fail("Not yet implemented");
    }
}