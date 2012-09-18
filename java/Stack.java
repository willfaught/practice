package com.willfaught;

import java.util.Iterator;

public interface Stack<E> extends Collection<E>
{
    E pop();

    void push(E element);

    Iterator<E> stacked();

    E top();
}