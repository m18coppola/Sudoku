import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Boardstate {
    private final static int BOARDSIZE = 9 * 9;
    private Set<Integer> clues;

    //opted in for 1d array, 2d arrays are non-contiguous by jvm spec ( https://docs.oracle.com/javase/specs/jls/se18/html/jls-10.html )
    private int board[];

    public Boardstate(char input[]) {
        int i;

        clues = new HashSet<Integer>();

        /* populate board with clues, 0 for empty spaces */
        this.board = new int[BOARDSIZE];
        for (i = 0; i < BOARDSIZE; i++) {
            if (input[i] == 'X') {
                board[i] = 0;
            } else {
                board[i] = Character.getNumericValue(input[i]);
                clues.add(i);
            }
        }
    }

    public int getCell(int row, int col) {
        //TODO: bounds check
        return board[row * 9 + col];
    }

    public void setCell(int row, int col, int value) {
        //TODO: bounds check, value check
        board[row * 9 + col] = value;
    }
    
    public boolean isValid() {
        int numberCount_row[] = new int[10];
        int numberCount_col[] = new int[10];
        int numberCount_sqr[] = new int[10];
        int i, j, k, l;

        // check rows/cols for errors
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                numberCount_row[getCell(i,j)]++;
                numberCount_col[getCell(j,i)]++;
            }
            for (j = 1; j <= 9; j++) {
                if (numberCount_row[j] > 1 || numberCount_col[j] > 1) {
                    return false;
                }
                numberCount_row[j] = 0;
                numberCount_col[j] = 0;
            }
            numberCount_row[0] = 0;
            numberCount_col[0] = 0;
        }

        // check squares for errors
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                for (k = 0; k < 3; k++) {
                    for (l = 0; l < 3; l++){
                        numberCount_sqr[getCell(k + (i * 3),l + (j * 3))]++;
                    }
                }
                for (k = 1; k <= 9; k++) {
                    if (numberCount_sqr[k] > 1) {
                        return false;
                    }
                    numberCount_sqr[k] = 0;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[i] == 0) {
                System.out.print("X");
            } else {
                System.out.print(board[i]);
            }
            if ((i+1)%9 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder boardtext = new StringBuilder();
        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[i] == 0) {
                boardtext.append("X");
            } else {
                boardtext.append(board[i]);
            }
            if ((i+1)%9 == 0) {
                boardtext.append("\n");
            }
        }
        return boardtext.toString();
    }

    public boolean Solve() {

        if (!isValid()) {
            System.err.println("Puzzle is unsolvable!");
            return false;
        } 

        int i = 0;
        while(i < BOARDSIZE) {
            // cell has clue, skip
            if (clues.contains(i)) {
                i++;
            } else if (board[i] < 9){
                board[i]++;
                if (isValid()) {
                    i++;
                }
            } else {
                board[i] = 0;
                do {
                    i--;
                } while (clues.contains(i));
            }
        }
        return true;
        
    }

}
