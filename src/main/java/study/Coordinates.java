package study;

public class Coordinates {
    private final Coordinate[] coordinates;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int X1 = 0;
    private static final int Y1 = 1;
    private static final int X2 = 2;
    private static final int Y2 = 3;

    public Coordinates() {
        this.coordinates = new Coordinate[2];
    }

    public void makeCoordinate(int[] pair) {
        coordinates[FIRST] = new Coordinate(pair[X1], pair[Y1]);
        coordinates[SECOND] = new Coordinate(pair[X2], pair[Y2]);
    }

    public double makeDistance() {
        return (Math.sqrt(Math.pow(coordinates[FIRST].getX() - coordinates[SECOND].getX(), 2) +
                          Math.pow(coordinates[FIRST].getY() - coordinates[SECOND].getY(), 2)));
    }

    public boolean equalCoordinate() {
        return coordinates[FIRST].equals(coordinates[SECOND]);
    }

    public boolean equalCoordinateY() {
        return coordinates[FIRST].getY() == coordinates[SECOND].getY();
    }

    public int getValue(String value) {
        if(value.equals("firstX")) return coordinates[FIRST].getX();
        if(value.equals("firstY")) return coordinates[FIRST].getY();
        if(value.equals("secondX")) return coordinates[SECOND].getX();
        if(value.equals("secondY")) return coordinates[SECOND].getY();
        return -1;
    }
}
