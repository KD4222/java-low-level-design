import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BankAccount
 */
public class BankAccount {
    private final String accountId;
    private BigDecimal balance;
    private final User accountHolder;
    private ReentrantLock lock = new ReentrantLock();

    public BankAccount(User user){
        this.accountHolder= user;
        this.balance=new BigDecimal("100000");//account opening balance
        this.accountId= UUID.randomUUID().toString();
    }

    public void credit(BigDecimal amount){
        requirePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        requirePositiveAmount(amount);
        if(this.balance.compareTo(amount)<0){
            throw new InsufficientBalanceException("Not enough Balance available.");
        }
        this.balance =this.balance.subtract(amount);
    }

    private static void requirePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    public ReentrantLock getLock(){
        return this.lock;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User getAccountHolder() {
        return accountHolder;
    }
}
