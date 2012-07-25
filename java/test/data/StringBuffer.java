package com.willfaught.test.data;

import com.willfaught.data.StringBuffer;

public class StringBuffer
{
    public static void main(String[] args)
    {
        StringBuffer s = new StringBuffer();
        s.append("");
        s.append(", ");
        s.append("world");
        s.append("!");
        System.out.println("\"" + s + "\"");
    }
}