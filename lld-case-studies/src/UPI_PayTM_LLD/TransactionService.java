import java.math.BigDecimal;

public class TransactionService {
    //transaction repo

    //create Transaction

    public Transaction createTransaction(User sender, User receiver, BigDecimal amount){
        Transaction newTransaction = new Transaction(sender, receiver, amount);
        if(executeTransaction(newTransaction)){
            newTransaction.setStatus(TransactionStatus.COMPLETED);
        }else{
            newTransaction.setStatus(TransactionStatus.FAILED);
        }
        return newTransaction;
    }

    private boolean executeTransaction(Transaction transaction){
        User sendUser= transaction.getSource();
        User destUser= transaction.getDest();
        BigDecimal amount= transaction.getAmount();

        BankAccount firstLock= sendUser.getBankAccount().getAccountId().compareTo(destUser.getBankAccount().getAccountId())<0?sendUser.getBankAccount() : destUser.getBankAccount();
        BankAccount secondLock = firstLock.equals(sendUser.getBankAccount())?destUser.getBankAccount(): sendUser.getBankAccount();
        boolean transactionSuccess= true;
        
        try{
            firstLock.getLock().lock();
            secondLock.getLock().lock();
            
            sendUser.getBankAccount().debit(amount);
            destUser.getBankAccount().credit(amount);

        }catch(InsufficientBalanceException e){
            transaction.setStatus(TransactionStatus.FAILED);
            System.out.println("Transfer Failed: " + e.getMessage());
            transactionSuccess=false;
        }
        finally{
            firstLock.getLock().unlock();
            secondLock.getLock().unlock();
        }
    
        return transactionSuccess;
    }

}
