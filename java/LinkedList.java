public class LinkedList<E> implements List<E>, Queue<E>, Stack<E>
{
    private static class Node<E>
    {
        public E element;
        private Node<E> previous;
		private Node<E> next;

        public Node()
        {
        }

        public Node(E element)
        {
            this.element = element;
        }

        public void left(Node<E> node)
        {
            link(previous, node);
            link(node, this);
        }

        private void link(Node<E> left, Node<E> right)
        {
            if (left != null)
            {
                left.next = right;
            }
            if (right != null)
            {
                right.previous = left;
            }
        }

        public void remove()
        {
            link(previous, next);
        }

        public void right(Node<E> node)
        {
            link(node, next);
            link(this, node);
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void add(E element)
    {
		Node<E> node = new Node<E>(element);
        if (head == null)
        {
            head = tail = node;
        }
        else
        {
            tail.right(node);
            tail = node;
        }
        ++size;
    }

	public void add(int index, E element)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node<E> node = new Node<E>(element);
		if (head == null)
		{
			head = tail = node;
		}
		else if (index == 0)
		{
			head.left(node);
			head = node;
		}
		else if (index == size)
		{
			tail.right(node);
			tail = node;
		}
		else
		{
			Node<E> n = head;
			for (int i = 0; i < index; ++i)
			{
				n = n.next;
			}
			n.left(node);
		}
		++size;
	}

    public void clear()
    {
		for (Node<E> node = head; node != null;)
		{
			Node<E> next = node.next;
			node.previous = node.next = null;
			node.element = null;
			node = next;
		}
        head = tail = null;
        size = 0;
    }

    public boolean contains(E element)
    {
        return index(element) >= 0;
    }
    
    @SuppressWarnings("unchecked")
    public LinkedList<E> copy()
    {
        LinkedList<E> copy = new LinkedList<E>();
        if (size == 0)
        {
            return copy;
        }
        copy.size = size;
        copy.head = new Node<E>(head.element);
        for (Node<E> node = head.next, previous = copy.head; node != null; node = node.next)
        {
            Node<E> copyNode = new Node<E>();
            copyNode.previous = previous;
            previous.next = copyNode;
            previous = copyNode;
            if (node.element instanceof Copyable<?>)
            {
                Copyable<E> copyable = (Copyable<E>)node.element;
                copyNode.element = copyable.copy();
            }
            else
            {
                copyNode.element = node.element;
            }
        }
        return copy;
    }
    
    public E dequeue()
    {
        if (head == null)
        {
            throw new IllegalStateException();
        }
        E element = head.element;
        remove(head);
        return element;
    }
    
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof LinkedList<?>))
        {
            return false;
        }
        LinkedList<?> linkedList = (LinkedList<?>)object;
        if (size != linkedList.size)
        {
            return false;
        }
        Node<E> ourNode = head;
        Node<?> theirNode = linkedList.head;
        while (ourNode != null && theirNode != null)
        {
            if (!ourNode.element.equals(theirNode.element))
            {
                return false;
            }
            ourNode = ourNode.next;
            theirNode = theirNode.next;
        }
        return true;
    }

    public boolean empty()
    {
        return head == null;
    }
    
    public void enqueue(E element)
    {
        add(element);
    }
    
    public Enumerator<E> enumerator()
    {
        return new Enumerator<E>()
        {
            private Node<E> node = head;
            
            public boolean more()
            {
                return node != null;
            }
            
            public E next()
            {
                if (!more())
                {
                    throw new IllegalStateException();
                }
                Node<E> next = node;
                node = node.next;
                return next.element;
            }
        };
    }

    public E get(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
        return node(index).element;
    }

    public int hashCode()
    {
        int hash = 0;
        for (Node<E> node = head; node != null; node = node.next)
        {
			hash = hash * 31 + node.element.hashCode();
        }
        return hash;
    }
    
    public E head()
    {
        if (head == null)
        {
            throw new IllegalStateException();
        }
        return head.element;
    }

    public int index(E element)
    {
        if (head == null)
        {
            return -1;
        }
        int index = 0;
        for (Node<E> node = head; node != null; node = node.next)
        {
            if (node.element == null || element == null)
            {
				if (node.element == element)
				{
                	return index;
				}
            }
			else if (node.element.equals(element))
            {
                return index;
            }
			++index;
        }
        return -1;
    }
    
    public E pop()
    {
        if (tail == null)
        {
            throw new IllegalStateException();
        }
        E element = tail.element;
        remove(tail);
        return element;
    }
    
    public void push(E element)
    {
        add(element);
    }

    public E remove(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = node(index);
        remove(node);
        return node.element;
    }
    
    public void remove(E element)
    {
        Node<E> node = head;
        while (node != null)
        {
            if (element.equals(node.element))
            {
                remove(node);
            }
        }
    }
    
    private void remove(Node<E> node)
    {
        if (node == head)
        {
            head = head.next;
        }
        if (node == tail)
        {
            tail = tail.previous;
        }
        node.remove();
        --size;
    }

    public E set(int index, E element)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException();
        }
		Node<E> node = node(index);
		E old = node.element;
        node.element = element;
		return old;
    }

    public int size()
    {
        return size;
    }
    
    public E top()
    {
        if (tail == null)
        {
            throw new IllegalStateException();
        }
        return tail.element;
    }

    public String toString()
    {
        CharArray charArray = new CharArray();
        charArray.append("[");
        Node<E> node = head;
        while (node != null)
        {
            if (node != head)
            {
                charArray.append(", ");
            }
            charArray.append(node.element.toString());
            node = node.next;
        }
        charArray.append("]");
        return charArray.toString();
    }

	private Node<E> node(int index)
    {
        if (index < size / 2)
        {
            Node<E> node = head;
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
            Node<E> node = tail;
            int i = size - 1;
            while (i > index)
            {
                node = node.previous;
                --i;
            }
            return node;
        }
    }

    private boolean valid(int index)
    {
        return index >= 0 && index + 1 <= size && size > 0;
    }
}