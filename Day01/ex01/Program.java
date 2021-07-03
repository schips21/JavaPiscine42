public class Program {
    private static void printUser(User user) {
        System.out.println("User id: " + user.getIdentifier());
        System.out.println("User name: " + user.getName());
        System.out.println("User balance: " + user.getBalance());
    }

    public static void main(String[] args) {
        User userOne = new User("First user", 100);
        User userTwo = new User("Second user", 200);
        User userThree = new User("Third user", 300);
        printUser(userOne);
        System.out.println();
        printUser(userTwo);
        System.out.println();
        printUser(userThree);
    }
}
