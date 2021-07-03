import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        int i = 2;
        int res = 0;
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()){
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        int num = sc.nextInt();
        if (num <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        while (i * i <= num) {
            res++;
            if (num % i == 0) {
                System.out.println("false " + res);
                System.exit(0);
            }
            i++;
        }
        res++;
        System.out.println("true " + res);
    }
}
