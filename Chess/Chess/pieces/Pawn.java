package Chess.pieces;

import Chess.Board;
import Chess.Color;
import Chess.Position;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // Determine direction based on color
        int forwardDirection = (color == Color.WHITE) ? -1 : 1;

        // Normal forward move
        if (colDiff == 0) {
            if (rowDiff == forwardDirection) {
                // One square forward
                if (board.isEmpty(to)) {
                    hasMoved = true;
                    return true;
                }
            } else if (rowDiff == 2 * forwardDirection && !hasMoved) {
                // Two squares forward on first move
                Position intermediate = new Position(from.getRow() + forwardDirection, from.getCol());
                if (board.isEmpty(intermediate) && board.isEmpty(to)) {
                    hasMoved = true;
                    return true;
                }
            }
        }
        // Capture move
        else if (Math.abs(colDiff) == 1 && rowDiff == forwardDirection) {
            // Normal capture
            if (board.isEnemy(to, color)) {
                hasMoved = true;
                return true;
            }
            // En passant
            if (isEnPassantCapture(from, to, board)) {
                hasMoved = true;
                return true;
            }
        }

        return false;
    }

    public boolean isEnPassantCapture(Position from, Position to, Board board) {
        // Simplified en passant logic
        // In full implementation, need to track previous move
        return false;
    }

    public boolean shouldPromote(Position to) {
        return (color == Color.WHITE && to.getRow() == 0) ||
                (color == Color.BLACK && to.getRow() == 7);
    }

    @Override
    public char getSymbol() {
        return (color == Color.WHITE) ? '♙' : '♟';
    }
}
