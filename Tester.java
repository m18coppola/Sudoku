import java.io.IOException;

public class Tester {
    public static void main(String args[]) throws IOException {
        System.out.println("Creating Puzzle...");
        SudokuPuzzle puzzle = SudokuPuzzleFactory.Load9by9("puzzle1.txt");
        System.out.println(puzzle.toString());
        System.out.println("Solving Puzzle...");
        SudokuSolver.solve(puzzle);
        System.out.println(puzzle.toString());

        for (int i = 1; i <= 5; i++) {
            puzzle = SudokuPuzzleFactory.Load9by9("puzzle"+i+".txt");
            System.out.println(puzzle.toString());
            System.out.println("Solving Puzzle...");
            SudokuSolver.solve(puzzle);
            System.out.println(puzzle.toString());
            Main.printFile(puzzle, "puzzle" + i + ".txt");
        }
    }
}
