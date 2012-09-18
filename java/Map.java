package com.willfaught;

public interface Map<K, V> extends Collection<K>
{
    V add(K key, V value);

    V get(K key);

    V remove(K key);
}