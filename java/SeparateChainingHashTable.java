package com.willfaught;

import java.util.Iterator;

// TODO: Iterable<K>, Copyable<SeparateChainingHashTable<K, V>>
// TODO: equals, hashCode
public class SeparateChainingHashTable<K, V> implements Map<K, V>
{
    private static class ListMap<K, V>
    {
        private static class Node<K, V>
        {
            public K key;
            public V value;
            public Node<K, V> next;

            public Node(K key, V value)
            {
                this.key = key;
                this.value = value;
            }

            public Node(K key, V value, Node<K, V> next)
            {
                this(key, value);
                this.next = next;
            }
        }

        private Node<K, V> first;

        public V add(K key, V value)
        {
            for (Node<K, V> node = first; node != null; node = node.next)
            {
                if (key.equals(node.key))
                {
                    V old = node.value;
                    node.value = value;
                    return old;
                }
            }
            first = new Node<K, V>(key, value, first);
            return null;
        }

        public void clear()
        {
            Node<K, V> node = first;
            while (node != null)
            {
                Node<K, V> next = node.next;
                node.key = null;
                node.value = null;
                node.next = null;
                node = next;
            }
            first = null;
        }

        public V get(K key)
        {
            for (Node<K, V> node = first; node != null; node = node.next)
            {
                if (key.equals(node.key))
                {
                    return node.value;
                }
            }
            return null;
        }

        public V remove(K key)
        {
            if (first == null)
            {
                return null;
            }
            if (key.equals(first.key))
            {
                V value = first.value;
                first = first.next;
                return value;
            }
            for (Node<K, V> node = first; node.next != null; node = node.next)
            {
                if (key.equals(node.next.key))
                {
                    Node<K, V> removed = node.next;
                    node.next = removed.next;
                    V value = removed.value;
                    removed.key = null;
                    removed.value = null;
                    removed.next = null;
                    return value;
                }
            }
            return null;
        }
    }

    private static final int MAXIMUM_LENGTH = 8;
    private static final int MINIMUM_LENGTH = 2;
    private int size;
    private ListMap<K, V>[] table;

    public SeparateChainingHashTable()
    {
        this(997);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity)
    {
        table = new ListMap[capacity];
    }

    @Override
    public V add(K key, V value)
    {
        if (key == null || value == null)
        {
            throw new IllegalArgumentException();
        }
        if (size / table.length > MAXIMUM_LENGTH)
        {
            resize(table.length * 2);
        }
        int index = index(key);
        ListMap<K, V> listMap = table[index];
        if (listMap == null)
        {
            listMap = table[index] = new ListMap<K, V>();
        }
        V old = listMap.add(key, value);
        if (old == null)
        {
            ++size;
        }
        return old;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < table.length; ++i)
        {
            if (table[i] != null)
            {
                table[i].clear();
                table[i] = null;
            }
        }
        size = 0;
    }

    @Override
    public boolean contains(K key)
    {
        return get(key) != null;
    }

    @Override
    public boolean empty()
    {
        return size == 0;
    }

    @Override
    public V get(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        int index = index(key);
        return table[index] == null ? null : table[index].get(key);
    }

    private int index(K key)
    {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public Iterator<K> iterator()
    {
        ArrayList<K> keys = new ArrayList<K>(size);
        for (int i = 0; i < table.length; ++i)
        {
            ListMap<K, V> listMap = table[i];
            if (listMap == null)
            {
                continue;
            }
            for (ListMap.Node<K, V> node = listMap.first; node != null; node = node.next)
            {
                keys.add(node.key);
            }
        }
        return keys.iterator();
    }

    @Override
    public V remove(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        ListMap<K, V> listMap = table[index(key)];
        if (listMap == null)
        {
            return null;
        }
        V value = listMap.remove(key);
        --size;
        if (size / table.length < MINIMUM_LENGTH && table.length / 2 >= MAXIMUM_LENGTH) // TODO: why does changing the second operand to table.length / 2 > 0 cause a bug?
        {
            resize(table.length / 2);
        }
        return value;
    }

    private void resize(int capacity)
    {
        SeparateChainingHashTable<K, V> copy = new SeparateChainingHashTable<K, V>(capacity);
        for (int i = 0; i < table.length; ++i)
        {
            if (table[i] != null)
            {
                ListMap<K, V> listMap = table[i];
                for (ListMap.Node<K, V> node = listMap.first; node != null; node = node.next)
                {
                    copy.add(node.key, node.value);
                }
            }
        }
        table = copy.table;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        boolean first = true;
        for (Iterator<K> i = iterator(); i.hasNext();)
        {
            if (!first)
            {
                charArray.append(", ");
            }
            K key = i.next();
            V value = get(key);
            charArray.append("(" + key.toString() + ", " + value.toString() + ")");
            first = false;
        }
        charArray.append("]");
        return charArray.toString();
    }
}