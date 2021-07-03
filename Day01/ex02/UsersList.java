public interface UsersList {
    void addUser(User user);
    User retrieveUserByID(int userID) throws UserNotFoundException;
    User retrieveUserByIndex(int userIndex)  throws UserNotFoundException;
    int retrieveNumberOfUsers();
}
