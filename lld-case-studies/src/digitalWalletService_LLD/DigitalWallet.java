import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DigitalWallet {
    private static DigitalWallet instance;
    private final Map<String,User> users;
    private final Map<String,Account> accounts;
    private final Map<String,PaymentMethod> paymentMethods;

    private DigitalWallet(){
        users= new ConcurrentHashMap<>();
        accounts = new ConcurrentHashMap<>();
        paymentMethods = new ConcurrentHashMap<>();
    }

    public static synchronized DigitalWallet getInstance(){
        if(instance==null){
             instance = new DigitalWallet();
        }
        return instance;
    }

    //createUser

    public void createUser(User user){
        users.put(user.getId(), user);
    }
    
    //getUser
    public User getUser(String userId){
        return users.get(userId);
    }

    //createAccount
    public void addAccount(Account account){
        accounts.put(account.getId(),account);
    }

    //getAccount
    public Account getAccount(String accountId){
        return accounts.get(accountId);
    }

    //addPaymentMethod
    public void addPaymentMethod(PaymentMethod paymentMethod){
        paymentMethods.put(paymentMethod.getId(), paymentMethod);
    }

    //getPaymentMethod
    public PaymentMethod getPaymentMethod(String paymentMethodId){
        return paymentMethods.get(paymentMethodId);
    }

    //transferFunds

    public synchronized void transferFunds(Account sourceAccount,Account destAccount,BigDecimal amount,Currency currency)
            throws InsufficientFundsException {
        if (sourceAccount == null || destAccount == null || currency == null
                || amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Accounts, currency, and a positive amount are required.");
        }

        BigDecimal sourceAmount = amount;
        if (!sourceAccount.getCurrency().equals(currency)) {
            sourceAmount = CurrencyConverter.convert(amount, currency, sourceAccount.getCurrency());
        }

        BigDecimal destinationAmount = sourceAmount;
        if (!destAccount.getCurrency().equals(sourceAccount.getCurrency())) {
            destinationAmount = CurrencyConverter.convert(
                    sourceAmount, sourceAccount.getCurrency(), destAccount.getCurrency());
        }

        sourceAccount.withdraw(sourceAmount);
        destAccount.deposit(destinationAmount);

        String transactionId= generateTransactionId();
        Transaction transaction = new Transaction(
                transactionId, sourceAccount, destAccount, destinationAmount, destAccount.getCurrency());
        transaction.setStatus(TransactionStatus.COMPLETED);
        sourceAccount.addTransaction(transaction);
        destAccount.addTransaction(transaction);
    }

    //getTransactionHistory
    public List<Transaction> getTransactionHistory(Account account){
        return account.getTransactions();
    }

    private String generateTransactionId(){
        return "TXN" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

}
