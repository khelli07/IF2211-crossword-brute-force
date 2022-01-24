import java.io.*;
import java.util.ArrayList;

public class CrossWord {
    private Matrix board;
    private ArrayList<String> words;

    CrossWord() {
        this.board = null;
        this.words = null;
    }

    public Matrix getBoard() {
        return this.board;
    }

    public ArrayList<String> getWords() {
        return this.words;
    }

    public void readCWFile(String relFilePath) throws IOException {
        try {
            File file = new File(relFilePath);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String ln;
            ArrayList<String> matLines = new ArrayList<>();

            // Read board first
            ln = br.readLine();
            while (!ln.isEmpty()) {
                matLines.add(ln);
                ln = br.readLine();
            }

            // Move board to matrix
            int rows = matLines.size();
            int cols = matLines.get(0).replaceAll("\\s", "").length();
            this.board = new Matrix(rows, cols);

            for (int i = 0; i < matLines.size(); i++) {
                char[] inchar = matLines.get(i).replaceAll("\\s", "").toCharArray();
                for (int j = 0; j < inchar.length; j++) {
                    this.board.setElmt(new Coordinate(i, j), inchar[j], ColoredChar.Color.RESET);
                }
            }

            // Read list of available words
            this.words = new ArrayList<>();
            ln = br.readLine();
            while (ln != null) {
                if (!this.words.contains(ln))
                    words.add(ln.toUpperCase());
                ln = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public void searchWords(boolean printColored) {
        ArrayList<Coordinate> wordCoordinates = new ArrayList<>();
        ColoredChar.Color[] availableColor = {
                ColoredChar.Color.RED,
                ColoredChar.Color.GREEN,
                ColoredChar.Color.YELLOW,
                ColoredChar.Color.BLUE,
                ColoredChar.Color.PURPLE,
                ColoredChar.Color.CYAN
        };

        int wordFound = 0;
        int index = 0;

        // For every word in the list
        for (String word : this.words) {
            char[] inchar = word.toCharArray();
            int i = 0;
            boolean found = false;
            while (i < this.board.getRows() && !found) {
                int j = 0;
                while (j < this.board.getCols() && !found) {
                    Coordinate startCr = new Coordinate(i, j); // Starting coordinate

                    // Check first character
                    if (this.board.checkCharacter(startCr, inchar[0])) {
                        // Proceed to check surrounding if initial character match
                        ArrayList<Matrix.Direction> dirList = this.board.decideDirection(startCr, inchar);

                        // Iterate through every direction possible
                        for (Matrix.Direction dir : dirList) {
                            // Initialize starting point
                            int itr = 1;
                            boolean walk = true;
                            wordCoordinates.add(startCr);
                            Coordinate cr = startCr.copy();

                            // Walk
                            while (itr < inchar.length && walk) {
                                this.board.moveOneStep(cr, dir);
                                if (!this.board.isCrValid(cr) || !this.board.checkCharacter(cr, inchar[itr])) {
                                    walk = false;
                                } else {
                                    wordCoordinates.add(cr.copy());
                                    itr++;
                                }
                            }

                            // If we found the word, print the board. Otherwise, start again
                            if (itr == inchar.length) {
                                if (!printColored) {
                                    this.board.printWord(wordCoordinates, word);
                                } else {
                                    for (Coordinate ctemp : wordCoordinates) {
                                        this.board.getElmt(ctemp).setColor(availableColor[index]);
                                    }
                                    index = (index + 1) % availableColor.length;
                                    found = true;
                                }
                                wordFound++;
                                wordCoordinates.clear();
                            } else {
                                wordCoordinates.clear();
                            }
                            if (found)
                                break;
                        }
                    }
                    j++;
                }
                i++;
            }
        }
        if (printColored) {
            this.board.printColoredMatrix();
        }
        System.out.println("Word found: (" + wordFound + "/" + this.words.size() + ").");
    }
}