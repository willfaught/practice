package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Iterator;

public class VertexTest
{
    private static void assertFound(String name, Iterator<Vertex<Integer>> it, Integer[] is)
    {
        begin(name);

        for (int i = 0; i < is.length; ++i)
        {
            begin("" + i);

            assertTrue("hasNext", it.hasNext());
            if (it.hasNext())
            {
                assertExpected("next", is[i], it.next().value);
            }

            end();
        }

        assertFalse("hasNext", it.hasNext());

        end();
    }

    private static void e1(Vertex<Integer> from, Vertex<Integer> to)
    {
        from.edge(to);
    }

    private static void e2(Vertex<Integer> from, Vertex<Integer> to)
    {
        e1(from, to);
        e1(to, from);
    }

    private static Vertex<Integer> v(Integer weight)
    {
        return new Vertex<Integer>(weight);
    }

    @SuppressWarnings("unchecked")
    private static Vertex<Integer> v1(Integer weight, Object[] os)
    {
        Vertex<Integer> v = new Vertex<Integer>(weight);
        for (Object o : os)
        {
            e1(v, (Vertex<Integer>)o);
        }
        return v;
    }

    @SuppressWarnings("unchecked")
    private static Vertex<Integer> v2(Integer weight, Object[] os)
    {
        Vertex<Integer> v = new Vertex<Integer>(weight);
        for (Object o : os)
        {
            e2(v, (Vertex<Integer>)o);
        }
        return v;
    }

    @Test
    public void one()
    {
        Vertex<Integer> v = v(1);
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1 });
    }

    @Test
    public void sevenPyramid1()
    {
        Vertex<Integer> v1 = v(1), v2 = v(2), v3 = v(3), v4 = v(4), v5 = v(5), v6 = v(6), v7 = v(7);
        e1(v1, v2);
        e1(v2, v3);
        e1(v2, v4);
        e1(v1, v5);
        e1(v5, v6);
        e1(v5, v7);
        assertFound("dfs", v1.depthFirstSearch(), new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        assertFound("bfs", v1.breadthFirstSearch(), new Integer[] { 1, 2, 5, 3, 4, 6, 7 });
    }

    @Test
    public void sevenPyramid2()
    {
        Vertex<Integer> v1 = v(1), v2 = v(2), v3 = v(3), v4 = v(4), v5 = v(5), v6 = v(6), v7 = v(7);
        e2(v1, v2);
        e2(v2, v3);
        e2(v2, v4);
        e2(v1, v5);
        e2(v5, v6);
        e2(v5, v7);
        assertFound("dfs", v1.depthFirstSearch(), new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        assertFound("bfs", v1.breadthFirstSearch(), new Integer[] { 1, 2, 5, 3, 4, 6, 7 });
    }

    @Test
    public void threeChain1()
    {
        Vertex<Integer> v = v1(1, new Object[] { v1(2, new Object[] { v(3) }) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void threeChain2()
    {
        Vertex<Integer> v = v2(1, new Object[] { v2(2, new Object[] { v(3) }) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void threeCircle1()
    {
        Vertex<Integer> v1 = v(1), v2 = v(2), v3 = v(3);
        e1(v1, v2);
        e1(v2, v3);
        e1(v3, v1);
        assertFound("dfs", v1.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v1.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void threeCircle2()
    {
        Vertex<Integer> v1 = v(1), v2 = v(2), v3 = v(3);
        e2(v1, v2);
        e2(v2, v3);
        e2(v3, v1);
        assertFound("dfs", v1.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v1.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void threeOrbit1()
    {
        Vertex<Integer> v = v1(1, new Object[] { v(2), v(3) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void threeOrbit2()
    {
        Vertex<Integer> v = v2(1, new Object[] { v(2), v(3) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2, 3 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2, 3 });
    }

    @Test
    public void two1()
    {
        Vertex<Integer> v = v1(1, new Object[] { v(2) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2 });
    }

    @Test
    public void two2()
    {
        Vertex<Integer> v = v2(1, new Object[] { v(2) });
        assertFound("dfs", v.depthFirstSearch(), new Integer[] { 1, 2 });
        assertFound("bfs", v.breadthFirstSearch(), new Integer[] { 1, 2 });
    }
}