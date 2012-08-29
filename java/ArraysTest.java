package com.willfaught;

import static com.willfaught.Assert.*;

public class ArraysTest
{
	@Test
	public void copyNullSrc()
	{
		try
		{
			Arrays.copy(null, new int[1], 0, 0, 1);
			Assert.fail(IllegalArgumentException.class);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void copyNullDst()
	{
		try
		{
			Arrays.copy(new int[1], null, 0, 0, 1);
			Assert.fail(IllegalArgumentException.class);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void copyBothNull()
	{
	    try
		{
			Arrays.copy(null, null, 0, 0, 1);
			Assert.fail(IllegalArgumentException.class);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void copySrcIndexLow()
	{
	    try
		{
			Arrays.copy(new int[1], new int[1], -1, 0, 1);
			Assert.fail(IndexOutOfBoundsException.class);
		}
		catch (IndexOutOfBoundsException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IndexOutOfBoundsException.class, e);
		}
	}
	
	@Test
	public void copySrcIndexHigh()
	{
	    try
		{
			Arrays.copy(new int[1], new int[1], 1, 0, 1);
			Assert.fail(IndexOutOfBoundsException.class);
		}
		catch (IndexOutOfBoundsException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IndexOutOfBoundsException.class, e);
		}
	}
	
	@Test
	public void copyDstIndexLow()
	{
	    try
		{
			Arrays.copy(new int[1], new int[1], 0, -1, 1);
			Assert.fail(IndexOutOfBoundsException.class);
		}
		catch (IndexOutOfBoundsException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IndexOutOfBoundsException.class, e);
		}
	}
	
	@Test
	public void copyDstIndexHigh()
	{
	    try
		{
			Arrays.copy(new int[1], new int[1], 0, 1, 1);
			Assert.fail(IndexOutOfBoundsException.class);
		}
		catch (IndexOutOfBoundsException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IndexOutOfBoundsException.class, e);
		}
	}
	
	@Test
	public void copySizeZero()
	{
	    int[] a = new int[] { 1 };
	    int[] b = new int[] { 2 };
	    Arrays.copy(a, b, 0, 0, 0);
	    assertExpected("a", 1, a[0]);
	    assertExpected("b", 2, b[0]);
	}
	
	@Test
	public void copySizeHigh()
	{
	    try
		{
			Arrays.copy(new int[1], new int[1], 0, 0, 2);
			Assert.fail(IllegalArgumentException.class);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (Exception e)
		{
			Assert.fail(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void copyOne()
	{
	    int[] a = new int[] { 1 };
	    int[] b = new int[] { 2 };
	    Arrays.copy(a, b, 0, 0, 1);
	    assertExpected("a", 1, a[0]);
	    assertExpected("b", 1, b[0]);
	}
	
	@Test
	public void copyManyAtZero()
	{
	    int[] a = new int[] { 1, 2, 3 };
	    int[] b = new int[] { 4, 5, 6 };
	    Arrays.copy(a, b, 0, 0, 3);
	    assertExpected("a", new int[] { 1, 2, 3 }, a);
	    assertExpected("b", new int[] { 1, 2, 3 }, b);
	}
	
	@Test
	public void copyManyAtNotZero()
	{
	    int[] a = new int[] { 1, 2, 3 };
	    int[] b = new int[] { 4, 5, 6 };
	    Arrays.copy(a, b, 0, 1, 2);
	    assertExpected("a", new int[] { 1, 2, 3 }, a);
	    assertExpected("b", new int[] { 4, 1, 2 }, b);
	}
}