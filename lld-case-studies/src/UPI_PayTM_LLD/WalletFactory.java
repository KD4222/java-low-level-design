public class WalletFactory {
    public Wallet createWallet(WalletType type,int userId){
        switch(type){
            case PAYTM_WALLET: return new PaytmWallet(userId);
            case FASTAG_WALLET: return new FastagWallet(userId);
            default: throw new IllegalArgumentException("Invalid Wallet type.");
        }
    }
}
