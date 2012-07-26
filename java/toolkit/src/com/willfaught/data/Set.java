package com.willfaught.data;

public interface Set<E> extends Collection<E>
{
    void complement(Set<E> set);
    void intersection(Set<E> set);
    void product(Set<E> set);
    void union(Set<E> set);
}