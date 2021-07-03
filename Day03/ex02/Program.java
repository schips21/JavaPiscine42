import java.util.Arrays;
import java.util.Random;

public class Program {

    private static int getArrSize(String []args) {
        String []arr = args[0].split("=");
        if (arr.length != 2 || !arr[0].equals("--arraySize")) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        try {
            int num = Integer.parseInt(arr[1]);
            return num;
        } catch (NumberFormatException e) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        return 1;
    }

    private static int getthreadsCount(String []args) {
        String []arr = args[1].split("=");
        if (arr.length != 2 || !arr[0].equals("--threadsCount")) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        try {
            int num = Integer.parseInt(arr[1]);
            return num;
        } catch (NumberFormatException e) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        return 1;
    }

    public static void main(String []args) {
        if (args.length != 2) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        int arrSize = getArrSize(args);
        int threadsCount = getthreadsCount(args);
        if (threadsCount > arrSize) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        int []arr = new int[arrSize];
        Random rand = new Random();
        int step = arrSize / threadsCount;
        int res = 0;
        for (int i = 0; i < arrSize; i++) {
            arr[i] = rand.nextInt(2001) - 1000;
        }
        SumThread []sthread = new SumThread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            if (i + 1 == threadsCount) {
                sthread[i] = new SumThread(i, step, arrSize - 1, arr);
                sthread[i].start();
            }
            else {
                sthread[i] = new SumThread(i, step, (i + 1) * step - 1, arr);
                sthread[i].start();
            }

        }
        for (int i = 0; i < threadsCount; i++) {
            try {
                sthread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threadsCount; i++) {
            res += sthread[i].getRes();
        }
        System.out.println("Sum by threads: " + res);
    }
}
