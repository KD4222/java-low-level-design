import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import models.GameBoard;
import models.Player;
import printer.ConsolePrint;
import service.TicTacToeGame;
import validator.GameValidator;

public class Main{
    public static void main(String[] args){
        Player p1= new Player(1, "Player 1", "X");
        Player p2= new Player(2, "Player 2", "O");
        

        HashMap<Integer, Boolean> playerCheck = new HashMap<>();
        playerCheck.put(p1.getId(), true);
        playerCheck.put(p2.getId(), true);
        List<Player> players = List.of(p1, p2);
        GameBoard board = new GameBoard(3);

        GameValidator validator = new GameValidator(board, playerCheck);
        TicTacToeGame game = new TicTacToeGame(board, List.of(p1, p2), validator, new ConsolePrint());
        
        while(true){
            System.out.println("Choose input 1: play 2: exit");
            Scanner sc = new Scanner(System.in);

            int option= sc.nextInt();
            switch(option){
                case 1 -> {
                    System.out.println("Enter player id:");
                    Player player = players.get(sc.nextInt() - 1);
                    System.out.println("Enter coordinates (x y):");
                    game.input(sc.nextInt(), sc.nextInt(), player);
                }
                case 2 -> {
                    System.exit(0);

                    sc.close();
                }
                default -> System.out.println("choose 1 to play");
            }
        }
    }
}