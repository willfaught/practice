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
        if (a == null || a.length <= 1)
        {
            return;
        }
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
        if (a == null || a.length <= 1)
        {
            return;
        }
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
        if (a == null || a.length <= 1)
        {
            return;
        }
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
        if (a == null || a.length <= 1)
        {
            return;
        }
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
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        int[] b = new int[n / 2];
        split(a, b, 0, n - 1);
    }
    
    public static void mergeBottomUp(int[] a)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        int[] b = new int[n];
        for (int w = 1; w < n; w *= 2)
        {
            int w2 = w * 2;
            for (int i = 0; i + w < n; i += w2)
            {
                merge(a, b, i, i + w, Math.min(n - 1, i + w2 - 1));
            }
        }
    }
    
    public static void quick(int[] a)
    {
        
    }
    
    public static void shell(int[] a)
    {
        
    }
    
    private static void copy(int[] a, int[] b, int i, int j)
    {
        int k = 0;
        while (i <= j)
        {
            b[k++] = a[i++];
        }
    }
    
    private static void merge(int[] a, int[] b, int i, int j, int k)
    {
        copy(a, b, i, j - 1);
        int x = 0;
        int xmax = j - i - 1;
        int y = j;
        int ymax = k;
        int z = i;
        while (x <= xmax && y <= ymax)
        {
            a[z++] = b[x] <= a[y] ? b[x++] : a[y++];
        }
        while (x <= xmax)
        {
            a[z++] = b[x++];
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
    
    private static void split(int[] a, int[] b, int i, int k)
    {
        int n = k - i + 1;
        if (n == 1)
        {
            return;
        }
        int j = i + (n / 2);
        int m = j - 1;
        split(a, b, i, m); // TODO: move n == 1 check to these
        split(a, b, j, k);
        if (a[m] > a[j])
        {
            merge(a, b, i, j, k);
        }
    }
    
    private static void swap(int[] a, int i, int j)
    {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}