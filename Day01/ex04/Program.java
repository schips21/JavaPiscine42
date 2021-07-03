import java.util.UUID;

public class Program {

    private static void printUser(User user) {
        System.out.println("User id: " + user.getIdentifier());
        System.out.println("User name: " + user.getName());
        System.out.println("User balance: " + user.getBalance());
        System.out.println("User transantions: ");
        Transaction[] trans = user.getTransList().toArray();
        if (trans == null)
            return;
        for (int i = 0; i < trans.length; i++){
            System.out.println("Transaction " + i);
            printTransaction(trans[i]);
        }
    }

    private static void printTransaction(Transaction transaction) {
        System.out.println("Transaction id: " + transaction.getIdentifier());
        System.out.println("Transaction category: " + transaction.getTransferCategory());
        System.out.println("Transaction amount: " + transaction.getTransferAmount());
        System.out.println("Transaction recipient ID:" + transaction.getRecipient().getIdentifier());
        System.out.println("Transaction sender ID:" + transaction.getSender().getIdentifier());
    }

    private static void printTransactionArr(Transaction[] transaction){
        for (int i = 0; i < transaction.length; i++){
            printTransaction(transaction[i]);
        }
    }

    private static void printTransactionsLinkedList(TransactionsLinkedList tranList) {
        Transaction head = tranList.getHead();
        Transaction tail = tranList.getTail();
        while (head != null) {
            printTransaction(head);
            head = head.nextTransaction;
        }
    }

    private static void printTransactionService(TransactionsService transactionsService){
        UsersList userList = transactionsService.getUsersList();
        int num = userList.retrieveNumberOfUsers();
        for (int i = 0; i < num; i++){
            printUser(userList.retrieveUserByIndex(i));
            System.out.println();
        }
    }


    public static void main(String[] args) {
        User userOne = new User("First user", 100);
        User userTwo = new User("Second user", 500);
        TransactionsLinkedList tranList = new TransactionsLinkedList();
        TransactionsLinkedList tranListTwo = new TransactionsLinkedList();
        userOne.setTransList(tranList);
        userTwo.setTransList(tranListTwo);

        UsersArrayList userArr = new UsersArrayList();
        TransactionsService transactionsService = new TransactionsService(userArr);
        transactionsService.addUser(userOne);
        transactionsService.addUser(userTwo);
        printTransactionService(transactionsService);

        System.out.print("retrieveUserBalanceById of userOne: ");
        System.out.println(transactionsService.retrieveUserBalanceById(userOne.getIdentifier()));
        System.out.print("retrieveUserBalanceByIndex of 1 index (UserTwo): ");
        System.out.println(transactionsService.retrieveUserBalanceByIndex(1));

        transactionsService.performTransferTransaction(userOne.getIdentifier(), userTwo.getIdentifier(), 150);
        System.out.println("\nAfter performing transaction");
        printTransactionService(transactionsService);

        Transaction[] transArrUserTwo = transactionsService.retrieveUserTransfersById(userTwo.getIdentifier());
        Transaction[] transArrUserOne = transactionsService.retrieveUserTransfersByIndex(0);

        System.out.println("\nretrieveUserTransfersById (User Two)\n");
        printTransactionArr(transArrUserTwo);

        System.out.println("\nretrieveUserTransfersByIndex (User One)\n");
        printTransactionArr(transArrUserOne);


        transactionsService.removeTransactionByIdByUserID(userOne.getIdentifier(), userOne.getTransList().toArray()[0].getIdentifier());

        Transaction[] tr = transactionsService.checkValidity();

        System.out.println("\nArray of unpaired transactions");
        printTransactionArr(tr);
    }
}
