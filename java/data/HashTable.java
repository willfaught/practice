package com.willfaught.data;

public class HashTable<K, V>
{
    private Object[] values;
    
    private Object[] getValues()
    {
        if (values == null)
        {
            values = new Object[101];
        }
        return values;
    }
    
    public void put(K key, V value)
    {
        int index = key.hashCode() % values.length;
        Object[] values = getValues();
        Node node = (Node)values[index];
        if (node == null)
        {
            node = new Node(key, value);
            values[index] = node;
        }
        else
        {
            if (!node.replace(key, value))
            {
                node.append(key, value);
            }
        }
    }
    
    public V get(K key)
    {
        int index = key.hashCode() % values.length;
        Object[] values = getValues();
        Node node = (Node)values[index];
        if (node == null)
        {
            return null;
        }
        
    }
    
    private class Node
    {
        private Object key;
        
        private Object value;
        
        private Node next;
        
        public Node(Object key, Object value)
        {
            this.key = key;
            this.value = value;
        }
        
        public boolean replace(Object key, Object value)
        {
            Node node = this;
            do
            {
                if (node.key.equals(key))
                {
                    node.value = value;
                    return true;
                }
                node = node.next;
            }
            while (node != null);
            return false;
        }
        
        public void append(Object key, Object value)
        {
            Node node = this;
            while (node.next != null)
            {
                node = node.next;
            }
            Node newNode = new Node(key, value);
            node.next = newNode;
        }
    }
    
    public static void main(String[] args)
    {
        HashTable
    }
}