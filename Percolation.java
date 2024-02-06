import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private final int n;
    private final boolean[][] grid;
    private int openSites;
    private final int virtualTopSiteID;
    private final int virtualBottomSiteID;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0) { throw new IllegalArgumentException("index out of bounds in constructor."); }

        this.n = n;
        grid = new boolean[n+1][n+1];
        openSites = 0;
        virtualTopSiteID = n * n;
        virtualBottomSiteID = n * n + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return openSites;
    }

    // matrix position to the equivalent of ID array in union find
    private int xyTo1D(int row, int col)
    {
        return (row - 1) * n + (col - 1);
    }

    private int getVirtualTopSiteID()
    {
        return virtualTopSiteID;
    }

    private int getVirtualBottomSiteID()
    {
        return virtualBottomSiteID;
    }

    // if a coordinate is in the right value
    private boolean isValid(int row, int col)
    {
        return (row > 0 && row <= n && col > 0 && col <= n);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (!isValid(row, col)) { throw new IllegalArgumentException("index out of bounds in \"isOpen\" function."); }
        return (grid[row][col]);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (!isValid(row, col)) { throw new IllegalArgumentException("index out of bounds in \"open\" function."); }
        if (!isOpen(row, col))
        {
            grid[row][col] = true;
            openSites++;
            int centralID = xyTo1D(row, col);
            if (row == 1)
            {
                weightedQuickUnionUF.union(centralID, getVirtualTopSiteID());
            }
            if (row == n)
            {
                weightedQuickUnionUF.union(centralID, getVirtualBottomSiteID());
            }
            if (isValid(row, col-1))
            {
                if (grid[row][col-1])
                    weightedQuickUnionUF.union(centralID, xyTo1D(row, col-1));
            }
            if (isValid(row, col+1))
            {
                if (grid[row][col+1])
                    weightedQuickUnionUF.union(centralID, xyTo1D(row, col+1));
            }
            if (isValid(row-1, col))
            {
                if (grid[row-1][col])
                    weightedQuickUnionUF.union(centralID, xyTo1D(row-1, col));
            }
            if (isValid(row+1, col))
            {
                if (grid[row+1][col])
                    weightedQuickUnionUF.union(centralID, xyTo1D(row+1, col));
            }
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (!isValid(row, col)) { throw new IllegalArgumentException("index out of bounds in \"isFull\" function."); }
        if (!isOpen(row, col)) { return false; }
        return weightedQuickUnionUF.find(xyTo1D(row, col)) == weightedQuickUnionUF.find(getVirtualTopSiteID());
    }

    // does the system percolate?
    public boolean percolates()
    {
        return weightedQuickUnionUF.find(getVirtualTopSiteID()) == weightedQuickUnionUF.find(getVirtualBottomSiteID());
    }

    // test client (optional)
    public static void main(String[] args)
    {

    }
}
