import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        User user2 = new User(2,"Akshit");
        User user5 = new User(5,"Ishita");
        User user6 = new User(4,"Nityam");

        TransactionService app= new TransactionService();

        System.out.println(user5.getBankAccount().getBalance());
        System.out.println(user6.getBankAccount().getBalance());

        synchronized(app){
            app.createTransaction(user5, user6, new BigDecimal("50000"));
            app.createTransaction(user5, user2, new BigDecimal("50000"));
            app.createTransaction(user5, user6, new BigDecimal("500"));


            System.out.println(user5.getBankAccount().getBalance());
            System.out.println(user6.getBankAccount().getBalance());
            System.out.println(user6.getBankAccount().getBalance());
            System.out.println(user2.getBankAccount().getBalance());
        }
        
    }
}
