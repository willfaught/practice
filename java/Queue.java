package com.willfaught;

import java.util.Iterator;

public interface Queue<E> extends Collection<E>
{
    E dequeue();

    void enqueue(E element);

    E head();

    Iterator<E> queued();
}