package study;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String POINT = "\\(\\d+,\\d+\\)";
    private static final Pattern FORM_PATTERN = Pattern.compile(
            String.format("^(%s)-(%s)(-(%s)){0,2}$", POINT, POINT, POINT)
    );
    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+");
    static Scanner scanner = new Scanner(System.in);

    public static Output inputPoint() {
        Output output = new Output();
        System.out.println("좌표를 입력하세요.");
        String input = scanner.nextLine();
        while (!parsePoint(input, output)) {
            output = new Output();
            System.out.println("좌표를 다시 입력하세요.");
            input = scanner.nextLine();
        }
        return output;
    }

    public static boolean parsePoint(String input, Output output) { // 코디네이트 객체 생성 예정
        if (input == null || !FORM_PATTERN.matcher(input).matches()) return false;
        Matcher m = PATTERN_NUMBER.matcher(input);
        while (m.find()) output.addValue(Integer.parseInt(m.group()));
        return output.validPoint();
    }


}
