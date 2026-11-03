import java.util.HashMap;
import java.util.Map;

public class User {
    private final int id;
    private final BankAccount bankAccount;
    private final String name;
    Map<WalletType,Wallet> wallets;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.wallets= new HashMap<>();
        this.bankAccount= new BankAccount(this);
    }

    public int getId() {
        return id;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public String getName() {
        return name;
    }
    public Map<WalletType, Wallet> getWallets() {
        return wallets;
    }

}
