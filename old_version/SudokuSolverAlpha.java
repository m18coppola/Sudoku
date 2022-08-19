import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.IllegalArgumentException;

/*
 * Sudoku Solver
 * 
 * @author Michael Coppola
 * @version 1
 */
class SudokuSolverAlpha {
    public static void main(String args[]) throws IOException {
        String filename;
        
        /* parse command line args */
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java -jar SudokuSolver filename");
        }
        filename = args[0];

        if (filename.indexOf(".txt") != filename.length() - 4) {
            throw new IllegalArgumentException("Invalid file extention. Must use text file ending in .txt");
        }

        /* Read puzzle input */
        try (Reader puzzleFile = new FileReader(filename);
            BufferedReader puzzleReader = new BufferedReader(puzzleFile)) {

            int input;
            input = puzzleReader.read();
            char cells[] = new char[9 * 9];
            int i;

            i = 0;
            while (input != -1) {
                if (!Character.isWhitespace((char)input)) {
                    cells[i] = (char)input;
                    i++;
                }
                input = puzzleReader.read();
            }
            puzzleReader.close();
            puzzleFile.close();

            Boardstate myBoard = new Boardstate(cells);
            System.out.println();
            myBoard.printBoard();
            System.out.println();
            long start = System.currentTimeMillis();
            myBoard.Solve();
            long finish = System.currentTimeMillis();
            System.out.println("Elapsed time: " + ((float)(finish - start) / 1000f) + "s");
            myBoard.printBoard();
            System.out.println();

            FileWriter solutionFile = new FileWriter(filename.substring(0,filename.indexOf(".txt")) + ".sln.txt", false);
            PrintWriter solutionPrinter = new PrintWriter(solutionFile);
            solutionPrinter.print(myBoard.toString());
            solutionPrinter.close();
            solutionFile.close();

        }
        
    }
    
}