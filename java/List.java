package com.willfaught;

public interface List<E> extends Collection<E>, Iterable<E>
{
    void add(E element);

    void add(int index, E element);

    E get(int index);

    int index(E element);

    void remove(E element);

    E remove(int index);

    E set(int index, E element);
}