package com.willfaught;

public class Arrays
{
    private Arrays()
    {
    }
    
    public static <E> void copy(E[] original, E[] copy, int originalIndex, int copyIndex, int length)
    {
        int originalLength = original.length;
        int copyLength = copy.length;
        if (originalIndex >= originalLength || copyIndex >= copyLength)
        {
            throw new IndexOutOfBoundsException();
        }
        if (originalIndex + length > originalLength || copyIndex + length > copyLength)
        {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < length; ++i)
        {
            copy[copyIndex + i] = original[originalIndex + i];
        }
    }
}