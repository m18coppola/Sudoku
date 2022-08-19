/**
 * Helper class containing a backtracking method of solving
 * sudoku puzzles.
 */
public class SudokuSolver {
    /**
     * Takes a SudokuPuzzle object and populates it
     * with the correct answers. leaves puzzle unchanged if
     * it is unsolvable.
     * 
     * @param puzzle SudokuPuzzle object to be populated with solution
     */
    public static void solve(SudokuPuzzle puzzle) {
        int i;
        int cellCount;
        int dimension;
        boolean solvable;

        cellCount = puzzle.getCellCount();
        dimension = puzzle.getDimension();
        solvable = true;
        for (i = 0; i < cellCount && solvable; i++) {
            /* If a clue is invalid, the puzzle is unsolvable */
            if (puzzle.isClue(i) && !puzzle.isValid(i)) {
                System.err.println("Puzzle is unsolvable.");
                solvable = false;
            }
        }

        if (solvable) {
            i = 0;
            while (i < cellCount) {
                /* Skip clues */
                if (puzzle.isClue(i)) {
                    i++;
                } else if (puzzle.getCell(i) < dimension) {
                    /* try a new guess, go to next cell if valid*/
                    puzzle.incrementCell(i);
                    if (puzzle.isValid(i)) {
                        i++;
                    }
                } else {
                    /* search tree branch terminated early, backtrace to last non-clue cell */
                    puzzle.setCell(i, 0);
                    do {
                        i--;
                    } while (puzzle.isClue(i));
                }
            }
        }
    }
    
}
