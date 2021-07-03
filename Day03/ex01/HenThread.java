public class HenThread extends Thread{
    private int n;
    private Manager manager;

    HenThread(int num, Manager m) {
        n = num;
        manager = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++)
            manager.hen();
    }
}
