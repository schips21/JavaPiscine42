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

    public static void main(String[] args) {
        User recipient = new User(1, "Recipient user", 500);
        User sender = new User(2, "Sender user", 1000);
        UUID uuid = new UUID(1, 5);
        Transaction transaction = new Transaction(UUID.randomUUID(), recipient, sender, "debit", 200);
        recipient.setBalance(600);
        sender.setBalance(900);
        transaction.setTransferAmount(150);
        printTransaction(transaction);
    }
}
