import java.util.*;

public class DoubleQueueStack<E>
{
    private LinkedList<E> store = new LinkedList<E>(), other = new LinkedList<E>();
    
    public void push(E e)
    {
        store.offer(e);
    }
    
    public E pop()
    {
        if (store.isEmpty()) throw new IllegalStateException();
        E e = null;
        while (!store.isEmpty())
        {
            e = store.remove();
            if (store.isEmpty())
            {
                break;
            }
            other.offer(e);
        }
        LinkedList<E> t = store;
        store = other;
        other = t;
        return e;
    }
    
    public boolean isEmpty()
    {
        return store.isEmpty();
    }
    
    public static void main(String[] args)
    {
        MyStack<Integer> s = new MyStack<Integer>();
        for (int i = 1; i <= 10; ++i)
        {
            s.push(i);
        }
        System.out.print("A: ");
        while (!s.isEmpty()) System.out.print(s.pop() + " ");
        System.out.println();
        
        for (int i = 10; i >= 1; --i)
        {
            s.push(i);
        }
        System.out.print("B: ");
        while (!s.isEmpty()) System.out.print(s.pop() + " ");
        System.out.println();
        
        for (int i = 1; i <= 10; ++i)
        {
            s.push(i);
        }
        for (int i = 1; i <= 5; ++i)
        {
            s.pop();
        }
        for (int i = 1; i <= 5; ++i)
        {
            s.push(i);
        }
        System.out.print("C: ");
        while (!s.isEmpty()) System.out.print(s.pop() + " ");
        System.out.println();
    }
}