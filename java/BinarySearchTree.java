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
    
    public void add(K key, V value)
    {
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
                    node.less = new Node<K, V>(key, value);
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
    
    public K ceiling(K key)
    {
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
    
    public boolean contains(K key)
    {
        return get(key) != null;
    }
    
    public BinarySearchTree<K, V> copy()
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
    
    public boolean empty()
    {
        return root == null;
    }
    
    public Iterator<K> iterator()
    {
        return inorder(getMinimum(), getMaximum());
    }
    
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
        if (root == null || bst.root == null)
        {
            return false;
        }
        Stack<Node<K, V>> stack1 = new ArrayList<Node<K, V>>();
        Stack<Node<?, ?>> stack2 = new ArrayList<Node<?, ?>>();
        stack1.push(root);
        stack2.push(bst.root);
        while (!stack1.empty() && !stack2.empty())
        {
            Node<K, V> node1 = stack1.pop();
            Node<?, ?> node2 = stack2.pop();
            if (!node1.key.equals(node2.key) || !node1.value.equals(node2.value))
            {
                return false;
            }
            if (node1.greater != null)
            {
                stack1.push(node1.greater);
            }
            if (node1.greater != null)
            {
                stack1.push(node1.less);
            }
            if (node2.greater != null)
            {
                stack2.push(node2.greater);
            }
            if (node2.greater != null)
            {
                stack2.push(node2.less);
            }
        }
        return stack1.empty() && stack2.empty();
    }
    
    public K floor(K key)
    {
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
    
    public V get(K key)
    {
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
    
    public K getMaximum()
    {
        return key(getMaximum(root));
    }
    
    private Node<K, V> getMaximum(Node<K, V> node)
    {
        while (node != null)
        {
            node = node.greater;
        }
        return node;
    }
    
    public K getMinimum()
    {
        return key(getMinimum(root));
    }
    
    private Node<K, V> getMinimum(Node<K, V> node)
    {
        while (node != null)
        {
            node = node.less;
        }
        return node;
    }
    
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
    
    public Iterator<K> inorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        final Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>();
        final K high2 = high;
        Node<K, V> node = root;
        while (node != null && low.compareTo(node.key) <= 0)
        {
            nodes.push(node);
            node = node.less;
        }
        return new Iterator<K>()
        {
            public boolean hasNext()
            {
                return !nodes.empty();
            }
            
            public K next()
            {
                if (!hasNext())
                {
                    throw new IllegalStateException();
                }
                Node<K, V> node = nodes.pop();
                K key = node.key;
                if (node.greater != null && high2.compareTo(node.key) >= 0)
                {
                    node = node.greater;
                    while (node != null)
                    {
                        nodes.push(node);
                    }
                }
                return key;
            }
            
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
    
    public Iterator<K> preorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        final Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>();
        final K low2 = low;
        final K high2 = high;
        if (root != null)
        {
            nodes.push(root);
        }
        return new Iterator<K>()
        {
            public boolean hasNext()
            {
                return !nodes.empty();
            }
            
            public K next()
            {
                if (!hasNext())
                {
                    throw new IllegalStateException();
                }
                Node<K, V> node = nodes.pop();
                K key = node.key;
                if (node.greater != null && high2.compareTo(node.greater.key) >= 0)
                {
                    nodes.push(node.greater);
                }
                if (node.less != null && low2.compareTo(node.less.key) <= 0)
                {
                    nodes.push(node.less);
                }
                return key;
            }
            
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public Iterator<K> postorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        final Stack<Node<K, V>> nodes = new ArrayList<Node<K, V>>();
        final K high2 = high;
        Node<K, V> node = root;
        while (true)
        {
            nodes.push(node);
            if (node.less != null && low.compareTo(node.less.key) <= 0)
            {
                node = node.less;
            }
            else if (node.greater != null && high.compareTo(node.greater.key) >= 0)
            {
                node = node.greater;
            }
            else
            {
                break;
            }
        }
        return new Iterator<K>()
        {
            public boolean hasNext()
            {
                return !nodes.empty();
            }
            
            public K next()
            {
                if (!hasNext())
                {
                    throw new IllegalStateException();
                }
                Node<K, V> node = nodes.pop();
                if (nodes.empty())
                {
                    return node.key;
                }
                K key = node.key;
                Node<K, V> parent = nodes.top();
                if (node == parent.less)
                {
                    node = parent.greater;
                    while (true)
                    {
                        nodes.push(node);
                        if (node.less != null)
                        {
                            node = node.less;
                        }
                        else if (node.greater != null && high2.compareTo(node.greater.key) >= 0)
                        {
                            node = node.greater;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                return key;
            }
            
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public int rank(K key)
    {
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
    
    public void remove(K key)
    {
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        Node<K, V> parent = null;
        int previous = 0;
        Node<K, V> node = root;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                parent = node;
                previous = c;
                node = node.less;
                stack.push(parent);
            }
            else if (c > 0)
            {
                parent = node;
                previous = c;
                node = node.greater;
                stack.push(parent);
            }
            else
            {
                if (parent == null)
                {
                    root = null;
                }
                else
                {
                    if (previous < 0)
                    {
                        parent.less = null;
                    }
                    else
                    {
                        parent.greater = null;
                    }
                    while (!stack.empty())
                    {
                        --stack.pop().size;
                    }
                }
            }
        }
    }
    
    public void removeMaximum()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        Node<K, V> parent = null;
        Node<K, V> maximum = root;
        while (maximum.greater != null)
        {
            parent = maximum;
            maximum = maximum.greater;
            stack.push(parent);
        }
        if (parent == null)
        {
            root = null;
        }
        else
        {
            parent.greater = null;
            while (!stack.empty())
            {
                --stack.pop().size;
            }
        }
    }
    
    public void removeMinimum()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node<K, V>> stack = new ArrayList<Node<K, V>>();
        Node<K, V> parent = null;
        Node<K, V> minimum = root;
        while (minimum.less != null)
        {
            parent = minimum;
            minimum = minimum.less;
            stack.push(parent);
        }
        if (parent == null)
        {
            root = null;
        }
        else
        {
            parent.less = null;
            while (!stack.empty())
            {
                --stack.pop().size;
            }
        }
    }
    
    public K select(int rank)
    {
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
    
    public int size()
    {
        return size(root);
    }
    
    private int size(Node<K, V> node)
    {
        return node == null ? 0 : node.size;
    }
    
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