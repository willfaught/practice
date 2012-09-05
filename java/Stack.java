package com.willfaught;

public interface Stack<E> extends Collection<E>
{
    E pop();

    void push(E element);

    E top();
}