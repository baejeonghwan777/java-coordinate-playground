package study;

public class Coordinator {
    private Coordinates coordinates;
    private static final int MAX = 24;
    private static final int THRESHOLD = 5;

    public Coordinator() {
    }

    public Coordinator(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void runCoordination() {
        int[] pair = InputView.inputCoordinate();
        make(pair);
        ResultView.printTotal(MAX, coordinates);
    }

    public void make(int[] pair) {
        if(pair[THRESHOLD] == 0) coordinates = new Line();
        if(pair[THRESHOLD] != 0) coordinates = new Quadrangle();
        coordinates.makePoint(pair);
    }
}
