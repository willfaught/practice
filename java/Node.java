import java.util.*;

class Node
{
    public Node[] adj;
    public Node left, right, parent;
    public int value;
    private boolean first;
    private int previous;

	public Node()
	{
	}

	public Node(int value)
	{
		this.value = value;
	}

	public Node(int value, Node left, Node right)
	{
		this.value = value;
		this.left = left;
		this.right = right;
	}
    
    private static int height(Node n)
    {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }
    
    public static boolean isBalanced(Node n)
    {
        if (n == null) return true;
        if (Math.abs(height(n.left) - height(n.right)) > 1) return false;
        return isBalanced(n.left) && isBalanced(n.right);
    }
    
    public boolean isPathTo(Node target)
    {
        if (target == null) return false;
        Stack<Node> s = new Stack<Node>();
        HashMap<Node, Node> m = new HashMap<Node, Node>();
        s.push(this);
        while (!s.empty())
        {
            Node n = s.pop();
            m.put(n, n);
            for (Node n2 : n.adj)
            {
                if (n2 == target) return true;
                if (n2 != null && !m.containsKey(n2)) s.push(n2);
            }
        }
        return false;
    }
    
    public static Node bst(int[] a)
    {
        if (a == null || a.length == 0) return null;
        return build(a, 0, a.length - 1);
    }
    
    private static Node build(int[] a, int left, int right)
    {
        if (left > right) return null;
        int middle = (left + right) / 2;
        Node n = new Node();
        n.value = a[middle];
        n.left = build(a, left, middle - 1);
        n.right = build(a, middle + 1, right);
        return n;
    }
    
    public LinkedList<LinkedList<Node>> traverse()
    {
        LinkedList<LinkedList<Node>> lists = new LinkedList<LinkedList<Node>>();
        LinkedList<Node> list = new LinkedList<Node>();
        Queue<Node> nodes = new LinkedList<Node>();
        nodes.offer(this);
        int parents = 1, children = 0;
        while (parents > 0)
        {
            Node n = nodes.remove();
            list.add(n);
            if (n.left != null)
            {
                nodes.offer(n.left);
                ++children;
            }
            if (n.right != null)
            {
                nodes.offer(n.right);
                ++children;
            }
            --parents;
            if (parents == 0)
            {
                lists.add(list);
                list = new LinkedList<Node>();
                parents = children;
                children = 0;
            }
        }
        return lists;
    }
    
    public LinkedList<LinkedList<Node>> traverse2()
    {
        LinkedList<LinkedList<Node>> lists = new LinkedList<LinkedList<Node>>();
        LinkedList<Node> parents = new LinkedList<Node>();
        parents.add(this);
        while (!parents.isEmpty())
        {
            lists.add(parents);
            LinkedList<Node> children = new LinkedList<Node>();
            for (Node parent : parents)
            {
                if (parent.left != null) children.add(parent.left);
                if (parent.right != null) children.add(parent.right);
            }
            parents = children;
        }
        return lists;
    }
    
    public boolean isBST()
    {
        first = true;
        return isBST(this);
    }
    
    private boolean isBST(Node n)
    {
        if (n == null) return true;
        if (!isBST(n.left)) return false;
        if (first)
        {
            first = false;
            previous = n.value;
        }
        else if (previous >= n.value) return false;
        previous = n.value;
        return isBST(n.right);
    }
    
    public boolean isBST2()
    {
        boolean first = false;
        int previous = 0;
        Stack<Node> nodes = new Stack<Node>();
        Node n = this;
        while (n != null)
        {
            nodes.push(n);
            n = n.left;
        }
        while (!nodes.empty())
        {
            n = nodes.pop();
            if (first) first = false;
            else if (previous >= n.value) return false;
            previous = n.value;
            n = n.right;
            while (n != null)
            {
                nodes.push(n);
                n = n.left;
            }
        }
        return true;
    }
    
    public boolean isBST3()
    {
        return isBST3(this, null, null);
    }
    
    public boolean isBST3(Node n, Integer min, Integer max)
    {
        if (n == null) return true;
        if ((min != null && n.value <= min) || (max != null && n.value >= max)) return false;
        return isBST3(n.left, min, n.value) && isBST3(n.right, n.value, max);
    }
    
    private static Node buildWithParents(int[] a, int left, int right)
    {
        if (left > right) return null;
        int middle = (left + right) / 2;
        Node n = new Node();
        n.value = a[middle];
        n.left = buildWithParents(a, left, middle - 1);
        if (n.left != null) n.left.parent = n;
        n.right = buildWithParents(a, middle + 1, right);
        if (n.right != null) n.right.parent = n;
        return n;
    }
    
