package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class CoordinateTest {
    static final int MAX = 24;
    static final int X1 = 0;
    static final int Y1 = 1;
    static final int X2 = 2;
    static final int Y2 = 3;
    Quadrangle quadrangle;
    Triangle triangle;
    Line line;
    Coordinator coordinatorQ;
    Coordinator coordinatorT;
    Coordinator coordinatorL;

    @BeforeEach
    public void setUp() {
        quadrangle = new Quadrangle();
        line = new Line();
        triangle = new Triangle();
        coordinatorQ = new Coordinator(quadrangle);
        coordinatorT = new Coordinator(triangle);
        coordinatorL = new Coordinator(line);
    }

    @DisplayName("거리 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void distanceTest() {
        int expected = 5;

        Output output = new Output(new ArrayList<>(Arrays.asList(3, 3, 6, 7)));
        line.makePoint(output);
        int result = (int) line.makeResult();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형 넓이 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void quadAreaTest() {
        int expected = 12;

        Output output = new Output(new ArrayList<>(Arrays.asList(3, 5, 3, 8, 7, 5, 7, 8)));
        quadrangle.makePoint(output);
        int result = (int) quadrangle.makeResult();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("삼각형 넓이 계산이 제대로 이루어지는지 확인한다.")
    @Test
    public void triAreaTest() {
        int expected = 10;

        Output output = new Output(new ArrayList<>(Arrays.asList(3, 5, 3, 9, 8, 9)));
        triangle.makePoint(output);
        int result = (int) triangle.makeResult();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 정상적인 문자를 입력했을 때 제대로 분리되는지 확인한다.")
    @Test
    public void parseTest() {
        Output expected = new Output(new ArrayList<>(Arrays.asList(3, 3, 6, 7)));

        Output result = new Output();
        String input = "(3,3)-(6,7)";
        InputView.parsePoint(input, result);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 과도하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestMore() {
        boolean expected = false;

        Output output = new Output();
        String input = "(3,3)-(6,7)-(8,9)-(4,5)-(11,14)";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("입력한 문자열이 숫자를 부족하게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestLess() {
        boolean expected = false;

        Output output = new Output();
        String input = "(3,3)";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 좌표를 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValidLine() {
        boolean expected = false;

        Output output = new Output();
        String input = ")3-3(,)6-7(";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("삼각형을을 이루는 좌표를 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValidTri() {
        boolean expected = false;

        Output output = new Output();
        String input = ")3-3(,)6-3(,)6-6(";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을을 이루는 좌표를 숫자를 형식에 맞지 않게 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestInValidQuad() {
        boolean expected = false;

        Output output = new Output();
        String input = ")3-3(,)6-3(,)3-7(,)6-7(";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 좌표를 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBoundLine() {
        boolean expected = false;

        Output output = new Output();
        String input = "(25,26)-(100,101)";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표를 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBoundTri() {
        boolean expected = false;

        Output output = new Output();
        String input = "(25,26)-(200,26)-(25,200)";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표를 숫자를 범위 바깥으로 입력했을 때 잘못된 입력으로 간주하는지 확인한다.")
    @Test
    public void parseTestOutOfBoundQuad() {
        boolean expected = false;

        Output output = new Output();
        String input = "(25,26)-(100,26)-(25,101)-(100,101)";
        boolean result = InputView.parsePoint(input, output);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("서로 같은 좌표를 가진 객체가 똑같다고 인식되는지 확인한다.")
    @Test
    public void equalCoordinationTest() {
        boolean expected = true;

        Output output = new Output(new ArrayList<>(Arrays.asList(3, 4, 3, 4)));
        Point coordinate1 = new Point(output, X1, Y1);
        Point coordinate2 = new Point(output, X2, Y2);
        boolean result = coordinate1.equals(coordinate2);

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("삼각형을 이루는 좌표 중 세 좌표가 세로로 일직선을 이룰 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void oneLineCoordinateTest() {
        boolean expected = false;

        Output output = new Output(new ArrayList<>(Arrays.asList(1, 6, 5, 6, 8, 6)));
        boolean result = output.checkPoint();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("삼각형을 이루는 좌표 중 세 좌표가 대각선으로 일직선을 이룰 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void diagonalCoordinateTest() {
        boolean expected = false;

        Output output = new Output(new ArrayList<>(Arrays.asList(1, 1, 3, 3, 5, 5)));
        boolean result = output.checkPoint();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표 중 중복된 좌표가 있을 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void duplicateCoordinateTest() {
        boolean expected = false;

        Output output = new Output(new ArrayList<>(Arrays.asList(1, 1, 1, 4, 1, 1, 1, 4)));
        boolean result = output.checkPoint();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형을 이루는 좌표 중 직사각형이 아닐 때 잘못되었음을 출력하는지 확인한다.")
    @Test
    public void validRectAngularTest() {
        boolean expected = false;

        Output output = new Output(new ArrayList<>(Arrays.asList(1, 6, 5, 6, 3, 3, 3, 9)));
        boolean result = output.checkPoint();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 두 좌표가 서로 겹칠 때 좌표가 하나만 출력되는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = "         *")
    public void duplicationTest(String expected) {
        Output output = new Output(new ArrayList<>(Arrays.asList(6, 7, 6, 7)));
        line.makePoint(output);
        String result = ResultView.organizePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("직선을 이루는 두 좌표가 같은 Y 좌표를 가질 때 두 점이 한 라인에 출력되는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = "*        *")
    public void equalYTest(String expected) {
        Output output = new Output(new ArrayList<>(Arrays.asList(1, 7, 6, 7)));
        line.makePoint(output);
        String result = ResultView.organizePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("삼각형의 길이를 이루는 두 점이 제대로 출력되는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = "*        *")
    public void TriangleWidthTest(String expected) {
        Output output = new Output(new ArrayList<>(Arrays.asList(1, 7, 6, 7, 1, 15)));
        line.makePoint(output);
        String result = ResultView.organizePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }

    @DisplayName("사각형의 길이를 이루는 두 점이 제대로 출력되는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = "*        *")
    public void QuadrangleWidthTest(String expected) {
        Output output = new Output(new ArrayList<>(Arrays.asList(1, 7, 6, 7, 1, 16, 6, 16)));
        line.makePoint(output);
        String result = ResultView.organizePoint(line, MAX, MAX - 7).toString();

        assertThat(expected).isEqualTo(result);
    }
}
