import java.util.Stack;

public class StackSort
{
	private static <E extends Comparable<E>> Stack<E> merge(Stack<E> s1, Stack<E> s2)
	{
		Stack<E> s3 = new Stack<E>();
		if (s1.empty() && s2.empty()) return s3;
		while (!s1.empty() && !s2.empty())
			s3.push(s1.peek().compareTo(s2.peek()) < 0 ? s2.pop() : s1.pop());
		while (!s1.empty()) s3.push(s1.pop());
		while (!s2.empty()) s3.push(s2.pop());
		Stack<E> s4 = new Stack<E>();
		while (!s3.empty()) s4.push(s3.pop());
		return s4;
	}
	
	public static <E extends Comparable<E>> Stack<E> sort(Stack<E> s)
	{
		if (s == null) return null;
		if (s.size() <= 1) return s;
		Stack<E> lt = new Stack<E>(), ge = new Stack<E>();
		E pivot = s.peek();
		while (!s.empty())
			if (s.peek().compareTo(pivot) < 0) lt.push(s.pop());
			else ge.push(s.pop());
		lt = sort(lt);
		ge = sort(ge);
		return merge(lt, ge);
	}
	
	public static Stack<Integer> sort2(Stack<Integer> s)
	{
		Stack<Integer> t = new Stack<Integer>();
		while (!s.empty())
		{
			Integer i = s.pop();
			while (!t.empty() && i.compareTo(t.peek()) < 0)
			{
				s.push(t.pop());
			}
			t.push(i);
		}
		return t;
	}
	
	private static void check(String name, Stack<Integer> s)
	{
		System.out.println(name);
		Integer i = s.pop();
		while (!s.empty())
		{
			Integer j = s.pop();
			if (i.compareTo(j) < 0)
			{
				System.out.println(i + " < " + j);
				break;
			}
			i = j;
		}
	}
	
	public static void main(String[] args)
	{
		final int max = 1000;
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 1; i <= max; ++i)
			s.push(i);
		s = sort2(s);
		check("A", s);
		
		for (int i = max; i >= 1; --i)
			s.push(i);
		s = sort2(s);
		check("B", s);
		
		Integer[] ints = new Integer[max];
		for (int i = 0; i < max; ++i)
			ints[i] = new Integer(i + 1);
		for (int i = 0; i < max - 1; ++i)
		{
			int j = i + 1 + (int)(Math.random() * (max - 1 - i));
			int t = ints[i];
			ints[i] = ints[j];
			ints[j] = t;
		}
		for (int i = 0; i < max; ++i)
			s.push(ints[i]);
		s = sort2(s);
		check("C", s);
	}
}