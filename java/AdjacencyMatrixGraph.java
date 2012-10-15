package com.willfaught;

//TODO: isDirected, isWeighted, edges, vertices, 

public class AdjacencyMatrixGraph<W>
{
    private W[][] edges;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int size)
    {
        edges = (W[][])new Object[size][size];
    }

    public W addEdge(int from, int to, W weight)
    {
        if (!valid(from, to) || weight == null)
        {
            throw new IllegalArgumentException();
        }
        W old = edges[from][to];
        edges[from][to] = weight;
        return old;
    }

    public W removeEdge(int from, int to)
    {
        if (!valid(from, to))
        {
            throw new IllegalArgumentException();
        }
        W old = edges[from][to];
        edges[from][to] = null;
        return old;
    }

    public W getEdge(int from, int to)
    {
        if (!valid(from, to))
        {
            throw new IllegalArgumentException();
        }
        return edges[from][to];
    }

    private boolean valid(int from, int to)
    {
        return from >= 0 && from < edges.length && to >= 0 && to < edges.length;
    }
}