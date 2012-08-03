import java.util.List;

public class ArraySort
{
	// Not stable
	// Not adaptive
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
	
	// Stable
	// Adaptive
	// Space complexity: O(1)
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void bubble(int[] array)
	{
		int size = array.length;
		for (int i = 0; i < size - 1; ++i)
		{
			boolean swapped = false;
			for (int j = size - 1; j > i; --j)
			{
				int leftIndex = j - 1;
				int rightIndex = j;
				int left = array[leftIndex];
				int right = array[rightIndex];
				if (left > right)
				{
					int item = right;
					array[rightIndex] = left;
					array[leftIndex] = item;
					swapped = true;
				}
			}
			if (!swapped)
			{
				return;
			}
		}
	}
	
	// Stable
	// Adaptive
	// Space complexity: O(1)
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void insertion(int[] array)
	{
		int size = array.length;
		for (int i = 1; i < size; ++i)
		{
			for (int j = i; j > 0; --j)
			{
				int right = array[j];
				int left = array[j - 1];
				if (left > right)
				{
					int item = right;
					array[j] = left;
					array[j - 1] = item;
				}
				else
				{
					break;
				}
			}
		}
	}
	
	public static void shell(int[] array)
	{
		
	}
	
	public static void merge(int[] array)
	{
		
	}
	
	public static void quick(int[] array)
	{
		
	}
	
	public static void heap(int[] array)
	{
		
	}
}