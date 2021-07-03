public class IllegalTransactionException extends RuntimeException{
    public IllegalTransactionException(){
        super("Error: attempt to make a transfer of the amount exceeding sender’s residual balance");
    }
}
