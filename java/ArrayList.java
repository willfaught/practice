public class ArrayList<E> implements List<E>
{
    private int size;
    private Object[] elements;
    
    public ArrayList()
    {
        this(10);
    }
    
    public ArrayList(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("capacity");
        }
        elements = new Object[capacity];
    }
    
    public int add(E element)
    {
        if (size == elements.length)
        {
            grow();
        }
        elements[size] = element;
        return size++;
    }
    
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("index");
        }
        if (size == elements.length)
        {
            grow();
        }
        for (int i = size; i > index; --i)
        {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        ++size;
    }
    
    public void clear()
    {
        for (int i = 0; i < size; ++i)
        {
            elements[i] = null;
        }
        size = 0;
    }
    
    public boolean contains(E element)
    {
        return index(element) >= 0;
    }
    
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object == null || !(object instanceof ArrayList<?>))
        {
            return false;
        }
        ArrayList<?> arrayList = (ArrayList<?>)object;
        if (size != arrayList.size)
        {
            return false;
        }
        for (int i = 0; i < size; ++i)
        {
            if (!elements[i].equals(arrayList.elements[i]))
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean empty()
    {
        return size == 0;
    }
    
    public E get(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        return element(index);
    }
    
    public int hashCode()
    {
        int hash = 0;
        for (int i = 0; i < size; ++i)
        {
            hash = hash * 31 + elements[i].hashCode();
        }
        return hash;
    }
    
    public int index(E element)
    {
        for (int i = 0; i < size; ++i)
        {
            if (elements[i].equals(element))
            {
                return i;
            }
        }
        return -1;
    }
    
    public E remove(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        E element = element(index);
        for (int i = index; i < size - 1; ++i)
        {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        --size;
        return element;
    }
    
    public E set(int index, E element)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        E old = element(index);
        elements[index] = element;
        return old;
    }
    
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
        CharBuffer charBuffer = new CharBuffer();
        charBuffer.append("[");
        for (int i = 0; i < size; ++i)
        {
            if (i != 0)
            {
                charBuffer.append(", ");
            }
            charBuffer.append(elements[i].toString());
        }
        charBuffer.append("]");
        return charBuffer.toString();
    }
    
    @SuppressWarnings("unchecked")
    private E element(int index)
    {
        return (E)elements[index];
    }
    
    private void grow()
    {
        Object[] copy = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; ++i)
        {
            copy[i] = elements[i];
        }
        elements = copy;
    }
    
    private boolean valid(int index)
    {
        return size > 0 && index >= 0 && index < size;
    }
}