import java.math.BigDecimal;
import java.util.Currency;

public class BankAccount extends PaymentMethod {
    
    private final String accountNumber;
    private final String routingNumber;
    
    public BankAccount(String id, User user,String accNum, String routingNum) {
        super(id, user);   
        this.accountNumber = accNum;
        this.routingNumber= routingNum;
    }

    @Override
    public boolean processPayment(BigDecimal amount, Currency currency) {
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }
}