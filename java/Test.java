import java.util.Arrays;

public class Test
{
    private interface ArraySorter
    {
        void sort(int[] a);
    }
    
    private static String sectionName;
    private static String subsectionName;
    private static int failures;
    
    private static void testCharArray()
    {
        beginSection("CharArray");
        try
        {
            beginSubsection("basic");
            CharArray c = new CharArray();
            beginSubsection("empty");
            assertEqual("empty", "", c.toString());
            c.append("H");
            assertEqual("letter", "H", c.toString());
            c.append("ello");
            assertEqual("word", "Hello", c.toString());
            c.append(", ");
            c.append("world");
            c.append("!");
            assertEqual("phrase", "Hello, world!", c.toString());
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
            assertEqual("size", 0, list1.size());

            beginSubsection("empty clear");
            list1.clear();
            assertTrue("empty", list1.empty());
            assertEqual("size", 0, list1.size());
            
            beginSubsection("add");
            list1.add(1);
            list1.add(2);
            list1.add(3);
            assertFalse("empty", list1.empty());
            assertEqual("size", 3, list1.size());
            assertFalse("contains 0", list1.contains(0));
            assertTrue("contains 1", list1.contains(1));
            assertTrue("contains 2", list1.contains(2));
            assertTrue("contains 3", list1.contains(3));
            assertEqual("index 0", -1, list1.index(0));
            assertEqual("index 1", 0, list1.index(1));
            assertEqual("index 2", 1, list1.index(2));
            assertEqual("index 3", 2, list1.index(3));
            assertEqual("get 0", 1, list1.get(0));
            assertEqual("get 1", 2, list1.get(1));
            assertEqual("get 2", 3, list1.get(2));
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
            assertEqual("size", 0, list1.size());
            
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
            assertEqual("size", 3, list1.size());
            assertTrue("contains 1", list1.contains(1));
            assertTrue("contains 2", list1.contains(2));
            assertTrue("contains 3", list1.contains(3));
            assertEqual("index 1", 0, list1.index(1));
            assertEqual("index 2", 1, list1.index(2));
            assertEqual("index 3", 2, list1.index(3));
            assertEqual("get 0", 1, list1.get(0));
            assertEqual("get 1", 2, list1.get(1));
            assertEqual("get 2", 3, list1.get(2));
            
            beginSubsection("set");
            assertEqual("set 0", 1, list1.set(0, 4));
            assertEqual("set 1", 2, list1.set(1, 5));
            assertEqual("set 2", 3, list1.set(2, 6));
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
            assertEqual("size", 3, list1.size());
            assertFalse("contains 1", list1.contains(1));
            assertFalse("contains 2", list1.contains(2));
            assertFalse("contains 3", list1.contains(3));
            assertTrue("contains 4", list1.contains(4));
            assertTrue("contains 5", list1.contains(5));
            assertTrue("contains 6", list1.contains(6));
            assertEqual("index 1", -1, list1.index(1));
            assertEqual("index 2", -1, list1.index(2));
            assertEqual("index 3", -1, list1.index(3));
            assertEqual("index 4", 0, list1.index(4));
            assertEqual("index 5", 1, list1.index(5));
            assertEqual("index 6", 2, list1.index(6));
            assertEqual("get 0", 4, list1.get(0));
            assertEqual("get 1", 5, list1.get(1));
            assertEqual("get 2", 6, list1.get(2));
            
            beginSubsection("remove");
            assertEqual("remove 2", 6, list1.remove(2).intValue());
            assertEqual("remove 1", 5, list1.remove(1).intValue());
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
            assertEqual("remove 0", 4, list1.remove(0).intValue());
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
            assertEqual("size", 0, list1.size());
            assertFalse("contains 4", list1.contains(4));
            assertFalse("contains 5", list1.contains(5));
            assertFalse("contains 6", list1.contains(6));
            assertEqual("index 4", -1, list1.index(4));
            assertEqual("index 5", -1, list1.index(5));
            assertEqual("index 6", -1, list1.index(6));
            
            for (int i = 0; i < 50; ++i)
            {
                beginSubsection("many add one " + i);
                list1.add(i);
                assertEqual("size", i + 1, list1.size());
                assertEqual("get", i, list1.get(i));
                assertEqual("index", i, list1.index(i));
                assertTrue("contains", list1.contains(i));
                assertFalse("empty", list1.empty());
            }
            
            for (int i = 49; i >= 0; --i)
            {
                beginSubsection("many removeLast " + i);
                int getValue = list1.get(i).intValue();
                int removeValue = list1.remove(i).intValue();
                assertEqual("remove == get", getValue, removeValue);
                assertEqual("size", i, list1.size());
                assertEqual("value", i, removeValue);
                assertEqual("index", -1, list1.index(removeValue));
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
            assertEqual("zero zero", list1.hashCode(), list2.hashCode());
            list1.add(1);
            list2.add(1);
            assertEqual("one one equal", list1.hashCode(), list2.hashCode());
            list1.add(2);
            list2.add(2);
            assertEqual("two two equal", list1.hashCode(), list2.hashCode());
            list1.add(3);
            list2.add(3);
            assertEqual("three three equal", list1.hashCode(), list2.hashCode());
            list1.set(1, 4);
            assertTrue("three three not equal", list1.hashCode() != list2.hashCode());
            
            beginSubsection("toString");
            list1.clear();
            assertEqual("zero", "[]", list1.toString());
            list1.add(1);
            assertEqual("one", "[1]", list1.toString());
            list1.add(2);
            assertEqual("two", "[1, 2]", list1.toString());
            list1.add(3);
            assertEqual("three", "[1, 2, 3]", list1.toString());
            list1.remove(1);
            assertEqual("three remove", "[1, 3]", list1.toString());
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
            public void sort(int[] a)
            {
                ArraySort.bubble(a);
            }
        });
    }
    
