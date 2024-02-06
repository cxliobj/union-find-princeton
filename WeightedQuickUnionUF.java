public class WeightedQuickUnionUF {

    private final int[] id;
    private final int[] size;

    public WeightedQuickUnionUF(int n)
    {
        id = new int[n];
        size = new int[n];
        for (int i=0; i<n; i++)
        {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int n)
    {
        while (id[n] != n)
        {
            n = id[n];
        }
        return n;
    }

    public boolean isConnected (int n, int m)
    {
        return (root(n)==root(m));
    }

    public void union (int n, int m)
    {
        int i = root(n);
        int j = root(m);
        if (i == j)
        {
            return;
        }
        if (size[i] < size[j])
        {
            id[i] = j;
            size[j] += size[i];
        }
        else
        {
            id[j] = i;
            size[i] += size[j];
        }
    }
}
