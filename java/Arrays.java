package com.willfaught;

public class Arrays
{
    private Arrays()
    {
    }
    
    public static void copy(int[] source, int[] destination, int sourceIndex, int destinationIndex, int length)
    {
		if (source == null || destination == null || length < 0)
		{
			throw new IllegalArgumentException();
		}
		if (length == 0)
		{
			return;
		}
        int sourceLength = source.length;
        int destinationLength = destination.length;
        if (sourceIndex >= sourceLength || destinationIndex >= destinationLength)
        {
            throw new IndexOutOfBoundsException();
        }
        if (sourceIndex + length > sourceLength || destinationIndex + length > destinationLength)
        {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < length; ++i)
        {
            destination[destinationIndex + i] = source[sourceIndex + i];
        }
    }
}