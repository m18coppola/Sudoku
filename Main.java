import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Commandline front-end for solving
 * Sudoku Puzzles. Completed as a technical assesment
 * for Gavant Software.
 * 
 * @author Michael Coppola
 * @version 2
 */
public class Main {
    /**
     * Commandline tool for solving 9x9 Sudoku puzzles.
     * 
     * @param args expects a file ending in .txt, containing 9x9 sudoku
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        String filename;
        SudokuPuzzle puzzle;
        long start, finish;

        /* parse command line args */
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: Main filename");
        }
        filename = args[0];

        if (filename.indexOf(".txt") != filename.length() - 4) {
            throw new IllegalArgumentException("Invalid file extention. Must use text file ending in .txt");
        }

        /* Load, solve and print puzzle to file */
        puzzle = SudokuPuzzleFactory.Load9by9(filename);
        System.out.println("Puzzle:\n" + puzzle.toString());
        
        start = System.currentTimeMillis();
        SudokuSolver.solve(puzzle);
        finish = System.currentTimeMillis();

        System.out.println("SolvedPuzzle:\n" + puzzle.toString());

        System.out.println("Elapsed time: " + ((float)(finish - start) / 1000f) + "s");

        printFile(puzzle, filename);
    }

    /**
     * Takes a SudokuPuzzle, and prints its content out
     * into a specified .sln.txt file. This utility will clobber
     * any existing file sharing a name.
     * 
     * @param puzzle The puzzle to be printed to file
     * @param filename The original puzzle filename
     * @throws IOException
     */
    public static void printFile(SudokuPuzzle puzzle, String filename) throws IOException {
        FileWriter solutionFile = new FileWriter(filename.substring(0,filename.indexOf(".txt")) + ".sln.txt", false);
        PrintWriter solutionPrinter = new PrintWriter(solutionFile);
        solutionPrinter.print(puzzle.toString());
        solutionPrinter.close();
        solutionFile.close();
    }

}