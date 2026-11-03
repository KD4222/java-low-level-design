package service;

import java.util.ArrayList;
import java.util.List;

import models.Expense;
import models.User;
import models.split.Split;
import models.split.SplitType;

public class SplitService {
    public List<Split> createEqualSplits(Expense expense,SplitType splitType){ 
        int numberOfParticipants = expense.getParticipants().size();

        double equalAmount = expense.getAmount() / numberOfParticipants;

        List<Split> splits = new ArrayList<>();

        for(User user : expense.getParticipants()) {
            splits.add(new Split(user, equalAmount));
        }

        return splits;
    }

    public List<Split> createExactSplits(Expense expense,SplitType splitType, List<Double> exactAmounts){ 
        if(exactAmounts.size() != expense.getParticipants().size()){
            throw new IllegalArgumentException("Number of exact amounts must match number of participants");
        }
        List<Split> splits = new ArrayList<>();
        for(int i=0;i<expense.getParticipants().size();i++){
            User user = expense.getParticipants().get(i);
            double amount = exactAmounts.get(i);
            splits.add(new Split(user, amount));
        }
        return splits;
    }

    public List<Split> createPercentageSplits(Expense expense,SplitType splitType, List<Double> percentages){ 
        if(percentages.stream().mapToDouble(Double::doubleValue).sum() != 100){
            throw new IllegalArgumentException("Percentages must sum up to 100");
        }

        if(percentages.size() != expense.getParticipants().size()){
            throw new IllegalArgumentException("Number of percentages must match number of participants");
        }

        List<Split> splits = new ArrayList<>();

        for(int i=0;i<expense.getParticipants().size();i++){
            User user = expense.getParticipants().get(i);
            double percentage = percentages.get(i);
            double amount = (percentage / 100) * expense.getAmount();
            splits.add(new Split(user, amount));
        }
        return splits;
    }
}