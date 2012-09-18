package com.willfaught;

import java.util.Iterator;

public class LinearDoublyLinkedList<E> implements Copyable<LinearDoublyLinkedList<E>>, Iterable<E>, List<E>, Queue<E>, Stack<E>
{
    private static class Node<E>
    {
        public E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E element)
        {
            this.element = element;
        }

        public void left(Node<E> node)
        {
            link(previous, node);
            link(node, this);
        }

        private void link(Node<E> left, Node<E> right)
        {
            if (left != null)
            {
                left.next = right;
            }
            if (right != null)
            {
                right.previous = left;
            }
        }

        public void remove()
        {
            link(previous, next);
        }

        public void right(Node<E> node)
        {
            link(node, next);
            link(this, node);
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public void add(E element)
    {
        Node<E> node = new Node<E>(element);
        if (first == null)
        {
            first = last = node;
        }
        else
        {
            last.right(node);
            last = node;
        }
        ++size;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = new Node<E>(element);
        if (first == null)
        {
            first = last = node;
        }
        else if (index == 0)
        {
            first.left(node);
            first = node;
        }
        else if (index == size)
        {
            last.right(node);
            last = node;
        }
        else
        {
            Node<E> n = first;
            for (int i = 0; i < index; ++i)
            {
                n = n.next;
            }
            n.left(node);
        }
        ++size;
    }

    @Override
    public void clear()
    {
        for (Node<E> node = first; node != null;)
        {
            Node<E> next = node.next;
            node.previous = node.next = null;
            node.element = null;
            node = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public boolean contains(E element)
    {
        return index(element) >= 0;
    }

    @Override
    public LinearDoublyLinkedList<E> copy()
    {
        LinearDoublyLinkedList<E> copy = new LinearDoublyLinkedList<E>();
        if (size == 0)
        {
            return copy;
        }
        copy.size = size;
        copy.first = new Node<E>(first.element);
        for (Node<E> node = first.next, previous = copy.first; node != null; node = node.next)
        {
            Node<E> copyNode = new Node<E>(node.element);
            copyNode.previous = previous;
            previous.next = copyNode;
            previous = copyNode;
        }
        return copy;
    }

    @Override
    public E dequeue()
    {
        if (first == null)
        {
            throw new IllegalStateException();
        }
        E element = first.element;
        remove(first);
        return element;
    }

    @Override
    public boolean empty()
    {
        return first == null;
    }

    @Override
    public void enqueue(E element)
    {
        add(element);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof LinearDoublyLinkedList<?>))
        {
            return false;
        }
        LinearDoublyLinkedList<?> linkedList = (LinearDoublyLinkedList<?>)object;
        if (size != linkedList.size)
        {
            return false;
        }
        Node<E> ourNode = first;
        Node<?> theirNode = linkedList.first;
        while (ourNode != null && theirNode != null)
        {
            if (!ourNode.element.equals(theirNode.element))
            {
                return false;
            }
            ourNode = ourNode.next;
            theirNode = theirNode.next;
        }
        return true;
    }

    @Override
    public E get(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
        return node(index).element;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        for (Node<E> node = first; node != null; node = node.next)
        {
            hash = hash * 31 + node.element.hashCode();
        }
        return hash;
    }

    @Override
    public E head()
    {
        if (first == null)
        {
            throw new IllegalStateException();
        }
        return first.element;
    }

    @Override
    public int index(E element)
    {
        if (first == null)
        {
            return -1;
        }
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next)
        {
            if (node.element == null || element == null)
            {
                if (node.element == element)
                {
                    return index;
                }
            }
            else if (node.element.equals(element))
            {
                return index;
            }
            ++index;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<E>()
        {
            private LinearDoublyLinkedList<E> copy = copy();
            private Node<E> node = copy.first;

            @Override
            public boolean hasNext()
            {
                return node != null;
            }

            @Override
            public E next()
            {
                if (!hasNext())
                {
                    throw new IllegalStateException();
                }
                E element = node.element;
                node = node.next;
                return element;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Iterator<E> listed()
    {
        return iterator();
    }

    private Node<E> node(int index)
    {
        if (index < size / 2)
        {
            Node<E> node = first;
            int i = 0;
            while (i < index)
            {
                node = node.next;
                ++i;
            }
            return node;
        }
        else
        {
            Node<E> node = last;
            int i = size - 1;
            while (i > index)
            {
                node = node.previous;
                --i;
            }
            return node;
        }
    }

    @Override
    public E pop()
    {
        if (last == null)
        {
            throw new IllegalStateException();
        }
        E element = last.element;
        remove(last);
        return element;
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
        int index = 0;
        Node<E> node = first;
        while (node != null)
        {
            if (element.equals(node.element))
            {
                remove(node);
                return index;
            }
            ++index;
        }
        return -1;
    }

    @Override
    public E remove(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = node(index);
        remove(node);
        return node.element;
    }

    private void remove(Node<E> node)
    {
        if (node == first)
        {
            first = first.next;
        }
        if (node == last)
        {
            last = last.previous;
        }
        node.remove();
        --size;
    }

    @Override
    public E set(int index, E element)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = node(index);
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
        Node<E> node = last;
        while (node != null)
        {
            arrayList.add(node.element);
            node = node.previous;
        }
        return arrayList.iterator();
    }

    @Override
    public E top()
    {
        if (last == null)
        {
            throw new IllegalStateException();
        }
        return last.element;
    }

    @Override
    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        Node<E> node = first;
        while (node != null)
        {
            if (node != first)
            {
                charArray.append(", ");
            }
            charArray.append(node.element.toString());
            node = node.next;
        }
        charArray.append("]");
        return charArray.toString();
    }

    private boolean valid(int index)
    {
        return index >= 0 && index + 1 <= size && size > 0;
    }
}