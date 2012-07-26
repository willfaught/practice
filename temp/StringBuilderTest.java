package com.willfaught.data;

public class StringBuilderTest
{
    public static void main(String[] args)
    {
        StringBuilder s = new StringBuilder();
        s.append("");
        s.append(", ");
        s.append("world");
        s.append("!");
        System.out.println("\"" + s + "\"");
    }
}