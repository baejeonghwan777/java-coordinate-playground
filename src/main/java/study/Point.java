package study;

import java.util.List;
import java.util.Objects;

public class Point {
    private final int x;
    private final int y;

    public Point(List<Integer> pair, int x, int y) {
        this.x = pair.get(x);
        this.y = pair.get(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
