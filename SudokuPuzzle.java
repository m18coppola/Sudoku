import java.lang.IllegalArgumentException;

class SudokuPuzzle {

    public int dimension, xDivisions, yDivisions;
    public int cellCount;
    public int board[];
    public boolean isClue[];


    public SudokuPuzzle(int dimension, int xDivisions, int yDivisions) {
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


    public int getCell(int row, int column) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return board[row * dimension + column];
    }

    public void setClue(int row, int column, boolean flag) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        isClue[row * dimension + column] = flag;
    }

    public boolean isClue(int row, int column) {
        if (row > dimension || column > dimension) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return isClue[row * dimension + column];
    }

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

    public int getCell(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return board[index];
    }

    public void setClue(int index, boolean flag) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        isClue[index] = flag;
    }

    public boolean isClue(int index) {
        if (index >= cellCount) {
            throw new IllegalArgumentException("Dimension out of bounds.");
        }
        return isClue[index];
    }

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