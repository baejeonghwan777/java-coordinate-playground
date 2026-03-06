package study;

public class K5 implements Car {
    private final String name;
    private final Distance distance;
    private final int DISTANCE_PER_LITER = 13;

    public K5(int distance) {
        this.distance = new Distance(distance);
        this.name = "K5";
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return distance.getValue();
    }

    @Override
    public String getName() {
        return name;
    }
}
