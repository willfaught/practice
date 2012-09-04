package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Iterator;

public class BinarySearchTreeTest
{
    @Test
    public void addClearMany()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            begin("add " + i + " " + (i + 1));
            b.add(i, i + 1);
            assertExpected("size", i, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 1, b.get(i));
            end();
        }

        begin("clear");
        b.clear();
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
        end();
    }

    @Test
    public void addClearOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();

        begin("empty");
        b.clear();
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
        end();

        begin("add");
        b.add(1, 2);
        assertExpected("size", 1, b.size());
        assertFalse("empty", b.empty());
        assertTrue("contains", b.contains(1));
        assertExpected("get", 2, b.get(1));
        end();

        begin("clear");
        b.clear();
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
        end();
    }

    @Test
    public void addNullKey()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.add(null, 2);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void addNullValue()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, null);
        assertExpected("null", null, b.get(1));
    }

    @Test
    public void addRemoveMany()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            begin("add " + i + " " + (i + 1));
            b.add(i, i + 1);
            assertExpected("size", i, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 1, b.get(i));
            end();

            begin("add overwrite " + i + " " + (i + 2));
            b.add(i, i + 2);
            assertExpected("size", i, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 2, b.get(i));
            end();
        }
        for (int i = 10; i >= 1; --i)
        {
            begin("remove " + i + " " + (i + 1));
            b.remove(i);
            assertExpected("size", i - 1, b.size());
            assertTrue("empty", i == 1 ? b.empty() : !b.empty());
            assertFalse("contains", b.contains(i));
            assertExpected("get", null, b.get(i));
            end();
        }
    }

    @Test
    public void addRemoveOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();

        begin("add");
        b.add(1, 2);
        assertExpected("size", 1, b.size());
        assertFalse("empty", b.empty());
        assertTrue("contains", b.contains(1));
        assertExpected("get", 2, b.get(1));
        end();

        begin("add overwrite");
        b.add(1, 3);
        assertExpected("size", 1, b.size());
        assertFalse("empty", b.empty());
        assertTrue("contains", b.contains(1));
        assertExpected("get", 3, b.get(1));
        end();

        begin("remove");
        b.remove(1);
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
        assertFalse("contains", b.contains(1));
        assertExpected("get", null, b.get(1));
        end();
    }

    @Test
    public void ceiling()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("empty", null, b.ceiling(1));
        b.add(2, 3);
        assertExpected("one less", 2, b.ceiling(1));
        assertExpected("one equal", 2, b.ceiling(2));
        assertExpected("one greater", null, b.ceiling(3));
        b.add(4, 5);
        assertExpected("two less", 2, b.ceiling(1));
        assertExpected("two first", 2, b.ceiling(2));
        assertExpected("two middle", 4, b.ceiling(3));
        assertExpected("two second", 4, b.ceiling(4));
        assertExpected("two greater", null, b.ceiling(5));
    }

    @Test
    public void ceilingNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.ceiling(null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void copyTest()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        BinarySearchTree<Integer, Integer> b2 = b.copy();
        assertTrue("this", b != b2);
        assertEqual("empty", b, b2);
        b.add(1, 2);
        assertNotEqual("add to b", b, b2);
        b2 = b.copy();
        assertEqual("empty", b, b2);
    }

    @Test
    public void empty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
    }

    @Test
    public void equalsTest()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertTrue("this", b.equals(b));
        assertFalse("null", b.equals(null));
        assertFalse("type", b.equals(1));
        BinarySearchTree<Integer, Integer> b2 = new BinarySearchTree<Integer, Integer>();
        assertEqual("empty", b, b2);
        b.add(1, 2);
        assertNotEqual("add 1 to b", b, b2);
        b2.add(1, 2);
        assertEqual("add 1 to b2", b, b2);
        b.add(1, 3);
        assertNotEqual("change 1 in b", b, b2);
        b2.add(1, 3);
        assertEqual("change 1 in b2", b, b2);
        b = new BinarySearchTree<Integer, Integer>();
        b2 = new BinarySearchTree<Integer, Integer>();
        b.add(2, 3);
        b.add(1, 2);
        b.add(3, 4);
        b2.add(1, 2);
        b2.add(2, 3);
        b2.add(3, 4);
        assertEqual("same diff order", b, b2);
    }

    @Test
    public void floor()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("empty", null, b.floor(1));
        b.add(2, 3);
        assertExpected("one less", null, b.floor(1));
        assertExpected("one equal", 2, b.floor(2));
        assertExpected("one greater", 2, b.floor(3));
        b.add(4, 5);
        assertExpected("two less", null, b.floor(1));
        assertExpected("two first", 2, b.floor(2));
        assertExpected("two middle", 2, b.floor(3));
        assertExpected("two second", 4, b.floor(4));
        assertExpected("two greater", 4, b.floor(5));
    }

    @Test
    public void floorNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.floor(null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void getEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("empty", null, b.get(0));
    }

    @Test
    public void getMaxEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.getMaximum();
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void getMaxMinMany()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        assertExpected("max asc", 10, b.getMaximum());
        assertExpected("min asc", 1, b.getMinimum());
        b = new BinarySearchTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        assertExpected("max desc", 10, b.getMaximum());
        assertExpected("min desc", 1, b.getMinimum());
    }

    @Test
    public void getMaxMinOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        assertEqual("max == min", b.getMaximum(), b.getMinimum());
    }

    @Test
    public void getMinEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.getMinimum();
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void getNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.get(null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void hashCodeTest()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        BinarySearchTree<Integer, Integer> b2 = new BinarySearchTree<Integer, Integer>();
        int bh = b.hashCode();
        int b2h = b2.hashCode();
        assertEqual("empty b b2", b, b2);
        assertEqual("empty h h2", bh, b2h);
        b.add(1, 2);
        int bh2 = b.hashCode();
        assertNotEqual("add one bst", b, b2);
        assertNotEqual("add one hash", bh, bh2);
        b = new BinarySearchTree<Integer, Integer>();
        bh = b.hashCode();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
            bh2 = b.hashCode();
            assertNotEqual("add many " + i, bh, bh2);
            bh = bh2;
        }
    }

    @Test
    public void inorderBothNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.inorder(null, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void inorderHighNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.inorder(0, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void inorderLowNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.inorder(null, 0);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void inorderManyReverse()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }

        begin("0 9");
        Iterator<Integer> it = b.inorder(0, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 9");
        it = b.inorder(1, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 9");
        it = b.inorder(2, 9);
        for (int i = 2; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("0 10");
        it = b.inorder(0, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 10");
        it = b.inorder(1, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 10");
        it = b.inorder(2, 10);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("0 11");
        it = b.inorder(0, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 11");
        it = b.inorder(1, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 11");
        it = b.inorder(2, 11);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();
    }

    @Test
    public void inorderManySorted()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }

        begin("0 9");
        Iterator<Integer> it = b.inorder(0, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 9");
        it = b.inorder(1, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 9");
        it = b.inorder(2, 9);
        for (int i = 2; i <= 9; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 9 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("0 10");
        it = b.inorder(0, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 10");
        it = b.inorder(1, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 10");
        it = b.inorder(2, 10);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("0 11");
        it = b.inorder(0, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("1 11");
        it = b.inorder(1, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();

        begin("2 11");
        it = b.inorder(2, 11);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        end();
    }

    @Test
    public void inorderOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);

        begin("1 1");
        Iterator<Integer> i = b.inorder(1, 1);
        assertTrue("has first", i.hasNext());
        assertExpected("first", 1, i.next());
        assertFalse("has second", i.hasNext());
        try
        {
            i.remove();
            fail(UnsupportedOperationException.class);
        }
        catch (UnsupportedOperationException e)
        {
        }
        catch (Exception e)
        {
            fail(UnsupportedOperationException.class, e);
        }
        end();

        begin("0 1");
        i = b.inorder(0, 1);
        assertTrue("has first", i.hasNext());
        assertExpected("first", 1, i.next());
        assertFalse("has second", i.hasNext());
        end();

        begin("1 2");
        i = b.inorder(1, 2);
        assertTrue("has first", i.hasNext());
        assertExpected("first", 1, i.next());
        assertFalse("has second", i.hasNext());
        end();

        begin("0 2");
        i = b.inorder(0, 2);
        assertTrue("has first", i.hasNext());
        assertExpected("first", 1, i.next());
        assertFalse("has second", i.hasNext());
        end();
    }

    @Test
    public void inorderRange()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.inorder(1, 0);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void iteratorManyReverse()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }

        Iterator<Integer> it = b.iterator();
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
    }

    @Test
    public void iteratorManySorted()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }

        Iterator<Integer> it = b.iterator();
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertEqual("has next", i == 10 ? false : true, it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
    }

    @Test
    public void iteratorOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);

        Iterator<Integer> i = b.iterator();
        assertTrue("has first", i.hasNext());
        assertExpected("first", 1, i.next());
        assertFalse("has second", i.hasNext());
        try
        {
            i.remove();
            fail(UnsupportedOperationException.class);
        }
        catch (UnsupportedOperationException e)
        {
        }
        catch (Exception e)
        {
            fail(UnsupportedOperationException.class, e);
        }
    }

    @Test
    public void postorder()
    {
        // TODO: useful for deleting bsts by freeing child nodes first?
    }

    @Test
    public void postorderBothNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.postorder(null, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void postorderHighNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.postorder(0, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void postorderLowNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.postorder(null, 0);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void preorder()
    {
        // TODO: use preorder to recreate/copy a bst?
    }

    @Test
    public void preorderBothNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.preorder(null, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void preorderHighNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.preorder(0, null);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void preorderLowNull()
    {
        try
        {
            BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
            b.preorder(null, 0);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void rankNotNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("zero", null, b.rank(1));
        b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        assertExpected("one", 0, b.rank(1));
        b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        b.add(2, 3);
        assertExpected("two first", 0, b.rank(1));
        assertExpected("two second", 1, b.rank(2));
        b = new BinarySearchTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
            assertExpected("asc rank " + i, i - 1, b.rank(i));
        }
        b = new BinarySearchTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
            assertExpected("desc rank " + i, 0, b.rank(i));
        }
    }

    @Test
    public void rankNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.rank(null);
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void remMaxEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.removeMaximum();
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void remMaxMinMany()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();

        begin("asc");
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        b.removeMaximum();
        b.removeMinimum();
        assertFalse("contains old min", b.contains(1));
        assertFalse("contains old max", b.contains(10));
        assertExpected("size", 8, b.size());
        assertExpected("get old min", null, b.get(1));
        assertExpected("get old max", null, b.get(10));
        end();

        begin("desc");
        b = new BinarySearchTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        b.removeMaximum();
        b.removeMinimum();
        assertFalse("contains old min", b.contains(1));
        assertFalse("contains old max", b.contains(10));
        assertExpected("size", 8, b.size());
        assertExpected("get old min", null, b.get(1));
        assertExpected("get old max", null, b.get(10));
        end();
    }

    @Test
    public void remMaxOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        b.removeMaximum();
        assertTrue("empty", b.empty());
        assertFalse("contains", b.contains(1));
        assertExpected("size", 0, b.size());
        assertExpected("get", null, b.get(1));
    }

    @Test
    public void remMinEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.removeMinimum();
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void remMinOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        b.removeMinimum();
        assertTrue("empty", b.empty());
        assertFalse("contains", b.contains(1));
        assertExpected("size", 0, b.size());
        assertExpected("get", null, b.get(1));
    }

    @Test
    public void removeNull()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.remove(null);
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
    }

    @Test
    public void selectEmpty()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("0", null, b.select(0));
    }

    @Test
    public void selectMany()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        b.add(2, 3);
        b.add(3, 4);
        assertExpected("0", 1, b.select(0));
        assertExpected("1", 2, b.select(1));
        assertExpected("2", 3, b.select(2));
        assertExpected("3", null, b.select(3));
    }

    @Test
    public void selectNeg()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        try
        {
            b.select(-1);
            fail(IllegalArgumentException.class);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalArgumentException.class, e);
        }
    }

    @Test
    public void selectOne()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        b.add(1, 2);
        assertExpected("0", 1, b.select(0));
        assertExpected("1", null, b.select(1));
    }

    @Test
    public void toStringTest()
    {
        BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer, Integer>();
        assertExpected("empty", "[]", b.toString());
        b.add(1, 2);
        assertExpected("1:2", "[(1, 2)]", b.toString());
        b.add(2, 3);
        b.add(3, 4);
        assertExpected("1:2,2:3,3:4", "[(1, 2), (2, 3), (3, 4)]", b.toString());
        b = new BinarySearchTree<Integer, Integer>();
        b.add(2, 3);
        b.add(1, 2);
        b.add(3, 4);
        assertExpected("1:2,2:3,3:4", "[(1, 2), (2, 3), (3, 4)]", b.toString());
    }
}