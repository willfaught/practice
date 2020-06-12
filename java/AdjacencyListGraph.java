import java.util.*;

public class AdjacencyListGraph
{
    public static class Node
    {
        public int value;
        private Set<Node> adjacent = new HashSet<>();
        
        public Node(int value)
        {
            this.value = value;
        }
        
        public String toString()
        {
            StringBuilder s = new StringBuilder();
            s.append("{Node ");
            s.append(String.valueOf(value));
            if (adjacent.size() > 0)
            {
                s.append(" ");
                boolean first = true;
                for (Node n : adjacent)
                {
                    if (!first) s.append(", ");
                    first = false;
                    s.append(n.value);
                }
            }
            s.append("}");
            return s.toString();
        }
    }
    
    private Set<Node> nodes = new HashSet<>();
    
    public void addEdge(Node from, Node to)
    {
        nodes.add(from);
        nodes.add(to);
        from.adjacent.add(to);
    }
    
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("{AdjacencyListGraph");
        if (nodes.size() > 0)
        {
            s.append(" ");
            boolean first = true;
            for (Node n : nodes)
            {
                if (!first) s.append(", ");
                first = false;
                s.append(n.toString());
            }
        }
        s.append("}");
        return s.toString();
    }
    
    private static enum Color { WHITE, GRAY, BLACK };
	
	public boolean hasCycle()
	{
	    Map<Node, Color> colors = new HashMap<>();
	    for (Node n : nodes) colors.put(n, Color.WHITE);
	    for (Node n : nodes)
	        if (colors.get(n) == Color.WHITE && hasCycleHelper(colors, n))
	            return true;
	    return false;
	}
	
	private boolean hasCycleHelper(Map<Node, Color> colors, Node x)
	{
	    colors.put(x, Color.GRAY);
	    for (Node n : x.adjacent)
	    {
	        if (colors.get(n) == Color.WHITE)
	        {
	            if (hasCycleHelper(colors, n)) return true;
	        }
	        else if (colors.get(n) == Color.GRAY)
	        {
	            return true;
	        }
	    }
	    colors.put(x, Color.BLACK);
	    return false;
	}
    
    public static void main(String[] args)
    {
        AdjacencyListGraph g = new AdjacencyListGraph();
        Node n1 = new Node(1), n2 = new Node(2), n3 = new Node(3);
        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);
        g.addEdge(n3, n3);
        System.out.println(g.toString());
        System.out.println(g.hasCycle());
        System.out.println("Done");
    }
}