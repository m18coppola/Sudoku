import java.io.IOException;

public class Tester {
    public static void main(String args[]) throws IOException {
        System.out.println("Creating Puzzle...");
        SudokuPuzzle puzzle = SudokuPuzzleFactory.Load9by9("puzzle1.txt");
        System.out.println(puzzle.toString());

        System.out.println("Trying to create invalid puzzle...");
        puzzle = SudokuPuzzleFactory.Load9by9("puzzle1bad.txt");
    }
}
