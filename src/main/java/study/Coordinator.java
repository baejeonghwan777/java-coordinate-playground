package study;

import java.util.List;

public class Coordinator {
    private static final int MAX = 24;
    private Coordinates coordinates;

    public Coordinator() {
    }

    public Coordinator(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void runCoordination() {
        List<Integer> pair = InputView.inputPoint();
        make(pair);
        ResultView.printTotal(MAX, coordinates);
    }

    public void make(List<Integer> pair) {
        if (pair.size() == 4) coordinates = new Line();
        if (pair.size() == 6) coordinates = new Triangle();
        if (pair.size() == 8) coordinates = new Quadrangle();
        coordinates.makePoint(pair);
    }
}
