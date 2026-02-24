package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CoordinateTest {
    Coordinates coordinates;
    Coordinator coordinator;
    static final int MAX = 24;

    @BeforeEach
    public void setUp() {
        coordinates = new Coordinates();
        coordinator = new Coordinator(coordinates);
    }

    @DisplayName("거리 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void distanceTest() {
        int expected = 5;

        int[] pair = {3,3,6,7};
        coordinates.makeCoordinate(pair);
        int result = (int) coordinates.makeDistance();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 정상적인 문자를 입력했을 때 제대로 분리되는지 확인한다.")
    @Test
    public void parseTest() {
        int[] expected = {3,3,6,7};

        int[] result = new int[4];
        String input = "(3,3)-(6,7)";
        InputView.parseCoordinate(input, result);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 과도하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestMore() {
        boolean expected = false;

        int[] pair = new int[4];
        String input = "(3,3)-(6,7)-(8,9)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 부족하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestLess() {
        boolean expected = false;

        int[] pair = new int[4];
        String input = "(3,3)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValid() {
        boolean expected = false;

        int[] pair = new int[4];
        String input = ")3-3(,)6-7(";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBound() {
        boolean expected = false;

        int[] pair = new int[4];
        String input = "(25,26)-(100,101)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("서로 같은 좌표를 가진 객체가 똑같다고 인식되는지 확인한다.")
    @Test
    public void equalCoordinationTest() {
        boolean expected = true;

        Coordinate coordinate1 = new Coordinate(3,4);
        Coordinate coordinate2 = new Coordinate(3,4);
        boolean result = coordinate1.equals(coordinate2);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("주어진 입력값을 넣었을 때 원하는 좌표 값이 제대로 반환되는지 확인한다.")
    @Test
    public void inputCoordinationTest() {
        int[] pair = {3,4,6,7};
        coordinates.makeCoordinate(pair);

        assertAll(
                () -> assertThat(3).isEqualTo(coordinates.getValue("firstX")),
                () -> assertThat(4).isEqualTo(coordinates.getValue("firstY")),
                () -> assertThat(6).isEqualTo(coordinates.getValue("secondX")),
                () -> assertThat(7).isEqualTo(coordinates.getValue("secondY")),
                () -> assertThat(-1).isEqualTo(coordinates.getValue("none"))
        );
    }

    @DisplayName("두 좌표가 서로 겹칠 때 좌표가 하나만 출력되는지 확인한다.")
    @Test
    public void duplicationTest() {
        String expected = "         *";

        int[] pair = {6,7,6,7};
        coordinates.makeCoordinate(pair);
        String result = ResultView.analyzePoint(coordinates, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("두 좌표가 같은 Y 좌표를 가질 때 두 점이 한 라인에 출력되는지 확인한다.")
    @Test
    public void equalYTest() {
        String expected = "*        *";

        int[] pair = {1,7,6,7};
        coordinates.makeCoordinate(pair);
        String result = ResultView.analyzePoint(coordinates, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }
}
