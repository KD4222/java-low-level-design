import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Board;
import models.Dice;
import models.Player;
import service.SnakeAndLadderGame;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.next();
            players.add(new Player(name));
        }

        Board board = new Board();
        Dice dice = new Dice();
        SnakeAndLadderGame game = new SnakeAndLadderGame(players, dice, board);

        System.out.print("Enter number of snakes: ");
        int numSnakes = scanner.nextInt();

        while (numSnakes-- > 0) {
            System.out.print("Enter snake start and end positions (start end): ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            board.addSnake(start, end);
        }

        System.out.print("Enter number of ladders: ");
        int numLadders = scanner.nextInt();

        while (numLadders-- > 0) {
            System.out.print("Enter ladder start and end positions (start end): ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            board.addLadder(start, end);
        }

        game.play();
    }
}
