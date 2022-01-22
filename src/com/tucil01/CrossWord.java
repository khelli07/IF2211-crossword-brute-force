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
            int cols = matLines.get(0).replace(" ", "").length();
            this.board = new Matrix(rows, cols);

            for (int i = 0; i < matLines.size(); i++) {
                char[] inchar = matLines.get(i).replace(" ", "").toCharArray();
                for (int j = 0; j < inchar.length; j++) {
                    this.board.setElmt(new Coordinate(i, j), inchar[j]);
                }
            }

            // Read list of available words
            this.words = new ArrayList<>();
            ln = br.readLine();
            while (ln != null) {
                words.add(ln);
                ln = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    private void printWord(ArrayList<Coordinate> wordCoordinates, String word) {
        int rows = this.board.getRows();
        int cols = this.board.getCols();
        Matrix tmpMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tmpMatrix.setElmt(new Coordinate(i, j), '-');
            }
        }

        for (int i = 0; i < word.length(); i++) {
            tmpMatrix.setElmt(wordCoordinates.get(i), word.charAt(i));
        }

        System.out.println(word);
        tmpMatrix.printMatrix();
    }

    public void searchWords() {
        ArrayList<Coordinate> wordCoordinates = new ArrayList<>();
        for (String word : this.words) {
            char[] inchar = word.toCharArray();
            for (int i = 0; i < this.board.getRows(); i++) {
                for (int j = 0; j < this.board.getCols(); j++) {
                    Coordinate startCr = new Coordinate(i, j); // Starting coordinate
                    if (this.board.checkCurrentChar(startCr, inchar[0])) {
                        // Determine which directions
                        ArrayList<Matrix.Direction> dirList = this.board.decideDirection(startCr, inchar[1]);

                        for (Matrix.Direction dir : dirList) {
                            // Initialize starting point
                            int itr = 1;
                            boolean walk = true;
                            wordCoordinates.add(startCr);
                            Coordinate cr = startCr.copy();

                            // Walk
                            while (itr < inchar.length && walk) {
                                this.board.moveOneStep(cr, dir);
                                if (!this.board.isCrValid(cr) || !this.board.checkCurrentChar(cr, inchar[itr])) {
                                    walk = false;
                                } else {
                                    wordCoordinates.add(cr.copy());
                                    itr++;
                                }
                            }

                            // If we found the word print, otherwise start again
                            if (itr == inchar.length) {
                                this.printWord(wordCoordinates, word);
                                wordCoordinates.clear();
                            } else {
                                wordCoordinates.clear();
                            }
                        }
                    }
                }
            }
        }
    }

}