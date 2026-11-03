import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Wallet
 */
abstract class Wallet {
    protected String walletId; 
    protected BigDecimal walletBalance;
    protected int userId;
    protected WalletType type; 
    protected BigDecimal transactionLimit;
    protected ReentrantLock lock = new ReentrantLock(); // for concurrency

    public Wallet(int userId,BigDecimal limit){
        this.userId= userId;
        this.transactionLimit= limit;
        this.walletId= UUID.randomUUID().toString();
        this.walletBalance= BigDecimal.ZERO;
    }

    public abstract WalletType getType();


    public BigDecimal getBalance(){
        return walletBalance;
    };

    public int getUserId(){
        return this.userId;
    }

    public void credit(BigDecimal amount){
        this.walletBalance =this.walletBalance.add(amount);
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        if(this.walletBalance.compareTo(amount)<0){
            throw new InsufficientBalanceException("Not enough Balance available.");
        }
        this.walletBalance =this.walletBalance.subtract(amount);
    }

    public ReentrantLock getLock(){
        return this.lock;
    }
}
