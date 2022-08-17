import java.util.Scanner;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
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
            while (input != -1) {
                if (!Character.isWhitespace((char)input))
                System.out.println((char)input);
                input = puzzleReader.read();
            }
            puzzleReader.close();
            puzzleFile.close();
        }
        
    }
    
}