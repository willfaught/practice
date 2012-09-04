package com.willfaught;

import java.util.Iterator;

public class LinkedList<E> implements List<E>, Queue<E>, Stack<E>
{
    private static class Node<E>
    {
        public E element;
        private Node<E> previous;
        private Node<E> next;

        public Node()
        {
        }

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

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void add(E element)
    {
        Node<E> node = new Node<E>(element);
        if (head == null)
        {
            head = tail = node;
        }
        else
        {
            tail.right(node);
            tail = node;
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
        if (head == null)
        {
            head = tail = node;
        }
        else if (index == 0)
        {
            head.left(node);
            head = node;
        }
        else if (index == size)
        {
            tail.right(node);
            tail = node;
        }
        else
        {
            Node<E> n = head;
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
        for (Node<E> node = head; node != null;)
        {
            Node<E> next = node.next;
            node.previous = node.next = null;
            node.element = null;
            node = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E element)
    {
        return index(element) >= 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedList<E> copy()
    {
        LinkedList<E> copy = new LinkedList<E>();
        if (size == 0)
        {
            return copy;
        }
        copy.size = size;
        copy.head = new Node<E>(head.element);
        for (Node<E> node = head.next, previous = copy.head; node != null; node = node.next)
        {
            Node<E> copyNode = new Node<E>();
            copyNode.previous = previous;
            previous.next = copyNode;
            previous = copyNode;
            if (node.element instanceof Copyable<?>)
            {
                Copyable<E> copyable = (Copyable<E>) node.element;
                copyNode.element = copyable.copy();
            }
            else
            {
                copyNode.element = node.element;
            }
        }
        return copy;
    }

    @Override
    public E dequeue()
    {
        if (head == null)
        {
            throw new IllegalStateException();
        }
        E element = head.element;
        remove(head);
        return element;
    }

    @Override
    public boolean empty()
    {
        return head == null;
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
        if (!(object instanceof LinkedList<?>))
        {
            return false;
        }
        LinkedList<?> linkedList = (LinkedList<?>) object;
        if (size != linkedList.size)
        {
            return false;
        }
        Node<E> ourNode = head;
        Node<?> theirNode = linkedList.head;
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
        for (Node<E> node = head; node != null; node = node.next)
        {
            hash = hash * 31 + node.element.hashCode();
        }
        return hash;
    }

    @Override
    public E head()
    {
        if (head == null)
        {
            throw new IllegalStateException();
        }
        return head.element;
    }

    @Override
    public int index(E element)
    {
        if (head == null)
        {
            return -1;
        }
        int index = 0;
        for (Node<E> node = head; node != null; node = node.next)
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
            private Node<E> node = head;

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
                Node<E> next = node;
                node = node.next;
                return next.element;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Node<E> node(int index)
    {
        if (index < size / 2)
        {
            Node<E> node = head;
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
            Node<E> node = tail;
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
        if (tail == null)
        {
            throw new IllegalStateException();
        }
        E element = tail.element;
        remove(tail);
        return element;
    }

    @Override
    public void push(E element)
    {
        add(element);
    }

    @Override
    public void remove(E element)
    {
        Node<E> node = head;
        while (node != null)
        {
            if (element.equals(node.element))
            {
                remove(node);
            }
        }
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
        if (node == head)
        {
            head = head.next;
        }
        if (node == tail)
        {
            tail = tail.previous;
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
    public E top()
    {
        if (tail == null)
        {
            throw new IllegalStateException();
        }
        return tail.element;
    }

    @Override
    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        Node<E> node = head;
        while (node != null)
        {
            if (node != head)
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