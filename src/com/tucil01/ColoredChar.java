public class ColoredChar {
    private final char character;
    private Color color;

    public enum Color {
        RESET,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        PURPLE,
        CYAN,
    }

    ColoredChar(char c) {
        this.character = Character.toUpperCase(c);
        this.color = Color.RESET;
    }

    public void setColor(ColoredChar.Color color) {
        this.color = color;
    }

    public char getChar() {
        return this.character;
    }

    public void printChar() {
        String ANSI;
        switch (this.color) {
            case RED -> ANSI = "\u001B[31m";
            case GREEN -> ANSI = "\u001B[32m";
            case YELLOW -> ANSI = "\u001B[33m";
            case BLUE -> ANSI = "\u001B[34m";
            case PURPLE -> ANSI = "\u001B[35m";
            case CYAN -> ANSI = "\u001B[36m";
            default -> ANSI = "\u001B[0m";
        }

        System.out.print(ANSI + this.character + "\u001B[0m" + " ");
    }
}
