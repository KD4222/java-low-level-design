package models;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int BOARD_SIZE = 100;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board() {
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public void addSnake(int start, int end) {
        validateSnakeOrLadder(start, end, true);
        snakes.put(start, end);
    }

    public void addLadder(int start, int end) {
        validateSnakeOrLadder(start, end, false);
        ladders.put(start, end);
    }

    public int makeMove(Player player, int diceValue) {
        int currentPosition = player.getPosition();
        int targetPosition = currentPosition + diceValue;

        if (targetPosition > BOARD_SIZE) {
            return currentPosition;
        }

        if (snakes.containsKey(targetPosition)) {
            return snakes.get(targetPosition);
        }

        if (ladders.containsKey(targetPosition)) {
            return ladders.get(targetPosition);
        }

        return targetPosition;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    private void validateSnakeOrLadder(int start, int end, boolean isSnake) {
        if (start < 1 || start >= BOARD_SIZE || end < 1 || end >= BOARD_SIZE) {
            throw new IllegalArgumentException("Positions must be between 1 and " + (BOARD_SIZE - 1));
        }

        if (isSnake && start <= end) {
            throw new IllegalArgumentException("Snake start must be greater than snake end.");
        }

        if (!isSnake && start >= end) {
            throw new IllegalArgumentException("Ladder start must be less than ladder end.");
        }
    }
}
