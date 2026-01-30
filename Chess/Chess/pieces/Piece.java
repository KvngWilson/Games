package Chess.pieces;

import Chess.Board;
import Chess.Color;
import Chess.Position;

public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean isValidMove(Position from, Position to, Board board);
    public abstract char getSymbol();
}
