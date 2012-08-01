import java.util.List;

public class ArraySort
{
	// Not stable
	// Not adaptive
	// Space complexity: O(1)
	// Best time complexity: O(n) swaps, O(n^2) comparisons
	// Average time complexity: O(n) swaps, O(n^2) comparisons
	// Worst time complexity: O(n) swaps, O(n^2) comparisons
	public static <T extends Comparable<T>> void selection(List<T> items)
	{
		int size = items.size();
		for (int i = 0; i < size - 1; ++i)
		{
			int minimumIndex = i;
			T minimumItem = items.get(i);
			for (int j = i + 1; j < size; ++j)
			{
				T item = items.get(j);
				if (minimumItem.compareTo(item) > 0)
				{
					minimumIndex = j;
					minimumItem = item;
				}
			}
			if (minimumIndex != i)
			{
				T item = items.get(i);
				items.set(i, minimumItem);
				items.set(minimumIndex, item);
			}
		}
	}
	
	// Stable
	// Adaptive
	// Space complexity: O(1)
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static <T extends Comparable<T>> void insertion(List<T> items)
	{
		int size = items.size();
		for (int i = 1; i < size; ++i)
		{
			for (int j = i; j > 0; --j)
			{
				T right = items.get(j);
				T left = items.get(j - 1);
				if (left.compareTo(right) > 0)
				{
					T item = right;
					items.set(j, left);
					items.set(j - 1, item);
				}
				else
				{
					break;
				}
			}
		}
	}
	
	public static <T extends Comparable<T>> void bubble(List<T> items)
	{
		
	}
	
	public static <T extends Comparable<T>> void shell(List<T> items)
	{
		
	}
	
	public static <T extends Comparable<T>> void merge(List<T> items)
	{
		
	}
	
	public static <T extends Comparable<T>> void quick(List<T> items)
	{
		
	}
	
	public static <T extends Comparable<T>> void heap(List<T> items)
	{
		
	}
}