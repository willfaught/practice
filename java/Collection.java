public interface Collection<E> extends Enumerable<E>
{
    void clear();
    boolean contains(E element);
    boolean empty();
    int size();
}