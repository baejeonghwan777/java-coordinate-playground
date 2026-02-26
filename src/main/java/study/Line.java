package study;

public class Line implements Coordinates {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int X1 = 0;
    private static final int Y1 = 1;
    private static final int X2 = 2;
    private static final int Y2 = 3;
    private final Point[] points;

    public Line() {
        this.points = new Point[2];
    }

    @Override
    public void makePoint(int[] pair) {
        points[FIRST] = new Point(pair[X1], pair[Y1]);
        points[SECOND] = new Point(pair[X2], pair[Y2]);
    }

    @Override
    public int pointLength() {
        return points.length;
    }

    @Override
    public double makeResult() {
        return (Math.sqrt(Math.pow(points[FIRST].getX() - points[SECOND].getX(), 2) +
                Math.pow(points[FIRST].getY() - points[SECOND].getY(), 2)));
    }

    public boolean equalPoint() {
        return points[FIRST].equals(points[SECOND]);
    }

    public boolean equalPointY() {
        return points[FIRST].getY() == points[SECOND].getY();
    }

    @Override
    public int getValue(String value) {
        if (value.equals("firstX")) return points[FIRST].getX();
        if (value.equals("firstY")) return points[FIRST].getY();
        if (value.equals("secondX")) return points[SECOND].getX();
        if (value.equals("secondY")) return points[SECOND].getY();
        return -1;
    }
}
