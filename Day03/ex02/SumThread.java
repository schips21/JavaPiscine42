public class SumThread extends Thread{
    int startI;
    int res;
    int step;
    int end;
    int []arr;

    SumThread (int i, int st, int en, int []a) {
        startI = i;
        res = 0;
        step = st;
        end = en;
        arr = a;
    }

    @Override
    public void run() {
        res = arr[startI * step];
        for (int i = startI * step + 1; i <= end; i++) {
            res += arr[i];
        }
        System.out.println("Thread " + startI + ": from " + startI * step + " to " + end + " sum is " + res);
    }
    public int getRes() {
        return res;
    }
}
