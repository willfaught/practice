public interface Heap<E>
{
	void add(E element);
	void clear();
	boolean contains(E element);
	boolean empty();
	E peek();
	E remove();
	int size();
}