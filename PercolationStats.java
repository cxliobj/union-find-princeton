import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_CONSTANT = 1.96;
    private final int n;
    private final int trials;
    private final int gridSize;
    private final double[] percolationResults;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0) { throw new IllegalArgumentException("index out of bounds"); }
        this.n = n;
        this.trials = trials;
        gridSize = n * n;
        percolationResults = new double[trials];
        experiment();
    }

    private void experiment()
    {
        for (int i = 0; i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                int u = StdRandom.uniformInt(1, n+1);
                int v = StdRandom.uniformInt(1, n+1);
                if (!percolation.isOpen(u, v))
                {
                    percolation.open(u, v);
                }
            }
            percolationResults[i] = ((double) percolation.numberOfOpenSites()) / ((double) gridSize);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(percolationResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(percolationResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        double meanExperiment = mean();
        double stdExperiment = stddev();
        return meanExperiment - ((CONFIDENCE_CONSTANT * stdExperiment) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        double meanExperiment = mean();
        double stdExperiment = stddev();
        return meanExperiment + ((CONFIDENCE_CONSTANT * stdExperiment) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats tst = new PercolationStats(n, t);

        StdOut.println("mean = " + tst.mean());
        StdOut.println("stddev = " + tst.stddev());
        StdOut.println("95% confidence interval = [" + tst.confidenceLo() + ", " + tst.confidenceHi() + ']');
    }
}
