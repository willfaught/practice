package com.willfaught;

import java.util.Iterator;

public class BinarySearchTree<K extends Comparable<K>, V> implements SearchTree<K, V>
{
    private static class Node<K, V>
    {
        public K key;
        public V value;
        public Node<K, V> less;
        public Node<K, V> greater;
        public int size = 1;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;

    @Override
    public void add(K key, V value)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            root = new Node<K, V>(key, value);
            return;
        }
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        Node<K, V> node = root;
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
                node.value = value;
                return;
            }
        }
        while (!stack.empty())
        {
            node = stack.pop();
            ++node.size;
        }
    }

    @Override
    public K ceiling(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return key(ceiling(root, key));
    }

    public Node<K, V> ceiling(Node<K, V> node, K key)
    {
        Node<K, V> best = null;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c == 0)
            {
                return node;
            }
            else if (c > 0)
            {
                node = node.greater;
            }
            else
            {
                best = node;
                node = node.less;
            }
        }
        return best;
    }

    @Override
    public void clear()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        stack.push(root);
        root = null;
        while (!stack.empty())
        {
            Node<K, V> node = stack.pop();
            if (node.less != null)
            {
                stack.push(node.less);
            }
            if (node.greater != null)
            {
                stack.push(node.greater);
            }
            node.less = node.greater = null;
        }
    }

    @Override
    public boolean contains(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return get(root, key) != null;
    }

    @Override
    public BinarySearchTree<K, V> copy() // TODO: use preorder to build it up,
                                         // otherwise worst case perf
    {
        BinarySearchTree<K, V> bst = new BinarySearchTree<K, V>();
        Iterator<K> iterator = iterator();
        while (iterator.hasNext())
        {
            K key = iterator.next();
            V value = get(key);
            bst.add(key, value);
        }
        return bst;
    }

    @Override
    public boolean empty()
    {
        return root == null;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof BinarySearchTree<?, ?>))
        {
            return false;
        }
        BinarySearchTree<?, ?> bst = (BinarySearchTree<?, ?>)object;
        if (root == null && bst.root == null)
        {
            return true;
        }
        if (root == null || bst.root == null || root.size != bst.root.size)
        {
            return false;
        }
        ArrayList<Node<K, V>> ordered1 = new ArrayList<Node<K, V>>();
        ArrayList<Node<?, ?>> ordered2 = new ArrayList<Node<?, ?>>();
        Stack<Node<K, V>> nodes1 = new ArrayList<Node<K, V>>(root.size);
        Stack<Node<?, ?>> nodes2 = new ArrayList<Node<?, ?>>(root.size);
        Node<K, V> node1 = root;
        Node<?, ?> node2 = bst.root;
        while (node1 != null)
        {
            nodes1.push(node1);
            node1 = node1.less;
        }
        while (!nodes1.empty())
        {
            node1 = nodes1.pop();
            ordered1.add(node1);
            node1 = node1.greater;
            while (node1 != null)
            {
                nodes1.push(node1);
                node1 = node1.less;
            }
        }
        while (node2 != null)
        {
            nodes2.push(node2);
            node2 = node2.less;
        }
        while (!nodes2.empty())
        {
            node2 = nodes2.pop();
            ordered2.add(node2);
            node2 = node2.greater;
            while (node2 != null)
            {
                nodes2.push(node2);
                node2 = node2.less;
            }
        }
        Iterator<Node<K, V>> i1 = ordered1.iterator();
        Iterator<Node<?, ?>> i2 = ordered2.iterator();
        while (i1.hasNext() && i2.hasNext())
        {
            node1 = i1.next();
            node2 = i2.next();
            if (!node1.key.equals(node2.key) || !node1.value.equals(node2.value))
            {
                return false;
            }
        }
        return !i1.hasNext() && !i2.hasNext();
    }

    @Override
    public K floor(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return key(floor(root, key));
    }

    private Node<K, V> floor(Node<K, V> node, K key)
    {
        Node<K, V> best = null;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c == 0)
            {
                return node;
            }
            else if (c < 0)
            {
                node = node.less;
            }
            else
            {
                best = node;
                node = node.greater;
            }
        }
        return best;
    }

    @Override
    public V get(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        Node<K, V> node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> get(Node<K, V> node, K key)
    {
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                node = node.less;
            }
            else if (c > 0)
            {
                node = node.greater;
            }
            else
            {
                return node;
            }
        }
        return null;
    }

    @Override
    public K getMaximum()
    {
        if (root == null)
        {
            return null;
        }
        return getMaximum(root).key;
    }

    private Node<K, V> getMaximum(Node<K, V> node)
    {
        while (node.greater != null)
        {
            node = node.greater;
        }
        return node;
    }

    @Override
    public K getMinimum()
    {
        if (root == null)
        {
            return null;
        }
        return getMinimum(root).key;
    }

    private Node<K, V> getMinimum(Node<K, V> node)
    {
        while (node.less != null)
        {
            node = node.less;
        }
        return node;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        Iterator<K> iterator = iterator();
        while (iterator.hasNext())
        {
            K key = iterator.next();
            V value = get(key);
            hash = hash * 31 + key.hashCode();
            hash = hash * 31 + value.hashCode();
        }
        return hash;
    }

    // Useful for determining equality.
    @Override
    public Iterator<K> inorder(K low, K high)
    {
        if (low == null || high == null)
        {
            throw new IllegalArgumentException();
        }
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return new ArrayList<K>().iterator();
        }
        ArrayList<K> keys = new ArrayList<K>(root.size);
        Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>(root.size);
        Node<K, V> node = root;
        while (node != null)
        {
            nodes.push(node);
            node = node.less;
        }
        while (!nodes.empty())
        {
            node = nodes.pop();
            if (low.compareTo(node.key) <= 0 && high.compareTo(node.key) >= 0)
            {
                keys.add(node.key);
            }
            node = node.greater;
            while (node != null)
            {
                nodes.push(node);
                node = node.less;
            }
        }
        return keys.iterator();
    }

    @Override
    public Iterator<K> iterator()
    {
        if (root != null)
        {
            return inorder(getMinimum(), getMaximum());
        }
        return new Iterator<K>()
        {
            @Override
            public boolean hasNext()
            {
                return false;
            }

            @Override
            public K next()
            {
                throw new IllegalStateException();
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    private K key(Node<K, V> node)
    {
        return node == null ? null : node.key;
    }

    // Useful for postfix syntax conversion and freeing memory.
    @Override
    public Iterator<K> postorder(K low, K high)
    {
        if (low == null || high == null)
        {
            throw new IllegalArgumentException();
        }
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return new ArrayList<K>().iterator();
        }
        ArrayList<K> keys = new ArrayList<K>(root.size);
        Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>();
        Node<K, V> node = root;
        while (node != null)
        {
            nodes.push(node);
            if (node.less != null)
            {
                node = node.less;
            }
            else
            {
                node = node.greater;
            }
        }
        while (!nodes.empty())
        {
            node = nodes.pop();
            if (low.compareTo(node.key) <= 0 && high.compareTo(node.key) >= 0)
            {
                keys.add(node.key);
            }
            if (!nodes.empty())
            {
                Node<K, V> parent = nodes.top();
                if (node == parent.less)
                {
                    node = parent.greater;
                    while (node != null)
                    {
                        nodes.push(node);
                        if (node.less != null)
                        {
                            node = node.less;
                        }
                        else
                        {
                            node = node.greater;
                        }
                    }
                }
            }
        }
        return keys.iterator();
    }

    // Useful for allocating memory.
    @Override
    public Iterator<K> preorder(K low, K high)
    {
        if (low == null || high == null)
        {
            throw new IllegalArgumentException();
        }
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return new ArrayList<K>().iterator();
        }
        ArrayList<K> keys = new ArrayList<K>(root.size);
        Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>();
        nodes.push(root);
        while (!nodes.empty())
        {
            Node<K, V> node = nodes.pop();
            if (low.compareTo(node.key) <= 0 && high.compareTo(node.key) >= 0)
            {
                keys.add(node.key);
            }
            if (node.greater != null)
            {
                nodes.push(node.greater);
            }
            if (node.less != null)
            {
                nodes.push(node.less);
            }
        }
        return keys.iterator();
    }

    @Override
    public int rank(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        int rank = 0;
        Node<K, V> node = root;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                node = node.less;
            }
            else if (c > 0)
            {
                rank += size(node.less) + 1;
                node = node.greater;
            }
            else
            {
                return rank + size(node.less);
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void remove(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return;
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
            if (child == root)
            {
                root = null;
            }
            else
            {
                remove(parent, child);
                while (!ancestors.empty())
                {
                    --ancestors.pop().size;
                }
            }
        }
    }

    private void remove(Node<K, V> parent, Node<K, V> child)
    {
        if (child.less == null)
        {
            if (child == root)
            {
                root = root.greater;
            }
            else
            {
                replace(parent, child, child.greater);
            }
        }
        else if (child.greater == null)
        {
            if (child == root)
            {
                root = root.less;
            }
            else
            {
                replace(parent, child, child.less);
            }
        }
        else
        {
            Node<K, V> successor = removeMinimum(child, child.greater);
            successor.less = child.less;
            successor.greater = child.greater;
            successor.size = size(successor.less) + size(successor.greater) + 1;
            replace(parent, child, successor);
        }
    }

    @Override
    public void removeMaximum()
    {
        if (root != null)
        {
            removeMaximum(null, root);
        }
    }

    private Node<K, V> removeMaximum(Node<K, V> parent, Node<K, V> child)
    {
        while (child.greater != null)
        {
            parent = child;
            --parent.size;
            child = child.greater;
        }
        if (child == root)
        {
            root = root.less;
        }
        else
        {
            parent.greater = child.less;
        }
        return child;
    }

    @Override
    public void removeMinimum()
    {
        if (root != null)
        {
            removeMinimum(null, root);
        }
    }

    private Node<K, V> removeMinimum(Node<K, V> parent, Node<K, V> child)
    {
        while (child.less != null)
        {
            parent = child;
            --parent.size;
            child = child.less;
        }
        if (child == root)
        {
            root = root.greater;
        }
        else
        {
            parent.less = child.greater;
        }
        return child;
    }

    // TODO: use where parent.less/greater used
    private void replace(Node<K, V> parent, Node<K, V> oldChild, Node<K, V> newChild)
    {
        if (parent.less == oldChild)
        {
            parent.less = newChild;
        }
        else
        {
            parent.greater = newChild;
        }
    }

    @Override
    public K select(int rank)
    {
        if (rank < 0)
        {
            throw new IllegalArgumentException();
        }
        Node<K, V> node = root;
        while (node != null)
        {
            int size = size(node.less);
            if (rank < size)
            {
                node = node.less;
            }
            else if (rank > size)
            {
                rank -= size + 1;
                node = node.greater;
            }
            else
            {
                return node.key;
            }
        }
        return null;
    }

    @Override
    public int size()
    {
        return size(root);
    }

    private int size(Node<K, V> node)
    {
        return node == null ? 0 : node.size;
    }

    @Override
    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        Iterator<K> iterator = iterator();
        boolean first = true;
        while (iterator.hasNext())
        {
            if (!first)
            {
                charArray.append(", ");
            }
            K key = iterator.next();
            V value = get(key);
            charArray.append("(" + key + ", " + value + ")");
            first = false;
        }
        charArray.append("]");
        return charArray.toString();
    }
}