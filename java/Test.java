import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Test
{
	private interface ArraySorter
	{
		void sort(int[] items);
	}
	
    private static String sectionName;
    private static String subsectionName;
    private static int failures;
    
    public static void testCharBuffer()
    {
        beginSection("CharBuffer");
        try
        {
            beginSubsection("basic");
            CharBuffer c = new CharBuffer();
            beginSubsection("empty");
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
        beginSection("LinkedList");
        try
        {
            beginSubsection("empty init");
            LinkedList<Integer> ll = new LinkedList<Integer>();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            
            beginSubsection("empty clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            
            beginSubsection("empty addFirst one");
            ll.addFirst(0);
            assertFalse("empty", ll.empty());
            assertTrue("contains", ll.contains(0));
            assertEquals("size", 1, ll.size());
            assertEquals("index", 0, ll.index(0));
            assertEquals("get", 0, ll.get(0));
            
            beginSubsection("empty addFirst one clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            assertFalse("contains", ll.contains(0));
            assertEquals("index", -1, ll.index(0));
            
            beginSubsection("empty addLast one");
            ll.addLast(0);
            assertFalse("empty", ll.empty());
            assertTrue("contains", ll.contains(0));
            assertEquals("size", 1, ll.size());
            assertEquals("index", 0, ll.index(0));
            assertEquals("get", 0, ll.get(0));
            
            beginSubsection("empty addLast one clear");
            ll.clear();
            ll.clear();
            assertTrue("empty", ll.empty());
            assertEquals("size", 0, ll.size());
            assertFalse("contains", ll.contains(0));
            assertEquals("index", -1, ll.index(0));
            
            beginSubsection("remove one");
            ll.addFirst(0);
            ll.remove(0);
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            beginSubsection("removeFirst one");
            ll.addFirst(0);
            ll.removeFirst();
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            beginSubsection("removeLast one");
            ll.addFirst(0);
            ll.removeLast();
            assertTrue("empty", ll.empty());
            assertFalse("contains", ll.contains(0));
            assertEquals("size", 0, ll.size());
            assertEquals("index", -1, ll.index(0));
            
            for (int i = 0; i < 3; ++i)
            {
                beginSubsection("many addLast one" + i);
                ll.addLast(i);
                assertEquals("size", i + 1, ll.size());
                assertEquals("get", i, ll.get(i));
                assertEquals("index", i, ll.index(i));
                assertTrue("contains", ll.contains(i));
                assertFalse("empty", ll.empty());
            }
            
            for (int i = 2; i >= 0; --i)
            {
                beginSubsection("many removeLast" + i);
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
            
            beginSubsection("addLast many");
            LinkedList<Integer> ll2 = new LinkedList<Integer>();
            ll2.addLast(0);
            ll2.addLast(1);
            ll2.addLast(2);
            ll.addLast(ll2);
            
            beginSubsection("addLast many ll2");
            assertTrue("empty", ll2.empty());
            assertEquals("size", 0, ll2.size());
            assertFalse("contains 0", ll2.contains(0));
            assertFalse("contains 1", ll2.contains(1));
            assertFalse("contains 2", ll2.contains(2));
            assertEquals("index 0", -1, ll2.index(0));
            assertEquals("index 1", -1, ll2.index(1));
            assertEquals("index 2", -1, ll2.index(2));
            
            beginSubsection("addLast many ll");
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
            
            beginSubsection("addFirst many");
            ll.clear();
            ll2.addLast(0);
            ll2.addLast(1);
            ll2.addLast(2);
            ll.addFirst(ll2);
            
            beginSubsection("addFirst many ll2");
            assertTrue("empty", ll2.empty());
            assertEquals("size", 0, ll2.size());
            assertFalse("contains 0", ll2.contains(0));
            assertFalse("contains 1", ll2.contains(1));
            assertFalse("contains 2", ll2.contains(2));
            assertEquals("index 0", -1, ll2.index(0));
            assertEquals("index 1", -1, ll2.index(1));
            assertEquals("index 2", -1, ll2.index(2));
            
            beginSubsection("addFirst many ll");
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

	private static void testSelectionSort()
	{
		beginSection("Selection sort");
		testArraySorter(new ArraySorter()
		{
			public void sort(int[] items)
			{
				ArraySort.selection(items);
			}
		});
	}
	
	private static void testBubbleSort()
	{
		beginSection("Bubble sort");
		testArraySorter(new ArraySorter()
		{
			public void sort(int[] items)
			{
				ArraySort.bubble(items);
			}
		});
	}
	
	private static void testInsertionSort()
	{
		beginSection("Insertion sort");
		testArraySorter(new ArraySorter()
		{
			public void sort(int[] items)
			{
				ArraySort.insertion(items);
			}
		});
	}
	
	private static void testArraySorter(ArraySorter arraySorter)
	{
		try
		{
			int[] array = new int[0];
			arraySorter.sort(array);
			
			array = new int[] { 0 };
			arraySorter.sort(array);
			assertEquals("one", 0, array[0]);
			
			array = new int[] { 0, 1 };
			arraySorter.sort(array);
			assertEquals("two sorted", new int[] { 0, 1 }, array);
			
			array = new int[] { 1, 0 };
			arraySorter.sort(array);
			assertEquals("two reversed", new int[] { 0, 1 }, array);
			
			array = new int[] { 0, 1, 2, 3, 4 };
			arraySorter.sort(array);
			assertEquals("five sorted", new int[] { 0, 1, 2, 3, 4 }, array);
			
			array = new int[] { 4, 3, 2, 1, 0 };
			arraySorter.sort(array);
			assertEquals("five reversed", new int[] { 0, 1, 2, 3, 4 }, array);
			
			array = new int[100];
			int[] array2 = new int[100];
			for (int i = 0; i < 100; ++i)
			{
				array[i] = i;
				array2[i] = i;
			}
			arraySorter.sort(array);
			assertEquals("hundred sorted", array2, array);
			
			array = Arrays.copyOf(array2, array2.length);
			for (int i = 0; i < array.length / 2; ++i)
			{
				int mirrorValue = array[i];
				int mirrorIndex = array.length - i - 1;
				array[i] = array[mirrorIndex];
				array[mirrorIndex] = mirrorValue;
			}
			arraySorter.sort(array);
			assertEquals("hundred reversed", array2, array);
			
			array = new int[(int)(Math.random() * 100)];
			boolean sorted = true;
			do
			{
				for (int i = 0; i < array.length; ++i)
				{
					array[i] = (int)(Math.random() * Integer.MAX_VALUE);
				}
				for (int i = 1; i < array.length; ++i)
				{
					if (array[i - 1] > array[i])
					{
						sorted = false;
					}
				}
			}
			while (sorted);
			arraySorter.sort(array);
			for (int i = 1; i < array.length; ++i)
			{
				assertTrue("random random: indices=" + (i - 1) + ", " + i + "; values=" + array[i - 1] + ", " + array[i], array[i - 1] <= array[i]);
			}
		}
		catch (Exception e)
		{
			assertFail(e.getMessage());
		}
	}
    
    private static void beginSection(String sectionName)
    {
        Test.sectionName = sectionName;
        subsectionName = null;
    }
    
    private static void beginSubsection(String subsectionName)
    {
        Test.subsectionName = subsectionName;
    }
    
    private static void printFailure(String detail)
    {
        String failure = sectionName + ": ";
        if (subsectionName != null)
        {
            failure += subsectionName + ": ";
        }
        failure += detail;
        System.err.println(failure);
    }

	private static String string(Object object)
	{
		if (object instanceof int[])
		{
			return Arrays.toString((int[])object);
		}
		return object.toString();
	}
	
	private static boolean equals(Object expected, Object actual)
	{
		if (expected instanceof int[])
		{
			if (actual instanceof int[])
			{
				return Arrays.equals((int[])expected, (int[])actual);
			}
			return false;
		}
		return expected.equals(actual);
	}
    
    private static void assertEquals(String testName, Object expected, Object actual)
    {
		if (!equals(expected, actual))
        {
            printFailure(testName + ": expected=\"" + string(expected) + "\", actual=\"" + string(actual) + "\"");
            ++failures;
        }
    }
    
    private static void assertTrue(String testName, boolean condition)
    {
        if (!condition)
        {
            printFailure(testName + ": expected=true, actual=false");
            ++failures;
        }
    }
    
    private static void assertFalse(String testName, boolean condition)
    {
        if (condition)
        {
            printFailure(testName + ": expected=false, actual=true");
            ++failures;
        }
    }
    
    private static void assertFail(String message)
    {
        printFailure("exception=" + message);
        ++failures;
    }
    
    public static void main(String[] args)
    {
        testCharBuffer();
        testLinkedList();
		testSelectionSort();
		testBubbleSort();
		testInsertionSort();
        if (failures > 0)
        {
            System.err.println(failures + " failures");
        }
    }
}