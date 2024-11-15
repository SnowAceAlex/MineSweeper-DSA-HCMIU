package Logic;

import java.util.Random;
import java.util.Stack;

public class Board {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLUMNS = 9;
    public static int NUM_MINES;
    public int count = -1;
    Random rd = new Random();

    public Square[][] square;
    public Stack gameSteps = new Stack();
    public Stack gameCounts = new Stack();
    public boolean gameState = true;

    public Board(){

        SetupBomb();

    }

    public void SetupBomb(){
        for(int i = 0; i < NUM_MINES; i++){
            int x = rd.nextInt(NUM_ROWS);
            int y = rd.nextInt(NUM_COLUMNS);

            // If the square already has a mine put in another square
            while(square[x][y].isHasMine()){
                x = rd.nextInt(NUM_ROWS);
                y = rd.nextInt(NUM_COLUMNS);
            }
            square[x][y].setHasMine(true);
        }
    }

    public void generateNumberInAroundSquare(){
        for(int i = 0; i < square.length; i++){
            for (int j = 0; j < square[0].length; j++){
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    if (i + m < 0) { m++; }
                    if (i + m > NUM_ROWS - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (j + n < 0) { n++; }
                        if (j + n > NUM_COLUMNS - 1) { break; }
                        if (!(m == 0 && n == 0) && square[i + m][j + n].isHasMine()) {
                            count++;
                        }
                    }
                }
                square[i][j].setNumMineAround(count);
            }
        }
    }

    public Square[][] getListSquare() {
        return square;
    }
}
