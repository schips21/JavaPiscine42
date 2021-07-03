public class UsersArrayList implements UsersList{
    private User[] userArr;
    private int userNumber;
    private int capacity;

    UsersArrayList() {
        userArr = new User[10];
        userNumber = 0;
        capacity = 10;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addUser(User user){
        if (userNumber == capacity) {
            capacity += capacity / 2;
            User[] tmpUserArr = new User[capacity];
            for (int i = 0; i < userNumber; i++) {
                tmpUserArr[i] = userArr[i];
            }
            userArr = tmpUserArr;
        }
        userArr[userNumber] = user;
        userNumber++;
    }

    public User retrieveUserByID(int userID) throws UserNotFoundException{
        for (int i = 0; i < userNumber; i++) {
            if (userArr[i].getIdentifier() == userID) {
                return userArr[i];
            }
        }
        throw new UserNotFoundException();
    }

    public User retrieveUserByIndex(int userIndex) throws UserNotFoundException{
        if (userIndex < 0 || userIndex >= userNumber){
            throw new UserNotFoundException();
        }
        return userArr[userIndex];
    }

    public int retrieveNumberOfUsers() {
        return userNumber;
    }
}
