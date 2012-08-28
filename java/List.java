package com.willfaught;

public interface List<E> extends Set<E>
{
	void add(int index, E element);
	E get(int index);
	int index(E element);
	E remove(int index);
	E set(int index, E element);
}