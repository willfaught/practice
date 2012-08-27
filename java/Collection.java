public interface Collection<E> extends Copyable<Collection<E>>, Enumerable<E>
{
    void clear();
    boolean contains(E element);
    boolean empty();
    int size();
}