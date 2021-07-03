public class HenThread extends Thread{
    private int n;

    HenThread(int num) {
        n = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++)
            System.out.println("Hen");
    }
}
