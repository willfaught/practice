package com.willfaught;

import static com.willfaught.Assert.*;

public class CollectionTest
{
    @Test
    public void linearSinglyLinkedMap()
    {
        collection(new LinearSinglyLinkedMap<Integer, Integer>(), new LinearSinglyLinkedMap<Integer, Integer>());
    }

    // TODO: move others here

    private static void collection(Collection<Integer> c1, Collection<Integer> c2)
    {
        c1.clear();
        c2.clear();
        assertExpected("size", 0, c1.size());
        assertTrue("empty", c1.empty());
        assertFalse("contains", c1.contains(1));
        assertTrue("equals self", c1.equals(c1));
        assertFalse("equals null", c1.equals(null));
        assertFalse("equals 1", c1.equals(1));
        assertTrue("equals empty", c1.equals(c2));
    }
}