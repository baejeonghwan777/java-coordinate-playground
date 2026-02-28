package study;

import java.util.ArrayList;
import java.util.List;

public class Quadrangle implements Coordinates {
    private static final int POINT_INT_LENGTH = 8;
    private static final int POINT_LENGTH = 4;
    private final Point[] points;

    public Quadrangle() {
        this.points = new Point[POINT_LENGTH];
    }

    public void makePoint(List<Integer> pair) {
        for (int i = 0; i < POINT_INT_LENGTH; i += 2) {
            points[i / 2] = new Point(pair, i, i + 1);
        }
    }

    @Override
    public double makeResult() {
        int height = getMaxY() - getMinY();
        int width = getMaxX() - getMinX();
        return height * width;
    }

    public int getMaxX() {
        int maxX = 0;

        for (int i = 0; i < POINT_LENGTH; i++) {
            if (maxX < points[i].getX()) maxX = points[i].getX();
        }

        return maxX;
    }

    public int getMaxY() {
        int maxY = 0;

        for (int i = 0; i < POINT_LENGTH; i++) {
            if (maxY < points[i].getY()) maxY = points[i].getY();
        }

        return maxY;
    }

    public int getMinX() {
        int minX = 100;

        for (int i = 0; i < POINT_LENGTH; i++) {
            if (minX > points[i].getX()) minX = points[i].getX();
        }

        return minX;
    }

    public int getMinY() {
        int minY = 100;

        for (int i = 0; i < POINT_LENGTH; i++) {
            if (minY > points[i].getY()) minY = points[i].getY();
        }

        return minY;
    }

    @Override
    public List<Integer> getPointX(int max, int index) {
        List<Integer> pointX = new ArrayList<>();

        if (getMaxY() == max - index || getMinY() == max - index) {
            pointX.add(getMinX());
            pointX.add(getMaxX());
        }

        return pointX;
    }

    @Override
    public StringBuilder printResult() {
        StringBuilder output = new StringBuilder();
        output.append("사각형의 넓이는 ");
        output.append(makeResult()).append("입니다.\n");
        return output;
    }
}
