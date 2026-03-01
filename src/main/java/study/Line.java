package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void makePoint(List<Integer> pair) {
        points[FIRST] = new Point(pair, X1, Y1);
        points[SECOND] = new Point(pair, X2, Y2);
    }

    public void makePoint(Point pointLeft, Point pointRight) {
        points[FIRST] = pointLeft;
        points[SECOND] = pointRight;
    }

    @Override
    public double makeResult() {
        return (Math.sqrt(Math.pow(points[FIRST].getX() - points[SECOND].getX(), 2) +
                Math.pow(points[FIRST].getY() - points[SECOND].getY(), 2)));
    }

    public boolean equalPoint() {
        return points[FIRST].equals(points[SECOND]);
    }

    public int getValueX(int index) {
        return points[index].getX();
    }

    public int getValueY(int index) {
        return points[index].getY();
    }

    @Override
    public List<Integer> getPointX(int max, int index) {
        List<Integer> pointX = new ArrayList<>();

        if (getValueY(FIRST) == max - index) pointX.add(getValueX(FIRST));
        if (!equalPoint() && getValueY(SECOND) == max - index) pointX.add(getValueX(SECOND));

        Collections.sort(pointX);
        return pointX;
    }

    @Override
    public StringBuilder printResult() {
        StringBuilder output = new StringBuilder();
        output.append("두 점 사이의 거리는 ");
        output.append(makeResult()).append("입니다.\n");
        return output;
    }
}
