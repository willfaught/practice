package com.willfaught.data;

public class LinkedList<E> implements Cloneable
{
    private Node head;
    private int size;
    
    private boolean validIndex(int index)
    {
        return index >= 0 && index + 1 <= size && size > 0;
    }
    
    public void addBefore(int index, E element)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        head.get(index).left(new Node(element));
    }
    
    public void addBefore(int index, LinkedList<E> linkedList)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        if (linkedList.size == 0)
        {
            return;
        }
        head.get(index).left(linkedList.head, linkedList.head.previous);
    }
    
    public void addAfter(int index, E element)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        head.get(index).right(new Node(element));
    }
    
    public void addAfter(int index, LinkedList<E> linkedList)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        if (linkedList.size == 0)
        {
            return;
        }
        head.get(index).right(linkedList.head, linkedList.head.previous);
    }
    
    public void addFirst(E element)
    {
        if (head == null)
        {
            head = new Node(element);
            head.first();
        }
        else
        {
            head = head.left(new Node(element));
        }
        ++size;
    }
    
    public void addFirst(LinkedList<E> linkedList)
    {
        if (head == null)
        {
            head = linkedList.head;
        }
        else
        {
            head = head.left(linkedList.head, linkedList.head.previous);
        }
        ++size;
    }
    
    public void addLast(E element)
    {
        if (head == null)
        {
            head = new Node(element);
            head.first();
        }
        else
        {
            head.left(new Node(element));
        }
        ++size;
    }
    
    public void addLast(LinkedList<E> linkedList)
    {
        if (head == null)
        {
            head = linkedList.head;
        }
        else
        {
            head.left(linkedList.head, linkedList.head.previous);
        }
        ++size;
    }
    
    public E get(int index)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        return head.get(index).element;
    }
    
    public E getFirst()
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        return head.element;
    }
    
    public E getLast()
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        return head.previous.element;
    }
    
    public E remove(int index)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        --size;
        return head.remove(index).element;
        
    }
    
    public E removeFirst()
    {
        if (!validIndex(0))
        {
            throw new IndexOutOfBoundsException();
        }
        --size;
        return head.remove(0).element;
    }
    
    public E removeLast()
    {
        int index = size - 1;
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException();
        }
        --size;
        return head.remove(index).element;
    }
    
    public void set(int index, E element)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        head.get(index).element = element;
    }
    
    public void setFirst(E element)
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        head.element = element;
    }
    
    public void setLast(E element)
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        head.previous.element = element;
    }
    
    public int size()
    {
        return size;
    }
    
    public void clear()
    {
        head = null;
        size = 0;
    }
    
    public boolean equals(Object object)
    {
        if (object == null || !(object instanceof LinkedList))
        {
            return false;
        }
        LinkedList linkedList = (LinkedList)object;
        if (size != linkedList.size())
        {
            return false;
        }
        // TODO: Use iterator
        for (int i = 0; i < size; ++i)
        {
            if (!get(i).equals(linkedList.get(i)))
            {
                return false;
            }
        }
        return true;
    }
    
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        // TODO: Use iterator
        for (int i = 0; i < size; ++i)
        {
            if (i > 0)
            {
                stringBuffer.append(", ");
            }
            stringBuffer.append(get(i).toString());
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
    
    public int index(E element)
    {
        for (int i = 0; i < size; ++i)
        {
            if (get(i).equals(element))
            {
                return i;
            }
        }
        return -1;
    }
    
    public boolean contains(E element)
    {
        return index(element) >= 0;
    }
    
    public boolean empty()
    {
        return size == 0;
    }
    
    public int hashCode()
    {
        int hashCode = super.hashCode();
        for (int i = 0; i < size; ++i)
        {
            hashCode = hashCode * 31 + get(i).hashCode();
        }
        return hashCode;
    }
    
    private class Node
    {
        private Node previous;
        private Node next;
        private E element;
        
        public Node(E element)
        {
            this.element = element;
        }
        
        private void splice(Node left, Node right)
        {
            left.next = right;
            right.previous = left;
        }
        
        public void first()
        {
            splice(this, this);
        }
        
        public Node left(Node node)
        {
            splice(previous, node);
            splice(node, this);
            return node;
        }
        
        public Node left(Node first, Node last)
        {
            splice(previous, first);
            splice(last, this);
            return first;
        }
        
        public Node right(Node node)
        {
            splice(node, next);
            splice(this, node);
            return node;
        }
        
        public Node right(Node first, Node last)
        {
            splice(last, next);
            splice(this, first);
            return first;
        }
        
        public Node get(int index)
        {
            if (index == 0)
            {
                return head;
            }
            if (index < size / 2)
            {
                Node node = this;
                int i = 0;
                while (i < index)
                {
                    node = node.next;
                    ++i;
                }
                return node;
            }
            else
            {
                Node node = this;
                int i = size;
                while (i > index)
                {
                    node = node.previous;
                    --i;
                }
                return node;
            }
        }
        
        public Node remove(int index)
        {
            Node node = get(index);
            splice(node.previous, node.next);
            return node;
        }
    }
}