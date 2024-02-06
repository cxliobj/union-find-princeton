public class QuickFindUF {

    private final int[] id;

    public QuickFindUF(int n)
    {
        id = new int[n];
        for (int i=0; i<n; i++)
        {
            id[i] = i;
        }
    }

    public boolean isConnected (int n, int m)
    {
        return (id[n]==id[m]);
    }

    public void union (int n, int m)
    {
        int idn = id[n]; //save before the iteration because id[p] will change in the middle of code
        int idm = id[m];

        if (idn == idm)
        {
            return;
        }

        for (int i=0; i<id.length; i++)
        {
            if (id[i] == idn)
            {
                id[i] = idm;
            }
        }
    }
}
