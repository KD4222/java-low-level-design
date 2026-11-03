package parkingLot_LLD.payment;

public class CashPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        // Implement cash payment logic here
        System.out.println("Processing cash payment of amount: " + amount);
        return true; 
    }
    
}
