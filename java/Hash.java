public class Hash
{
	// No avalanche
	public static int xor(byte[] items)
	{
		int hash = 0;
		for (int item : items)
		{
			hash ^= item;
		}
		return hash;
	}
	
	public static int rotate(byte[] items)
	{
		int hash = 0;
		for (int item : items)
		{
			hash = (hash << 4) ^ (hash >> 28) ^ item;
		}
		return hash;
	}
	
	// No avalanche
	public static int kr(byte[] items)
	{
		int hash = 0;
		for (int item : items)
		{
			hash = hash * 31 + item;
		}
		return hash;
	}
	
	// No avalanche
	public static int bernstein(byte[] items)
	{
		int hash = 5381;
		for (int item : items)
		{
			hash = hash * 33 + item;
		}
		return hash;
	}
	
	// No avalanche
	public static int bernstein2(byte[] items)
	{
		int hash = 5381;
		for (int item : items)
		{
			hash = hash * 33 ^ item;
		}
		return hash;
	}
	
	// No avalanche
	public static int sax(byte[] items)
	{
		int hash = 0;
		for (int item : items)
		{
			hash ^= (hash << 5) + (hash >> 2) + item;
		}
		return hash;
	}
	
	public static int fnv(byte[] items)
	{
		long hash = 2166136261L;
		for (int item : items)
		{
			hash = hash * 16777619 ^ item;
		}
		return (int)(hash & 0x7fffffff);
	}
	
	public static int fnv1a(byte[] items)
	{
		long hash = 2166136261L;
		for (int item : items)
		{
			hash = (hash ^ item) * 16777619;
		}
		return (int)(hash & 0x7fffffff);
	}
	
	// Avalanche
	public static int oat(byte[] items)
	{
		int hash = 0;
		for (int item : items)
		{
			hash += item;
			hash += hash << 10;
			hash ^= hash >> 6;
		}
		hash += hash << 3;
		hash ^= hash >> 11;
		hash += hash << 15;
		return hash;
	}
}