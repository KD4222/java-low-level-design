import java.math.BigDecimal;

/**
 * FastagWallet
 */
public class FastagWallet extends Wallet {
    public FastagWallet(int userId){
        super(userId, new BigDecimal("20000"));
    }

    @Override
    public WalletType getType() {
        return WalletType.FASTAG_WALLET;
    }
}
