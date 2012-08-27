public interface Stack<E> extends Collection<E>
{
    E top();
    E pop();
    void push(E element);
}