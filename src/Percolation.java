import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author mkazmyryk
 */
public final class Percolation {
    private final static int EX_NUMBER = 3;
    private int[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF unionUF;
    private int n;
    private int openNum = 0;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.n = n;
            gridSize = n * n;
            grid = new int[n + 1][n + 1];
            unionUF = new WeightedQuickUnionUF(gridSize + EX_NUMBER);
        }
    }              // create n-by-n grid, with all sites blocked

    public void open(final int row, final int col) {
        validate(row);
        validate(col);
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            openNum++;
            linkNeighbors(row, col);
        }
    }    // open site (row, col) if it is not open already

    public boolean isOpen(final int row, final int col) {
        validate(row);
        validate(col);
        return grid[row][col] > 0;
    }  // is site (row, col) open?

    public boolean isFull(final int row, final int col) {
        validate(row);
        validate(col);
        if (unionUF.connected(xyTo1D(row, col), gridSize + 1)) {
            return true;
        }
        return false;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return openNum;
    }      // number of open sites

    public boolean percolates() {
        if (unionUF.connected(gridSize + 1, gridSize + 2)) {
            return true;
        }
        return false;

    }              // does the system percolate?

    private int xyTo1D(final int row, final int col) {
        return (row - 1) * n + col;
    }

    private void validate(final int num) {
        if (num <= 0 || num > n) {
            throw
                    new IndexOutOfBoundsException("row index i out of bounds");
        }
    }

    private void linkNeighbors(final int row, final int col) {
        int cur = xyTo1D(row, col);
        if (col == 1 && row == 1) {
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (!unionUF.connected(cur, gridSize)) {
                unionUF.union(cur, gridSize + 1);
            }
        }
        if (row == 1 && col < n && col > 1) {
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (!unionUF.connected(cur, gridSize + 1)) {
                unionUF.union(cur, gridSize + 1);
            }
        }
        if (row == 1 && col == n) {
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (!unionUF.connected(cur, gridSize + 1)) {
                unionUF.union(cur, gridSize + 1);
            }
        }
        if (col == 1 && row < n && row > 1) {
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
        }
        if (row == n && col == 1) {
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
            if (!unionUF.connected(cur, gridSize + 2)) {
                unionUF.union(cur, gridSize + 2);
            }
        }
        if (row == n && col > 1 && col < n) {
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
            if (!unionUF.connected(cur, gridSize + 2)) {
                unionUF.union(cur, gridSize + 2);
            }
        }
        if (row == n && col == n) {
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
            if (!unionUF.connected(cur, gridSize + 2)) {
                unionUF.union(cur, gridSize + 2);
            }
        }
        if (col == n && row > 1 && row < n) {
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
        }
        if (col > 1 && col < n && row > 1 && row < n) {
            if (isOpen(row - 1, col)) {
                unionUF.union(cur, xyTo1D(row - 1, col));
            }
            if (isOpen(row + 1, col)) {
                unionUF.union(cur, xyTo1D(row + 1, col));
            }
            if (isOpen(row, col - 1)) {
                unionUF.union(cur, xyTo1D(row, col - 1));
            }
            if (isOpen(row, col + 1)) {
                unionUF.union(cur, xyTo1D(row, col + 1));
            }
        }
    }
}
