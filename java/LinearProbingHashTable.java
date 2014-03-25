package com.willfaught;

// TODO: equals, hashCode, toString, Iterable<K>, Copyable<...>
public class LinearProbingHashTable<K, V> implements Map<K, V>
{
    private int size;
    private K[] keys;
    private V[] values;

    public LinearProbingHashTable()
    {
        this(997);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int capacity)
    {
        keys = (K[])new Object[capacity];
        values = (V[])new Object[capacity];
    }

    @Override
    public V add(K key, V value)
    {
        if (key == null || value == null)
        {
            throw new IllegalArgumentException();
        }
        if (size >= keys.length / 2)
        {
            resize(keys.length * 2);
        }
        int i;
        for (i = index(key); keys[i] != null; i = (i + 1) % keys.length)
        {
            if (key.equals(keys[i]))
            {
                V old = values[i];
                values[i] = value;
                return old;
            }
        }
        keys[i] = key;
        values[i] = value;
        ++size;
        return null;
    }

    private int index(K key)
    {
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    private void resize(int capacity)
    {
        LinearProbingHashTable<K, V> copy = new LinearProbingHashTable<K, V>(capacity);
        for (int i = 0; i < keys.length; ++i)
        {
            if (keys[i] != null)
            {
                copy.add(keys[i], values[i]);
            }
        }
        keys = copy.keys;
        values = copy.values;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < keys.length; ++i)
        {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
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
        for (int i = index(key); keys[i] != null; i = (i + 1) % keys.length)
        {
            if (key.equals(keys[i]))
            {
                return values[i];
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
        int i;
        for (i = index(key); keys[i] != null && !key.equals(keys[i]); i = (i + 1) % keys.length);
        if (keys[i] == null)
        {
            return null;
        }
        V value = values[i];
        keys[i] = null;
        values[i] = null;
        --size;
        i = (i + 1) % keys.length;
        while (keys[i] != null)
        {
            K k = keys[i];
            V v = values[i];
            keys[i] = null;
            values[i] = null;
            --size;
            add(k, v);
            i = (i + 1) % keys.length;
        }
        if (size <= keys.length / 8 && keys.length / 2 > 0)
        {
            resize(keys.length / 2);
        }
        return value;
    }

    @Override
    public int size()
    {
        return size;
    }
}