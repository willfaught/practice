import java.util.*;

public class MinStack<E extends Comparable<E>>
{
    private Stack<E> elements = new Stack<>();
    private Stack<E> mins = new Stack<>();
    
    public void push(E element)
    {
        elements.push(element);
        if (mins.isEmpty() || mins.peek().compareTo(element) > 0) mins.push(element);
    }
    
    public E pop()
    {
        if (elements.isEmpty()) throw new EmptyStackException();
        E element = elements.pop();
        if (mins.peek().compareTo(element) == 0) mins.pop();
        return element;
    }
    
    public E min()
    {
        if (elements.isEmpty()) throw new EmptyStackException();
        return mins.peek();
    }
    
    public static void main(String[] args)
    {
        MinStack<Integer> m = new MinStack<>();
        m.push(5);
        System.out.println("1 " + m.min());
        m.push(6);
        System.out.println("2 " + m.min());
        m.push(4);
        System.out.println("3 " + m.min());
        m.pop();
        System.out.println("4 " + m.min());
        m.pop();
        System.out.println("5 " + m.min());
        m.pop();
        System.out.println("Done");
    }
}