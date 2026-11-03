public class HasCoinState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin already inserted, please select product.");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected, ready to dispense!");
        machine.setState(new DispenseState());
    }

    @Override
    public void dispense(VendingMachine machine) {  
        System.out.println("Can't dispense, product not selected yet!");
    }
    
}
