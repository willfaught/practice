package com.willfaught;

public interface Collection<E> extends Copyable<Collection<E>>, Iterable<E>
{
    void clear();
    boolean contains(E element);
    boolean empty();
    int size();
}