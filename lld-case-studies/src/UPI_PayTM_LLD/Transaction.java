import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final User source;
    private final User dest;
    private final BigDecimal amount;
    private TransactionStatus status;


    public Transaction(User sender,User receiver, BigDecimal amount){
        if (sender == null || receiver == null || amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Sender, receiver, and a positive amount are required.");
        }
        this.id = UUID.randomUUID().toString();
        this.source=sender;
        this.dest=receiver;
        this.amount=amount;
        this.status = TransactionStatus.CREATED;
    }

    public void setStatus(TransactionStatus status){
        this.status= status;
    }

    public String getTransactionId() {
        return id;
    }

    public User getSource() {
        return source;
    }

    public User getDest() {
        return dest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}
