package com.willfaught;

public class CharArray
{
    private int size;
    private int capacity = 256;
    private char[] buffer = new char[capacity];
    
    public void append(Object o)
    {
        String s = o.toString();
        int sLength = s.length();
        char[] sBuffer = s.toCharArray();
        if (sLength + size > capacity)
        {
            capacity = (sLength + size) * 2;
            char[] buffer2 = new char[capacity];
            for (int i = 0; i < size; ++i)
            {
                buffer2[i] = buffer[i];
            }
            buffer = buffer2;
        }
        for (int i = 0; i < sLength; ++i)
        {
            buffer[size + i] = sBuffer[i];
        }
        size += sLength;
    }
    
    public int capacity()
    {
        return capacity;
    }
    
    public void ensure(int capacity)
    {
        // TODO
    }
    
    public char get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }
    
    public void insert(int index, char c)
    {
        // TODO
    }
    
    public void put(int index, char c)
    {
        // TODO
    }
    
    public void remove(int index)
    {
        // TODO
    }
    
    public void resize(int size)
    {
        // TODO
    }
    
    public void reverse(int size)
    {
        // TODO
    }
    
    public int size()
    {
        return size;
    }
    
    public void trim()
    {
        // TODO
    }
        
    public String toString()
    {
        return new String(buffer, 0, size);
    }
}