package study;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static int[] inputCoordinate() {
        int[] pair = new int[4];
        System.out.println("좌표를 입력하세요.");
        String input = scanner.nextLine();
        while(!parseCoordinate(input, pair)) {
            System.out.println("좌표를 다시 입력하세요.");
            input = scanner.nextLine();
        }
        return pair;
    }

    public static boolean parseCoordinate(String input, int[] output) { // 코디네이트 객체 생성 예정
        String regex = "^\\(\\d+,\\d+\\)-\\(\\d+,\\d+\\)$";
        if (!Pattern.matches(regex, input)) return false;
        String[] pairString = input.split("[-,()]+");
        return validCoordinate(pairString, output);
    }

    public static boolean validCoordinate(String[] input, int[] output) {
        if(input.length != 5) return false;
        for (int i = 0; i < 4; i++) {
            output[i] = Integer.parseInt(input[i+1]);
            if(output[i] <= 0 || output[i] > 24) return false;
        }
        return true;
    }
}
