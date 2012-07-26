package com.willfaught.data;

public interface List<E>
{
    void addBefore(int index, E element);
    void addBefore(int index, List<E> list);
    void addAfter(int index, E element);
    void addAfter(int index, List<E> list);
    void addFirst(E element);
    void addFirst(List<E> list);
    void addLast(E element);
    void addLast(List<E> list);
    E get(int index);
    E getFirst();
    E getLast();
    E remove(int index);
    E removeFirst();
    E removeLast();
    void set(int index, E element);
    void setFirst(E element);
    void setLast(E element);
}