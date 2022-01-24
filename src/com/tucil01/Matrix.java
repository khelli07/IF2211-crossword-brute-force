import java.util.ArrayList;

public
class Matrix {
    private final ColoredChar[][] contents;
    private int rows;
    private int cols;
    private int compareCount;

    public enum Direction {
        UPPER,
        UPPER_RIGHT,
        RIGHT,
        BOTTOM_RIGHT,
        BOTTOM,
        BOTTOM_LEFT,
        LEFT,
        UPPER_LEFT
    }

    Matrix(int rows, int cols) {
        this.contents = new ColoredChar[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.compareCount = 0;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public ColoredChar getElmt(Coordinate cr) {
        return this.contents[cr.getX()][cr.getY()];
    }

    public int getCompareCount() {
        return this.compareCount;
    }

    public void setElmt(Coordinate cr, char c, ColoredChar.Color color) {
        this.contents[cr.getX()][cr.getY()] = new ColoredChar(c);
    }

    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%c ", this.getElmt(new Coordinate(i, j)).getChar());
            }
            System.out.println();
        }
    }

    public void printColoredMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.getElmt(new Coordinate(i, j)).printChar();
            }
            System.out.println();
        }
    }

    public void printWord(ArrayList<Coordinate> wordCoordinates, String word) {
        Matrix tmpMatrix = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                tmpMatrix.setElmt(new Coordinate(i, j), '-', ColoredChar.Color.RESET);
            }
        }

        for (int i = 0; i < word.length(); i++) {
            tmpMatrix.setElmt(wordCoordinates.get(i), word.charAt(i), ColoredChar.Color.RESET);
        }

        System.out.println("Word: " + word);
        tmpMatrix.printMatrix();
        System.out.println();
    }

    public boolean isCrValid(Coordinate cr) {
        return (cr.getX() >= 0 && cr.getY() >= 0 && cr.getX() < this.rows && cr.getY() < this.cols);
    }

    public boolean checkCharacter(Coordinate cr, char c) {
        this.compareCount++;
        return (this.getElmt(cr).getChar() == c);
    }

    private void moveRight(Coordinate cr) {
        cr.setCoordinate(cr.getX(), cr.getY() + 1);
    }

    private void moveBottomRight(Coordinate cr) {
        cr.setCoordinate(cr.getX() + 1, cr.getY() + 1);
    }

    private void moveBottom(Coordinate cr) {
        cr.setCoordinate(cr.getX() + 1, cr.getY());
    }

    private void moveBottomLeft(Coordinate cr) {
        cr.setCoordinate(cr.getX() + 1, cr.getY() - 1);
    }

    private void moveLeft(Coordinate cr) {
        cr.setCoordinate(cr.getX(), cr.getY() - 1);
    }

    private void moveUpperLeft(Coordinate cr) {
        cr.setCoordinate(cr.getX() - 1, cr.getY() - 1);
    }

    private void moveUpper(Coordinate cr) {
        cr.setCoordinate(cr.getX() - 1, cr.getY());
    }

    private void moveUpperRight(Coordinate cr) {
        cr.setCoordinate(cr.getX() - 1, cr.getY() + 1);
    }

    public ArrayList<Direction> decideDirection(Coordinate cr, char[] word) {
        ArrayList<Direction> dirList = new ArrayList<>();
        int len = word.length - 1;
        int x = cr.getX();
        int y = cr.getY();

        int[] moveX = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] moveY = {1, 1, 0, -1, -1, -1, 0, 1};
        Direction[] availableDir = {
                Direction.RIGHT,
                Direction.BOTTOM_RIGHT,
                Direction.BOTTOM,
                Direction.BOTTOM_LEFT,
                Direction.LEFT,
                Direction.UPPER_LEFT,
                Direction.UPPER,
                Direction.UPPER_RIGHT
        };

        for (int i = 0; i < moveX.length; i++) {
            Coordinate secCr = new Coordinate(x + moveX[i], y + moveY[i]);
            Coordinate lastCr = new Coordinate(x + moveX[i] * len, y + moveY[i] * len);
            if (this.isCrValid(secCr) && this.isCrValid(lastCr) && this.checkCharacter(secCr, word[1])) {
                dirList.add(availableDir[i]);
            }
        }
        return dirList;
    }

    public void moveOneStep(Coordinate cr, Direction dir) {
        switch (dir) {
            case RIGHT -> moveRight(cr);
            case BOTTOM_RIGHT -> moveBottomRight(cr);
            case BOTTOM_LEFT -> moveBottomLeft(cr);
            case BOTTOM -> moveBottom(cr);
            case LEFT -> moveLeft(cr);
            case UPPER_LEFT -> moveUpperLeft(cr);
            case UPPER -> moveUpper(cr);
            case UPPER_RIGHT -> moveUpperRight(cr);
        }
    }
}
