public class LinkedList<E>
{
    private class Node
    {
        public E element;
        public Node next;
        public Node previous;

        public Node(E element)
        {
            this.element = element;
        }
        
        public void only()
        {
            splice(this, this);
        }
        
        public Node get(int index)
        {
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
        
        public int index(E element)
        {
            Node node = this;
            int index = 0;
            do
            {
                if (node.element.equals(element))
                {
                    return index;
                }
                node = node.next;
                ++index;
            }
            while (node != this);
            return -1;
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

        public void remove()
        {
            splice(previous, next);
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

        private void splice(Node left, Node right)
        {
            left.next = right;
            right.previous = left;
        }
    }

    private Node head;
    private int size;

    public void addAfter(int index, E element)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        head.get(index).right(new Node(element));
        ++size;
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
        size += linkedList.size;
        linkedList.head = null;
        linkedList.size = 0;
    }

    public void addBefore(int index, E element)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        head.get(index).left(new Node(element));
        ++size;
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
        size += linkedList.size;
        linkedList.head = null;
        linkedList.size = 0;
    }

    public void addFirst(E element)
    {
        if (head == null)
        {
            head = new Node(element);
            head.only();
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
        size += linkedList.size;
        linkedList.head = null;
        linkedList.size = 0;
    }

    public void addLast(E element)
    {
        if (head == null)
        {
            head = new Node(element);
            head.only();
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
        size += linkedList.size;
        linkedList.head = null;
        linkedList.size = 0;
    }

    public void clear()
    {
        head = null;
        size = 0;
    }

    public boolean contains(E element)
    {
        return index(element) != -1;
    }

    public boolean empty()
    {
        return head == null;
    }

    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object == null || !(object instanceof LinkedList<?>))
        {
            return false;
        }
        LinkedList<?> linkedList = (LinkedList<?>) object;
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

    public int hashCode()
    {
        int hashCode = super.hashCode();
        for (int i = 0; i < size; ++i)
        {
            hashCode = hashCode * 31 + get(i).hashCode();
        }
        return hashCode;
    }

    public int index(E element)
    {
        if (head == null)
        {
            return -1;
        }
        return head.index(element);
    }

    public E remove(int index)
    {
        if (!validIndex(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        Node node = head.get(index);
        if (size == 1)
        {
            head = null;
        }
        else
        {
            node.remove();
        }
        if (node == head)
        {
            head = head.next;
        }
        --size;
        return node.element;
    }

    public E removeFirst()
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        return remove(0);
    }

    public E removeLast()
    {
        if (head == null)
        {
            throw new IndexOutOfBoundsException();
        }
        return remove(size - 1);
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

    private boolean validIndex(int index)
    {
        return index >= 0 && index + 1 <= size && size > 0;
    }
}