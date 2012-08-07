public interface List<E>
{
	int add(E element);
	void add(int index, E element);
	void clear();
	boolean contains(E element);
	boolean empty();
	E get(int index);
	int index(E element);
	E remove(int index);
	E set(int index, E element);
	int size();
}