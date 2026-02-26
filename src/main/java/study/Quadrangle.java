package study;

public class Quadrangle implements Coordinates {
    private final Point[] points;
    private static final int POINT_INT_LENGTH = 8;
    private static final int POINT_LENGTH = 4;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Quadrangle() {
        this.points = new Point[POINT_LENGTH];
    }

    public void makePoint(int[] pair) {
        for (int i = 0; i < POINT_INT_LENGTH; i += 2) {
            points[i / 2] = new Point(pair[i], pair[i + 1]);
        }
        initMaxX();
        initMaxY();
        initMinX();
        initMinY();
    }

    @Override
    public int pointLength() {
        return points.length;
    }

    @Override
    public double makeResult() {
        int height = maxY - minY;
        int width = maxX - minX;
        return height * width;
    }

    public void initMaxX() {
        maxX = 0;
        for (int i = 0; i < POINT_LENGTH; i++) {
            if(maxX < points[i].getX()) maxX = points[i].getX();
        }
    }

    public void initMaxY() {
        maxY = 0;
        for (int i = 0; i < POINT_LENGTH; i++) {
            if(maxY < points[i].getY()) maxY = points[i].getY();
        }
    }

    public void initMinX() {
        minX = 100;
        for (int i = 0; i < POINT_LENGTH; i++) {
            if(minX > points[i].getX()) minX = points[i].getX();
        }
    }

    public void initMinY() {
        minY = 100;
        for (int i = 0; i < POINT_LENGTH; i++) {
            if(minY > points[i].getY()) minY = points[i].getY();
        }
    }

    @Override
    public int getValue(String value) {
        if(value.equals("maxX")) return maxX;
        if(value.equals("maxY")) return maxY;
        if(value.equals("minX")) return minX;
        if(value.equals("minY")) return minY;
        return -1;
    }
}
