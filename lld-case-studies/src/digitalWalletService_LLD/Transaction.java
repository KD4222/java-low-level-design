import java.math.BigDecimal;
import java.util.Currency;

/**
 * Transaction
 */
public class Transaction {
    private final String id;
    private final Account debitAccount;
    private final Account creditAccount;
    private final BigDecimal amount;
    private final Currency currency;
    private TransactionStatus status;

    
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }


    public Transaction(String id, Account debitAccount, Account creditAccount, BigDecimal amount, Currency currency) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.currency = currency;
    }


    public String getId() {
        return id;
    }


    public Account getDebitAccount() {
        return debitAccount;
    }


    public Account getCreditAccount() {
        return creditAccount;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public Currency getCurrency() {
        return currency;
    }


    public TransactionStatus getStatus() {
        return status;
    }

}
