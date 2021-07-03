import java.util.Scanner;

public class Program {

    static private int letterInArr(char []arr, char letter, int size) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == letter) {
                return i;
            }
        }
        return -1;
    }

    private static void drawBars(char []lettersArr, int []countArr, int countLetters){
        double stepForDraw = countArr[0] / 10.0;
        int []barsHeight = new int[10];
        if (countLetters > 10) {
            countLetters = 10;
        }
        for (int i = 0; i < countLetters; i++) {
            barsHeight[i] = (int)(countArr[i] / stepForDraw) + 1;
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < countLetters; j++) {
                if (barsHeight[j] + i == 11) {
                    System.out.printf("%d\t", countArr[j]);
                }
                else if (barsHeight[j] + i > 11) {
                    System.out.print("#\t");
                }
                else {
                    System.out.print("\n");
                    break;
                }
                if (j + 1 == countLetters) {
                    System.out.print("\n");
                }
            }
        }
        for (int i = 0; i < countLetters; i++) {
            System.out.printf("%c", lettersArr[i]);
            if (i + 1 != countLetters)
                System.out.print("\t");
        }
    }

    private static void sortDraw(char []lettersArr, int []countArr, int countLetters) {
        boolean isSorted = false;
        boolean sortInCycle;
        int i;
        int tmp_int;
        char tmp_char;
        while (!isSorted) {
            i = 0;
            sortInCycle = false;
            while (i < countLetters) {
                if (i + 1 != countLetters && (countArr[i] < countArr[i + 1] ||
                    (countArr[i] == countArr[i + 1] && lettersArr[i] > lettersArr[i + 1]))) {
                    tmp_int = countArr[i];
                    countArr[i] = countArr[i + 1];
                    countArr[i + 1] = tmp_int;
                    tmp_char = lettersArr[i];
                    lettersArr[i] = lettersArr[i + 1];
                    lettersArr[i + 1] = tmp_char;
                    sortInCycle = true;
                }
                else
                    i++;
            }
            if (!sortInCycle) {
                isSorted = true;
            }
        }
        drawBars(lettersArr, countArr, countLetters);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.length() == 0)
            System.exit(0);
        char []strArr = str.toCharArray();
        char []lettersArr = new char[str.length()];
        int []countArr = new int[str.length()];
        int j = 0;
        int countLetters = 0;
        for (int i = 0; i < strArr.length; i++) {
            if ((j = letterInArr(lettersArr, strArr[i], countLetters)) != -1) {
                countArr[j] += 1;
            }
            else {
                lettersArr[countLetters] = strArr[i];
                countArr[countLetters] = 1;
                countLetters++;
            }
        }
        sortDraw(lettersArr, countArr, countLetters);
    }
}
