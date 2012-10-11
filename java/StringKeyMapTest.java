package com.willfaught;

import static com.willfaught.Assert.*;
import java.util.Iterator;

public class StringKeyMapTest
{
    private interface StringKeyMap<V>
    {
        V add(String key, V value);

        void clear();

        Iterator<String> keysThatMatch(String pattern);

        Iterator<String> keysWithPrefix(String prefix);

        String longestPrefixOf(String string);
    }

    private static void test(StringKeyMap<Integer> m)
    {
        // TODO
    }

    @Test
    public void ternarySearchTree()
    {
        test(new StringKeyMap<Integer>()
        {
            private TernarySearchTree<Integer> tst = new TernarySearchTree<Integer>();

            public Integer add(String key, Integer value)
            {
                return tst.add(key, value);
            }

            public void clear()
            {
                tst.clear();
            }

            public Iterator<String> keysThatMatch(String pattern)
            {
                return tst.keysThatMatch(pattern);
            }

            public Iterator<String> keysWithPrefix(String prefix)
            {
                return tst.keysWithPrefix(prefix);
            }

            public String longestPrefixOf(String string)
            {
                return tst.longestPrefixOf(string);
            }
        });
    }

    @Test
    public void trie()
    {
        test(new StringKeyMap<Integer>()
        {
            private Trie<Integer> trie = new Trie<Integer>();

            public Integer add(String key, Integer value)
            {
                return trie.add(key, value);
            }

            public void clear()
            {
                trie.clear();
            }

            public Iterator<String> keysThatMatch(String pattern)
            {
                return trie.keysThatMatch(pattern);
            }

            public Iterator<String> keysWithPrefix(String prefix)
            {
                return trie.keysWithPrefix(prefix);
            }

            public String longestPrefixOf(String string)
            {
                return trie.longestPrefixOf(string);
            }
        });
    }
}