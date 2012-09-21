package com.willfaught;

import java.util.Comparator;

public class ArraySort
{
    // Stable
    // Adaptive
    // Offline
    // Comparison
    // Worst case space complexity: O(n) total, O(1) auxiliary
    // Best time complexity: O(1) swaps, O(n) comparisons
    // Average time complexity: O(n^2) swaps, comparisons
    // Worst time complexity: O(n^2) swaps, comparisons
    public static <T> void bubble(T[] a, Comparator<T> c)
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
                if (c.compare(a[i - 1], a[i]) > 0)
                {
                    swap(a, i - 1, i);
                    j = i;
                }
            }
            n = j;
        }
    }

    private static <T> void copy(T[] a, T[] b, int i, int j)
    {
        int k = 0;
        while (i <= j)
        {
            b[k++] = a[i++];
        }
    }

    // Unstable
    // Not adaptive
    // Offline
    // Comparison
    // Worst case space complexity: O(n) total, O(1) auxiliary
    // Best time complexity: O(n log n) swaps, O(n log n) comparisons
    // Average time complexity: O(n log n) swaps, O(n log n) comparisons
    // Worst time complexity: O(n log n) swaps, O(n log n) comparisons
    public static <T> void heap(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        for (int i = (n / 2) - 1; i >= 0; --i)
        {
            sink(a, c, i, n);
        }
        for (int i = 1; i < n; ++i)
        {
            swap(a, 0, n - i);
            sink(a, c, 0, n - i);
        }
    }

    // Stable
    // Adaptive
    // Online
    // Comparison
    // Worst case space complexity: O(n) total, O(1) auxiliary
    // Best time complexity: O(1) swaps, O(n) comparisons
    // Average time complexity: O(n^2) swaps, comparisons
    // Worst time complexity: O(n^2) swaps, comparisons
    public static <T> void insertion(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        for (int i = 1; i < n; ++i)
        {
            int j;
            T x = a[i];
            for (j = i; j > 0; --j)
            {
                if (c.compare(a[j - 1], x) <= 0)
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

    private static <T> void merge(T[] a, T[] b, Comparator<T> c, int i, int j, int k)
    {
        copy(a, b, i, j - 1);
        int x = 0;
        int xmax = j - i - 1;
        int y = j;
        int ymax = k;
        int z = i;
        while (x <= xmax && y <= ymax)
        {
            a[z++] = c.compare(b[x], a[y]) <= 0 ? b[x++] : a[y++];
        }
        while (x <= xmax)
        {
            a[z++] = b[x++];
        }
    }

    // Stable
    // Not adaptive
    // Online
    // Comparison
    // Worst case space complexity: O(n) total, O(n) auxiliary
    // Best time complexity: O(n log n) comparisons, O(n log n) writes
    // Average time complexity: O(n log n) comparisons, O(n log n) writes
    // Worst time complexity: O(n log n) comparisons, O(n log n) writes
    @SuppressWarnings("unchecked")
    public static <T> void mergeBottomUp(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        T[] b = (T[])new Object[n];
        for (int w = 1; w < n; w *= 2)
        {
            int w2 = w * 2;
            for (int i = 0; i + w < n; i += w2)
            {
                merge(a, b, c, i, i + w, Math.min(n - 1, i + w2 - 1));
            }
        }
    }

    // Stable
    // Not adaptive
    // Online
    // Comparison
    // Worst case space complexity: O(n) total, O(n) auxiliary
    // Best time complexity: O(n log n) comparisons
    // Average time complexity: O(n log n) comparisons
    // Worst time complexity: O(n log n) comparisons
    @SuppressWarnings("unchecked")
    public static <T> void mergeTopDown(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        T[] b = (T[])new Object[n / 2];
        split(a, b, c, 0, n - 1);
    }

    // Unstable
    // Adaptive
    // Offline
    // Comparison
    // Worst case space complexity: O(log n) auxiliary
    // Best time complexity: O(n log n) comparisons
    // Average time complexity: O(n log n) comparisons
    // Worst time complexity: O(n^2) comparisons
    public static <T> void quick(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        quick(a, c, 0, a.length);
    }

    private static <T> void quick(T[] a, Comparator<T> c, int i, int n)
    {
        if (n <= 1)
        {
            return;
        }
        T pivot = a[i + (int)(Math.random() * n)];
        int j = i, p = i - 1, q = i + n;
        while (j < q)
        {
            int x = c.compare(a[j], pivot);
            if (x < 0)
            {
                swap(a, ++p, j++);
            }
            else if (x > 0)
            {
                swap(a, --q, j);
            }
            else
            {
                ++j;
            }
        }
        quick(a, c, i, p - i + 1);
        quick(a, c, q, i + n - q);
    }

    // Unstable
    // Not adaptive
    // Offline
    // Comparison
    // Worst case space complexity: O(n) total, O(1) auxiliary
    // Best time complexity: O(n) swaps, O(n^2) comparisons
    // Average time complexity: O(n) swaps, O(n^2) comparisons
    // Worst time complexity: O(n) swaps, O(n^2) comparisons
    public static <T> void selection(T[] a, Comparator<T> c)
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
                if (c.compare(a[k], a[j]) < 0)
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

    // Unstable
    // Adaptive
    // Offline
    // Comparison
    // Worst case space complexity: O(n) total, O(1) auxiliary
    // Best time complexity: ?
    // Average time complexity: ?
    // Worst time complexity: O(n^3/2) comparisons
    public static <T> void shell(T[] a, Comparator<T> c)
    {
        if (a == null || a.length <= 1)
        {
            return;
        }
        int n = a.length;
        int h = 1;
        while (h < n / 3)
        {
            h = h * 3 + 1;
        }
        while (h >= 1)
        {
            for (int i = h; i < n; i += h)
            {
                int j;
                T x = a[i];
                for (j = i; j > 0; j -= h)
                {
                    if (c.compare(a[j - h], x) <= 0)
                    {
                        break;
                    }
                    a[j] = a[j - h];
                }
                if (j != i)
                {
                    a[j] = x;
                }
            }
            h /= 3;
        }
    }

    private static <T> void sink(T[] a, Comparator<T> c, int parent, int n)
    {
        while (true)
        {
            int left = parent * 2 + 1;
            if (left >= n)
            {
                return;
            }
            int right = left + 1;
            int child = right >= n ? left : c.compare(a[left], a[right]) >= 0 ? left : right;
            if (c.compare(a[parent], a[child]) >= 0)
            {
                break;
            }
            swap(a, parent, child);
            parent = child;
        }
    }

    private static <T> void split(T[] a, T[] b, Comparator<T> c, int i, int k)
    {
        int n = k - i + 1;
        if (n == 1)
        {
            return;
        }
        int j = i + (n / 2);
        int m = j - 1;
        split(a, b, c, i, m); // TODO: move n == 1 check to these
        split(a, b, c, j, k);
        if (c.compare(a[m], a[j]) > 0)
        {
            merge(a, b, c, i, j, k);
        }
    }

    private static <T> void swap(T[] a, int i, int j)
    {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    private ArraySort()
    {
    }
}