    public Node nextInorder()
    {
        if (right != null)
        {
            Node n = right;
            while (n.left != null) n = n.left;
            return n;
        }
        else
        {
            Node n = this;
            while (n.parent != null && n != n.parent.left) n = n.parent;
            return n.parent;
        }
    }
    
    private static class Info
    {
        public Node search, result;
    }
    
    private static boolean contains(Node n, Node x)
    {
        if (n == null) return false;
        return n == x || contains(n.left, x) || contains(n.right, x);
    }
    
    private static void search(Node r, Node x, Node y, Info info)
    {
        if (r == null) return;
        if (r == x || r == y)
        {
            Node other = r == x ? y : x;
            if (contains(r, other)) info.result = r;
            else info.search = other;
        }
        else
        {
            search(r.left, x, y, info);
            if (info.result != null) return;
            if (info.search != null)
            {
                if (contains(r.right, info.search)) info.result = r;
                return;
            }
            search(r.right, x, y, info);
        }
    }
    
    public static Node common(Node r, Node x, Node y)
    {
        if (r == null || x == null || y == null) return null;
        if (x == y) return x;
        if (r == x || r == y) return r;
        Info info = new Info();
        search(r, x, y, info);
        return info.result;
    }
    
    private static Node common2Helper(Node r, Node x, Node y)
    {
        if (r == null) return null;
        if (r == x || r == y) return r;
        boolean leftX = contains(r.left, x), leftY = contains(r.left, y);
        if (leftX != leftY) return r;
        return common2Helper(leftX ? r.left : r.right, x, y);
    }
    
    public static Node common2(Node r, Node x, Node y)
    {
        if (r == null || x == null || y == null) return null;
        if (!contains(r, x) || !contains(r, y)) return null;
        return common2Helper(r, x, y);
    }

	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Node))
		{
			return false;
		}
		return equals(this, (Node)o);
	}
	
	private static boolean equals(Node t, Node u)
	{
		if (t == u) return true;
		if (t == null || u == null) return false;
		return t.value == u.value && equals(t.left, u.left) && equals(t.right, u.right);
	}
	
	// TODO: hashCode override
	
	public static boolean subtree(Node t, Node s)
	{
		if (t == null || s == null) return false;
		return t.equals(s) || subtree(t.left, s) || subtree(t.right, s);
	}
	
	private static class ListNode
	{
		public int value;
		public ListNode next;
	}
	
	private static void print(ListNode ln)
	{
		boolean first = true;
		while (ln != null)
		{
			if (first) first = false;
			else System.out.print(", ");
			System.out.print(ln.value);
			ln = ln.next;
		}
	}
	
	private static int sum(ListNode ln)
	{
		int x = ln.value;
		while (ln.next != null)
		{
			ln = ln.next;
			x += ln.value;
		}
		return x;
	}
	
	private static void paths(Node tn, int sum, ListNode prev)
	{
		if (tn == null) return;
		ListNode current = new ListNode();
		current.value = tn.value;
		current.next = prev;
		if (sum(current) == sum) print(current);
		paths(tn.left, sum, current);
		paths(tn.right, sum, current);
	}
	
	public static void paths(Node tn, int sum)
	{
		paths(tn, sum, null);
	}
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        toString(s);
        return s.toString();
    }
    
    private void toString(StringBuilder s)
    {
        s.append("{" + value + "," + (parent == null ? "X" : parent.value) + ",");
        if (left == null)
        {
            s.append("X");
        }
        else
        {
            left.toString(s);
        }
        s.append(",");
        if (right == null)
        {
            s.append("X");
        }
        else
        {
            right.toString(s);
        }
        s.append("}");
    }

	public Node copy()
	{
		Node n = new Node();
		n.value = value;
		if (left != null) n.left = left.copy();
		if (right != null) n.right = right.copy();
		return n;
	}
    
    private static void assertTrue(String name, boolean condition)
    {
        if (!condition)
        {
            System.out.println("Failure: " + name);
        }
    }

	public static boolean mirror(Node x, Node y)
	{
		if (x == null && y == null) return true;
		if (x == null || y == null) return false;
		return x.value == y.value && mirror(x.left, y.right) && mirror(x.right, y.left);
	}
	
	public static void main(String[] args)
    {
		Node n1 = new Node(1, new Node(2), new Node(3));
		Node n2 = new Node(1, new Node(3), new Node(2, null, null));
		System.out.println(mirror(n1, n2));
        System.out.println("Done");
    }
}