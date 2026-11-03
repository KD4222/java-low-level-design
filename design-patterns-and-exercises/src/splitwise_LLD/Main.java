import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.split.SplitType;

public class Main {

    public static void main(String[] args) throws Exception {

        ExpenseManager expenseManager = new ExpenseManager();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String newInput = sc.nextLine();

            String[] tokens = newInput.split(" ");

            if ("SHOW".equals(tokens[0])) {

                if (tokens.length == 1) {
                    expenseManager.showAllBalances();
                } else {
                    expenseManager.showBalance(tokens[1]);
                }

            } else if ("EXPENSE".equals(tokens[0])) {

                String paidBy = tokens[1];
                double amount = Double.parseDouble(tokens[2]);

                int numParticipants = Integer.parseInt(tokens[3]);

                List<String> participants = new ArrayList<>();

                int i = 4;

                while (i < 4 + numParticipants) {
                    participants.add(tokens[i]);
                    i++;
                }

                SplitType type = SplitType.valueOf(tokens[i]);
                i++;

                List<Double> splitData = new ArrayList<>();

                while (i < tokens.length) {
                    splitData.add(Double.parseDouble(tokens[i]));
                    i++;
                }

                expenseManager.addExpense(
                    paidBy,
                    amount,
                    "",
                    participants,
                    type,
                    splitData
                );
            }
        }
        sc.close();
    }
}