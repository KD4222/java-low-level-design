package service;

import java.util.List;
import models.GameBoard;
import models.Player;
import printer.*;
import validator.GameValidator;


public class TicTacToeGame {
    GameBoard board;
    List<Player> players;
    GameValidator validator;
    Print printer;
    int gameCount=0;

    public TicTacToeGame(GameBoard board2, List<Player> of, GameValidator validator2, ConsolePrint printer2) {
        //TODO Auto-generated constructor stub
        this.board=board2;
        this.players=of;   
        this.validator=validator2;
        this.printer=printer2;
    }

    public void input(int x, int y, Player player){
        if(!validator.validateCoordinates(x,y)){
            throw new BoardException("Invalid coordinates");
        }
        if(!validator.validateIsBoardPositionEmpty(x,y)){
            throw new BoardException("Position already filled");
        }

        String symbol= player.getSymbol();
        board.setPosition(x,y,symbol);
        gameCount++;
        printer.printBoard(board.getBoard());

        if(checkBoard(x,y,symbol)){
            System.out.println("Player "+player.getId()+" wins!");
            System.exit(0);
        } else if(gameCount==board.getBoard().length*board.getBoard().length){
            System.out.println("Game Draw!");
            System.exit(0);
        }
    }

    private boolean checkBoard(int x,int y,String symbol){
        boolean winRow=true,winCol=true,winDiag1=true,winDiag2=true;

        for(int i=0;i<board.getBoard().length;i++){
            if(!board.getPosition(x,i).equals(symbol)){
                winRow=false;
            }
            if(!board.getPosition(i,y).equals(symbol)){
                winCol=false;
            }
            if(!board.getPosition(i,i).equals(symbol)){
                winDiag1=false;
            }
            if(!board.getPosition(i,board.getBoard().length-1-i).equals(symbol)){
                winDiag2=false;
            }
        }
        return winRow || winCol || winDiag1 || winDiag2;
    }
}
