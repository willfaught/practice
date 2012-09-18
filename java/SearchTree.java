package com.willfaught;

import java.util.Iterator;

public interface SearchTree<K extends Comparable<K>, V> extends Map<K, V>
{
    K ceiling(K key);

    K floor(K key);

    K getMaximum();

    K getMinimum();

    Iterator<K> inorder(K low, K high);

    Iterator<K> postorder(K low, K high);

    Iterator<K> preorder(K low, K high);

    int rank(K key);

    void removeMaximum(); // TODO: return value

    void removeMinimum(); // TODO: return value

    K select(int rank);
}