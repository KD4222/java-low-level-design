package models;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private final Map<User, Map<User, Double>> balances = new HashMap<>();

    public Map<User, Map<User, Double>> getBalances() {
        return balances;
    }
}
