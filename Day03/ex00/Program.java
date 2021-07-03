public class Program {
    private static int checkArgs(String []args) {
        if (args.length != 1 || args[0].length() < 9 || !args[0].substring(0, 8).equals("--count=")) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        String numStr = args[0].substring(8);
        try {
            int num = Integer.parseInt(numStr);
            return num;
        } catch (NumberFormatException e) {
            System.err.println("Invalid argument");
            System.exit(-1);
        }
        return 1;
    }

    public static void main(String []args) {
        int num = checkArgs(args);
        EggThread eggThread = new EggThread(num);
        HenThread henThread = new HenThread(num);
        eggThread.start();
        henThread.start();
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < num; i++) {
            System.out.println("Human");
        }
    }
}
