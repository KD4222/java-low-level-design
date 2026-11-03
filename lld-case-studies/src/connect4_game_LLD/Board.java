/**
 * Board
 */
public class Board {

    private final DiscColor[][] grid;
    private static final int ROWS = 6;
    private static final int COLS = 7;

    public Board(){
        this.grid= new DiscColor[ROWS][COLS];
    }
    


    public boolean canPlace(int col) {
        if (col < 0 || col >= COLS) {
            return false;
        }
        return grid[0][col] == null;
    }

    public int placeDisk(int col, DiscColor color) {
        if(canPlace(col)){
            for(int i=ROWS-1;i>=0;i--){
                if(grid[i][col]==null){
                    grid[i][col]=color;
                    return i;
                }
            }
        }
        return -1; //cannot place because the column is already full
    }

    public boolean isFull() {
        for(int i=0;i<COLS;i++){
            if(grid[0][i]==null)return false;
        }
        return true;
    }

    private boolean inBounds(int row,int col){
        return row>=0 && row<ROWS && col>=0 && col<COLS; // verify if the cell we are checking is within bounds
    }

    private int countInDirection(int row, int col,int dr, int dc, DiscColor color){
        int count=0;
        int r=row+dr;
        int c=col+dc;

        while(inBounds(r, c) && grid[r][c]==color){
            count++;
            r+=dr;
            c+=dc;
        }
        return count;
    }

    public boolean checkWin(int row, int col, DiscColor color) {
        
        int[][] directions = new int[][]{
            {0,1},
            {1,0},
            {1,1},
            {-1,1},
        };

        for(int[]dir : directions){
            int count=1;
            count+=countInDirection(row, col, dir[0], dir[1], color);
            count+=countInDirection(row, col, -dir[0], -dir[1], color);
            if(count>=4){
                return true;
            }
        }
        
        return false;
    }

    public DiscColor getCell(int row,int col){
        if(!inBounds(row, col)){
            return null;
        }
        return grid[row][col];
    }

}
