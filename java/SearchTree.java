package com.willfaught;

import java.util.Iterator;

public interface SearchTree<K extends Comparable<K>, V> extends Collection<K>
{
    void add(K key, V value);
    K ceiling(K key);
    K floor(K key);
    V get(K key);
    K getMaximum();
    K getMinimum();
    Iterator<K> inorder(K low, K high);
    Iterator<K> preorder(K low, K high);
    Iterator<K> postorder(K low, K high);
    int rank(K key);
    void remove(K key);
    void removeMaximum();
    void removeMinimum();
    K select(int rank);
}