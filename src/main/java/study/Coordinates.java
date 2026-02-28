package study;

import java.util.List;

public interface Coordinates {
    void makePoint(List<Integer> pair);

    double makeResult();

    StringBuilder printResult();

    List<Integer> getPointX(int max, int index);
}
