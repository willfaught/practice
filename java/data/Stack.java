package com.willfaught.data;

public interface Stack<E> : Collection<E>
{
    E peek();
    E pop();
    void push(E element);
}