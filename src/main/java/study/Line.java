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

    @Override
    public double makeResult() {
        return (Math.sqrt(Math.pow(points[FIRST].getX() - points[SECOND].getX(), 2) +
                Math.pow(points[FIRST].getY() - points[SECOND].getY(), 2)));
    }

    public boolean equalPoint() {
        return points[FIRST].equals(points[SECOND]);
    }

    public int getFirstX() {
        return points[FIRST].getX();
    }

    public int getFirstY() {
        return points[FIRST].getY();
    }

    public int getSecondX() {
        return points[SECOND].getX();
    }

    public int getSecondY() {
        return points[SECOND].getY();
    }

    @Override
    public List<Integer> getPointX(int max, int index) {
        List<Integer> pointX = new ArrayList<>();

        if (getFirstY() == max - index) pointX.add(getFirstX());
        if (!equalPoint() && getSecondY() == max - index) pointX.add(getSecondX());

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
