package study;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.lineSeparator();
    private final List<Car> cars;

    public static RentCompany create() {
        return new RentCompany();
    }

    public RentCompany() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Car car : cars) {
            report.append(car.getName()).append(" : ").append((int) car.getChargeQuantity()).append("리터").append(NEWLINE);
        }
        return report.toString();
    }
}
