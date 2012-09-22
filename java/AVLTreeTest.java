package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Iterator;

public class AVLTreeTest
{
    @Test
    public void addClearMany()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();

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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        try
        {
            b.add(1, null);
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
    public void addRemoveMany()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            begin("add " + i + " " + (i + 1));
            b.add(i, i + 1);
            assertExpected("size", i, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 1, b.get(i));
            end();

            begin("overwrite during " + i + " " + (i + 2));
            b.add(i, i + 2);
            assertExpected("size", i, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 2, b.get(i));
            end();
        }
        for (int i = 1; i <= 10; ++i)
        {
            begin("overwrite after " + i + " " + (i + 3));
            b.add(i, i + 3);
            assertExpected("size", 10, b.size());
            assertFalse("empty", b.empty());
            assertTrue("contains", b.contains(i));
            assertExpected("get", i + 3, b.get(i));
            end();
        }
        for (int i = 10; i >= 1; --i)
        {
            begin(i + ", " + (i + 3));
            assertExpected("remove", i + 3, b.remove(i));
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();

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
        assertExpected("remove", 3, b.remove(1));
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
        assertFalse("contains", b.contains(1));
        assertExpected("get", null, b.get(1));
        end();
    }

    @Test
    public void ceiling()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
    public void containsNull()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.contains(null);
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

    // @Test
    // public void copyTest()
    // {
    // AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
    // AVLTree<Integer, Integer> b2 = b.copy();
    // assertTrue("object reference", b != b2);
    // assertEqual("empty", b, b2);
    // b.add(1, 2);
    // assertNotEqual("one not equal", b, b2);
    // b2 = b.copy();
    // assertEqual("one equal", b, b2);
    // }

