package com.willfaught.data;

public interface Queue<E> extends Collection<E>
{
    void enqueue(E element);
    E dequeue();
}