public class BinarySearchTree<K extends Comparable<K>, V> implements SearchTree<K, V>
{
    private class Node
    {
        public K key;
        public V value;
        public Node less;
        public Node greater;
        public int size = 1;
        
        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
    
    private Node root;
    
    public void add(K key, V value)
    {
        Stack<Node> stack = new ArrayList<Node>();
        Node node = root;
        while (node != null)
        {
            stack.push(node);
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                if (node.less == null)
                {
                    node.less = new Node(key, value);
                    break;
                }
                node = node.less;
            }
            else if (c > 0)
            {
                if (node.greater == null)
                {
                    node.less = new Node(key, value);
                    break;
                }
                node = node.greater;
            }
            else
            {
                node.value = value;
                return;
            }
        }
        while (!stack.empty())
        {
            node = stack.pop();
            ++node.size;
        }
    }
    
    public K ceiling(K key)
    {
        return key(ceiling(root, key));
    }
    
    public Node ceiling(Node node, K key)
    {
        Node best = null;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c == 0)
            {
                return node;
            }
            else if (c > 0)
            {
                node = node.greater;
            }
            else
            {
                best = node;
                node = node.less;
            }
        }
        return best;
    }
    
    public void clear()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node> stack = new ArrayList<Node>();
        stack.push(root);
        root = null;
        while (!stack.empty())
        {
            Node node = stack.pop();
            if (node.less != null)
            {
                stack.push(node.less);
            }
            if (node.greater != null)
            {
                stack.push(node.greater);
            }
            node.less = node.greater = null;
        }
    }
    
    public boolean contains(K key)
    {
        return get(key) != null;
    }
    
    public BinarySearchTree<K, V> copy()
    {
        //Node node = root;
        return null; // TODO 
    }
    
    public boolean empty()
    {
        return root == null;
    }
    
    public Enumerator<K> enumerator()
    {
        return inorder(getMinimum(), getMaximum());
    }
    
    public K floor(K key)
    {
        return key(floor(root, key));
    }
    
    private Node floor(Node node, K key)
    {
        Node best = null;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c == 0)
            {
                return node;
            }
            else if (c < 0)
            {
                node = node.less;
            }
            else
            {
                best = node;
                node = node.greater;
            }
        }
        return best;
    }
    
    public V get(K key)
    {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }
    
    private Node get(Node node, K key)
    {
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                node = node.less;
            }
            else if (c > 0)
            {
                node = node.greater;
            }
            else
            {
                return node;
            }
        }
        return null;
    }
    
    public K getMaximum()
    {
        return key(getMaximum(root));
    }
    
    private Node getMaximum(Node node)
    {
        while (node != null)
        {
            node = node.greater;
        }
        return node;
    }
    
    public K getMinimum()
    {
        return key(getMinimum(root));
    }
    
    private Node getMinimum(Node node)
    {
        while (node != null)
        {
            node = node.less;
        }
        return node;
    }
    
    public Enumerator<K> inorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        final Stack<Node> nodes = new ArrayList<Node>();
        Node node = root;
        while (node != null && low.compareTo(node.key) <= 0)
        {
            nodes.push(node);
            node = node.less;
        }
        final K high2 = high;
        return new Enumerator<K>()
        {
            public boolean more()
            {
                return !nodes.empty();
            }
            
            public K next()
            {
                if (!more())
                {
                    throw new IllegalStateException();
                }
                Node node = nodes.pop();
                K key = node.key;
                if (node.greater != null && high2.compareTo(node.key) >= 0)
                {
                    node = node.greater;
                    while (node != null)
                    {
                        nodes.push(node);
                    }
                }
                return key;
            }
        };
    }
    
    private K key(Node node)
    {
        return node == null ? null : node.key;
    }
    
    public Enumerator<K> preorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        return null; // TODO
    }
    
    public Enumerator<K> postorder(K low, K high)
    {
        if (low.compareTo(high) > 0)
        {
            throw new IllegalArgumentException();
        }
        return null; // TODO
    }
    
    public int rank(K key)
    {
        int rank = 0;
        Node node = root;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                node = node.less;
            }
            else if (c > 0)
            {
                rank += size(node.less) + 1;
                node = node.greater;
            }
            else
            {
                return rank + size(node.less);
            }
        }
        throw new IllegalArgumentException();
    }
    
    public void remove(K key)
    {
        Stack<Node> stack = new ArrayList<Node>();
        Node parent = null;
        int previous = 0;
        Node node = root;
        while (node != null)
        {
            int c = key.compareTo(node.key);
            if (c < 0)
            {
                parent = node;
                previous = c;
                node = node.less;
                stack.push(parent);
            }
            else if (c > 0)
            {
                parent = node;
                previous = c;
                node = node.greater;
                stack.push(parent);
            }
            else
            {
                if (parent == null)
                {
                    root = null;
                }
                else
                {
                    if (previous < 0)
                    {
                        parent.less = null;
                    }
                    else
                    {
                        parent.greater = null;
                    }
                    while (!stack.empty())
                    {
                        --stack.pop().size;
                    }
                }
            }
        }
    }
    
    public void removeMaximum()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node> stack = new ArrayList<Node>();
        Node parent = null;
        Node maximum = root;
        while (maximum.greater != null)
        {
            parent = maximum;
            maximum = maximum.greater;
            stack.push(parent);
        }
        if (parent == null)
        {
            root = null;
        }
        else
        {
            parent.greater = null;
            while (!stack.empty())
            {
                --stack.pop().size;
            }
        }
    }
    
    public void removeMinimum()
    {
        if (root == null)
        {
            return;
        }
        Stack<Node> stack = new ArrayList<Node>();
        Node parent = null;
        Node minimum = root;
        while (minimum.less != null)
        {
            parent = minimum;
            minimum = minimum.less;
            stack.push(parent);
        }
        if (parent == null)
        {
            root = null;
        }
        else
        {
            parent.less = null;
            while (!stack.empty())
            {
                --stack.pop().size;
            }
        }
    }
    
    public K select(int rank)
    {
        Node node = root;
        while (node != null)
        {
            int size = size(node.less);
            if (rank < size)
            {
                node = node.less;
            }
            else if (rank > size)
            {
                rank -= size + 1;
                node = node.greater;
            }
            else
            {
                return node.key;
            }
        }
        return null;
    }
    
    public int size()
    {
        return size(root);
    }
    
    private int size(Node node)
    {
        return node == null ? 0 : node.size;
    }
}