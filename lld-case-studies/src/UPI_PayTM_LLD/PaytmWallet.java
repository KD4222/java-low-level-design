import java.math.BigDecimal;

/**
 * PaytmWallet
 */
public class PaytmWallet extends Wallet {

    public PaytmWallet(int userId){
        super(userId, new BigDecimal("100000"));
    }

    @Override
    public WalletType getType() {
        return WalletType.PAYTM_WALLET;
    }
}
