package com.willfaught;

import java.util.Iterator;

public class LinearSinglyLinkedList<E> implements Copyable<LinearSinglyLinkedList<E>>, Iterable<E>, List<E>, Queue<E>, Stack<E>
{
    private static class Node<E>
    {
        private E element;
        private Node<E> next;

        public Node(E element)
        {
            this.element = element;
        }

        public Node(E element, Node<E> next)
        {
            this.element = element;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first;

    @Override
    public void add(E element)
    {
        add(size, element);
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        if (first == null)
        {
            first = new Node<E>(element);
        }
        else if (index == 0)
        {
            first = new Node<E>(element, first);
        }
        else
        {
            Node<E> node = first;
            while (index > 1)
            {
                --index;
                node = node.next;
            }
            node.next = new Node<E>(element, node.next);
        }
        ++size;
    }

    @Override
    public void clear()
    {
        Node<E> node = first;
        while (node != null)
        {
            Node<E> next = node.next;
            node.element = null;
            node.next = null;
            node = next;
        }
        first = null;
        size = 0;
    }

    @Override
    public boolean contains(E element)
    {
        return index(element) != -1;
    }

    @Override
    public LinearSinglyLinkedList<E> copy()
    {
        LinearSinglyLinkedList<E> copy = new LinearSinglyLinkedList<E>();
        Node<E> node = first;
        while (node != null)
        {
            copy.add(node.element);
            node = node.next;
        }
        return copy;
    }

    @Override
    public E dequeue()
    {
        return remove(0);
    }

    @Override
    public boolean empty()
    {
        return size == 0;
    }

    @Override
    public void enqueue(E element)
    {
        add(element);
    }

    private boolean equal(Object a, Object b)
    {
        if (a == null)
        {
            if (b == null)
            {
                return true;
            }
            return b.equals(a);
        }
        return a.equals(b);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof LinearSinglyLinkedList<?>))
        {
            return false;
        }
        LinearSinglyLinkedList<?> linearSinglyLinkedList = (LinearSinglyLinkedList<?>)object;
        if (size != linearSinglyLinkedList.size)
        {
            return false;
        }
        Node<E> node1 = first;
        Node<?> node2 = linearSinglyLinkedList.first;
        while (node1 != null && node2 != null)
        {
            if (!equal(node1.element, node2.element))
            {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1 == null && node2 == null;
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        while (index > 0)
        {
            --index;
            node = node.next;
        }
        return node.element;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        Node<E> node = first;
        while (node != null)
        {
            hash = hash * 31 + node.element.hashCode();
            node = node.next;
        }
        return hash;
    }

    @Override
    public E head()
    {
        if (first == null)
        {
            return null;
        }
        return first.element;
    }

    @Override
    public int index(E element)
    {
        Node<E> node = first;
        for (int i = 0; i < size; ++i)
        {
            if (equal(element, node.element))
            {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator()
    {
        ArrayList<E> arrayList = new ArrayList<E>(size);
        Node<E> node = first;
        while (node != null)
        {
            arrayList.add(node.element);
            node = node.next;
        }
        return arrayList.iterator();
    }

    @Override
    public Iterator<E> listed()
    {
        return iterator();
    }

    @Override
    public E pop()
    {
        return remove(size - 1);
    }

    @Override
    public void push(E element)
    {
        add(element);
    }

    @Override
    public Iterator<E> queued()
    {
        return iterator();
    }

    @Override
    public int remove(E element)
    {
        if (size == 0)
        {
            return -1;
        }
        if (equal(element, first.element))
        {
            Node<E> removed = first;
            first = first.next;
            removed.element = null;
            removed.next = null;
            removed = null;
            --size;
            return 0;
        }
        else
        {
            int index = 1;
            Node<E> node = first;
            while (node.next != null)
            {
                if (equal(element, node.next.element))
                {
                    Node<E> removed = node.next;
                    node.next = node.next.next;
                    removed.element = null;
                    removed.next = null;
                    removed = null;
                    --size;
                    return index;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> removed;
        if (index == 0)
        {
            removed = first;
            first = first.next;
        }
        else
        {
            Node<E> node = first;
            while (index > 1)
            {
                --index;
                node = node.next;
            }
            removed = node.next;
            node.next = removed.next;
        }
        E element = removed.element;
        removed.element = null;
        removed.next = null;
        removed = null;
        --size;
        return element;
    }

    @Override
    public E set(int index, E element)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        if (element == null)
        {
            throw new IllegalArgumentException();
        }
        Node<E> node = first;
        while (index > 0)
        {
            --index;
            node = node.next;
        }
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public Iterator<E> stacked()
    {
        ArrayList<E> arrayList = new ArrayList<E>(size);
        Node<E> node = first;
        while (node != null)
        {
            arrayList.push(node.element);
            node = node.next;
        }
        return arrayList.stacked();
    }

    @Override
    public E top()
    {
        return get(size - 1);
    }

    @Override
    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        for (Node<E> node = first; node != null; node = node.next)
        {
            if (node != first)
            {
                charArray.append(", ");
            }
            charArray.append(node.element.toString());
        }
        charArray.append("]");
        return charArray.toString();
    }
}