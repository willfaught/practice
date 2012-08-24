public class Heap<E extends Comparable<E>>
{
    private boolean maximum;
    private List<E> list;
    
    public Heap()
    {
        this(false);
    }
    
    public Heap(boolean maximum)
    {
        this(maximum, 10);
    }
    
    public Heap(int capacity)
    {
        this(false, capacity);
    }
    
    public Heap(boolean maximum, int capacity)
    {
        this.maximum = maximum;
        list = new ArrayList<E>(capacity);
    }
    
    public void add(E element)
    {
        list.add(element);
        int child = list.size() - 1;
        while (child > 0)
        {
            int parent = (child - 1) / 2;
            int comparison = list.get(parent).compareTo(list.get(child));
            if ((maximum && comparison >= 0) || (!maximum && comparison <= 0))
            {
                break;
            }
            swap(parent, child);
            child = parent;
        }
    }
    
    public void clear()
    {
        list.clear();
    }
    
    public boolean contains(E element)
    {
        return list.contains(element);
    }
    
    public boolean empty()
    {
        return list.size() == 0;
    }
    
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object == null || !(object instanceof Heap))
        {
            return false;
        }
        Heap<?> heap = (Heap<?>)object;
        return list.equals(heap.list);
    }
    
    public int hashCode()
    {
        return list.hashCode();
    }
    
    public E peek()
    {
        if (list.size() == 0)
        {
            throw new IllegalStateException();
        }
        return list.get(0);
    }
    
    public E remove()
    {
        int size = list.size();
        if (size == 0)
        {
            throw new IllegalStateException();
        }
        else if (size == 1)
        {
            return list.remove(0);
        }
        E element = list.set(0, list.remove(size-- - 1));
        int parent = 0;
        while (true)
        {
            int left = parent * 2 + 1;
            if (left >= size)
            {
                break;
            }
            int right = left + 1;
            int child;
            if (right >= size)
            {
                child = left;
            }
            else
            {
                int comparison = list.get(left).compareTo(list.get(right));
                child = maximum ? (comparison >= 0 ? left : right) : (comparison <= 0 ? left : right);
            }
            int comparison = list.get(parent).compareTo(list.get(child));
            if ((maximum && comparison >= 0) || (!maximum && comparison <= 0))
            {
                break;
            }
            swap(parent, child);
            parent = child;
        }
        return element;
    }
    
    public int size()
    {
        return list.size();
    }
    
    public String toString()
    {
        return list.toString();
    }
    
    private void swap(int i, int j)
    {
        E element = list.set(i, list.get(j));
        list.set(j, element);
    }
}
