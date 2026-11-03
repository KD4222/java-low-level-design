import java.math.BigDecimal;
import java.util.Currency;

public class CreditCard extends PaymentMethod {
    private final String cardNumber;
    private final String expirationDate;
    private final String cvv;
    
    public CreditCard(String id, User user, String cardNumber, String expirationDate, String cvv) {
        super(id,user);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public boolean processPayment(BigDecimal amount, Currency currency) {
        return true;
    }

    
}
