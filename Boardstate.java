import java.util.Arrays;

public class Boardstate {
    private final static int BOARDSIZE = 9 * 9;

    //opted in for 1d array, 2d arrays are non-contiguous by jvm spec ( https://docs.oracle.com/javase/specs/jls/se18/html/jls-10.html )
    private int board[];

    public Boardstate(char input[]) {
        int i;

        /* populate board with clues, 0 for empty spaces */
        this.board = new int[BOARDSIZE];
        for (i = 0; i < BOARDSIZE; i++) {
            if (input[i] == 'X') {
                board[i] = 0;
            } else {
                board[i] = Character.getNumericValue(input[i]);
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
        int i, j, k;

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
                numberCount_sqr[getCell(0 + (i * 3),0 + (j * 3))]++;
                numberCount_sqr[getCell(0 + (i * 3),1 + (j * 3))]++;
                numberCount_sqr[getCell(0 + (i * 3),2 + (j * 3))]++;
                numberCount_sqr[getCell(1 + (i * 3),0 + (j * 3))]++;
                numberCount_sqr[getCell(1 + (i * 3),1 + (j * 3))]++;
                numberCount_sqr[getCell(1 + (i * 3),2 + (j * 3))]++;
                numberCount_sqr[getCell(2 + (i * 3),0 + (j * 3))]++;
                numberCount_sqr[getCell(2 + (i * 3),1 + (j * 3))]++;
                numberCount_sqr[getCell(2 + (i * 3),2 + (j * 3))]++;

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
}
