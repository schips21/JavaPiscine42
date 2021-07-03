import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService(UsersList newUserList){
        usersList = newUserList;
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public void addUser(User user){
        usersList.addUser(user);
    }

    public int retrieveUserBalanceById(int identifier){
        return usersList.retrieveUserByID(identifier).getBalance();
    }

    public int retrieveUserBalanceByIndex(int index){
        return usersList.retrieveUserByIndex(index).getBalance();
    }

    public void performTransferTransaction(int recipientID, int senderID, int transferAmount) throws IllegalTransactionException{
        try{
            User sender = usersList.retrieveUserByID(senderID);
            User recipient = usersList.retrieveUserByID(recipientID);
            if (sender.getBalance() - transferAmount < 0)
                throw new IllegalTransactionException();
            Transaction transSender = new Transaction(UUID.randomUUID(), recipient, sender, "credit", transferAmount * (-1));
            Transaction transRecipient = new Transaction(transSender.getIdentifier(), recipient, sender, "debit", transferAmount);
            sender.getTransList().addTransaction(transSender);
            recipient.getTransList().addTransaction(transRecipient);
            sender.setBalance(sender.getBalance() + transSender.getTransferAmount());
            recipient.setBalance(recipient.getBalance() + transRecipient.getTransferAmount());
        } catch (UserNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Transaction[] retrieveUserTransfersById(int identifier){
        return usersList.retrieveUserByID(identifier).getTransList().toArray();
    }

    public Transaction[] retrieveUserTransfersByIndex(int index){
        return usersList.retrieveUserByIndex(index).getTransList().toArray();
    }

    public void removeTransactionByIdByUserID(int userID, UUID transactionID){
        usersList.retrieveUserByID(userID).getTransList().removeTransaction(transactionID);
    }

    public Transaction[] checkValidity(){
        TransactionsLinkedList transList = new TransactionsLinkedList();
        for (int i = 0; i < usersList.retrieveNumberOfUsers(); i++){
            Transaction[] tmp = usersList.retrieveUserByIndex(i).getTransList().toArray();
            if (tmp == null)
                continue;
            for (int j = 0; j < tmp.length; j++){
                User usr;
                if (tmp[j].getRecipient() == usersList.retrieveUserByIndex(i)){
                    usr = tmp[j].getSender();
                } else {
                    usr = tmp[j].getRecipient();
                }
                Transaction[] tmp2 = usr.getTransList().toArray();
                if (tmp2 == null){
                    transList.addTransaction(tmp[j]);
                    continue;
                }
                int k;
                for (k = 0; k < tmp2.length; k++){
                    if (tmp2[k].getIdentifier().equals(tmp[j].getIdentifier())){
                        break;
                    }
                }
                if (k == tmp2.length){
                    transList.addTransaction(tmp[j]);
                }
            }

        }
        return transList.toArray();
    }
}
