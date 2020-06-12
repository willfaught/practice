import java.util.Stack;

public class Tower
{
    private int number;
    private Stack<Integer> disks = new Stack<Integer>();
    
    public Tower(int number)
    {
        this.number = number;
    }
    
    public void add(Integer i)
    {
        if (!disks.empty() && i >= disks.peek()) throw new IllegalArgumentException();
        disks.push(i);
    }
    
    private void moveTop(Tower to)
    {
        Integer i = disks.pop();
        to.disks.push(i);
        System.out.println("Moved " + i + " from Tower " + number + " to Tower " + to.number);
    }
    
    public void move(int n, Tower to, Tower temp)
    {
        if (n <= 0) return;
        if (to == null || temp == null) throw new NullPointerException();
        move(n - 1, temp, to);
        moveTop(to);
        temp.move(n - 1, to, this);
    }
    
    public static void main(String[] args)
    {
        int n = 4;
        Tower[] t = new Tower[3];
        for (int i = 0; i < 3; ++i) t[i] = new Tower(i + 1);
        for (int i = n; i >= 1; --i) t[0].add(i);
        t[0].move(n, t[2], t[1]);
        System.out.println("Tower 1: " + t[0].disks.toString());
        System.out.println("Tower 2: " + t[1].disks.toString());
        System.out.println("Tower 3: " + t[2].disks.toString());
    }
}