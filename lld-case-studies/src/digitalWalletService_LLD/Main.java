import java.math.BigDecimal;
import java.util.Currency;

public class Main {

    public static void main(String[] args) {
        DigitalWallet wallet = DigitalWallet.getInstance();
        Currency usd = Currency.getInstance("USD");

        User user = new User("USER-001", "Alex Morgan", "alex@example.com");
        wallet.createUser(user);

        Account sourceAccount = new Account(
                "ACCOUNT-001", user, "000123456789", usd, BigDecimal.ZERO);
        Account destinationAccount = new Account(
                "ACCOUNT-002", user, "000987654321", usd, BigDecimal.ZERO);

        sourceAccount.deposit(new BigDecimal("1000.00"));
        destinationAccount.deposit(new BigDecimal("100.00"));
        wallet.addAccount(sourceAccount);
        wallet.addAccount(destinationAccount);

        PaymentMethod paymentMethod =
                new BankAccount("PAYMENT-001", user, "000123456789", "110000000");
        wallet.addPaymentMethod(paymentMethod);

        System.out.println("User: " + wallet.getUser(user.getId()).getName());
        System.out.println("Payment method added: "
                + (wallet.getPaymentMethod(paymentMethod.getId()) != null));
        System.out.println("Source balance before transfer: "
                + sourceAccount.getBalance() + " " + usd.getCurrencyCode());

        try {
            wallet.transferFunds(
                    sourceAccount, destinationAccount, new BigDecimal("250.00"), usd);
            System.out.println("Transfer completed.");
        } catch (InsufficientFundsException e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("Source balance: "
                + sourceAccount.getBalance() + " " + usd.getCurrencyCode());
        System.out.println("Destination balance: "
                + destinationAccount.getBalance() + " " + usd.getCurrencyCode());
        System.out.println("Source transaction count: "
                + wallet.getTransactionHistory(sourceAccount).size());
    }
}
