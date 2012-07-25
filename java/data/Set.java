package com.willfaught.data;

public interface Set<E> : Collection<E>
{
    void complement(Set set);
    void intersection(Set set);
    void product(Set set);
    void union(Set set);
}