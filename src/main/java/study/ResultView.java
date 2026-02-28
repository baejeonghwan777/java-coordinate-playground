package study;

import java.util.List;

public class ResultView {
    public static int INIT_NUMBER = 0;

    public static void printTotal(int max, Coordinates coordinates) {
        String output = printPlain(max, coordinates);
        System.out.println(output);
    }

    public static String printPlain(int max, Coordinates coordinates) {
        StringBuilder output = new StringBuilder();

        output.append(printPlainTop(max, coordinates));
        output.append(printPlainBottom());
        output.append(coordinates.printResult());

        return output.toString();
    }

    public static StringBuilder printPlainTop(int max, Coordinates coordinates) {
        StringBuilder output = new StringBuilder();

        for (int i = INIT_NUMBER; i < max; i++) {
            if (i % 2 == 0 && max - i >= 10) output.append(max - i).append("|"); // 일의 자리는 숫자 한개분만큼 건너 뛰어야 하기 때문에 구분한다.
            if (i % 2 == 0 && max - i < 10) output.append(" ").append(max - i).append("|");
            if (i % 2 != 0) output.append("  |");
            output.append(organizePoint(coordinates, max, i));
            output.append("\n");
        }

        return output;
    }

    public static StringBuilder organizePoint(Coordinates coordinates, int max, int index) {
        List<Integer> xPoints = coordinates.getPointX(max, index); // 정렬된 숫자 리스트

        StringBuilder output = new StringBuilder();
        int lastX = INIT_NUMBER; // 첫 좌표를 찍을 때는 이전값이 없기 때문에 0으로 설정한다.

        for (int currentX : xPoints) {
            output.append(printPoint(currentX, lastX));
            lastX = currentX;
        }
        return output;
    }

    public static StringBuilder printPoint(int moreValue, int lessValue) {
        StringBuilder output = new StringBuilder();

        for (int i = lessValue + 1; i < moreValue; i++) {
            if (i <= 2) output.append(" "); // 0에서 2까지는 뒤 좌표들과 비교하여 점의 거리가 다르므로 한칸만 띄운다.
            if (i > 2) output.append("  ");
        }

        if (moreValue <= 2) output.append("*");
        if (moreValue > 2) output.append(" *");

        return output;
    }

    public static StringBuilder printPlainBottom() {
        StringBuilder output = new StringBuilder();
        output.append("  +-ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
        output.append("0   2   4   6   8  10  12  14  16  18  20  22  24\n\n");
        return output;
    }
}
