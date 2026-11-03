package validator;

import java.util.HashMap;
import models.GameBoard;

public class GameValidator {
    GameBoard board;
    HashMap<Integer,Boolean> playerCheck;

    public GameValidator(GameBoard board, HashMap<Integer,Boolean> playerCheck){
        this.board = board;
        this.playerCheck = playerCheck;
    }

    public boolean validateCoordinates(int x,int y){
        return !(x<0 || x>=board.getBoard().length || y<0 || y>=board.getBoard().length);
    }

    public boolean validateIsBoardPositionEmpty(int x,int y){
        return board.getPosition(x,y).equals("-");
    }
}
