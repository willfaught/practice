package com.willfaught;

import java.util.Iterator;

public class Trie<V> implements Iterable<String>, Map<String, V>
{
    private static class Node<V>
    {
        public V value;
        @SuppressWarnings("unchecked")
        public Node<V>[] next = new Node[ALPHABET_SIZE];
    }

    private static final int ALPHABET_SIZE = 256;

    private static boolean valid(char c)
    {
        return c >= 0 && c < ALPHABET_SIZE;
    }

    private int size;

    private Node<V> root = new Node<V>();

    @Override
    public V add(String key, V value)
    {
        if (key == null || value == null)
        {
            throw new IllegalArgumentException();
        }
        Node<V> node = root;
        int length = key.length();
        for (int i = 0; i < length; ++i)
        {
            char c = key.charAt(i);
            if (!valid(c))
            {
                throw new IllegalArgumentException();
            }
            if (node.next[c] == null)
            {
                node.next[c] = new Node<V>();
            }
            node = node.next[c];
        }
        V old = node.value;
        node.value = value;
        if (old == null)
        {
            ++size;
        }
        return old;
    }

    @Override
    public void clear()
    {
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        nodes.push(root);
        while (!nodes.empty())
        {
            Node<V> node = nodes.pop();
            node.value = null;
            for (int i = 0; i < ALPHABET_SIZE; ++i)
            {
                if (node.next[i] != null)
                {
                    nodes.push(node.next[i]);
                    node.next[i] = null;
                }
            }
            node.next = null;
        }
        root = new Node<V>();
        size = 0;
    }

    private Iterator<String> collect(Node<V> node, String key)
    {
        Queue<String> results = new ArrayList<String>(size);
        if (node == null)
        {
            return results.queued();
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        Stack<String> keys = new ArrayList<String>();
        nodes.push(node);
        keys.push(key);
        while (!nodes.empty())
        {
            node = nodes.pop();
            key = keys.pop();
            if (node.value != null)
            {
                results.enqueue(key);
            }
            for (char i = ALPHABET_SIZE - 1; i >= 0; --i)
            {
                if (node.next[i] != null)
                {
                    nodes.push(node.next[i]);
                    keys.push(key + i);
                }
            }
        }
        return results.queued();
    }

    @Override
    public boolean contains(String key)
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
    public V get(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public Iterator<String> iterator()
    {
        return collect(root, "");
    }

    public Iterator<String> keysThatMatch(String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException();
        }
        Queue<String> results = new ArrayList<String>(size);
        if (root == null)
        {
            return results.queued();
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        Stack<String> keys = new ArrayList<String>();
        nodes.push(root);
        keys.push("");
        while (!nodes.empty())
        {
            Node<V> node = nodes.pop();
            String key = keys.pop();
            if (key.length() == pattern.length())
            {
                if (node.value != null)
                {
                    results.enqueue(key);
                }
                continue;
            }
            char c = pattern.charAt(key.length());
            if (!valid(c))
            {
                throw new IllegalArgumentException();
            }
            if (c == '*')
            {
                for (char i = ALPHABET_SIZE - 1; i >= 0; --i)
                {
                    if (node.next[i] != null)
                    {
                        nodes.push(node.next[i]);
                        keys.push(key + i);
                    }
                }
            }
            else if (node.next[c] != null)
            {
                nodes.push(node.next[c]);
                keys.push(key + c);
            }
        }
        return results.queued();
    }

    public Iterator<String> keysWithPrefix(String prefix)
    {
        if (prefix == null)
        {
            throw new IllegalArgumentException();
        }
        return collect(node(prefix), prefix);
    }

    public String longestPrefixOf(String string)
    {
        if (string == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return null;
        }
        int longest = -1;
        if (root.value != null)
        {
            longest = 0;
        }
        Node<V> node = root;
        int length = string.length();
        for (int i = 0; i < length && node != null; ++i)
        {
            char c = string.charAt(i);
            if (!valid(c))
            {
                throw new IllegalArgumentException();
            }
            node = node.next[c];
            if (node.value != null)
            {
                longest = i + 1;
            }
        }
        if (longest == -1)
        {
            return null;
        }
        return string.substring(0, longest);
    }

    private Node<V> node(String key)
    {
        Node<V> node = root;
        int length = key.length();
        for (int i = 0; i < length; ++i)
        {
            char c = key.charAt(i);
            if (!valid(c))
            {
                throw new IllegalArgumentException();
            }
            if (node.next[c] == null)
            {
                return null;
            }
            node = node.next[c];
        }
        return node;
    }

    @Override
    public V remove(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        Node<V> node = root;
        int length = key.length();
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        nodes.push(root);
        for (int i = 0; i < length; ++i)
        {
            char c = key.charAt(i);
            if (!valid(c))
            {
                throw new IllegalArgumentException();
            }
            if (node.next[c] == null)
            {
                return null;
            }
            node = node.next[c];
            nodes.push(node);
        }
        V value = node.value;
        if (value != null)
        {
            node.value = null;
            --size;
        }
        for (int i = length - 1; i >= 0; --i)
        {
            node = nodes.pop();
            if (node.value != null)
            {
                return value;
            }
            for (int j = 0; j < ALPHABET_SIZE; ++j)
            {
                if (node.next[j] != null)
                {
                    return value;
                }
            }
            nodes.top().next[i] = null;
        }
        return value;
    }

    @Override
    public int size()
    {
        return size;
    }
}