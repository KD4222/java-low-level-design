package service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import models.BalanceSheet;
import models.Expense;
import models.User;
import models.split.Split;

public class BalanceService {
    private final BalanceSheet balanceSheet = new BalanceSheet();

    public synchronized void addBalance(Expense expense) {

    User paidBy = expense.getPaidBy();

    for (Split split : expense.getSplits()) {

        User debtor = split.getUser();
        double amount = split.getAmount();

        // Ignore payer's own share
        if (paidBy.equals(debtor)) {
            continue;
        }

        balanceSheet.getBalances()
                .putIfAbsent(paidBy, new HashMap<>());

        balanceSheet.getBalances()
                .putIfAbsent(debtor, new HashMap<>());


        // debtor owes paidBy
        balanceSheet.getBalances()
                .get(debtor)
                .put(
                    paidBy,
                    balanceSheet.getBalances()
                            .get(debtor)
                            .getOrDefault(paidBy, 0.0)
                            - amount
                );


        // paidBy should receive from debtor
        balanceSheet.getBalances()
                .get(paidBy)
                .put(
                    debtor,
                    balanceSheet.getBalances()
                            .get(paidBy)
                            .getOrDefault(debtor, 0.0)
                            + amount
                );
    }

    simplifyDebts();
}

    private void simplifyDebts() {
        Map<User, Double> consolidatedBalanceMap = new HashMap<>();
        PriorityQueue<User> creditors = new PriorityQueue<>(
                (a, b) -> Double.compare(consolidatedBalanceMap.get(b), consolidatedBalanceMap.get(a)));
        PriorityQueue<User> debtors = new PriorityQueue<>(
                (a, b) -> Double.compare(consolidatedBalanceMap.get(a), consolidatedBalanceMap.get(b)));
        Map<User, Map<User, Double>> balances = balanceSheet.getBalances();
        for (User user1 : balances.keySet()) {
            double netContribution = 0.00;
            for (Map.Entry<User, Double> entry : balances.get(user1).entrySet()) {
                netContribution += entry.getValue();
            }
            consolidatedBalanceMap.put(user1, netContribution);
            if (netContribution > 0) {
                // one of the debtors
                creditors.add(user1);
            } else if (netContribution < 0) {
                // creditor
                debtors.add(user1);
            }
        }
        System.out.println("Consolidated:");
        System.out.println(consolidatedBalanceMap);

        // populated debtors and creditors PriorityQueues
        balances.clear(); // empty out the balanceSheet map

        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            User creditor = creditors.poll();
            User debtor = debtors.poll();

            double debt = Math.abs(consolidatedBalanceMap.get(debtor));
            double credit = consolidatedBalanceMap.get(creditor);

            double amount = Math.min(debt, credit);// amount that can be settled in this transaction

            balances.computeIfAbsent(debtor, key -> new HashMap<>()).put(creditor, -amount);
            balances.computeIfAbsent(creditor, key -> new HashMap<>()).put(debtor, amount);

            credit -= amount;
            debt -= amount;

            consolidatedBalanceMap.put(debtor, -debt);
            consolidatedBalanceMap.put(creditor, credit);

            if (debt > 0) {
                debtors.add(debtor);
            }
            if (credit > 0) {
                creditors.add(creditor);
            }
        }
        System.out.println("Simplified debts");
        System.out.println("After simplify:");
        System.out.println(balances);

    }

    public synchronized void showBalance(User user) {
        if(balanceSheet.getBalances().containsKey(user)){
            for (User user2 : balanceSheet.getBalances().get(user).keySet()) {
            double amount = balanceSheet.getBalances().get(user).get(user2);
                if (amount > 0) {
                    System.out.println(user.getName() + " owes " + user2.getName() + ": " + amount);
                }
            }
        }else{
            System.out.println("No Balances");
        }
    }

    public synchronized void showAllBalances() {
        for (User user1 : balanceSheet.getBalances().keySet()) {
            for (User user2 : balanceSheet.getBalances().get(user1).keySet()) {
                double amount = balanceSheet.getBalances().get(user1).get(user2);
                if (amount < 0) {
                    amount=Math.abs(amount);
                    System.out.println(user1.getId() + " owes " + user2.getId() + ": " + amount);
                }
            }
        }
    }

}
