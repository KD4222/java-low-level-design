package models.split;

import models.User;

public class Split {
    protected User user;
    protected Double amount;

    public Split(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }
}
