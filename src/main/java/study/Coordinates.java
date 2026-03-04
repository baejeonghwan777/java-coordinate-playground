package study;

import java.util.List;

public interface Coordinates {
    void makePoint(Output output);

    double makeResult();

    StringBuilder printResult();

    List<Integer> getPointX(int max, int index);
}
