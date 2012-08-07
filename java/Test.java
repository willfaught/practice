import java.util.Arrays;

public class Test
{
	private interface ArraySorter
	{
		void sort(int[] items);
	}
	
    private static String sectionName;
    private static String subsectionName;
    private static int failures;
    
    private static void testCharBuffer()
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
            fail(e);
        }
    }
    
    private static void testArrayList()
	{
		beginSection("ArrayList");
		testList(new ArrayList<Integer>(), new ArrayList<Integer>());
	}
	
	private static void testLinkedList()
	{
		beginSection("LinkedList");
		testList(new LinkedList<Integer>(), new LinkedList<Integer>());
	}

	private static void testList(List<Integer> list1, List<Integer> list2)
    {
        try
        {
			beginSubsection("init");
            assertTrue("empty", list1.empty());
            assertEquals("size", 0, list1.size());

			beginSubsection("empty clear");
			list1.clear();
			assertTrue("empty", list1.empty());
            assertEquals("size", 0, list1.size());
			
			beginSubsection("add");
			assertEquals("1", 0, list1.add(1));
			assertEquals("2", 1, list1.add(2));
			assertEquals("3", 2, list1.add(3));
			assertFalse("empty", list1.empty());
            assertEquals("size", 3, list1.size());
			assertFalse("contains 0", list1.contains(0));
			assertTrue("contains 1", list1.contains(1));
			assertTrue("contains 2", list1.contains(2));
			assertTrue("contains 3", list1.contains(3));
            assertEquals("index 0", -1, list1.index(0));
			assertEquals("index 1", 0, list1.index(1));
			assertEquals("index 2", 1, list1.index(2));
			assertEquals("index 3", 2, list1.index(3));
            assertEquals("get 0", 1, list1.get(0));
			assertEquals("get 1", 2, list1.get(1));
			assertEquals("get 2", 3, list1.get(2));
			try
			{
				list1.get(-1);
				fail("expected IndexOutOfBoundsException -1");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException -1");
			}
			try
			{
				list1.get(3);
				fail("expected IndexOutOfBoundsException 3");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException 3");
			}
			
			beginSubsection("full clear");
			list1.clear();
			assertTrue("empty", list1.empty());
            assertEquals("size", 0, list1.size());
			
			beginSubsection("add index");
			list1.add(0, 2);
			list1.add(0, 1);
			list1.add(2, 3);
			try
			{
				list1.add(-1, 5);
				fail("expected IndexOutOfBoundsException -1");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException -1");
			}
			try
			{
				list1.add(4, 5);
				fail("expected IndexOutOfBoundsException 4");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException 4");
			}
			assertFalse("empty", list1.empty());
            assertEquals("size", 3, list1.size());
			assertTrue("contains 1", list1.contains(1));
			assertTrue("contains 2", list1.contains(2));
			assertTrue("contains 3", list1.contains(3));
            assertEquals("index 1", 0, list1.index(1));
			assertEquals("index 2", 1, list1.index(2));
			assertEquals("index 3", 2, list1.index(3));
            assertEquals("get 0", 1, list1.get(0));
			assertEquals("get 1", 2, list1.get(1));
			assertEquals("get 2", 3, list1.get(2));
			
			beginSubsection("set");
			assertEquals("set 0", 1, list1.set(0, 4));
			assertEquals("set 1", 2, list1.set(1, 5));
			assertEquals("set 2", 3, list1.set(2, 6));
			try
			{
				list1.set(-1, 7);
				fail("expected IndexOutOfBoundsException -1");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException -1");
			}
			try
			{
				list1.set(3, 7);
				fail("expected IndexOutOfBoundsException 3");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException 3");
			}
			assertFalse("empty", list1.empty());
            assertEquals("size", 3, list1.size());
			assertFalse("contains 1", list1.contains(1));
			assertFalse("contains 2", list1.contains(2));
			assertFalse("contains 3", list1.contains(3));
			assertTrue("contains 4", list1.contains(4));
			assertTrue("contains 5", list1.contains(5));
			assertTrue("contains 6", list1.contains(6));
            assertEquals("index 1", -1, list1.index(1));
			assertEquals("index 2", -1, list1.index(2));
			assertEquals("index 3", -1, list1.index(3));
			assertEquals("index 4", 0, list1.index(4));
			assertEquals("index 5", 1, list1.index(5));
			assertEquals("index 6", 2, list1.index(6));
			assertEquals("get 0", 4, list1.get(0));
			assertEquals("get 1", 5, list1.get(1));
			assertEquals("get 2", 6, list1.get(2));
			
			beginSubsection("remove");
			assertEquals("remove 2", 6, list1.remove(2).intValue());
			assertEquals("remove 1", 5, list1.remove(1).intValue());
			try
			{
				list1.remove(-1);
				fail("expected IndexOutOfBoundsException -1");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException -1");
			}
			assertEquals("remove 0", 4, list1.remove(0).intValue());
			try
			{
				list1.remove(0);
				fail("expected IndexOutOfBoundsException 0");
			}
			catch (IndexOutOfBoundsException e)
			{
			}
			catch (Exception e)
			{
				fail(e, "IndexOutOfBoundsException 0");
			}
            assertTrue("empty", list1.empty());
            assertEquals("size", 0, list1.size());
			assertFalse("contains 4", list1.contains(4));
			assertFalse("contains 5", list1.contains(5));
			assertFalse("contains 6", list1.contains(6));
			assertEquals("index 4", -1, list1.index(4));
			assertEquals("index 5", -1, list1.index(5));
			assertEquals("index 6", -1, list1.index(6));
            
            for (int i = 0; i < 50; ++i)
            {
                beginSubsection("many add one " + i);
                list1.add(i);
                assertEquals("size", i + 1, list1.size());
                assertEquals("get", i, list1.get(i));
                assertEquals("index", i, list1.index(i));
                assertTrue("contains", list1.contains(i));
                assertFalse("empty", list1.empty());
            }
            
            for (int i = 49; i >= 0; --i)
            {
                beginSubsection("many removeLast " + i);
                int getValue = list1.get(i).intValue();
                int removeValue = list1.remove(i).intValue();
                assertEquals("remove == get", getValue, removeValue);
                assertEquals("size", i, list1.size());
                assertEquals("value", i, removeValue);
                assertEquals("index", -1, list1.index(removeValue));
                assertFalse("contains", list1.contains(removeValue));
                if (i > 0)
                {
                    assertFalse("empty", list1.empty());
                }
                else
                {
                    assertTrue("empty", list1.empty());
                }
            }

			beginSubsection("equals");
			list1.clear();
			list2.clear();
			assertTrue("zero zero", list1.equals(list2));
			list1.add(1);
			assertFalse("one zero", list1.equals(list2));
			list2.add(1);
			assertTrue("one one", list1.equals(list2));
			list1.add(2);
			assertFalse("two one", list1.equals(list2));
			list2.add(2);
			assertTrue("two two", list1.equals(list2));
			list2.set(1, 3);
			assertFalse("two two not equal", list1.equals(list2));
			
			beginSubsection("hashCode");
			list1.clear();
			list2.clear();
			assertEquals("zero zero", list1.hashCode(), list2.hashCode());
			list1.add(1);
			list2.add(1);
			assertEquals("one one equal", list1.hashCode(), list2.hashCode());
			list1.add(2);
			list2.add(2);
			assertEquals("two two equal", list1.hashCode(), list2.hashCode());
			list1.add(3);
			list2.add(3);
			assertEquals("three three equal", list1.hashCode(), list2.hashCode());
			list1.set(1, 4);
			assertTrue("three three not equal", list1.hashCode() != list2.hashCode());
			
			beginSubsection("toString");
			list1.clear();
			assertEquals("zero", "[]", list1.toString());
			list1.add(1);
			assertEquals("one", "[1]", list1.toString());
			list1.add(2);
			assertEquals("two", "[1, 2]", list1.toString());
			list1.add(3);
			assertEquals("three", "[1, 2, 3]", list1.toString());
			list1.remove(1);
			assertEquals("three remove", "[1, 3]", list1.toString());
        }
        catch (Exception e)
        {
            fail(e);
        }
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
	
	private static void testHeapSort()
	{
		beginSection("Heap sort");
		testArraySorter(new ArraySorter()
		{
			public void sort(int[] items)
			{
				ArraySort.heap(items);
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
			
			array = new int[] { 7, 7 };
			arraySorter.sort(array);
			assertEquals("two same", new int[] { 7, 7 }, array);
			
			array = new int[] { 0, 1, 2, 3, 4 };
			arraySorter.sort(array);
			assertEquals("five sorted", new int[] { 0, 1, 2, 3, 4 }, array);
			
			array = new int[] { 4, 3, 2, 1, 0 };
			arraySorter.sort(array);
			assertEquals("five reversed", new int[] { 0, 1, 2, 3, 4 }, array);
			
			array = new int[] { 8, 8, 8, 8, 8 };
			arraySorter.sort(array);
			assertEquals("five same", new int[] { 8, 8, 8, 8, 8 }, array);
			
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
			
			array = new int[100];
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
				boolean condition = array[i - 1] <= array[i];
				assertTrue("random random: indices=" + (i - 1) + ", " + i + "; values=" + array[i - 1] + ", " + array[i], condition);
				if (!condition)
				{
					break;
				}
			}
		}
		catch (Exception e)
		{
			fail(e);
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

	private static void fail(String message)
	{
		printFailure(message);
        ++failures;
	}
    
    private static void fail(Exception e)
    {
        printFailure("exception=" + e);
        ++failures;
    }

	private static void fail(Exception actual, String expected)
    {
        printFailure("exception: expected=" + expected + ", actual=" + actual);
        ++failures;
    }
    
    public static void main(String[] args)
    {
        testCharBuffer();
        testArrayList();
		testLinkedList();
		testSelectionSort();
		testBubbleSort();
		testInsertionSort();
        if (failures > 0)
        {
            System.err.print(failures + " failure" + (failures == 1 ? "" : "s"));
        }
    }
}