import java.util.List;

public class ArraySort
{
	// Stable
	// Adaptive
	// Offline
	// Comparison
	// Space complexity: O(1)
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void bubble(int[] array)
	{
		int unsorted = array.length;
		do
		{
			int sortedIndex = 0;
			for (int i = 1; i < unsorted; ++i)
			{
				int leftIndex = i - 1;
				int rightIndex = i;
				int left = array[leftIndex];
				int right = array[rightIndex];
				if (left > right)
				{
					int item = right;
					array[rightIndex] = left;
					array[leftIndex] = item;
					sortedIndex = rightIndex;
				}
			}
			unsorted = sortedIndex;
		}
		while (unsorted > 0);
	}

	// Stable
	// Adaptive
	// Online
	// Comparison
	// Space complexity: O(1)
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void insertion(int[] array)
	{
		int size = array.length;
		for (int i = 1; i < size; ++i)
		{
			int j;
			int current = array[i];
			for (j = i; j > 0; --j)
			{
				int item = array[j - 1];
				if (item < current)
				{
					break;
				}
				array[j] = item;
			}
			if (j != i)
			{
				array[j] = current;
			}
		}
	}

	// Not stable
	// Not adaptive
	// Offline
	// Comparison
	// Space complexity: O(1)
	// Best time complexity: O(n) swaps, O(n^2) comparisons
	// Average time complexity: O(n) swaps, O(n^2) comparisons
	// Worst time complexity: O(n) swaps, O(n^2) comparisons
	public static void selection(int[] array)
	{
		int size = array.length;
		for (int i = 0; i < size - 1; ++i)
		{
			int minimumIndex = i;
			int minimumItem = array[i];
			for (int j = i + 1; j < size; ++j)
			{
				int item = array[j];
				if (minimumItem > item)
				{
					minimumIndex = j;
					minimumItem = item;
				}
			}
			if (minimumIndex != i)
			{
				int item = array[i];
				array[i] = minimumItem;
				array[minimumIndex] = item;
			}
		}
	}
	
	public static void shell(int[] array)
	{
		
	}
	
	public static void merge(int[] array)
	{
		
	}
	
	public static void heap(int[] array)
	{
		
	}
	
	public static void quick(int[] array)
	{
		
	}
}