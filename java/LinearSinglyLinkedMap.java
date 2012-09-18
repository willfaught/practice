package com.willfaught;

import java.util.Iterator;

public class LinearSinglyLinkedMap<K, V> implements Copyable<LinearSinglyLinkedMap<K, V>>, Iterable<K>, Map<K, V>
{
    private static class Node<K, V>
    {
        private K key;
        private V value;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object object)
        {
            if (object == this)
            {
                return true;
            }
            if (!(object instanceof Node<?, ?>))
            {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>)object;
            return key.equals(node.key) && value.equals(node.value);
        }

        @Override
        public int hashCode()
        {
            return key.hashCode() * 31 + value.hashCode();
        }
    }

    private LinearSinglyLinkedList<Node<K, V>> linearSinglyLinkedList = new LinearSinglyLinkedList<Node<K, V>>();

    @Override
    public V add(K key, V value)
    {
        if (key == null || value == null)
        {
            throw new IllegalArgumentException();
        }
        Node<K, V> node = node(key);
        if (node == null)
        {
            linearSinglyLinkedList.add(0, new Node<K, V>(key, value));
            return null;
        }
        else
        {
            V old = node.value;
            node.value = value;
            front(node);
            return old;
        }
    }

    @Override
    public void clear()
    {
        linearSinglyLinkedList.clear();
    }

    @Override
    public boolean contains(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public LinearSinglyLinkedMap<K, V> copy()
    {
        LinearSinglyLinkedMap<K, V> copy = new LinearSinglyLinkedMap<K, V>();
        copy.linearSinglyLinkedList = linearSinglyLinkedList.copy();
        return copy;
    }

    @Override
    public boolean empty()
    {
        return linearSinglyLinkedList.empty();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof LinearSinglyLinkedMap<?, ?>))
        {
            return false;
        }
        LinearSinglyLinkedMap<?, ?> linearSinglyLinkedMap = (LinearSinglyLinkedMap<?, ?>)object;
        return linearSinglyLinkedList.equals(linearSinglyLinkedMap.linearSinglyLinkedList);
    }

    private void front(Node<K, V> node)
    {
        linearSinglyLinkedList.remove(node);
        linearSinglyLinkedList.add(0, node);
    }

    @Override
    public V get(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        Node<K, V> node = node(key);
        if (node == null)
        {
            return null;
        }
        front(node);
        return node.value;
    }

    @Override
    public int hashCode()
    {
        return linearSinglyLinkedList.hashCode();
    }

    @Override
    public Iterator<K> iterator()
    {
        ArrayList<K> arrayList = new ArrayList<K>(linearSinglyLinkedList.size());
        Iterator<Node<K, V>> i = linearSinglyLinkedList.iterator();
        while (i.hasNext())
        {
            Node<K, V> node = i.next();
            arrayList.add(node.key);
        }
        return arrayList.iterator();
    }

    private Node<K, V> node(K key)
    {
        Iterator<Node<K, V>> i = linearSinglyLinkedList.iterator();
        while (i.hasNext())
        {
            Node<K, V> node = i.next();
            if (key.equals(node.key))
            {
                return node;
            }
        }
        return null;
    }

    @Override
    public V remove(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        Node<K, V> node = node(key);
        if (node == null)
        {
            return null;
        }
        linearSinglyLinkedList.remove(node);
        return node.value;
    }

    @Override
    public int size()
    {
        return linearSinglyLinkedList.size();
    }
}