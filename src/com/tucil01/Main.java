import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CrossWord cw = new CrossWord();
        Scanner reader = new Scanner(System.in);
        System.out.print("Input your filename: ");
        String inputFile = reader.next();
        cw.readCWFile("test/" + inputFile);

        System.out.println("Initial board:");
        cw.getBoard().printMatrix();
        System.out.println("Provided words:");
        System.out.println(cw.getWords() + "\n");

        // Ask if want to print colored
        boolean printColored = true;
        System.out.print("Want to print the board colored? (Y/N) ");
        char in = reader.next().charAt(0);
        if (in == 'N' || in == 'n')
            printColored = false;

        double startTime = System.nanoTime();
        cw.searchWords(printColored);
        double elapsedTime = (System.nanoTime() - startTime) / Math.pow(10, 9);

        System.out.println("Board executed in " + elapsedTime + "s.");
        System.out.println("Total comparation done: " + cw.getBoard().getCompareCount() + ".");
    }
}
