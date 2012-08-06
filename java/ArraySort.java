public class ArraySort
{
	// Stable
	// Adaptive
	// Offline
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void bubble(int[] items)
	{
		int unsorted = items.length;
		do
		{
			int sorted = 0;
			for (int i = 1; i < unsorted; ++i)
			{
				if (items[i - 1] > items[i])
				{
					swap(items, i - 1, i);
					sorted = i;
				}
			}
			unsorted = sorted;
		}
		while (unsorted > 0);
	}

	// Stable
	// Adaptive
	// Online
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void insertion(int[] items)
	{
		int length = items.length;
		for (int i = 1; i < length; ++i)
		{
			int j;
			int item = items[i];
			for (j = i; j > 0; --j)
			{
				if (items[j - 1] <= item)
				{
					break;
				}
				items[j] = items[j - 1];
			}
			if (j != i)
			{
				items[j] = item;
			}
		}
	}

	// Not stable
	// Not adaptive
	// Offline
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(n) swaps, O(n^2) comparisons
	// Average time complexity: O(n) swaps, O(n^2) comparisons
	// Worst time complexity: O(n) swaps, O(n^2) comparisons
	public static void selection(int[] items)
	{
		int length = items.length;
		for (int i = 0; i < length - 1; ++i)
		{
			int minimum = i;
			for (int j = i + 1; j < length; ++j)
			{
				if (items[j] < items[minimum])
				{
					minimum = j;
				}
			}
			if (minimum != i)
			{
				swap(items, i, minimum);
			}
		}
	}
	
	public static void shell(int[] items)
	{
		
	}
	
	public static void merge(int[] items)
	{
		
	}
	
	// Not stable
	// Not adaptive
	// Offline
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(n log n) swaps, O(n log n) comparisons
	// Average time complexity: O(n log n) swaps, O(n log n) comparisons
	// Worst time complexity: O(n log n) swaps, O(n log n) comparisons
	public static void heap(int[] items)
	{
		int length = items.length;
		for (int i = length / 2; i >= 0; --i)
		{
			sink(items, i, length);
		}
		for (int i = 1; i < length; ++i)
		{
			swap(items, 0, length - i);
			sink(items, 0, length - i);
		}
	}
	
	private static void sink(int[] items, int index, int length)
	{
		while (true)
		{
			int left = index * 2 + 1;
			int right = left + 1;
			if (left >= length)
			{
				return;
			}
			int maximum = right >= length ? left : left >= right ? left : right;
			if (items[index] < items[maximum])
			{
				swap(items, index, maximum);
				index = maximum;
			}
		}
	}
	
	public static void quick(int[] items)
	{
		
	}
	
	private static void swap(int[] items, int first, int second)
	{
		int item = items[first];
		items[first] = items[second];
		items[second] = item;
	}
}