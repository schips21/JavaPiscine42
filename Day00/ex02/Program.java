import java.util.Scanner;

public class Program {

    public static int countSum(int num){
        int res = 0;
        while (num > 0){
            res += num % 10;
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args){
        int i;
        int res = 0;
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        int num = 0;
        while (num != 42){
            i = 2;
            if (!sc.hasNextInt()){
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            num = sc.nextInt();
            sum = countSum(num);
            if (sum == 0 || sum == 1)
                continue;
            if (sum == 2){
                res++;
                continue;
            }
            while (i * i <= sum) {
                if (sum % i == 0) {
                    break;
                }
                i++;
            }
            if (sum % i != 0)
                res++;
        }
        System.out.println("Count of coffee-request – " + res);
    }
}
