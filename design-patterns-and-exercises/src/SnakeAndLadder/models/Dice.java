package models;

public class Dice {
    public Integer rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
}
