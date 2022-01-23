import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CrossWord cw = new CrossWord();
        cw.readCWFile("test/input2.txt");

        System.out.println("Initial board:");
        cw.getBoard().printMatrix();
        System.out.println("Provided words:");
        System.out.println(cw.getWords());
        System.out.println();

        double startTime = System.nanoTime();
        cw.searchWords();
        double elapsedTime = (System.nanoTime() - startTime) / Math.pow(10, 9);

        System.out.println("Board executed in " + elapsedTime + "s.");
    }
}
