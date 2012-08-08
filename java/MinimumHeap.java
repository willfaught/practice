public class MinimumHeap<E extends Comparable<E>> implements Heap<E>
{
	private List<E> list;
	
	public MinimumHeap()
	{
		this(10);
	}
	
	public MinimumHeap(int capacity)
	{
		list = new ArrayList<E>(capacity);
	}
	
	public void add(E element)
	{
		int child = list.add(element);
		while (child > 0)
		{
			int parent = (child - 1) / 2;
			if (list.get(parent).compareTo(list.get(child)) < 0)
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
		if (object == null || !(object instanceof MinimumHeap))
		{
			return false;
		}
		MinimumHeap<?> minimumHeap = (MinimumHeap<?>)object;
		return list.equals(minimumHeap.list);
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
			int child = right >= size ? left : left <= right ? left : right;
			if (list.get(parent).compareTo(list.get(child)) < 0)
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