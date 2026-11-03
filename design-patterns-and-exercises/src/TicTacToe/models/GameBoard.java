package models;

public class GameBoard {
    private String[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        board = new String[size][size];
        for(String [] row: board){
            java.util.Arrays.fill(row, "-");
        }
    }

    public void setPosition(int row, int col, String value){
        board[row][col] = value;
    }

    public String getPosition(int row, int col){
        return board[row][col];
    }

    public String[][] getBoard() {
        return board;
    }
}