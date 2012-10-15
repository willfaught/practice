package com.willfaught;

import java.util.Iterator;

public class Vertex<T>
{
    public T value;
    private List<Vertex<T>> adjacent = new ArrayList<Vertex<T>>();

    public Vertex()
    {
    }

    public Vertex(T value)
    {
        this.value = value;
    }

    public Iterator<Vertex<T>> breadthFirstSearch()
    {
        ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
        SeparateChainingHashTable<Vertex<T>, Boolean> visited = new SeparateChainingHashTable<Vertex<T>, Boolean>();
        Queue<Vertex<T>> queue = new ArrayList<Vertex<T>>();
        queue.enqueue(this);
        while (!queue.empty())
        {
            Vertex<T> vertex = queue.dequeue();
            if (visited.contains(vertex))
            {
                continue;
            }
            visited.add(vertex, true);
            vertices.add(vertex);
            Iterator<Vertex<T>> i = vertex.adjacent.listed();
            while (i.hasNext())
            {
                queue.enqueue(i.next());
            }
        }
        return vertices.iterator();
    }

    public Iterator<Vertex<T>> depthFirstSearch()
    {
        ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
        SeparateChainingHashTable<Vertex<T>, Boolean> visited = new SeparateChainingHashTable<Vertex<T>, Boolean>();
        Stack<Vertex<T>> stack = new ArrayList<Vertex<T>>();
        stack.push(this);
        while (!stack.empty())
        {
            Vertex<T> vertex = stack.pop();
            if (visited.contains(vertex))
            {
                continue;
            }
            visited.add(vertex, true);
            vertices.add(vertex);
            int size = vertex.adjacent.size();
            for (int i = size - 1; i >= 0; --i)
            {
                stack.push(vertex.adjacent.get(i));
            }
        }
        return vertices.iterator();
    }

    public void edge(Vertex<T> vertex)
    {
        adjacent.add(vertex);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof Vertex<?>))
        {
            return false;
        }
        Vertex<?> v = (Vertex<?>)o;
        return value.equals(v.value) && adjacent.equals(v.adjacent);
    }

    @Override
    public int hashCode()
    {
        if (value == null)
        {
            return 0;
        }
        return value.hashCode();
    }
}