import java.util.*;
import java.io.*;

public class Misc
{
    public static int[] longest(int[] a)
    {
        if (a == null) return null;
        int longestIndex = -1, longestLength = 0;
        for (int i = 0; i < a.length; ++i)
        {
            int sum = 0;
            int length = 0;
            for (int j = i; j < a.length; ++j)
            {
                sum += a[j];
                ++length;
                if (sum >= 0 && length > longestLength)
                {
                    longestIndex = i;
                    longestLength = length;
                }
            }
        }
        if (longestIndex == -1) return null;
        int[] b = new int[longestLength];
        for (int i = 0; i < longestLength; ++i)
            b[i] = a[i + longestIndex];
        return b;
    }
    
    private static boolean sudokuRows(Integer[][] a)
	{
		for (int i = 0; i < a.length; ++i)
		{
			Set<Integer> s = new HashSet<>();
			for (int j = 0; j < a.length; ++j)
			{
				Integer x = a[i][j];
				if (x == null) continue;
				if (x < 1 || x > a.length || s.contains(x)) return false;
				s.add(x);
			}
		}
		return true;
	}
	
	private static boolean sudokuCols(Integer[][] a)
	{
		for (int i = 0; i < a.length; ++i)
		{
			Set<Integer> s = new HashSet<>();
			for (int j = 0; j < a.length; ++j)
			{
				Integer x = a[j][i];
				if (x == null) continue;
				if (x < 1 || x > a.length || s.contains(x)) return false;
				s.add(x);
			}
		}
		return true;
	}
	
	private static boolean sudokuSquare(Integer[][] a, int s, int i, int j)
	{
		Set<Integer> h = new HashSet<>();
		for (int u = 0; u < s; ++u)
			for (int v = 0; v < s; ++v)
			{
				Integer x = a[i + u][j + v];
				if (x == null) continue;
				if (x < 1 || x > a.length || h.contains(x)) return false;
				h.add(x);
			}
		return true;
	}

    public static boolean sudoku(Integer[][] a)
    {
		if (!sudokuRows(a) || !sudokuCols(a)) return false;
		int s = (int)Math.sqrt(a.length);
        for (int i = 0; i < s; ++i)
			for (int j = 0; j < s; ++j)
				if (!sudokuSquare(a, s, i * s, j * s)) return false;
		return true;
    }
    
    public static <T> void shuffle(T[] a)
    {
        Random r = new Random();
        for (int i = a.length; i > 1; --i)
        {
            int j = r.nextInt(i);
            T t = a[i - 1];
            a[i - 1] = a[j];
            a[j] = t;
        }
    }

    public static String printBinary(double num)
	{
		if (num >= 1 || num <= 0) return "ERROR";
		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while (num > 0)
		{
			if (binary.length() >= 32) return "ERROR";
			double r = num * 2;
			if (r >= 1)
			{
				binary.append(1);
				num = r - 1;
			}
			else
			{
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}
	
	public static int insertBits(int m, int n, int i, int j)
	{
		int width = j - i + 1;
		int extrude = (1 << width) - 1;
		int position = extrude << i;
		int mask = ~position;
        n &= mask;
		n |= m << i;
		return n;
	}
	
    public static int atoi(String s)
    {
        if (s == null) throw new NullPointerException("s");
        int length = s.length();
        if (length == 0) throw new NumberFormatException("Empty string");
        char[] c = s.toCharArray();
        int n = 0;
        boolean negative = c[0] == '-';
        for (int i = negative ? 1 : 0; i < length; ++i)
        {
            if (!Character.isDigit(c[i])) throw new NumberFormatException("Non-digit character");
            n = n * 10 + (c[i] - '0');
        }
        return negative ? -n : n;
    }
    
    public static boolean power2(int x)
    {
        return x != 0 && (x & (x - 1)) == 0;
    }
    
    public static boolean ransom(String note, String magazine)
    {
        if (note == null || magazine == null) throw new NullPointerException();
        if (note.length() == 0) return true;
        if (magazine.length() == 0 || magazine.length() < note.length()) return false;
        char[] cs = note.toCharArray();
        Map<Character, Integer> m = new HashMap<>();
        for (char c : cs)
            if (m.containsKey(c)) m.put(c, m.get(c) + 1);
            else m.put(c, 1);
        cs = magazine.toCharArray();
        for (char c : cs)
            if (m.containsKey(c))
            {
                int i = m.get(c);
                if (i == 1)
                {
                    m.remove(c);
                    if (m.isEmpty())
                    {
                        break;
                    }
                }
                else m.put(c, i - 1);
            }
        return m.isEmpty();
    }
    
    public static boolean contains(String string, String substring)
    {
        if (string == null || substring == null) throw new NullPointerException();
        int sublength = substring.length();
        if (sublength == 0) return true;
        int length = string.length();
        if (length < sublength) return false;
        char[] cs = string.toCharArray();
        char[] subcs = substring.toCharArray();
        for (int i = 0; i < length; ++i)
        {
            if (cs[i] == subcs[0])
            {
                int j = 1;
                while (j < sublength && cs[i + j] == subcs[j]) ++j;
                if (j == sublength) return true;
            }
        }
        return false;
    }
    
    // kth smallest unsorted array item. partition and count, O(n) because discarding half each time. same as find median    

    // path from node ref to node ref in binary tree, O(n)
    
    // an algorithm that takes an unsorted array of axis aligned rectangles and returns any pair of rectangles that overlaps, O(n log n)
    
    // do two array elements add to x? two pointers start at ends, move right for bigger, left for smaller
	
	public static void sortTickets()
	{
	    class Ticket
	    {
	        public String from, to;
	        
	        public Ticket(String from, String to)
	        {
	            this.from = from;
	            this.to = to;
	        }
	    }
	    Ticket[] t = new Ticket[]
	    {
	        new Ticket("LAX", "SFO"),
	        new Ticket("IAH", "LAX"),
	        new Ticket("PUS", "IAH"),
	        new Ticket("SEA", "PUS"),
	        new Ticket("SMF", "SEA")
	    };
	}
    
    public static int[] maximumSubarray(int[] a)
    {
        if (a == null) return null;
        if (a.length <= 1) return Arrays.copyOf(a, a.length);
        
        return null;
    }
	
	public static void main(String[] args)
    {
        System.out.println("Done");
    }
}
