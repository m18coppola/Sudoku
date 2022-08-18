import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.IllegalArgumentException;

/*
 * Sudoku Solver
 * 
 * Michael Coppola
 */
class SudokuSolver {
    public static void main(String args[]) throws IOException {
        String filename;
        
        /* parse command line args */
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java -jar SudokuSolver filename");
        }
        filename = args[0];

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

            Boardstate myBoard = new Boardstate(cells);
            System.out.println();
            myBoard.printBoard();
            System.out.println();
            System.out.println(myBoard.isValid());
            puzzleReader.close();
            puzzleFile.close();
        }
        
    }
    
}