    @Test
    public void empty()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertExpected("size", 0, b.size());
        assertTrue("empty", b.empty());
    }

    @Test
    public void equalsTest()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertTrue("this", b.equals(b));
        assertFalse("null", b.equals(null));
        assertFalse("type", b.equals(1));
        AVLTree<Integer, Integer> b2 = new AVLTree<Integer, Integer>();
        assertEqual("empty", b, b2);
        b.add(1, 2);
        assertNotEqual("add 1 to b", b, b2);
        b2.add(1, 2);
        assertEqual("add 1 to b2", b, b2);
        b.add(1, 3);
        assertNotEqual("change 1 in b", b, b2);
        b2.add(1, 3);
        assertEqual("change 1 in b2", b, b2);
        b = new AVLTree<Integer, Integer>();
        b2 = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertExpected("empty", null, b.get(0));
    }

    @Test
    public void getMaxMin()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertExpected("empty max", null, b.getMaximum());
        assertExpected("empty min", null, b.getMinimum());
        b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        assertEqual("one max min", b.getMaximum(), b.getMinimum());
        b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        assertExpected("many asc max", 10, b.getMaximum());
        assertExpected("many asc min", 1, b.getMinimum());
        b = new AVLTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        assertExpected("many desc max", 10, b.getMaximum());
        assertExpected("many desc min", 1, b.getMinimum());
    }

    @Test
    public void getNull()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        AVLTree<Integer, Integer> b2 = new AVLTree<Integer, Integer>();
        int bh = b.hashCode();
        int b2h = b2.hashCode();
        assertEqual("empty b b2", b, b2);
        assertEqual("empty h h2", bh, b2h);
        b.add(1, 2);
        int bh2 = b.hashCode();
        assertNotEqual("add one bst", b, b2);
        assertNotEqual("add one hash", bh, bh2);
        b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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

    private void inorderMany(AVLTree<Integer, Integer> b)
    {
        begin("0 9");
        Iterator<Integer> it = b.inorder(0, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("1 9");
        it = b.inorder(1, 9);
        for (int i = 1; i <= 9; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("2 9");
        it = b.inorder(2, 9);
        for (int i = 2; i <= 9; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("0 10");
        it = b.inorder(0, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("1 10");
        it = b.inorder(1, 10);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("2 10");
        it = b.inorder(2, 10);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("0 11");
        it = b.inorder(0, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("1 11");
        it = b.inorder(1, 11);
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();

        begin("2 11");
        it = b.inorder(2, 11);
        for (int i = 2; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
        end();
    }

    @Test
    public void inorderManyAsc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        inorderMany(b);
    }

    @Test
    public void inorderManyDesc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        inorderMany(b);
    }

    @Test
    public void inorderOne()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
    public void inorderZero()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        Iterator<Integer> i = b.iterator();
        assertFalse("hasNext", i.hasNext());
        try
        {
            i.next();
            fail(IllegalStateException.class);
        }
        catch (IllegalStateException e)
        {
        }
        catch (Exception e)
        {
            fail(IllegalStateException.class, e);
        }
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

    private void iteratorMany(AVLTree<Integer, Integer> b)
    {
        Iterator<Integer> it = b.iterator();
        for (int i = 1; i <= 10; ++i)
        {
            begin("" + i);
            assertTrue("has next", it.hasNext());
            assertExpected("next", i, it.next());
            end();
        }
        assertFalse("last has next", it.hasNext());
    }

    @Test
    public void iteratorManyAsc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        iteratorMany(b);
    }

    @Test
    public void iteratorManyDesc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        iteratorMany(b);
    }

    @Test
    public void iteratorOne()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
    public void rankEmpty()
    {
        try
        {
            AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
            b.rank(1);
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        assertExpected("one", 0, b.rank(1));
        b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        b.add(2, 3);
        assertExpected("two first", 0, b.rank(1));
        assertExpected("two second", 1, b.rank(2));
        b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
            assertExpected("asc rank " + i, i - 1, b.rank(i));
        }
        b = new AVLTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
            assertExpected("desc rank " + i, 0, b.rank(i));
        }
    }

    @Test
    public void rankNull()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.rank(null);
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
    public void remMaxMinEmpty()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.removeMaximum();
        b.removeMinimum();
        assertTrue("empty", b.empty());
        assertExpected("size", 0, b.size());
    }

    private void remMaxMinMany(AVLTree<Integer, Integer> b)
    {
        assertExpected("get max", 10, b.getMaximum());
        assertExpected("get min", 1, b.getMinimum());
        b.removeMaximum();
        b.removeMinimum();
        assertFalse("contains old max", b.contains(10));
        assertFalse("contains old min", b.contains(1));
        assertExpected("size", 8, b.size());
        assertExpected("get old max", null, b.get(10));
        assertExpected("get old min", null, b.get(1));
    }

    @Test
    public void remMaxMinManyAsc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            b.add(i, i + 1);
        }
        remMaxMinMany(b);
    }

    @Test
    public void remMaxMinManyDesc()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        for (int i = 10; i >= 1; --i)
        {
            b.add(i, i + 1);
        }
        remMaxMinMany(b);
    }

    @Test
    public void remMaxOne()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        b.removeMaximum();
        assertTrue("empty", b.empty());
        assertFalse("contains", b.contains(1));
        assertExpected("size", 0, b.size());
        assertExpected("get", null, b.get(1));
    }

    @Test
    public void remMinOne()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        try
        {
            b.remove(null);
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
    public void selectEmpty()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertExpected("0", null, b.select(0));
    }

    @Test
    public void selectMany()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
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
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.add(1, 2);
        assertExpected("0", 1, b.select(0));
        assertExpected("1", null, b.select(1));
    }

    @Test
    public void toStringTest()
    {
        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        assertExpected("empty", "[]", b.toString());
        b.add(1, 2);
        assertExpected("1:2", "[(1, 2)]", b.toString());
        b.add(2, 3);
        b.add(3, 4);
        assertExpected("1:2,2:3,3:4", "[(1, 2), (2, 3), (3, 4)]", b.toString());
        b = new AVLTree<Integer, Integer>();
        b.add(2, 3);
        b.add(1, 2);
        b.add(3, 4);
        assertExpected("1:2,2:3,3:4", "[(1, 2), (2, 3), (3, 4)]", b.toString());
    }
}