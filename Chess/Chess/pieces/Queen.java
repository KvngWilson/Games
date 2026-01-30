package Chess.pieces;

import Chess.Board;
import Chess.Color;
import Chess.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // Must move in straight line or diagonal
        if (rowDiff != 0 && colDiff != 0 && Math.abs(rowDiff) != Math.abs(colDiff)) {
            return false;
        }

        // Check if path is clear
        int rowStep = Integer.signum(rowDiff);
        int colStep = Integer.signum(colDiff);

        int currentRow = from.getRow() + rowStep;
        int currentCol = from.getCol() + colStep;

        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            if (!board.isEmpty(new Position(currentRow, currentCol))) {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Check destination
        Piece target = board.getPiece(to);
        return target == null || target.getColor() != this.color;
    }

    @Override
    public char getSymbol() {
        return (color == Color.WHITE) ? '♕' : '♛';
    }
}