package com.willfaught;

public interface Collection<E> extends Copyable<Collection<E>>
{
    void clear();

    boolean contains(E element);

    boolean empty();

    int size();
}