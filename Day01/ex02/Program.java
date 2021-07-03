public class Program {
    private static void printUser(User user) {
        System.out.println("User id: " + user.getIdentifier());
        System.out.println("User name: " + user.getName());
        System.out.println("User balance: " + user.getBalance());
    }

    private static void printUsersArrayList(UsersArrayList userArr) {
        System.out.println("User array list size: " + userArr.getUserNumber());
        System.out.println("User array capacity: " + userArr.getCapacity());
        for (int i = 0; i < userArr.getUserNumber(); i++) {
            System.out.println("User with index " + i + " in user array list:");
            printUser(userArr.retrieveUserByIndex(i));
        }
    }

    public static void main(String[] args) {
        User userOne = new User("First user", 100);
        User userTwo = new User("Second user", 200);
        User userThree = new User("Third user", 300);
        User userFour = new User("Fourth user", 100);
        User userFive = new User("Fifth user", 200);
        User userSix = new User("Sixth user", 300);
        User userSeven = new User("Seventh user", 100);
        User userEight = new User("Eighth user", 200);
        User userNine = new User("Ninth user", 300);
        User userTen = new User("Tenth user", 200);
        User userEleven = new User("Eleventh user", 300);
        UsersArrayList userArr = new UsersArrayList();
        userArr.addUser(userOne);
        userArr.addUser(userTwo);
        userArr.addUser(userThree);
        printUsersArrayList(userArr);
        try{
            System.out.println("\nTrying to get user with existing ID:");
            printUser(userArr.retrieveUserByID(userTwo.getIdentifier()));
            System.out.println("\nTrying to get user with non-existing ID:");
            printUser(userArr.retrieveUserByID(15));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        userArr.addUser(userFour);
        userArr.addUser(userFive);
        userArr.addUser(userSix);
        userArr.addUser(userSeven);
        userArr.addUser(userEight);
        userArr.addUser(userNine);
        userArr.addUser(userTen);
        userArr.addUser(userEleven);
        System.out.println("\nAfter adding eleven users: ");
        System.out.println("User array list size: " + userArr.getUserNumber());
        System.out.println("User array capacity: " + userArr.getCapacity());

        try{
            System.out.println("\nTrying to get user with non-existing index:");
            printUser(userArr.retrieveUserByIndex(-5));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
