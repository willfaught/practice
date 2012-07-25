package com.willfaught.data;

public interface Collection<E>
{
    void add(E element)
    void clear();
    boolean contains(E element);
    boolean empty();
    int size();
    Object[] toArray();
    String toString()
}