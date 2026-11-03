public class DispenseState implements VendingMachineState {

   @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Can not insert coin, machine is dispensing!");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Already selected, wait for the machine to dispense!");
    }

    @Override
    public void dispense(VendingMachine machine) {  
        System.out.println("Dispensing product");
        machine.setState(new NoCoinState());
    }
    
}
