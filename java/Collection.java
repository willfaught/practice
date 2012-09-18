package com.willfaught;

public interface Collection<E>
{
    void clear();

    boolean contains(E element);

    boolean empty();

    int size();
}