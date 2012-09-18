package com.willfaught;

import java.util.Iterator;

public interface List<E> extends Collection<E>
{
    void add(E element);

    void add(int index, E element);

    E get(int index);

    int index(E element);

    Iterator<E> listed();

    int remove(E element);

    E remove(int index);

    E set(int index, E element);
}