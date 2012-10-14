package com.willfaught;

import java.util.Iterator;

public class BinaryTree<K, V>
{
    public K key;
    public V value;
    public BinaryTree<K, V> left;
    public BinaryTree<K, V> right;

    public BinaryTree()
    {
    }

    public BinaryTree(BinaryTree<K, V> left, BinaryTree<K, V> right)
    {
        this.left = left;
        this.right = right;
    }

    public BinaryTree(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public BinaryTree(K key, V value, BinaryTree<K, V> left, BinaryTree<K, V> right)
    {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Iterator<V> breadthFirstSearch(K key)
    {
        ArrayList<V> values = new ArrayList<V>();
        Queue<BinaryTree<K, V>> queue = new ArrayList<BinaryTree<K, V>>();
        queue.enqueue(this);
        while (!queue.empty())
        {
            BinaryTree<K, V> binaryTree = queue.dequeue();
            if (key.equals(binaryTree.key))
            {
                values.add(binaryTree.value);
            }
            if (binaryTree.left != null)
            {
                queue.enqueue(binaryTree.left);
            }
            if (binaryTree.right != null)
            {
                queue.enqueue(binaryTree.right);
            }
        }
        return values.iterator();
    }

    public Iterator<V> depthFirstSearch(K key)
    {
        ArrayList<V> values = new ArrayList<V>();
        Stack<BinaryTree<K, V>> stack = new ArrayList<BinaryTree<K, V>>();
        stack.push(this);
        while (!stack.empty())
        {
            BinaryTree<K, V> binaryTree = stack.pop();
            if (key.equals(binaryTree.key))
            {
                values.add(binaryTree.value);
            }
            if (binaryTree.right != null)
            {
                stack.push(binaryTree.right);
            }
            if (binaryTree.left != null)
            {
                stack.push(binaryTree.left);
            }
        }
        return values.iterator();
    }
}