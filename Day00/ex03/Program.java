import java.util.Scanner;

public class Program {

    private static void exitWithError() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static void drawBars(long grades) {
        long rank = 1;
        long curBars = 0;
        int weekNum = 1;
        while (grades / (rank * 10) > 0) {
            rank *= 10;
        }
        while (grades > 0) {
            curBars = grades / rank;
            grades = grades % rank;
            rank /= 10;
            System.out.printf("Week %d ", weekNum);
            weekNum++;
            for (int i = 0; i < curBars; i++) {
                System.out.printf("%c", '=');
            }
            System.out.println(">");
        }
    }

    public static void main(String[] args) {
        int numWeeks = 0;
        int minGradePerWeek = 0;
        int curGrade = 0;
        long allMinGrades = 0;
        String curWeek;
        Scanner sc = new Scanner(System.in);
        while (numWeeks < 18) {
            minGradePerWeek = 10;
            numWeeks++;
            curWeek = sc.nextLine();
            if (curWeek.equals("42")) {
                break;
            }
            if (!curWeek.equals("Week " + numWeeks)) {
                exitWithError();
            }
            for (int i = 0; i < 5; i++) {
                curGrade = sc.nextInt();
                if (curGrade < 1 || curGrade > 9) {
                    exitWithError();
                }
                if (curGrade < minGradePerWeek) {
                    minGradePerWeek = curGrade;
                }
            }
            sc.nextLine();
            if (allMinGrades == 0) {
                allMinGrades = minGradePerWeek;
            }
            else {
                allMinGrades = allMinGrades * 10 + minGradePerWeek;
            }
        }
        drawBars(allMinGrades);
    }
}
