public class Manager {
    private int status;

    {
        status = 0;
    }

    public synchronized void hen() {
        while (status == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = 0;
        System.out.println("Hen");
        notify();
    }

    public synchronized void egg() {
        while (status == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = 1;
        System.out.println("Egg");
        notify();
    }
}
