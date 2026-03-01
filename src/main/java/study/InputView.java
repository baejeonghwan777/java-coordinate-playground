package study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String POINT = "\\(\\d+,\\d+\\)";
    private static final Pattern FORM_PATTERN = Pattern.compile(
            String.format("^(%s)-(%s)(-(%s)){0,2}$", POINT, POINT, POINT)
    );
    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+");
    static Scanner scanner = new Scanner(System.in);

    public static List<Integer> inputPoint() {
        List<Integer> pair = new ArrayList<>();
        System.out.println("좌표를 입력하세요.");
        String input = scanner.nextLine();
        while (!parsePoint(input, pair)) {
            pair = new ArrayList<>();
            System.out.println("좌표를 다시 입력하세요.");
            input = scanner.nextLine();
        }
        return pair;
    }

    public static boolean parsePoint(String input, List<Integer> output) { // 코디네이트 객체 생성 예정
        if (input == null || !FORM_PATTERN.matcher(input).matches()) return false;
        Matcher m = PATTERN_NUMBER.matcher(input);
        while (m.find()) output.add(Integer.parseInt(m.group()));
        return validPoint(output);
    }

    public static boolean validPoint(List<Integer> output) {
        if (output.size() == 4 || output.size() == 6 || output.size() == 8) {
            for (Integer i : output) {
                if (i <= 0 || i > 24) return false;
            }
            return checkPoint(output);
        }
        return false;
    }

    public static boolean checkPoint(List<Integer> output) {
        if (output.size() == 4) return true;

        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();

        for (int i = 0; i < output.size(); i += 2) {
            xSet.add(output.get(i));
            ySet.add(output.get(i + 1));
        }

        if (output.size() == 6) return !(xSet.size() == 1 || ySet.size() == 1);
        if (output.size() == 8) return xSet.size() == 2 && ySet.size() == 2;
        return false;
    }
}
