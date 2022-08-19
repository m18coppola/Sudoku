import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SudokuPuzzleFactory {
    public static SudokuPuzzle Load9by9(String filename) throws IOException {
        SudokuPuzzle puzzle = new SudokuPuzzle(9, 3, 3);

        try (Reader puzzleFile = new FileReader(filename);
             BufferedReader puzzleReader = new BufferedReader(puzzleFile)) {

            int input = puzzleReader.read();
            int i;

            i = 0;
            while (input != -1) {
                if (!Character.isWhitespace((char)input)) {
                    if (Character.isDigit(input)) {
                        puzzle.setCell(i, Character.getNumericValue((char)input));
                        puzzle.setClue(i, true);
                        if (!puzzle.isValid(i)) {
                            System.err.println("Puzzle has no valid solution.");
                            return null;
                        }
                    }
                    i++;
                }
                input = puzzleReader.read();
            }
            puzzleReader.close();
            puzzleFile.close();

        }     
        return puzzle;
    }
}
