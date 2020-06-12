import java.util.*;

class Misc2
{
    private static class Ticket
    {
        public String from, to;

        public Ticket(String from, String to)
        {
            this.from = from;
            this.to = to;
        }

        public String toString()
        {
            return "{Ticket " + from + " -> " + to + "}";
        }
    }

    public static void sort(Ticket[] a)
    {
        if (a == null || a.length < 2) return;
        Map<String, Ticket> m = new HashMap<String, Ticket>();
        List<String> from = new ArrayList<String>();
        List<String> to = new ArrayList<String>();
        for (Ticket t : a)
        {
            m.put(t.from, t);
            from.add(t.from);
            to.add(t.to);
        }
        from.removeAll(to);
        String city = from.get(0);
        for (int i = 0; i < a.length; ++i)
        {
            a[i] = m.get(city);
            city = a[i].to;
        }
    }

    private static class Appt
    {
        public int begin, end, value;

        public Appt(int begin, int end, int value)
        {
            this.begin = begin;
            this.end = end;
            this.value = value;
        }
    }

    private static class Result
    {
        public int value;
        public Set<Appt> appts;
    }

    public static Set<Appt> schedule(List<Appt> appts)
    {
        if (appts.size() <= 1) return new HashSet<Appt>(appts);
        return schedule(appts, 0).appts;
    }

    private static Result schedule(List<Appt> appts, int index)
    {
        Appt appt = appts.get(index);
        int value = appt.value;
        Set<Appt> chosen = new HashSet<Appt>();
        for (int i = start; i < appts.size(); ++i)
        {
            Appt appt = appts.get(i);
            int conflicts = conflicts(appt, i);
            if (conflicts == 0)
            {
                if (first)
                {
                    first = false;
                    value = appt.value;
                }
                else value += appt.value;
                chosen.add(appt);
            }
            else
            {
                Result result = schedule(appts, i, i + conflicts);
                if (first) return maxResult;
                value += maxResult.value;
                chosen.addAll(maxResult.appts);
                break;
            }
        }
        return new Result(value, chosen);
    }

    private static int conflicts(List<Appt> appts, int index)
    {
        int j = index;
        while (j < appts.size() && appts.get(j + 1).begin < appt.end)
        {
            ++j;
        }
        return j - index;
    }

    private static Result schedule(List<Appt> appts, int start, int end)
    {
        Result maxResult = schedule(appts, start);
        for (int i = start + 1; i <= end; ++i)
        {
            Result result = schedule(appts, j--);
            if (result.value > maxResult.value) maxResult = result;
        }
        return maxResult;
    }

    public static void main(String[] args)
    {
        System.out.println("Done");
    }
}
