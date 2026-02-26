package study;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static int[] inputCoordinate() {
        int[] pair = new int[8];
        System.out.println("좌표를 입력하세요.");
        String input = scanner.nextLine();
        while (!parseCoordinate(input, pair)) {
            pair = new int[8];
            System.out.println("좌표를 다시 입력하세요.");
            input = scanner.nextLine();
        }
        return pair;
    }

    public static boolean parseCoordinate(String input, int[] output) { // 코디네이트 객체 생성 예정
        String regexQuad = "^\\(\\d+,\\d+\\)-\\(\\d+,\\d+\\)-\\(\\d+,\\d+\\)-\\(\\d+,\\d+\\)$";
        String regexLine = "^\\(\\d+,\\d+\\)-\\(\\d+,\\d+\\)$";
        if (!Pattern.matches(regexQuad, input) && !Pattern.matches(regexLine, input)) return false;
        String[] pairString = input.split("[-,()]+");
        return validCoordinate(pairString, output);
    }

    public static boolean validCoordinate(String[] input, int[] output) {
        if (validCoordinateLine(input, output)) return true;
        if (validCoordinateQuad(input, output)) return true;
        return false;
    }

    public static boolean validCoordinateLine(String[] input, int[] output) {
        if (input.length == 5) {
            for (int i = 0; i < 4; i++) {
                output[i] = Integer.parseInt(input[i + 1]);
                if (output[i] <= 0 || output[i] > 24) return false;
            }
            return true;
        }
        return false;
    }

    public static boolean validCoordinateQuad(String[] input, int[] output) {
        if (input.length == 9) {
            for (int i = 0; i < 8; i++) {
                output[i] = Integer.parseInt(input[i + 1]);
                if (output[i] <= 0 || output[i] > 24) return false;
            }
            return (checkCoordinateQuad(output) && equalQuad(output));
        }
        return false;
    }

    public static boolean checkCoordinateQuad(int[] output) {
        double averageX = (double) (output[0] + output[2] + output[4] + output[6]) / 4; // x좌표 평균값
        double averageY = (double) (output[1] + output[3] + output[5] + output[7]) / 4; // y좌표 평균값
        double expectedX = Math.abs(output[0] - averageX);
        double expectedY = Math.abs(output[1] - averageY);
        for (int i = 2; i < 8; i += 2) {
            if (expectedX != Math.abs(output[i] - averageX)) return false; // 중심 x좌표, y좌표와 평균값이 같은지 비교
            if (expectedY != Math.abs(output[i + 1] - averageY)) return false;
        }
        return true;
    }

    public static boolean equalQuad(int[] output) {
        for (int i = 0; i < 8; i += 2) { // 바로 뒤에 있는 점과 좌표가 일치하는지 비교
            for (int j = i + 2; j < 8; j += 2) {
                if (output[i] == output[j] && output[i + 1] == output[j + 1]) return false;
            }
        }
        return true;
    }
}
