import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author mkazmyryk
 */
public final class PercolationStats {
    private int n;
    private int trials;
    private double[] threshold;
    private int gridSize;

    public PercolationStats(final int n, final int trials) {
        validate(n);
        validate(trials);
        this.n = n;
        this.trials = trials;
        threshold = new double[trials];
        gridSize = n * n;
        doTrials();
    }    // perform trials independent experiments on an n-by-n grid

    public static void main(String[] args) {
        PercolationStats percolationStats =
                new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
    }       // test client (described below)

    public double mean() {
        return StdStats.mean(threshold);

    }                          // sample mean of percolation threshold
    // sample standard deviation of percolation threshold

    public double stddev() {
        if (trials == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(threshold);
    }
    // low  endpoint of 95% confidence interval

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }
    // high endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    private void validate(final int num) {
        if (num <= 0) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
    }

    private double randomPercolation() {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
            if (percolation.numberOfOpenSites() == (gridSize)) {
                return 1;
            }
        }
        int numberOfOpenSites = percolation.numberOfOpenSites();
        double result = (double) numberOfOpenSites / gridSize;
        return result;
    }

    private void doTrials() {
        for (int i = 0; i < trials; i++) {
            threshold[i] = randomPercolation();
        }
    }
}
