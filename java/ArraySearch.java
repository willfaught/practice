package com.willfaught;

import java.util.Comparator;

public class ArraySearch
{
    public static <T> int binary(T[] a, T t, Comparator<T> c)
    {
        if (a == null || t == null || c == null)
        {
            throw new IllegalArgumentException();
        }
        if (a.length == 0)
        {
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        int i = a.length / 2;
        while (low <= high)
        {
            int x = c.compare(t, a[i]);
            if (x == 0)
            {
                return i;
            }
            if (x < 0)
            {
                high = i - 1;
            }
            else
            {
                low = i + 1;
            }
            i = ((high - low) / 2) + low;
        }
        return -1;
    }

    public static <T> int linear(T[] a, T t, Comparator<T> c)
    {
        if (a == null || t == null || c == null)
        {
            throw new IllegalArgumentException();
        }
        if (a.length == 0)
        {
            return -1;
        }
        for (int i = 0; i < a.length; ++i)
        {
            if (c.compare(a[i], t) == 0)
            {
                return i;
            }
        }
        return -1;
    }

    private ArraySearch()
    {
    }
}