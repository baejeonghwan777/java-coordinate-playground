package study;

public class ResultView {
    public static String printPlain(int max, Coordinates coordinates) {
        StringBuilder output = new StringBuilder();
        output.append(printPlainTop(max, coordinates));
        output.append(printPlainBottom());
        output.append(printDistance(coordinates));
        return output.toString();
    }

    public static void printTotal(int max, Coordinates coordinates) {
        String output = printPlain(max, coordinates);
        System.out.println(output);
    }

    public static StringBuilder printPlainTop(int max, Coordinates coordinates) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < max; i++) {
            if(i % 2 == 0 && max - i >= 10) output.append(max - i).append("|"); // 일의 자리는 숫자 한개분만큼 건너 뛰어야 하기 때문에 구분한다.
            if(i % 2 == 0 && max - i < 10) output.append(" ").append(max - i).append("|");
            if(i % 2 != 0) output.append("  |");
            output.append(analyzePoint(coordinates, max, i));
            output.append("\n");
        }
        return output;
    }

    public static StringBuilder printPoint(Coordinates coordinates, String value) {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < coordinates.getValue(value); i++) {
            if(i <= 2) output.append(" "); // 0에서 2까지는 뒤 좌표들과 비교하여 점의 거리가 다르므로 한칸만 띄운다.
            if(i > 2) output.append("  ");
        }
        if(coordinates.getValue(value) <= 2) output.append("*");
        if(coordinates.getValue(value) > 2) output.append(" *");
        return output;
    }

    public static StringBuilder printEqualPoint(Coordinates coordinates, String afterValue, String beforeValue) {
        StringBuilder output = new StringBuilder();
        for (int i = coordinates.getValue(beforeValue) + 1; i < coordinates.getValue(afterValue); i++) {
            if(i <= 2) output.append(" "); // 0에서 2까지는 뒤 좌표들과 비교하여 점의 거리가 다르므로 한칸만 띄운다.
            if(i > 2) output.append("  ");
        }
        if(coordinates.getValue(afterValue) <= 2) output.append("*");
        if(coordinates.getValue(afterValue) > 2) output.append(" *");
        return output;
    }

    public static StringBuilder printEqualPointDecide(Coordinates coordinates, int max, int index) {
        StringBuilder output = new StringBuilder();
        if(coordinates.getValue("firstX") < coordinates.getValue("secondX")) {
            if(coordinates.getValue("firstY") == max - index) output.append(printPoint(coordinates, "firstX"));
            if(coordinates.getValue("secondY") == max - index) output.append(printEqualPoint(coordinates, "secondX", "firstX"));
        }
        if(coordinates.getValue("firstX") >= coordinates.getValue("secondX")) {
            if(coordinates.getValue("secondY") == max - index) output.append(printPoint(coordinates, "secondX"));
            if(coordinates.getValue("firstY") == max - index) output.append(printEqualPoint(coordinates, "firstX", "secondX"));
        }
        return output;
    }

    public static StringBuilder analyzePoint(Coordinates coordinates, int max, int index) {
        StringBuilder output = new StringBuilder();
        if(coordinates.equalCoordinate()) if(coordinates.getValue("firstY") == max - index) return output.append(printPoint(coordinates, "firstX"));
        if(coordinates.equalCoordinateY()) return output.append(printEqualPointDecide(coordinates, max, index));
        if(coordinates.getValue("firstY") == max - index) output.append(printPoint(coordinates, "firstX"));
        if(coordinates.getValue("secondY") == max - index) output.append(printPoint(coordinates, "secondX"));
        return output;
    }

    public static StringBuilder printPlainBottom() {
        StringBuilder output = new StringBuilder();
        output.append("  +-ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
        output.append("0   2   4   6   8  10  12  14  16  18  20  22  24\n\n");
        return output;
    }

    public static StringBuilder printDistance(Coordinates coordinates) {
        StringBuilder output = new StringBuilder();
        output.append("두 점 사이의 거리는 ").append(coordinates.makeDistance()).append("입니다.\n");
        return output;
    }


}
