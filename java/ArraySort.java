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
	public static void bubble(int[] a)
	{
		int n = a.length;
		while (n > 0)
		{
			int j = 0;
			for (int i = 1; i < n; ++i)
			{
				if (a[i - 1] > a[i])
				{
					swap(a, i - 1, i);
					j = i;
				}
			}
			n = j;
		}
	}

	// Stable
	// Adaptive
	// Online
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(1) swaps, O(n) comparisons
	// Average time complexity: O(n^2) swaps, comparisons
	// Worst time complexity: O(n^2) swaps, comparisons
	public static void insertion(int[] a)
	{
		int n = a.length;
		for (int i = 1; i < n; ++i)
		{
			int j;
			int x = a[i];
			for (j = i; j > 0; --j)
			{
				if (a[j - 1] <= x)
				{
					break;
				}
				a[j] = a[j - 1];
			}
			if (j != i)
			{
				a[j] = x;
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
	public static void selection(int[] a)
	{
		int n = a.length;
		for (int i = 0; i < n - 1; ++i)
		{
			int j = i;
			for (int k = i + 1; k < n; ++k)
			{
				if (a[k] < a[j])
				{
					j = k;
				}
			}
			if (j != i)
			{
				swap(a, i, j);
			}
		}
	}
	
	// Not stable
	// Not adaptive
	// Offline
	// Comparison
	// Space complexity: O(n) total, O(1) auxiliary
	// Best time complexity: O(n log n) swaps, O(n log n) comparisons
	// Average time complexity: O(n log n) swaps, O(n log n) comparisons
	// Worst time complexity: O(n log n) swaps, O(n log n) comparisons
	public static void heap(int[] a)
	{
		int n = a.length;
		for (int i = (n / 2) - 1; i >= 0; --i)
		{
			sink(a, i, n);
		}
		for (int i = 1; i < n; ++i)
		{
			swap(a, 0, n - i);
			sink(a, 0, n - i);
		}
	}
	
	// Stable
	// Not adaptive
	// Online
	// Comparison
	// Space complexity: O(n) total, O(n) auxiliary
	// Best time complexity: O(n log n) comparisons
	// Average time complexity: O(n log n) comparisons
	// Worst time complexity: O(n log n) comparisons
	public static void mergeTopDown(int[] a)
	{
		if (a.length < 2)
		{
			return;
		}
		int n = a.length;
		int[] b = new int[n / 2];
		split(a, b, 0, n);
	}
	
	public static void mergeBottomUp(int[] a)
	{
		int n = a.length;
		int[] b = new int[n / 2];
	}
	
	public static void quick(int[] a)
	{
		
	}
	
	public static void shell(int[] a)
	{
		
	}
	
	private static void copy(int[] a, int[] b, int i, int n)
	{
		for (int j = 0; j < n; ++j)
		{
			b[j] = a[i + j];
		}
	}
	
	private static void merge(int[] a, int[] b, int i, int m, int n)
	{
		copy(a, b, i, m);
		int h = 0;
		int j = i + m;
		int end = i + m + n - 1;
		int k = i;
		while (h < m && j <= end)
		{
			a[k++] = b[h] <= a[j] ? b[h++] : a[j++];
		}
		while (h < m)
		{
			a[k++] = b[h++];
		}
	}
	
	private static void sink(int[] a, int parent, int n)
	{
		while (true)
		{
			int left = parent * 2 + 1;
			if (left >= n)
			{
				return;
			}
			int right = left + 1;
			int child = right >= n ? left : a[left] >= a[right] ? left : right;
			if (a[parent] >= a[child])
			{
				break;
			}
			swap(a, parent, child);
			parent = child;
		}
	}
	
	private static void split(int[] a, int[] b, int i, int n)
	{
		if (n == 1)
		{
			return;
		}
		int m = n / 2;
		split(a, b, i, m);
		split(a, b, i + m, n - m);
		if (a[i + m - 1] > a[i + m])
		{
			merge(a, b, i, m, n - m);
		}
	}
	
	private static void swap(int[] a, int i, int j)
	{
		int x = a[i];
		a[i] = a[j];
		a[j] = x;
	}
}