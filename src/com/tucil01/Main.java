import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CrossWord cw = new CrossWord();
        cw.readCWFile("test/input.txt");

        cw.getBoard().printMatrix();
        System.out.println(cw.getWords());
    }
}
