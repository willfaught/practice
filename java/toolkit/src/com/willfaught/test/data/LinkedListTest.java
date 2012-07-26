package com.willfaught.test.data;

import com.willfaught.data.LinkedList;

public class LinkedListTest
{
    public static void main(String[] args)
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        assert ll.empty() : "init empty";
        for (int i = 0; i < 10; ++i)
        {
            ll.addLast(i);
            assert ll.size() == i + 1 : "size";
            assert ll.get(i) == i : "get";
            assert ll.index(i) == i : "index";
            assert ll.contains(i) : "contains";
            assert !ll.empty() : "not empty";
        }
        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.addLast(ll);
        assert ll.equals(ll2) : "equals";
        ll.clear();
        assert ll.size() == 0 : "clear size";
        assert ll.empty() : "clear empty";
        ll = null;
        for (int i = 9; i >= 0; --i)
        {
            //int j = ll2.removeLast(i).intValue();
            int j = 0;
            assert ll2.size() == i : "removeLast size";
            assert j == i : "removeLast value";
            assert ll2.index(j) == -1 : "removeLast index";
            assert !ll2.contains(j) : "removeLast contains";
            if (i > 0)
            {
                assert !ll2.empty() : "removeLast not empty";
            }
            else
            {
                assert ll2.empty() : "removeLast empty";
            }
        }
        ll2.clear();
        assert ll2.size() != 0 : "clear 2 size";
        assert ll2.empty() : "clear 2 empty";
    }
}