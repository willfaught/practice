package com.willfaught;

import java.util.Iterator;

public class TernarySearchTree<V> implements Iterable<String>, Map<String, V>
{
    private static class Node<V>
    {
        public char letter;
        public V value;
        public Node<V> less;
        public Node<V> equal;
        public Node<V> greater;

        public Node(char letter)
        {
            this.letter = letter;
        }
    }

    private static final char WILDCARD = '*';
    private int size;
    private Node<V> root;

    @Override
    public V add(String key, V value)
    {
        if (key == null || value == null || key.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        char c = key.charAt(0);
        if (root == null)
        {
            root = new Node<V>(c);
        }
        Node<V> node = addNextHere(root, c);
        int length = key.length();
        for (int i = 1; i < length; ++i)
        {
            c = key.charAt(i);
            node = addNextNotHere(node, c);
        }
        V old = node.value;
        node.value = value;
        if (old == null)
        {
            ++size;
        }
        return old;
    }

    private Node<V> addNextHere(Node<V> node, char c)
    {
        if (node.letter == c)
        {
            return node;
        }
        return addNextLessGreater(node, c);
    }

    private Node<V> addNextLessGreater(Node<V> node, char c)
    {
        while (node.letter != c)
        {
            if (c < node.letter)
            {
                if (node.less == null)
                {
                    node.less = new Node<V>(c);
                    return node.less;
                }
                node = node.less;
            }
            else
            {
                if (node.greater == null)
                {
                    node.greater = new Node<V>(c);
                    return node.greater;
                }
                node = node.greater;
            }
        }
        return node;
    }

    private Node<V> addNextNotHere(Node<V> node, char c)
    {
        if (node.equal == null)
        {
            node.equal = new Node<V>(c);
            return node.equal;
        }
        return addNextHere(node.equal, c);
    }

    @Override
    public void clear()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        nodes.push(root);
        while (!nodes.empty())
        {
            Node<V> node = nodes.pop();
            node.value = null;
            if (node.less != null)
            {
                nodes.push(node.less);
                node.less = null;
            }
            if (node.equal != null)
            {
                nodes.push(node.equal);
                node.equal = null;
            }
            if (node.greater != null)
            {
                nodes.push(node.greater);
                node.greater = null;
            }
        }
        size = 0;
    }

    private void collapse(Stack<Node<V>> nodes)
    {
        while (!nodes.empty())
        {
            Node<V> node = nodes.pop();
            if (node.value != null || node.less != null || node.equal != null || node.greater != null)
            {
                break;
            }
            if (nodes.empty())
            {
                root = null;
            }
            else
            {
                remove(nodes.top(), node);
            }
        }
    }

