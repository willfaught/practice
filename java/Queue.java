public interface Queue<E> extends Collection<E>
{
    E dequeue();
    void enqueue(E element);
    E head();
}