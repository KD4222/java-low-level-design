package service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import models.Board;
import models.Dice;
import models.Player;

public class SnakeAndLadderGame {
    private static final int WINNING_POSITION = 100;

    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final Deque<Player> nextTurnPlayer;

    public SnakeAndLadderGame(List<Player> players, Dice dice, Board board) {
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.nextTurnPlayer = new ArrayDeque<>(players);
    }

    public void play() {
        while (true) {
            Player currPlayer = nextTurnPlayer.pollFirst();
            int diceValue = dice.rollDice();
            int currentPosition = currPlayer.getPosition();
            int newPosition = board.makeMove(currPlayer, diceValue);

            if (newPosition == currentPosition && currentPosition + diceValue > WINNING_POSITION) {
                System.out.println(currPlayer.getName() + " rolled a " + diceValue + " and stays at position " + currentPosition);
            } else {
                currPlayer.setPosition(newPosition);
                System.out.println(currPlayer.getName() + " rolled a " + diceValue + " and moved to position " + newPosition);

                if (newPosition == WINNING_POSITION) {
                    System.out.println(currPlayer.getName() + " has won the game!");
                    break;
                }
            }

            nextTurnPlayer.offerLast(currPlayer);
        }
    }
}
