public interface Stack<E> extends Collection<E>
{
    E peek();
    E pop();
    void push(E element);
}