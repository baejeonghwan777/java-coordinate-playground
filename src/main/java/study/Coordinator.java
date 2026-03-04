package study;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Coordinator {
    private static final int MAX = 24;
    private Coordinates coordinates;

    public Coordinator() {

    }

    public Coordinator(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void runCoordination() {
        Output output = InputView.inputPoint();
        make(output);
        ResultView.printTotal(MAX, coordinates);
    }

    private final Map<Predicate<Output>, Supplier<Coordinates>> factory = Map.of( // 조건에 따른 사이즈 판별
            Output::isValidLineSize, Line::new,
            Output::isValidTriSize, Triangle::new,
            Output::isValidQuadSize, Quadrangle::new
    );

    public void make(Output output) { // Map을 조회하여 키값을 훑어 생성할 객체를 정한 후 벨류값을 가져와 실제 객체 생성
        coordinates = factory.entrySet().stream()
                .filter(entry -> entry.getKey().test(output))
                .map(entry -> entry.getValue().get())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 도형입니다."));

        coordinates.makePoint(output);
    }
}
