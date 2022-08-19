import java.lang.IllegalArgumentException;
/**
 * Class representation of a Sudoku puzzle.
 * This can store the intermediate states of sudoku
 * puzzles, differentiate between clues and guesses
 * and determine if a given cell is filled with a 
 * valid value.
 * 
 * The board can be indexed with coordinates (0,0 at top left) 
 * or indexed with a single value (cells index from left to right, then top to bottom).
 * 
 * @author Michael Coppola
 */
public class SudokuPuzzle {

    private int dimension, xDivisions, yDivisions;
    private int cellCount;
    private int board[];
    private boolean isClue[];
    
    /**
     * Private constructor for sudoku puzzle. Use SudokuPuzzleFactory to
     * load in a 9x9 puzzle;
     * 
     * @param dimension The width/length of the puzzle
     * @param xDivisions The number of divisions for each constraint supercell, horizontally
     * @param yDivisions The number of divisions for each constraint supercell, vertically
     */
    protected SudokuPuzzle(int dimension, int xDivisions, int yDivisions) {
        if (xDivisions * yDivisions != dimension) {
            throw new IllegalArgumentException("Dimension mismatch.");
        }

        this.dimension = dimension;
        this.xDivisions = xDivisions;
        this.yDivisions = yDivisions;
        this.cellCount = dimension * dimension;

        this.board = new int[cellCount];
        this.isClue = new boolean[cellCount];

        
    }

    /**
     * Gets the puzzle's dimension
     * 
     * @return Width/length of puzzle
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Gets the number of cells in the puzzle
     * 
     * @return Number of cells in puzzle
     */
    public int getCellCount() {
        return this.cellCount;
    }
    
    /**
     * Sets the cell at a row/column pair to a given
     * value. Throws an error when a immutable clue is
     * indexed.
     * 
     * @param row The row to be modified
     * @param column The column to be modified
     * @param value The value to place in cell
     */
    public void setCell(int row, int column, int value) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        if (value > dimension) {
            throw new IllegalArgumentException("Value is out of range");
        }
        if (isClue[row * dimension + column]) {
            throw new IllegalArgumentException("Cell contains a clue.");
        }
        board[row * dimension + column] = value;
    }

    /**
     * Gets the value stored in a given cell.
     * 
     * @param row The row to retrieve value from
     * @param column The column to retrieve value from
     * @return  Value in given cell
     */
    public int getCell(int row, int column) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return board[row * dimension + column];
    }

    /**
     * Sets a cell's clue state.
     * Clues are immutable.
     * 
     * @param row The row containing the cell to set as clue
     * @param column The column containing the cell to set as clue
     * @param flag The clue state to set cell as
     */
    public void setClue(int row, int column, boolean flag) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        isClue[row * dimension + column] = flag;
    }

    /**
     * Checks whether a given cell is a clue.
     * 
     * @param row the row containing the suspect cell
     * @param column the column containing the suspect cell
     * @return boolean stating if cell is a clue
     */
    public boolean isClue(int row, int column) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return isClue[row * dimension + column];
    }

    /**
     * Set a value of a cell using 1d indexing.
     * 
     * @param index index of cell
     * @param value value to place in cell
     */
    public void setCell(int index, int value) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        if (value > dimension) {
            throw new IllegalArgumentException("Value is out of range");
        }
        if (isClue[index]) {
            throw new IllegalArgumentException("Cell contains a clue.");
        }
        board[index] = value;
    }

    /**
     * Increment the value of a given cell by 1
     * 
     * @param index 1d index of cell
     */
    public void incrementCell(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        if (isClue[index]) {
            throw new IllegalArgumentException("Cell contains a clue.");
        }
        board[index]++;
    }

    /**
     * Gets the value of a cell using 1d indexing.
     * 
     * @param index index of cell
     * @return value inside of given cell
     */
    public int getCell(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return board[index];
    }

    /**
     * Sets a cell's clue state.
     * Clues are immutable.
     * 
     * @param index 1d index of cell
     * @param flag the clue state to set the cell as
     */
    public void setClue(int index, boolean flag) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        isClue[index] = flag;
    }

    /**
     * Checks whether a given cell is a clue.
     * 
     * @param index the index containing the suspect cell
     * @return boolean stating if cell is a clue
     */
    public boolean isClue(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return isClue[index];
    }

    /**
     * Returns a boolean stating if the suspect cell
     * is violating any sudoku constraints.
     * 
     * @param row suspect row index
     * @param column suspect coolumn index
     * @return true if valid, false if invalid
     */
    public boolean isValid(int row, int column) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        int numberCount_row[] = new int[dimension + 1];
        int numberCount_col[] = new int[dimension + 1];
        int numberCount_sqr[] = new int[dimension + 1];
        int i, j;

        for (i = 0; i < dimension; i++) {
            numberCount_row[getCell(row, i)]++;
            numberCount_col[getCell(i, column)]++;
        }
        for (i = 1; i <= dimension; i++) {
            if (numberCount_row[i] > 1 || numberCount_col[i] > 1)
                return false;
        }
        
        int superCellRow = row/yDivisions;
        int superCellCol = row/xDivisions;
        int superCellHeight = dimension/yDivisions;
        int superCellWidth = dimension/xDivisions;

        for (i = 0; i < superCellWidth; i++) {
            for (j = 0; j < superCellHeight; j++) {
                numberCount_sqr[getCell(
                    i + (superCellRow * superCellWidth),
                    j + (superCellCol * superCellHeight)
                )]++;
            }
        }
        for (i = 1; i <= dimension; i++) {
            if (numberCount_sqr[i] > 1)
                return false;
        }
        return true;
    }

    /**
     * Returns a boolean stating if the suspect cell
     * is violating any sudoku constraints.
     * 
     * @param index suspect cell index
     * @return true if valid, false if invalid
     */
    public boolean isValid(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return isValid(index / dimension, index % dimension);
    }

    @Override
    public String toString() {
        StringBuilder boardtext = new StringBuilder();
        for (int i = 0; i < cellCount; i++) {
            if (board[i] == 0) {
                boardtext.append("X");
            } else {
                boardtext.append(board[i]);
            }
            if ((i+1)%dimension == 0) {
                boardtext.append("\n");
            }
        }
        return boardtext.toString();
    }
}