package com.willfaught;

import static com.willfaught.Assert.*;

public class ArraySortTest
{
    private interface ArraySorter
    {
        void sort(int[] a);
    }
    
    @Test
    public void bubbleSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.bubble(a);
            }
        });
    }
    
    @Test
    public void heapSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.heap(a);
            }
        });
    }
    
    @Test
    public void insertionSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.insertion(a);
            }
        });
    }
    
    @Test
    public void mergeBottomUpSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.mergeBottomUp(a);
            }
        });
    }
    
    @Test
    public void mergeTopDownSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.mergeTopDown(a);
            }
        });
    }
    
    @Test
    public void selectionSort()
    {
        sort(new ArraySorter()
        {
            @Override
			public void sort(int[] a)
            {
                ArraySort.selection(a);
            }
        });
    }
    
    private static void sort(ArraySorter arraySorter)
    {
        int[] array = new int[0];
        arraySorter.sort(array);
        
        array = new int[] { 1 };
        arraySorter.sort(array);
        assertExpected("one", 1, array[0]);
        
        array = new int[] { 1, 2 };
        arraySorter.sort(array);
        assertExpected("two sorted", new int[] { 1, 2 }, array);
        
        array = new int[] { 2, 1 };
        arraySorter.sort(array);
        assertExpected("two reversed", new int[] { 1, 2 }, array);
        
        array = new int[] { 1, 1 };
        arraySorter.sort(array);
        assertExpected("two same", new int[] { 1, 1 }, array);
        
        array = new int[] { 1, 2, 3 };
        arraySorter.sort(array);
        assertExpected("three sorted", new int[] { 1, 2, 3 }, array);
        
        array = new int[] { 3, 2, 1 };
        arraySorter.sort(array);
        assertExpected("three reversed", new int[] { 1, 2, 3 }, array);
        
        array = new int[] { 1, 1, 1 };
        arraySorter.sort(array);
        assertExpected("three same", new int[] { 1, 1, 1 }, array);
        
        array = new int[] { 1, 2, 3, 4, 5 };
        arraySorter.sort(array);
        assertExpected("five sorted", new int[] { 1, 2, 3, 4, 5 }, array);
        
        array = new int[] { 5, 4, 3, 2, 1 };
        arraySorter.sort(array);
        assertExpected("five reversed", new int[] { 1, 2, 3, 4, 5 }, array);
        
        array = new int[] { 1, 1, 1, 1, 1 };
        arraySorter.sort(array);
        assertExpected("five same", new int[] { 1, 1, 1, 1, 1 }, array);
        
        array = new int[101];
        int[] array2 = new int[101];
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = array2[i] = i + 1;
        }
        arraySorter.sort(array);
        assertEqual("odd many sorted", array, array2);
        
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = array.length - i;
        }
        arraySorter.sort(array);
        assertEqual("odd many reversed", array, array2);
        
        array = new int[100];
        array2 = new int[100];
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = array2[i] = i + 1;
        }
        arraySorter.sort(array);
        assertEqual("even many sorted", array, array2);
        
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = array.length - i;
        }
        arraySorter.sort(array);
        assertEqual("even many reversed", array, array2);
        
        array = new int[101];
        boolean sorted = true;
        do
        {
            for (int i = 0; i < array.length; ++i)
            {
                array[i] = (int)(Math.random() * Integer.MAX_VALUE);
            }
            for (int i = 1; i < array.length; ++i)
            {
                if (array[i - 1] > array[i])
                {
                    sorted = false;
                }
            }
        }
        while (sorted);
        arraySorter.sort(array);
        for (int i = 1; i < array.length; ++i)
        {
            boolean condition = array[i - 1] <= array[i];
            assertTrue("random random: indices=" + (i - 1) + ", " + i + "; values=" + array[i - 1] + ", " + array[i], condition);
            if (!condition)
            {
                break;
            }
        }
    }
}