package com.willfaught;

import java.util.Iterator;

public class CircularArray<E> implements Iterable<E>
{
    private E[] elements;
    private int first;

    public CircularArray()
    {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public CircularArray(int capacity)
    {
        elements = (E[])new Object[capacity];
    }

    public E get(int index)
    {
        if (!valid(index))
        {
            throw new IllegalArgumentException();
        }
        return elements[rotate(index)];
    }

    @Override
    public Iterator<E> iterator()
    {
        int length = elements.length;
        ArrayList<E> arrayList = new ArrayList<E>(length);
        for (int i = 0, j = first; i < length; ++i, j = (j + 1) % length)
        {
            arrayList.add(elements[j]);
        }
        return arrayList.iterator();
    }

    public int length()
    {
        return elements.length;
    }

    private int rotate(int index)
    {
        return (first + index) % elements.length;
    }

    public void rotateLeft()
    {
        first = (first + 1) % elements.length;
    }

    public void rotateRight()
    {
        if (first == 0)
        {
            first = elements.length - 1;
        }
        else
        {
            --first;
        }
    }

    public E set(int index, E element)
    {
        if (!valid(index))
        {
            throw new IllegalArgumentException();
        }
        index = rotate(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    private boolean valid(int index)
    {
        return index >= 0 && index < elements.length;
    }
}