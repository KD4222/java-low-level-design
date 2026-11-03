public class Main{
    public static void main(String[] args) {
        Player p1= new Player("One", DiscColor.RED);
        Player p2= new Player("Two", DiscColor.YELLOW);

        Connect4Game game = new Connect4Game(p1,p2);

        game.makeMove(p1,0);
        game.makeMove(p2, 5);
        game.makeMove(p1, 1);
        game.makeMove(p2, 5);
        game.makeMove(p1, 2);
        game.makeMove(p2, 5);
        game.makeMove(p1, 3);
        game.makeMove(p2, 5);
    }
}