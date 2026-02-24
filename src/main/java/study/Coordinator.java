package study;

public class Coordinator {
    private final Coordinates coordinates;
    private static final int MAX = 24;

    public Coordinator(Coordinates coordinates) {
        this.coordinates = new Coordinates();
    }

    public void runCoordination() {
        int[] pair = InputView.inputCoordinate();
        coordinates.makeCoordinate(pair);
        ResultView.printTotal(MAX, coordinates);
    }
}
