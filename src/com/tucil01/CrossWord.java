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
                    this.board.setElmt(i, j, inchar[j]);
                }
            }

            // Read available words
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

    public void searchWords() {
        
    }

}