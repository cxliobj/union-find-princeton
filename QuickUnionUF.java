public class QuickUnionUF {

    private final int[] id;

    public QuickUnionUF(int n)
    {
        id = new int[n];
        for (int i=0; i<n; i++)
        {
            id[i] = i;
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
        id[i] = j;
    }

}
