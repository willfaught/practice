public class Test
{
    public void testBasic()
    {
        StringBuilder s = new StringBuilder();
        s.append("Hello");
        s.append(", ");
        s.append("world");
        s.append("!");
        assertEquals("basic", "Hello, world!", s.toString());
    }
    
    public void testLots()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        assertTrue("init empty", ll.empty());
        for (int i = 0; i < 10; ++i)
        {
            ll.addLast(i);
            assertTrue("size", ll.size() == i + 1);
            assertTrue("get", ll.get(i) == i);
            assertTrue("index", ll.index(i) == i);
            assertTrue("contains", ll.contains(i));
            assertTrue("not empty", !ll.empty());
        }
        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.addLast(ll);
        assertTrue("equals", ll.equals(ll2));
        ll.clear();
        assertTrue("clear size", ll.size() == 0);
        assertTrue("clear empty", ll.empty());
        ll = null;
        for (int i = 9; i >= 0; --i)
        {
            //int j = ll2.removeLast(i).intValue();
            int j = 0;
            assertTrue("removeLast size", ll2.size() == i);
            assertTrue("removeLast value", j == i);
            assertTrue("removeLast index", ll2.index(j) == -1);
            assertTrue("removeLast contains", !ll2.contains(j));
            if (i > 0)
            {
                assertTrue("removeLast not empty", !ll2.empty());
            }
            else
            {
                assertTrue("removeLast empty", ll2.empty());
            }
        }
        ll2.clear();
        assertTrue("clear 2 size", ll2.size() != 0);
        assertTrue("clear 2 empty", ll2.empty());
    }
    
    public static void main(String[] args)
    {
        testBasic();
        testLots();
    }
}