package com.willfaught;

import java.util.Iterator;

public class BinaryHeap<E extends Comparable<E>> implements Collection<E>, Copyable<BinaryHeap<E>>, Iterable<E>
{
    private boolean maximum;
    private ArrayList<E> arrayList;

    public BinaryHeap()
    {
        this(false);
    }

    public BinaryHeap(boolean maximum)
    {
        this(maximum, 10);
    }

    public BinaryHeap(boolean maximum, int capacity)
    {
        this.maximum = maximum;
        arrayList = new ArrayList<E>(capacity);
    }

    public BinaryHeap(int capacity)
    {
        this(false, capacity);
    }

    public void add(E element)
    {
        arrayList.add(element);
        int child = arrayList.size() - 1;
        while (child > 0)
        {
            int parent = (child - 1) / 2;
            int comparison = arrayList.get(parent).compareTo(arrayList.get(child));
            if ((maximum && comparison >= 0) || (!maximum && comparison <= 0))
            {
                break;
            }
            swap(parent, child);
            child = parent;
        }
    }

    @Override
    public void clear()
    {
        arrayList.clear();
    }

    @Override
    public boolean contains(E element)
    {
        return arrayList.contains(element);
    }

    @Override
    public BinaryHeap<E> copy()
    {
        BinaryHeap<E> copy = new BinaryHeap<E>(maximum);
        copy.arrayList = arrayList.copy();
        return copy;
    }

    @Override
    public boolean empty()
    {
        return arrayList.size() == 0;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object == null || !(object instanceof BinaryHeap))
        {
            return false;
        }
        BinaryHeap<?> heap = (BinaryHeap<?>)object;
        return arrayList.equals(heap.arrayList);
    }

    @Override
    public int hashCode()
    {
        return arrayList.hashCode();
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<E>()
        {
            private BinaryHeap<E> heap = copy();

            @Override
            public boolean hasNext()
            {
                return !heap.empty();
            }

            @Override
            public E next()
            {
                return heap.remove();
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    public E peek()
    {
        if (arrayList.size() == 0)
        {
            throw new IllegalStateException();
        }
        return arrayList.get(0);
    }

    public E remove()
    {
        int size = arrayList.size();
        if (size == 0)
        {
            throw new IllegalStateException();
        }
        else if (size == 1)
        {
            return arrayList.remove(0);
        }
        E element = arrayList.set(0, arrayList.remove(size-- - 1));
        int parent = 0;
        while (true)
        {
            int left = parent * 2 + 1;
            if (left >= size)
            {
                break;
            }
            int right = left + 1;
            int child;
            if (right >= size)
            {
                child = left;
            }
            else
            {
                int comparison = arrayList.get(left).compareTo(arrayList.get(right));
                child = maximum ? (comparison >= 0 ? left : right) : (comparison <= 0 ? left : right);
            }
            int comparison = arrayList.get(parent).compareTo(arrayList.get(child));
            if ((maximum && comparison >= 0) || (!maximum && comparison <= 0))
            {
                break;
            }
            swap(parent, child);
            parent = child;
        }
        return element;
    }

    @Override
    public int size()
    {
        return arrayList.size();
    }

    private void swap(int i, int j)
    {
        E element = arrayList.set(i, arrayList.get(j));
        arrayList.set(j, element);
    }

    @Override
    public String toString()
    {
        return arrayList.toString();
    }
}
