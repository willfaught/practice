public class LinkedList<E> implements List<E>
{
    private static class Node<E>
    {
        public E element;
        private Node<E> previous;
		private Node<E> next;

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

    public int add(E element)
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
        return size++;
    }

	public void add(int index, E element)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("index");
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
        LinkedList<?> linkedList = (LinkedList<?>)object;
        if (size != linkedList.size)
        {
            return false;
        }
        Node ourNode = head;
        Node theirNode = linkedList.head;
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

    public E get(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
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

    public E remove(int index)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
        }
        Node<E> node = node(index);
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
        return node.element;
    }

    public E set(int index, E element)
    {
        if (!valid(index))
        {
            throw new IndexOutOfBoundsException("index");
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

    public String toString()
    {
        CharBuffer charBuffer = new CharBuffer();
        charBuffer.append("[");
        Node<E> node = head;
        while (node != null)
        {
            if (node != head)
            {
                charBuffer.append(", ");
            }
            charBuffer.append(node.element.toString());
            node = node.next;
        }
        charBuffer.append("]");
        return charBuffer.toString();
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