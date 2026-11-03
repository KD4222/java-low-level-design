/**
 * NoCoinState
 */
public class NoCoinState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted!");
        machine.setState(new HasCoinState());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Coin not inserted");
    }

    @Override
    public void dispense(VendingMachine machine) {  
        System.out.println("Coin not inserted, and hence no product can be dispensed.");
    }
}
