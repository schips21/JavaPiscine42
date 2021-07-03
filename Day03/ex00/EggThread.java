public class EggThread extends Thread{
    private int n;

    EggThread(int num) {
        n = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++)
            System.out.println("Egg");
    }
}
