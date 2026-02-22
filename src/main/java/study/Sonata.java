package study;

public class Sonata implements Car {
    private final String name;
    private final Distance distance;
    private final int DISTANCE_PER_LITER = 10;

    public Sonata(int distance) {
        this.distance = new Distance(distance);
        this.name = "Sonata";
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
