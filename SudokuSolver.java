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

        cellCount = puzzle.getCellCount();
        dimension = puzzle.getDimension();
        for (i = 0; i < cellCount; i++) {
            if (puzzle.isClue(i) && !puzzle.isValid(i)) {
                System.err.println("Puzzle is unsolvable.");
            } else {
                i = 0;
                while (i < cellCount) {
                    if (puzzle.isClue(i)) {
                        i++;
                    } else if (puzzle.getCell(i) < dimension) {
                        puzzle.incrementCell(i);
                        if (puzzle.isValid(i)) {
                            i++;
                        }
                    } else {
                        puzzle.setCell(i, 0);
                        do {
                            i--;
                        } while (puzzle.isClue(i));
                    }
                }
            }
        }
    }
}
