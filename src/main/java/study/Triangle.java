package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle implements Coordinates {
    private static final int POINT_INT_LENGTH = 6;
    private static final int LENGTH = 3;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private final Point[] points;
    private final Line[] lines;

    public Triangle() {
        this.lines = new Line[LENGTH];
        this.points = new Point[LENGTH];
    }

    @Override
    public void makePoint(List<Integer> pair) {
        for (int i = 0; i < POINT_INT_LENGTH; i += 2) {
            points[i / 2] = new Point(pair, i, i + 1);
        }
        for (int i = 0; i < LENGTH; i++) {
            lines[i] = new Line();
            if(i == 2) lines[i].makePoint(points[i], points[0]);
            if(i != 2) lines[i].makePoint(points[i], points[i + 1]);
        }
    }

    @Override
    public double makeResult() {
        double semi = (lines[FIRST].makeResult() + lines[SECOND].makeResult() + lines[THIRD].makeResult()) / 2;
        return Math.sqrt(semi * (semi - lines[FIRST].makeResult()) * (semi - lines[SECOND].makeResult()) *
                (semi - lines[THIRD].makeResult()));
    }

    @Override
    public StringBuilder printResult() {
        StringBuilder output = new StringBuilder();
        output.append("삼각형의 넓이는 ");
        output.append(makeResult()).append("입니다.\n");
        return output;
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
        if (getValueY(SECOND) == max - index) pointX.add(getValueX(SECOND));
        if (getValueY(THIRD) == max - index) pointX.add(getValueX(THIRD));

        Collections.sort(pointX);
        return pointX;
    }
}
