public class Coordinate {
    private int x;
    private int y;

    Coordinate(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinate(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public Coordinate copy() {
        return new Coordinate(this.x, this.y);
    }
}
