public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();


        machine.dispense();
        machine.insertCoin();
        machine.dispense();
        machine.selectProduct();
        machine.dispense();

    }
}
