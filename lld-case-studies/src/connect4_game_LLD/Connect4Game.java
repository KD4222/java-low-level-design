/**
 * Connect4Game
 */
public class Connect4Game {
    //game orchestrator
    private final Board board;
    private final Player player1;
    private  final Player player2;
    private Player currentPlayer;
    private GameState state;
    Player winner ;
    
    public Connect4Game(Player player1,Player player2) {
        board = new Board();
        this.player1 = player1;
        this.player2= player2;
        state = GameState.IN_PROGRESS;
        currentPlayer= player1;
        winner= null;
    }

    //getters
    public Board getBoard() {
        return board;
    }

    public GameState getState() {
        return state;
    }

    public Player getWinner() {
        if(winner==null){
            if(state == GameState.IN_PROGRESS){
                System.out.println("Game is still running noi winner decided yet.");
                return null;
            }else if(state ==  GameState.DRAW){
                System.out.println("The board is full, this game ended at a draw.");
                return null;
            }
        }
        return winner;
    }

    //make move
    boolean makeMove(Player player, int col){
        if(player == currentPlayer && state == GameState.IN_PROGRESS && board.canPlace(col)){
            int row= board.placeDisk(col, player.getColor());
            if(row==-1)return false;
            if(board.checkWin(row,col,player.getColor())){
                state= GameState.WON;
                winner= player;
                System.out.println("Player" + player.getId() + " Wins.");
            }else if(board.isFull()){
                state= GameState.DRAW;
            }
            currentPlayer = currentPlayer == player1?player2 : player1;
            return true;
        }
        return false;
    }

    Player getCurrentPlayer(){
        return currentPlayer;
    }
}

