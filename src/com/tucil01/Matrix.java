import java.util.ArrayList;

public class Matrix {
    private final char[][] contents;
    private int rows;
    private int cols;

    public enum Direction {
        UPPER, UPPER_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, UPPER_LEFT
    }

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

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public boolean isCrValid(Coordinate cr) {
        return (cr.getX() >= 0 && cr.getY() >= 0 && cr.getX() < this.rows && cr.getY() < this.cols);
    }

    public void setElmt(Coordinate cr, char c) {
        this.contents[cr.getX()][cr.getY()] = c;
    }

    public char getElmt(Coordinate cr) {
        return this.contents[cr.getX()][cr.getY()];
    }

    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%c ", this.getElmt(new Coordinate(i, j)));
            }
            System.out.println();
        }
    }

    public boolean checkCurrentChar(Coordinate cr, char c) {
        return (this.getElmt(cr) == c);
    }

    public ArrayList<Direction> decideDirection(Coordinate cr, char nextChar) {
        ArrayList<Direction> dirList = new ArrayList<>();
        int x = cr.getX();
        int y = cr.getY();
        Coordinate crRight = new Coordinate(x, y + 1);
        Coordinate crBRight = new Coordinate(x + 1, y + 1);
        Coordinate crBottom = new Coordinate(x + 1, y);
        Coordinate crBLeft = new Coordinate(x + 1, y - 1);
        Coordinate crLeft = new Coordinate(x, y - 1);
        Coordinate crULeft = new Coordinate(x - 1, y - 1);
        Coordinate crUpper = new Coordinate(x - 1, y);
        Coordinate crURight = new Coordinate(x - 1, y + 1);

        if (this.isCrValid(crRight) && this.getElmt(crRight) == nextChar) {
            dirList.add(Direction.RIGHT);
        } else if (this.isCrValid(crBRight) && this.getElmt(crBRight) == nextChar) {
            dirList.add(Direction.BOTTOM_RIGHT);
        } else if (this.isCrValid(crBottom) && this.getElmt(crBottom) == nextChar) {
            dirList.add(Direction.BOTTOM);
        } else if (this.isCrValid(crBLeft) && this.getElmt(crBLeft) == nextChar) {
            dirList.add(Direction.BOTTOM_LEFT);
        } else if (this.isCrValid(crLeft) && this.getElmt(crLeft) == nextChar) {
            dirList.add(Direction.LEFT);
        } else if (this.isCrValid(crULeft) && this.getElmt(crULeft) == nextChar) {
            dirList.add(Direction.UPPER_LEFT);
        } else if (this.isCrValid(crUpper) && this.getElmt(crUpper) == nextChar) {
            dirList.add(Direction.UPPER);
        } else if (this.isCrValid(crURight) && this.getElmt(crURight) == nextChar) {
            dirList.add(Direction.UPPER_RIGHT);
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
}
