public class Test
{
    private static String className;
    private static String sectionName;
    private static int failureCount;
    
    public static void testCharBuffer()
    {
        beginClass("CharBuffer");
        try
        {
            beginSection("basic");
            CharBuffer c = new CharBuffer();
            beginSection("empty");
            assertEquals("empty", "", c.toString());
            c.append("H");
            assertEquals("letter", "H", c.toString());
            c.append("ello");
            assertEquals("word", "Hello", c.toString());
            c.append(", ");
            c.append("world");
            c.append("!");
            assertEquals("phrase", "Hello, world!", c.toString());
        }
        catch (Exception e)
        {
            assertFail(e.getMessage());
        }
    }
    
    public static void testLinkedList()
    {
        beginClass("LinkedList");
        try
        {
            beginSection("empty init");
            LinkedList<Integer> ll = new LinkedList<Integer>();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            
            beginSection("empty clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            
            beginSection("empty addFirst one");
            ll.addFirst(0);
            assertFalse("empty", ll.empty());
            assertTrue("contains", ll.contains(0));
            assertEquals("size", 1, ll.size());
            assertEquals("index", 0, ll.index(0));
            assertEquals("get", 0, ll.get(0));
            
            beginSection("empty addFirst one clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            assertFalse("contains", ll.contains(0));
            assertEquals("index", -1, ll.index(0));
            
            beginSection("empty addLast one");
            ll.addLast(0);
            assertFalse("empty", ll.empty());
            assertTrue("contains", ll.contains(0));
            assertEquals("size", 1, ll.size());
            assertEquals("index", 0, ll.index(0));
            assertEquals("get", 0, ll.get(0));
            
            beginSection("empty addLast one clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            assertFalse("contains", ll.contains(0));
            assertEquals("index", -1, ll.index(0));
            
            beginSection("remove one");
            ll.addFirst(0);
            ll.remove(0);
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            beginSection("removeFirst one");
            ll.addFirst(0);
            ll.removeFirst();
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            beginSection("removeLast one");
            ll.addFirst(0);
            ll.removeLast();
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            for (int i = 0; i < 3; ++i)
            {
                beginSection("many addLast one" + i);
                ll.addLast(i);
                assertEquals("size", i + 1, ll.size());
                assertEquals("get", i, ll.get(i));
                assertEquals("index", i, ll.index(i));
                assertTrue("contains", ll.contains(i));
                assertFalse("empty", ll.empty());
            }
            
            for (int i = 2; i >= 0; --i)
            {
                beginSection("many removeLast" + i);
                int getValue = ll.get(i).intValue();
                int removeValue = ll.removeLast().intValue();
                assertEquals("removeLast == get", getValue, removeValue);
                assertEquals("size", i, ll.size());
                assertEquals("value", i, removeValue);
                assertEquals("index", -1, ll.index(removeValue));
                assertFalse("contains", ll.contains(removeValue));
                if (i > 0)
                {
                    assertFalse("empty", ll.empty());
                }
                else
                {
                    assertTrue("empty", ll.empty());
                }
            }
            
            beginSection("addLast many");
            LinkedList<Integer> ll2 = new LinkedList<Integer>();
            ll2.addLast(0);
            ll2.addLast(1);
            ll2.addLast(2);
            ll.addLast(ll2);
            
            beginSection("addLast many ll2");
            assertTrue("empty", ll2.empty());
            assertEquals("size", 0, ll2.size());
            assertFalse("contains 0", ll2.contains(0));
            assertFalse("contains 1", ll2.contains(1));
            assertFalse("contains 2", ll2.contains(2));
            assertEquals("index 0", -1, ll2.index(0));
            assertEquals("index 1", -1, ll2.index(1));
            assertEquals("index 2", -1, ll2.index(2));
            
            beginSection("addLast many ll");
            assertFalse("empty", ll.empty());
            assertEquals("size", 3, ll.size());
            assertTrue("contains 0", ll.contains(0));
            assertTrue("contains 1", ll.contains(1));
            assertTrue("contains 2", ll.contains(2));
            assertEquals("index 0", 0, ll.index(0));
            assertEquals("index 1", 1, ll.index(1));
            assertEquals("index 2", 2, ll.index(2));
            assertEquals("get 0", 0, ll.get(0));
            assertEquals("get 1", 1, ll.get(1));
            assertEquals("get 2", 2, ll.get(2));
            
            beginSection("addFirst many");
            ll.clear();
            ll2.addLast(0);
            ll2.addLast(1);
            ll2.addLast(2);
            ll.addFirst(ll2);
            
            beginSection("addFirst many ll2");
            assertTrue("empty", ll2.empty());
            assertEquals("size", 0, ll2.size());
            assertFalse("contains 0", ll2.contains(0));
            assertFalse("contains 1", ll2.contains(1));
            assertFalse("contains 2", ll2.contains(2));
            assertEquals("index 0", -1, ll2.index(0));
            assertEquals("index 1", -1, ll2.index(1));
            assertEquals("index 2", -1, ll2.index(2));
            
            beginSection("addFirst many ll");
            assertFalse("empty", ll.empty());
            assertEquals("size", 3, ll.size());
            assertTrue("contains 0", ll.contains(0));
            assertTrue("contains 1", ll.contains(1));
            assertTrue("contains 2", ll.contains(2));
            assertEquals("index 0", 0, ll.index(0));
            assertEquals("index 1", 1, ll.index(1));
            assertEquals("index 2", 2, ll.index(2));
            assertEquals("get 0", 0, ll.get(0));
            assertEquals("get 1", 1, ll.get(1));
            assertEquals("get 2", 2, ll.get(2));
            
        }
        catch (Exception e)
        {
            assertFail(e.getMessage());
        }
    }
    
    private static void beginClass(String className)
    {
        Test.className = className;
        sectionName = null;
    }
    
    private static void beginSection(String sectionName)
    {
        Test.sectionName = sectionName;
    }
    
    private static void printFailure(String detail)
    {
        String failure = className + ": ";
        if (sectionName != null)
        {
            failure += sectionName + ": ";
        }
        failure += detail;
        System.err.println(failure);
    }
    
    private static void assertEquals(String testName, Object expected, Object actual)
    {
        if (!expected.equals(actual))
        {
            printFailure(testName + ": expected=\"" + expected + "\", actual=\"" + actual + "\"");
            ++failureCount;
        }
    }
    
    private static void assertTrue(String testName, boolean condition)
    {
        if (!condition)
        {
            printFailure(testName + ": expected=true, actual=false");
            ++failureCount;
        }
    }
    
    private static void assertFalse(String testName, boolean condition)
    {
        if (condition)
        {
            printFailure(testName + ": expected=false, actual=true");
            ++failureCount;
        }
    }
    
    private static void assertFail(String message)
    {
        printFailure("exception=" + message);
        ++failureCount;
    }
    
    public static void main(String[] args)
    {
        testCharBuffer();
        testLinkedList();
        if (failureCount > 0)
        {
            System.err.println(failureCount + " failures");
        }
    }
}