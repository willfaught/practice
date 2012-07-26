package com.willfaught.data;

public class StringBuilder
{
    private int length;
    private int size = 256;
    private char[] buffer = new char[size];
    
    public StringBuilder()
    {
        this(256);
    }
    
    public StringBuilder(int size)
    {
        
    }
    
    public void append(String s)
    {
        int sLength = s.length();
        char[] sBuffer = s.toCharArray();
        if (sLength + length > size)
        {
            size = (sLength + length) * 2;
            char[] buffer2 = new char[size];
            for (int i = 0; i < length; ++i)
            {
                buffer2[i] = buffer[i];
            }
            buffer = buffer2;
        }
        for (int i = 0; i < sLength; ++i)
        {
            buffer[length + i] = sBuffer[i];
        }
        length += sLength;
    }
    
    public String toString()
    {
        return new String(buffer);
    }
}