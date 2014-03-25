package com.willfaught;

import static com.willfaught.Assert.*;

public class BinaryHeapTest
{
    @Test
    public void heapMinimum()
    {
        BinaryHeap<Integer> heap1 = new BinaryHeap<Integer>();
        BinaryHeap<Integer> heap2 = new BinaryHeap<Integer>();
        heap(heap1, heap2);
        
        begin("two peek remove");
        heap1.clear();
        heap1.add(1);
        heap1.add(2);
        assertExpected("peek 1", 1, heap1.peek());
        assertExpected("remove 1", 1, heap1.remove());
        assertExpected("peek 2", 2, heap1.peek());
        assertExpected("remove 2", 2, heap1.remove());
        end();
        
        begin("many peek remove");
        heap1.clear();
        for (int i = 0; i < 100; ++i)
        {
            heap1.add((int)(Math.random() * Integer.MAX_VALUE));
        }
        int last, current;
        for (int i = 0; i < 100; ++i)
        {
            current = heap1.remove();
            if (i > 0)
            {
                last = current;
                assertTrue("less than", last <= current);
            }
        }
        end();
        
        begin("many toString");
        heap1.clear();
        heap1.add(1);
        heap1.add(2);
        assertEqual("two asc", "[1, 2]", heap1.toString());
        heap1.clear();
        heap1.add(2);
        heap1.add(1);
        assertEqual("two desc", "[1, 2]", heap1.toString());
        end();
    }
    
    @Test
    public void heapMaximum()
    {
        BinaryHeap<Integer> heap1 = new BinaryHeap<Integer>(true);
        BinaryHeap<Integer> heap2 = new BinaryHeap<Integer>(true);
        heap(heap1, heap2);
        
        begin("two peek remove");
        heap1.clear();
        heap1.add(2);
        heap1.add(1);
        assertExpected("peek 1", 2, heap1.peek());
        assertExpected("remove 1", 2, heap1.remove());
        assertExpected("peek 2", 1, heap1.peek());
        assertExpected("remove 2", 1, heap1.remove());
        end();
        
        begin("many peek remove");
        heap1.clear();
        for (int i = 0; i < 100; ++i)
        {
            heap1.add((int)(Math.random() * Integer.MAX_VALUE));
        }
        int last, current;
        for (int i = 0; i < 100; ++i)
        {
            current = heap1.remove();
            if (i > 0)
            {
                last = current;
                assertTrue("less than", last >= current);
            }
        }
        end();
        
        begin("many toString");
        heap1.clear();
        heap1.add(1);
        heap1.add(2);
        assertEqual("two asc", "[2, 1]", heap1.toString());
        heap1.clear();
        heap1.add(2);
        heap1.add(1);
        assertEqual("two desc", "[2, 1]", heap1.toString());
        end();
    }
    
    private static void heap(BinaryHeap<Integer> heap1, BinaryHeap<Integer> heap2)
    {
        begin("clear 1");
        heap1.clear();
        assertTrue("empty", heap1.empty());
        assertEqual("size", 0, heap1.size());
        end();
        
        begin("add one");
        heap1.add(1);
        assertFalse("empty", heap1.empty());
        assertEqual("size", 1, heap1.size());
        assertTrue("contains", heap1.contains(1));
        assertEqual("peek", 1, heap1.peek());
        end();
        
        begin("remove one");
        assertEqual("remove", 1, heap1.remove());
        assertTrue("empty", heap1.empty());
        assertEqual("size", 0, heap1.size());
        end();
        
        begin("clear 2");
        heap1.add(1);
        heap1.clear();
        assertTrue("empty", heap1.empty());
        assertEqual("size", 0, heap1.size());
        end();
        
        begin("zero peek");
        heap1.clear();
        try
        {
            heap1.peek();
            fail("expected IllegalStateException");
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail("expected IllegalStateException");
        }
        end();
        
        begin("zero remove");
        heap1.clear();
        try
        {
            heap1.remove();
            fail("expected IllegalStateException");
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail("expected IllegalStateException");
        }
        end();
        
        for (int i = 1; i <= 100; ++i)
        {
            begin("add many " + i);
            heap1.add(i);
            assertFalse("empty", heap1.empty());
            assertEqual("size", i, heap1.size());
            assertTrue("contains", heap1.contains(i));
            end();
        }
        
        for (int i = 1; i <= 100; ++i)
        {
            begin("remove many " + i);
            heap1.remove();
            if (i == 100)
            {
                assertTrue("empty", heap1.empty());
            }
            else
            {
                assertFalse("empty", heap1.empty());
            }
            assertEqual("size", 100 - i, heap1.size());
            end();
        }
        
        begin("equals");
        heap1.clear();
        heap2.clear();
        assertEqual("zero zero 1", heap1, heap2);
        assertEqual("zero zero 2", heap2, heap1);
        heap1.add(1);
        assertNotEqual("one zero 1", heap1, heap2);
        assertNotEqual("one zero 2", heap2, heap1);
        heap2.add(1);
        assertEqual("one one equal 1", heap1, heap2);
        assertEqual("one one equal 2", heap2, heap1);
        assertExpected("remove", 1, heap1.remove());
        heap1.add(2);
        assertNotEqual("one one not equal", heap1, heap2);
        assertNotEqual("one one not equal", heap2, heap1);
        heap1.clear();
        heap2.clear();
        heap1.add(1);
        heap1.add(2);
        heap1.add(3);
        heap2.add(1);
        heap2.add(2);
        heap2.add(3);
        assertEqual("three three equal 1", heap1, heap2);
        assertEqual("three three equal 2", heap2, heap1);
        heap1.remove();
        assertNotEqual("three three not equal 1", heap1, heap2);
        assertNotEqual("three three not equal 2", heap2, heap1);
        end();
        
        begin("hashCode");
        heap1.clear();
        heap2.clear();
        assertEqual("zero zero", heap1.hashCode(), heap2.hashCode());
        heap1.add(1);
        heap2.add(1);
        assertEqual("one one equal", heap1.hashCode(), heap2.hashCode());
        heap1.add(2);
        heap2.add(2);
        assertEqual("two two equal", heap1.hashCode(), heap2.hashCode());
        heap1.add(3);
        heap2.add(3);
        assertEqual("three three equal", heap1.hashCode(), heap2.hashCode());
        heap1.remove();
        assertTrue("three three not equal", heap1.hashCode() != heap2.hashCode());
        end();
        
        begin("toString");
        heap1.clear();
        assertEqual("zero", "[]", heap1.toString());
        heap1.add(1);
        assertEqual("one", "[1]", heap1.toString());
        end();
    }
}