    private static void testHeapSort()
    {
        beginSection("Heap sort");
        testArraySorter(new ArraySorter()
        {
            public void sort(int[] a)
            {
                ArraySort.heap(a);
            }
        });
    }
    
    private static void testInsertionSort()
    {
        beginSection("Insertion sort");
        testArraySorter(new ArraySorter()
        {
            public void sort(int[] a)
            {
                ArraySort.insertion(a);
            }
        });
    }

    private static void testMergeBottomUpSort()
    {
        beginSection("Merge bottom up sort");
        testArraySorter(new ArraySorter()
        {
            public void sort(int[] a)
            {
                ArraySort.mergeBottomUp(a);
            }
        });
    }

    private static void testMergeTopDownSort()
    {
        beginSection("Merge top down sort");
        testArraySorter(new ArraySorter()
        {
            public void sort(int[] a)
            {
                ArraySort.mergeTopDown(a);
            }
        });
    }
    
    private static void testSelectionSort()
    {
        beginSection("Selection sort");
        testArraySorter(new ArraySorter()
        {
            public void sort(int[] a)
            {
                ArraySort.selection(a);
            }
        });
    }
    
    private static void testArraySorter(ArraySorter arraySorter)
    {
        try
        {
            int[] array = new int[0];
            arraySorter.sort(array);
            
            array = new int[] { 1 };
            arraySorter.sort(array);
            assertExpected("one", 1, array[0]);
            
            array = new int[] { 1, 2 };
            arraySorter.sort(array);
            assertExpected("two sorted", new int[] { 1, 2 }, array);
            
            array = new int[] { 2, 1 };
            arraySorter.sort(array);
            assertExpected("two reversed", new int[] { 1, 2 }, array);
            
            array = new int[] { 1, 1 };
            arraySorter.sort(array);
            assertExpected("two same", new int[] { 1, 1 }, array);
            
            array = new int[] { 1, 2, 3 };
            arraySorter.sort(array);
            assertExpected("three sorted", new int[] { 1, 2, 3 }, array);
            
            array = new int[] { 3, 2, 1 };
            arraySorter.sort(array);
            assertExpected("three reversed", new int[] { 1, 2, 3 }, array);
            
            array = new int[] { 1, 1, 1 };
            arraySorter.sort(array);
            assertExpected("three same", new int[] { 1, 1, 1 }, array);
            
            array = new int[] { 1, 2, 3, 4, 5 };
            arraySorter.sort(array);
            assertExpected("five sorted", new int[] { 1, 2, 3, 4, 5 }, array);
            
            array = new int[] { 5, 4, 3, 2, 1 };
            arraySorter.sort(array);
            assertExpected("five reversed", new int[] { 1, 2, 3, 4, 5 }, array);
            
            array = new int[] { 1, 1, 1, 1, 1 };
            arraySorter.sort(array);
            assertExpected("five same", new int[] { 1, 1, 1, 1, 1 }, array);
            
            array = new int[101];
            int[] array2 = new int[101];
            for (int i = 0; i < array.length; ++i)
            {
                array[i] = array2[i] = i + 1;
            }
            arraySorter.sort(array);
            assertEqual("odd many sorted", array, array2);
            
            for (int i = 0; i < array.length; ++i)
            {
                array[i] = array.length - i;
            }
            arraySorter.sort(array);
            assertEqual("odd many reversed", array, array2);
            
            array = new int[100];
            array2 = new int[100];
            for (int i = 0; i < array.length; ++i)
            {
                array[i] = array2[i] = i + 1;
            }
            arraySorter.sort(array);
            assertEqual("even many sorted", array, array2);
            
            for (int i = 0; i < array.length; ++i)
            {
                array[i] = array.length - i;
            }
            arraySorter.sort(array);
            assertEqual("even many reversed", array, array2);
            
            array = new int[101];
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
    
    private static void testHeapMinimum()
    {
        beginSection("Heap minimum");
        try
        {
            Heap<Integer> heap1 = new Heap<Integer>();
            Heap<Integer> heap2 = new Heap<Integer>();
            testHeap(heap1, heap2);
            
            beginSubsection("two peek remove");
            heap1.clear();
            heap1.add(1);
            heap1.add(2);
            assertExpected("peek 1", 1, heap1.peek());
            assertExpected("remove 1", 1, heap1.remove());
            assertExpected("peek 2", 2, heap1.peek());
            assertExpected("remove 2", 2, heap1.remove());
            
            beginSubsection("many peek remove");
            heap1.clear();
            for (int i = 0; i < 100; ++i)
            {
                heap1.add((int)(Math.random() * Integer.MAX_VALUE));
            }
            int last, current;
            for (int i = 0; i < 100; ++i)
            {
                current = heap1.remove();
                if (i > 0)
                {
                    last = current;
                    assertTrue("less than", last <= current);
                }
            }
            
            beginSubsection("many toString");
            heap1.clear();
            heap1.add(1);
            heap1.add(2);
            assertEqual("two asc", "[1, 2]", heap1.toString());
            heap1.clear();
            heap1.add(2);
            heap1.add(1);
            assertEqual("two desc", "[1, 2]", heap1.toString());
        }
        catch (Exception e)
        {
            fail(e);
        }
    }
    
    private static void testHeapMaximum()
    {
        beginSection("Heap maximum");
        try
        {
            Heap<Integer> heap1 = new Heap<Integer>(true);
            Heap<Integer> heap2 = new Heap<Integer>(true);
            testHeap(heap1, heap2);
            
            beginSubsection("two peek remove");
            heap1.clear();
            heap1.add(2);
            heap1.add(1);
            assertExpected("peek 1", 2, heap1.peek());
            assertExpected("remove 1", 2, heap1.remove());
            assertExpected("peek 2", 1, heap1.peek());
            assertExpected("remove 2", 1, heap1.remove());
            
            beginSubsection("many peek remove");
            heap1.clear();
            for (int i = 0; i < 100; ++i)
            {
                heap1.add((int)(Math.random() * Integer.MAX_VALUE));
            }
            int last, current;
            for (int i = 0; i < 100; ++i)
            {
                current = heap1.remove();
                if (i > 0)
                {
                    last = current;
                    assertTrue("less than", last >= current);
                }
            }
            
            beginSubsection("many toString");
            heap1.clear();
            heap1.add(1);
            heap1.add(2);
            assertEqual("two asc", "[2, 1]", heap1.toString());
            heap1.clear();
            heap1.add(2);
            heap1.add(1);
            assertEqual("two desc", "[2, 1]", heap1.toString());
        }
        catch (Exception e)
        {
            fail(e);
        }
    }
    
    private static void testHeap(Heap<Integer> heap1, Heap<Integer> heap2)
    {
        try
        {
            beginSubsection("clear 1");
            heap1.clear();
            assertTrue("empty", heap1.empty());
            assertEqual("size", 0, heap1.size());
            
            beginSubsection("add one");
            heap1.add(1);
            assertFalse("empty", heap1.empty());
            assertEqual("size", 1, heap1.size());
            assertTrue("contains", heap1.contains(1));
            assertEqual("peek", 1, heap1.peek());
            
            beginSubsection("remove one");
            assertEqual("remove", 1, heap1.remove());
            assertTrue("empty", heap1.empty());
            assertEqual("size", 0, heap1.size());
            
            beginSubsection("clear 2");
            heap1.add(1);
            heap1.clear();
            assertTrue("empty", heap1.empty());
            assertEqual("size", 0, heap1.size());
            
            beginSubsection("zero peek");
            heap1.clear();
            try
            {
                heap1.peek();
                fail("expected IllegalStateException");
            }
            catch (IllegalStateException e)
            {
            }
            catch (Exception e)
            {
                fail("expected IllegalStateException");
            }
            
            beginSubsection("zero remove");
            heap1.clear();
            try
            {
                heap1.remove();
                fail("expected IllegalStateException");
            }
            catch (IllegalStateException e)
            {
            }
            catch (Exception e)
            {
                fail("expected IllegalStateException");
            }
            
            for (int i = 1; i <= 100; ++i)
            {
                beginSubsection("add many " + i);
                heap1.add(i);
                assertFalse("empty", heap1.empty());
                assertEqual("size", i, heap1.size());
                assertTrue("contains", heap1.contains(i));
            }
            
            for (int i = 1; i <= 100; ++i)
            {
                beginSubsection("remove many " + i);
                heap1.remove();
                if (i == 100)
                {
                    assertTrue("empty", heap1.empty());
                }
                else
                {
                    assertFalse("empty", heap1.empty());
                }
                assertEqual("size", 100 - i, heap1.size());
            }
            
            beginSubsection("equals");
            heap1.clear();
            heap2.clear();
            assertEqual("zero zero 1", heap1, heap2);
            assertEqual("zero zero 2", heap2, heap1);
            heap1.add(1);
            assertNotEqual("one zero 1", heap1, heap2);
            assertNotEqual("one zero 2", heap2, heap1);
            heap2.add(1);
            assertEqual("one one equal 1", heap1, heap2);
            assertEqual("one one equal 2", heap2, heap1);
            assertExpected("remove", 1, heap1.remove());
            heap1.add(2);
            assertNotEqual("one one not equal", heap1, heap2);
            assertNotEqual("one one not equal", heap2, heap1);
            heap1.clear();
            heap2.clear();
            heap1.add(1);
            heap1.add(2);
            heap1.add(3);
            heap2.add(1);
            heap2.add(2);
            heap2.add(3);
            assertEqual("three three equal 1", heap1, heap2);
            assertEqual("three three equal 2", heap2, heap1);
            heap1.remove();
            assertNotEqual("three three not equal 1", heap1, heap2);
            assertNotEqual("three three not equal 2", heap2, heap1);
            
            beginSubsection("hashCode");
            heap1.clear();
            heap2.clear();
            assertEqual("zero zero", heap1.hashCode(), heap2.hashCode());
            heap1.add(1);
            heap2.add(1);
            assertEqual("one one equal", heap1.hashCode(), heap2.hashCode());
            heap1.add(2);
            heap2.add(2);
            assertEqual("two two equal", heap1.hashCode(), heap2.hashCode());
            heap1.add(3);
            heap2.add(3);
            assertEqual("three three equal", heap1.hashCode(), heap2.hashCode());
            heap1.remove();
            assertTrue("three three not equal", heap1.hashCode() != heap2.hashCode());
            
            beginSubsection("toString");
            heap1.clear();
            assertEqual("zero", "[]", heap1.toString());
            heap1.add(1);
            assertEqual("one", "[1]", heap1.toString());
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
        String failure = "Failure: " + sectionName + ": ";
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
    
    private static void assertExpected(String testName, Object expected, Object actual)
    {
        if (!equals(expected, actual))
        {
            printFailure(testName + ": expected=\"" + string(expected) + "\", actual=\"" + string(actual) + "\"");
            ++failures;
        }
    }

    private static void assertNotExpected(String testName, Object notExpected, Object actual)
    {
        if (equals(notExpected, actual))
        {
            printFailure(testName + ": not expected=\"" + string(notExpected) + "\", actual=\"" + string(actual) + "\"");
            ++failures;
        }
    }

    private static void assertEqual(String testName, Object first, Object second)
    {
        if (!equals(first, second))
        {
            printFailure(testName + ": not equal: first=\"" + string(first) + "\", second=\"" + string(second) + "\"");
            ++failures;
        }
    }

    private static void assertNotEqual(String testName, Object first, Object second)
    {
        if (equals(first, second))
        {
            printFailure(testName + ": equal: first=\"" + string(first) + "\", second=\"" + string(second) + "\"");
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
        testArrayList();
        testCharArray();
        testLinkedList();
        testHeapMinimum();
        testHeapMaximum();
        
        testBubbleSort();
        testHeapSort();
        testInsertionSort();
        testMergeBottomUpSort();
        testMergeTopDownSort();
        testSelectionSort();
        
        if (failures > 0)
        {
            System.err.println(failures + " failure" + (failures == 1 ? "" : "s"));
        }
    }
}