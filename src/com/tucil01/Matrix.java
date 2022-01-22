public class Matrix {
    private final char[][] contents;
    private int rows;
    private int cols;

    Matrix(int rows, int cols) {
        this.contents = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setElmt(int row, int col, char c) {
        this.contents[row][col] = c;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%c ", this.contents[i][j]);
            }
            System.out.println();
        }
    }
}
