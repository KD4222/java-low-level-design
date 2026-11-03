package models;

import java.util.ArrayList;
import java.util.List;
import models.split.Split;

public class Expense {
    private final double amount;
    private final User paidBy;
    private final List<User> participants;
    private final String description;
    private List<Split> splits = new ArrayList<>();

    public Expense(double amount, User paidBy, List<User> participants, String description) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.participants = new ArrayList<>(participants);
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public String getDescription() {
        return description;
    }

    public List<Split> getSplits() {
        return new ArrayList<>(splits);
    }

    public void assignSplits(List<Split> splits) {
        this.splits = new ArrayList<>(splits);
    }
}
