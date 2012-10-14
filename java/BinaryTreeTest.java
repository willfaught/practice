package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Iterator;

public class BinaryTreeTest
{
    private static BinaryTree<Integer, Integer> b()
    {
        return b(null, null);
    }

    private static BinaryTree<Integer, Integer> b(Integer key, Integer value)
    {
        return b(key, value, null, null);
    }

    private static BinaryTree<Integer, Integer> b(Integer key, Integer value, BinaryTree<Integer, Integer> left, BinaryTree<Integer, Integer> right)
    {
        return new BinaryTree<Integer, Integer>(key, value, left, right);
    }

    @Test
    public void one()
    {
        BinaryTree<Integer, Integer> b = b(1, 2);
        assertFalse("dfs search 3 find 0", b.depthFirstSearch(3).hasNext());
        assertFalse("bfs search 3 find 0", b.breadthFirstSearch(3).hasNext());
        assertFound("dfs search 1 find 1", b.depthFirstSearch(1), new Integer[] { 2 });
        assertFound("bfs search 1 find 1", b.breadthFirstSearch(1), new Integer[] { 2 });
    }

    @Test
    public void twoLDiff()
    {
        BinaryTree<Integer, Integer> b = b(1, 2, b(2, 3), null);
        assertFalse("dfs search 4 find 0", b.depthFirstSearch(4).hasNext());
        assertFalse("bfs search 4 find 0", b.breadthFirstSearch(4).hasNext());
        assertFound("dfs search 1 find 1", b.depthFirstSearch(1), new Integer[] { 2 });
        assertFound("dfs search 2 find 1", b.depthFirstSearch(2), new Integer[] { 3 });
        assertFound("bfs search 1 find 1", b.breadthFirstSearch(1), new Integer[] { 2 });
        assertFound("bfs search 2 find 1", b.breadthFirstSearch(2), new Integer[] { 3 });
    }

    @Test
    public void twoLSame()
    {
        BinaryTree<Integer, Integer> b = b(1, 2, b(1, 3), null);
        assertFalse("dfs search 3 find 0", b.depthFirstSearch(3).hasNext());
        assertFalse("bfs search 3 find 0", b.breadthFirstSearch(3).hasNext());
        assertFound("dfs search 1 find 2", b.depthFirstSearch(1), new Integer[] { 2, 3 });
        assertFound("bfs search 1 find 2", b.breadthFirstSearch(1), new Integer[] { 2, 3 });
    }

    @Test
    public void twoRDiff()
    {
        BinaryTree<Integer, Integer> b = b(1, 2, null, b(2, 3));
        assertFalse("dfs search 3 find 0", b.depthFirstSearch(3).hasNext());
        assertFalse("bfs search 3 find 0", b.breadthFirstSearch(3).hasNext());
        assertFound("dfs search 1 find 1", b.depthFirstSearch(1), new Integer[] { 2 });
        assertFound("dfs search 2 find 1", b.depthFirstSearch(2), new Integer[] { 3 });
        assertFound("bfs search 1 find 1", b.breadthFirstSearch(1), new Integer[] { 2 });
        assertFound("bfs search 2 find 1", b.breadthFirstSearch(2), new Integer[] { 3 });
    }

    @Test
    public void twoRSame()
    {
        BinaryTree<Integer, Integer> b = b(1, 2, null, b(1, 3));
        assertFalse("dfs search 3 find 0", b.depthFirstSearch(3).hasNext());
        assertFalse("bfs search 3 find 0", b.breadthFirstSearch(3).hasNext());
        assertFound("dfs search 1 find 2", b.depthFirstSearch(1), new Integer[] { 2, 3 });
        assertFound("bfs search 1 find 2", b.breadthFirstSearch(1), new Integer[] { 2, 3 });
    }

    @Test
    public void completeFull3()
    {
        BinaryTree<Integer, Integer> b1 = b(1, 3, b(1, 4), b(1, 5));
        BinaryTree<Integer, Integer> b2 = b(1, 6, b(1, 7), b(1, 8));
        BinaryTree<Integer, Integer> b3 = b(1, 2, b1, b2);
        assertFalse("dfs search 9 find 0", b3.depthFirstSearch(9).hasNext());
        assertFalse("bfs search 9 find 0", b3.breadthFirstSearch(9).hasNext());
        assertFound("dfs search 1 find 7", b3.depthFirstSearch(1), new Integer[] { 2, 3, 4, 5, 6, 7, 8 });
        assertFound("bfs search 1 find 7", b3.breadthFirstSearch(1), new Integer[] { 2, 3, 6, 4, 5, 7, 8 });
    }

    private void assertFound(String name, Iterator<Integer> it, Integer[] in)
    {
        begin(name);

        if (in.length == 0)
        {
            assertFalse("hasNext", it.hasNext());
        }
        else
        {
            for (int i = 0; i < in.length; ++i)
            {
                assertTrue("hasNext before " + (i + 1), it.hasNext());
                if (!it.hasNext())
                {
                    break;
                }
                assertExpected("next " + (i + 1), in[i], it.next());
                assertEqual("hasNext after " + (i + 1), i != in.length - 1, it.hasNext());
            }
        }

        end();
    }

    @Test
    public void zero()
    {
        BinaryTree<Integer, Integer> b = b();
        assertFalse("dfs", b.depthFirstSearch(1).hasNext());
        assertFalse("bfs", b.breadthFirstSearch(1).hasNext());
    }
}