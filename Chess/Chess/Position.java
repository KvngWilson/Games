package Chess;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(String notation) {
        if (notation == null) {
            throw new IllegalArgumentException("Invalid notation: null");
        }

        String trimmed = notation.trim();
        if (trimmed.length() < 2) {
            throw new IllegalArgumentException("Invalid notation: " + notation);
        }

        String square = trimmed.substring(trimmed.length() - 2);

        char colChar = square.charAt(0);
        char rowChar = square.charAt(1);

        this.col = colChar - 'a';
        this.row = 8 - (rowChar - '0');

        if (this.row < 0 || this.row > 7 || this.col < 0 || this.col > 7) {
            throw new IllegalArgumentException("Position out of bounds: " + notation);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        char colChar = (char) ('a' + col);
        char rowChar = (char) ('8' - row);
        return "" + colChar + rowChar;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
}
