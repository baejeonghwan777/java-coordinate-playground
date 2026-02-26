package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CoordinateTest {
    Quadrangle quadrangle;
    Line line;
    Coordinator coordinatorQ;
    Coordinator coordinatorL;
    static final int MAX = 24;

    @BeforeEach
    public void setUp() {
        quadrangle = new Quadrangle();
        line = new Line();
        coordinatorQ = new Coordinator(quadrangle);
        coordinatorL = new Coordinator(line);
    }

    @DisplayName("거리 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void distanceTest() {
        int expected = 5;

        int[] pair = {3,3,6,7,0,0,0,0};
        line.makePoint(pair);
        int result = (int) line.makeResult();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("넓이 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void AreaTest() {
        int expected = 12;

        int[] pair = {3,5,3,8,7,5,7,8};
        quadrangle.makePoint(pair);
        int result = (int) quadrangle.makeResult();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 정상적인 문자를 입력했을 때 제대로 분리되는지 확인한다.")
    @Test
    public void parseTest() {
        int[] expected = {3,3,6,7,0,0,0,0};

        int[] result = new int[8];
        String input = "(3,3)-(6,7)";
        InputView.parseCoordinate(input, result);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 과도하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestMore() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = "(3,3)-(6,7)-(8,9)-(4,5)-(11,14)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 부족하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestLess() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = "(3,3)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 좌표를 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValidLine() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = ")3-3(,)6-7(";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을을 이루는 좌표를 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValidQuad() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = ")3-3(,)6-3(,)3-7(,)6-7(";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 좌표를 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBoundLine() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = "(25,26)-(100,101)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표를 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBoundQuad() {
        boolean expected = false;

        int[] pair = new int[8];
        String input = "(25,26)-(100,26)-(25,101)-(100,101)";
        boolean result = InputView.parseCoordinate(input, pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("서로 같은 좌표를 가진 객체가 똑같다고 인식되는지 확인한다.")
    @Test
    public void equalCoordinationTest() {
        boolean expected = true;

        Point coordinate1 = new Point(3,4);
        Point coordinate2 = new Point(3,4);
        boolean result = coordinate1.equals(coordinate2);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표 중 중복된 좌표가 있을 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void duplicateCoordinateTest() {
        boolean expected = false;

        int[] pair = {1,1,1,4,1,1,1,4};
        boolean result = InputView.equalQuad(pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표 중 직사각형이 아닐 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void validRectAngularTest() {
        boolean expected = false;

        int[] pair = {1,1,2,2,3,3,1,4};
        boolean result = InputView.checkCoordinateQuad(pair);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 좌표 입력값을 넣었을 때 원하는 좌표 값이 제대로 반환되는지 확인한다.")
    @Test
    public void inputPointTestLine() {
        int[] pair = {3,4,6,7,0,0,0,0};
        line.makePoint(pair);

        assertAll(
                () -> assertThat(3).isEqualTo(line.getValue("firstX")),
                () -> assertThat(4).isEqualTo(line.getValue("firstY")),
                () -> assertThat(6).isEqualTo(line.getValue("secondX")),
                () -> assertThat(7).isEqualTo(line.getValue("secondY")),
                () -> assertThat(-1).isEqualTo(line.getValue("none"))
        );
    }

    @DisplayName("사각형을 이루는 좌표 입력값을 넣었을 때 원하는 좌표 값이 제대로 반환되는지 확인한다.")
    @Test
    public void inputPointTestQuad() {
        int[] pair = {3,4,5,4,3,7,5,7};
        quadrangle.makePoint(pair);

        assertAll(
                () -> assertThat(5).isEqualTo(quadrangle.getValue("maxX")),
                () -> assertThat(7).isEqualTo(quadrangle.getValue("maxY")),
                () -> assertThat(3).isEqualTo(quadrangle.getValue("minX")),
                () -> assertThat(4).isEqualTo(quadrangle.getValue("minY")),
                () -> assertThat(-1).isEqualTo(quadrangle.getValue("none"))
        );
    }

    @DisplayName("직선을 이루는 두 좌표가 서로 겹칠 때 좌표가 하나만 출력되는지 확인한다.")
    @Test
    public void duplicationTest() {
        String expected = "         *";

        int[] pair = {6,7,6,7,0,0,0,0};
        line.makePoint(pair);
        String result = ResultView.analyzePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 두 좌표가 같은 Y 좌표를 가질 때 두 점이 한 라인에 출력되는지 확인한다.")
    @Test
    public void equalYTest() {
        String expected = "*        *";

        int[] pair = {1,7,6,7,0,0,0,0};
        line.makePoint(pair);
        String result = ResultView.analyzePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 변수가 제대로 된 좌표 개수를 반환하는지 확인한다.")
    @Test
    public void lengthTestLine() {
        int expected = 2;

        int[] pair = {1,7,6,7,0,0,0,0};
        line.makePoint(pair);
        int result = line.pointLength();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 변수가 제대로 된 좌표 개수를 반환하는지 확인한다.")
    @Test
    public void lengthTestQuad() {
        int expected = 4;

        int[] pair = {1,7,6,7,1,10,6,10};
        quadrangle.makePoint(pair);
        int result = quadrangle.pointLength();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형의 길이를 이루는 두 점이 제대로 출력되는지 확인한다.")
    @Test
    public void QuadrangleWidthTest() {
        String expected = "*        *";

        int[] pair = {1,7,6,7,1,16,6,16};
        line.makePoint(pair);
        String result = ResultView.analyzePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

}
