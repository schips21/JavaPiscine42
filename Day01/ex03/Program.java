import java.util.UUID;

public class Program {

    private static void printUser(User user) {
        System.out.println("User id: " + user.getIdentifier());
        System.out.println("User name: " + user.getName());
        System.out.println("User balance: " + user.getBalance());
    }

    private static void printTransaction(Transaction transaction) {
        System.out.println("Transaction id: " + transaction.getIdentifier());
        System.out.println("Transaction category: " + transaction.getTransferCategory());
        System.out.println("Transaction amount: " + transaction.getTransferAmount());
        System.out.println("Transaction recipient:");
        printUser(transaction.getRecipient());
        System.out.println("Transaction sender:");
        printUser(transaction.getSender());
    }

    private static void printTransactionsLinkedList(TransactionsLinkedList tranList) {
        Transaction head = tranList.getHead();
        Transaction tail = tranList.getTail();
        while (head != null) {
            printTransaction(head);
            head = head.nextTransaction;
        }
    }


    public static void main(String[] args) {
        User userOne = new User("First user", 100);
        User userTwo = new User("Second user", 200);
        Transaction trans = new Transaction(UUID.randomUUID(), userOne, userTwo, "debit", 200);
        Transaction transTwo = new Transaction(UUID.randomUUID(), userOne, userTwo, "debit", 200);
        TransactionsLinkedList tranList = new TransactionsLinkedList();
        Transaction transArr[];
        tranList.addTransaction(trans);
        tranList.addTransaction(transTwo);
        userOne.setTransList(tranList);
        printTransactionsLinkedList(tranList);
        transArr = tranList.toArray();
        System.out.println("\nTransactionsLinkedList as array");
        for (int i = 0; i < transArr.length; i++) {
            printTransaction(transArr[i]);
        }
        tranList.removeTransaction(transTwo.getIdentifier());
        System.out.println("\nTransactionsLinkedList after removing one transaction");
        printTransactionsLinkedList(tranList);
        tranList.removeTransaction(trans.getIdentifier());
        System.out.println("\nTransactionsLinkedList after removing one more transaction");
        printTransactionsLinkedList(tranList);
        System.out.println("\nTrying to remove transaction from empty TransactionsLinkedList");
        try {
            tranList.removeTransaction(trans.getIdentifier());
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        }
    }
}