    @Override
    public boolean contains(String key)
    {
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
        if (key == null || key.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    private Node<V> getNextHere(Node<V> node, char c)
    {
        if (node.letter == c)
        {
            return node;
        }
        return getNextLessGreater(node, c);
    }

    private Node<V> getNextLessGreater(Node<V> node, char c)
    {
        while (node != null && node.letter != c)
        {
            if (c < node.letter)
            {
                node = node.less;
            }
            else
            {
                node = node.greater;
            }
        }
        return node;
    }

    private Node<V> getNextNotHere(Node<V> node, char c)
    {
        if (node.equal == null)
        {
            return null;
        }
        return getNextHere(node.equal, c);
    }

    @Override
    public Iterator<String> iterator()
    {
        return keys(root, "");
    }

    private Iterator<String> keys(Node<V> node, String prefix)
    {
        ArrayList<String> keys = new ArrayList<String>();
        if (node == null)
        {
            return keys.iterator();
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        Stack<String> prefixes = new ArrayList<String>();
        nodes.push(node);
        prefixes.push(prefix);
        while (!nodes.empty())
        {
            node = nodes.pop();
            prefix = prefixes.pop();
            if (node.greater != null)
            {
                nodes.push(node.greater);
                prefixes.push(prefix + node.letter);
            }
            if (node.equal != null)
            {
                nodes.push(node.equal);
                prefixes.push(prefix + node.letter);
            }
            if (node.less != null)
            {
                nodes.push(node.less);
                prefixes.push(prefix + node.letter);
            }
            if (node.value != null)
            {
                keys.add(prefix + node.letter);
            }
        }
        return keys.iterator();
    }

    public Iterator<String> keysThatMatch(String pattern)
    {
        if (pattern == null || pattern.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        ArrayList<String> results = new ArrayList<String>();
        if (root == null)
        {
            return results.iterator();
        }
        char c = pattern.charAt(0);
        Node<V> node = matchNextHere(root, c);
        if (node == null)
        {
            return results.iterator();
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        Stack<String> keys = new ArrayList<String>();
        Stack<Integer> indices = new ArrayList<Integer>();
        nodes.push(node);
        keys.push("" + c);
        indices.push(1);
        int length = pattern.length();
        while (!nodes.empty())
        {
            node = nodes.pop();
            String key = keys.pop();
            for (int i = indices.pop(); i < length; ++i)
            {
                c = pattern.charAt(i);
                if (c == WILDCARD)
                {
                    if (node.greater != null)
                    {
                        nodes.push(node.greater);
                        keys.push(key);
                        indices.push(i);
                    }
                    if (node.equal != null)
                    {
                        nodes.push(node.equal);
                        keys.push(key.substring(0, key.length() - 1) + node.letter);
                        indices.push(i + 1);
                    }
                    if (node.less != null)
                    {
                        nodes.push(node.less);
                        keys.push(key);
                        indices.push(i);
                    }
                    break;
                }
                node = matchNextNotHere(node, c);
                if (node == null)
                {
                    break;
                }
                key += c;
            }
            if (node != null && node.value != null)
            {
                results.add(key);
            }
        }
        return results.iterator();
    }

    public Iterator<String> keysWithPrefix(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        Node<V> node = node(prefix);
        return keys(node, prefix.substring(0, prefix.length() - 1));
    }

    public String longestPrefixOf(String string)
    {
        if (string == null || string.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return null;
        }
        Node<V> node = getNextHere(root, string.charAt(0));
        if (node == null)
        {
            return null;
        }
        int count = 1;
        int length = string.length();
        for (int i = 1; i < length; ++i)
        {
            char c = string.charAt(i);
            if (node.letter == c)
            {
                node = node.equal;
            }
            else
            {
                while (node.letter != c)
                {
                    if (c < node.letter)
                    {
                        node = node.less;
                    }
                    else
                    {
                        node = node.greater;
                    }
                }
            }
            if (node == null)
            {
                return null;
            }
            ++count;
        }
        return string.substring(0, count);
    }

    private Node<V> matchNextHere(Node<V> node, char c)
    {
        if (node.letter == c || c == WILDCARD)
        {
            return node;
        }
        return getNextLessGreater(node, c);
    }

    private Node<V> matchNextNotHere(Node<V> node, char c)
    {
        if (node.equal == null)
        {
            return null;
        }
        return getNextLessGreater(node.equal, c);
    }

    private Node<V> node(String key)
    {
        if (root == null)
        {
            return null;
        }
        Node<V> node = getNextHere(root, key.charAt(0));
        if (node == null)
        {
            return null;
        }
        int length = key.length();
        for (int i = 1; i < length; ++i)
        {
            char c = key.charAt(i);
            node = getNextNotHere(node, c);
            if (node == null)
            {
                return null;
            }
        }
        return node;
    }

    private void remove(Node<V> parent, Node<V> child)
    {
        if (parent.less == child)
        {
            parent.less = null;
        }
        else if (parent.equal == child)
        {
            parent.equal = null;
        }
        else
        {
            parent.greater = null;
        }
    }

    @Override
    public V remove(String key)
    {
        if (key == null || key.length() == 0)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return null;
        }
        Stack<Node<V>> nodes = new ArrayList<Node<V>>();
        Node<V> node = removeNextHere(nodes, root, key.charAt(0));
        if (node == null)
        {
            return null;
        }
        int length = key.length();
        for (int i = 1; i < length; ++i)
        {
            char c = key.charAt(i);
            node = removeNextNotHere(nodes, node, c);
            if (node == null)
            {
                return null;
            }
        }
        V old = node.value;
        if (old != null)
        {
            node.value = null;
            --size;
            collapse(nodes);
        }
        return old;
    }

    private Node<V> removeNextHere(Stack<Node<V>> nodes, Node<V> node, char c)
    {
        nodes.push(node);
        if (node.letter == c)
        {
            return node;
        }
        return removeNextLessGreater(nodes, node, c);
    }

    private Node<V> removeNextLessGreater(Stack<Node<V>> nodes, Node<V> node, char c)
    {
        while (node.letter != c)
        {
            if (c < node.letter)
            {
                node = node.less;
            }
            else
            {
                node = node.greater;
            }
            if (node == null)
            {
                return null;
            }
            nodes.push(node);
        }
        return node;
    }

    private Node<V> removeNextNotHere(Stack<Node<V>> nodes, Node<V> node, char c)
    {
        if (node.equal == null)
        {
            return null;
        }
        return removeNextHere(nodes, node.equal, c);
    }

    @Override
    public int size()
    {
        return size;
    }
}