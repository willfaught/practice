package com.willfaught;

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V>
{
    @Override
    public V add(K key, V value)
    {
        if (key == null || value == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            root = new Node<K, V>(key, value);
            return null;
        }
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        Node<K, V> node = root;
        V old = null;
        while (node != null)
        {
            stack.push(node);
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                if (node.less == null)
                {
                    node.less = new Node<K, V>(key, value);
                    break;
                }
                node = node.less;
            }
            else if (c > 0)
            {
                if (node.greater == null)
                {
                    node.greater = new Node<K, V>(key, value);
                    break;
                }
                node = node.greater;
            }
            else
            {
                old = node.value;
                node.value = value;
                break;
            }
        }
        while (!stack.empty())
        {
            node = stack.pop();
            if (old == null)
            {
                ++node.size;
            }
            balance(node);
        }
        return old;
    }

    private void balance(Node<K, V> node)
    {
        int f = balanceFactor(node);
        if (Math.abs(f) <= 1)
        {
            return;
        }
        if (f == -2)
        {
            balanceGreater(node.greater);
            rotateLeft(node);
        }
        else
        {
            balanceLess(node.less);
            rotateRight(node);
        }
    }

    private int balanceFactor(Node<K, V> node)
    {
        return height(node.less) - height(node.greater);
    }

    private void balanceGreater(Node<K, V> node)
    {
        if (balanceFactor(node) == -1)
        {
            rotateLeft(node);
        }
    }

    private void balanceLess(Node<K, V> node)
    {
        if (balanceFactor(node) == -1)
        {
            rotateLeft(node);
        }
    }

    private int height(Node<K, V> node)
    {
        if (node == null)
        {
            return 0;
        }
        return Math.max(height(node.less), height(node.greater)) + 1;
    }

    @Override
    public V remove(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return null;
        }
        Stack<Node<K, V>> ancestors = new ArrayList<Node<K, V>>();
        Node<K, V> parent = null;
        Node<K, V> child = root;
        while (child != null)
        {
            int c = key.compareTo(child.key);
            if (c == 0)
            {
                break;
            }
            else
            {
                parent = child;
                ancestors.push(parent);
                if (c < 0)
                {
                    child = child.less;
                }
                else
                {
                    child = child.greater;
                }
            }
        }
        if (child != null)
        {
            V value = child.value;
            remove(parent, child);
            while (!ancestors.empty())
            {
                Node<K, V> node = ancestors.pop();
                --node.size;
                balance(node);
            }
            return value;
        }
        return null;
    }

    @Override
    protected Node<K, V> removeMaximum(Node<K, V> parent, Node<K, V> child)
    {
        Stack<Node<K, V>> ancestors = new ArrayList<Node<K, V>>();
        if (parent != null)
        {
            ancestors.push(parent);
        }
        while (child.greater != null)
        {
            parent = child;
            ancestors.push(parent);
            child = child.greater;
        }
        if (child == root)
        {
            root = root.less;
            return child;
        }
        parent.greater = child.less;
        while (!ancestors.empty())
        {
            Node<K, V> node = ancestors.pop();
            --node.size;
            balance(node);
        }
        return child;
    }

    @Override
    protected Node<K, V> removeMinimum(Node<K, V> parent, Node<K, V> child)
    {
        Stack<Node<K, V>> ancestors = new ArrayList<Node<K, V>>();
        if (parent != null)
        {
            ancestors.push(parent);
        }
        while (child.less != null)
        {
            parent = child;
            ancestors.push(parent);
            child = child.less;
        }
        if (child == root)
        {
            root = root.greater;
            return child;
        }
        parent.less = child.greater;
        while (!ancestors.empty())
        {
            Node<K, V> node = ancestors.pop();
            --node.size;
            balance(node);
        }
        return child;
    }

    private void rotateLeft(Node<K, V> parent)
    {
        Node<K, V> less = parent.less;
        Node<K, V> greater = parent.greater;
        Node<K, V> greaterLess = greater.less;
        Node<K, V> greaterGreater = greater.greater;
        K key = parent.key;
        V value = parent.value;
        parent.key = greater.key;
        parent.value = greater.value;
        greater.key = key;
        greater.value = value;
        parent.less = greater;
        parent.less.less = less;
        parent.less.greater = greaterLess;
        parent.less.size = size(parent.less.less) + size(parent.less.greater) + 1;
        parent.greater = greaterGreater;
    }

    private void rotateRight(Node<K, V> parent)
    {
        Node<K, V> greater = parent.greater;
        Node<K, V> less = parent.less;
        Node<K, V> lessLess = less.less;
        Node<K, V> lessGreater = less.greater;
        K key = parent.key;
        V value = parent.value;
        parent.key = less.key;
        parent.value = less.value;
        less.key = key;
        less.value = value;
        parent.greater = less;
        parent.greater.greater = greater;
        parent.greater.less = lessGreater;
        parent.greater.size = size(parent.greater.less) + size(parent.greater.greater) + 1;
        parent.less = lessLess;
    }
}