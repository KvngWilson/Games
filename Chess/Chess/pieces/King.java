package Chess.pieces;

import Chess.Board;
import Chess.Color;
import Chess.Position;

public class King extends Piece {
    private boolean hasMoved;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // King moves (one square in any direction)
        if (rowDiff <= 1 && colDiff <= 1 && canMoveTo(to, board)) {
            hasMoved = true;
            return true;
        }

        // Castling
        if (rowDiff == 0 && colDiff == 2 && !hasMoved && isValidCastling(from, to, board)) {
            hasMoved = true;
            return true;
        }
        return false;
    }

    private boolean canMoveTo(Position to, Board board) {
        Piece targetPiece = board.getPieceAt(to);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }

    private boolean isValidCastling(Position from, Position to, Board board) {
        int direction = to.getCol() - from.getCol() > 0 ? 1 : -1;
        Position rookPos = new Position(from.getRow(), direction > 0 ? 7 : 0);
        Piece rook = board.getPieceAt(rookPos);

        if (!(rook instanceof Rook)) {
            return false;
        }

        if (((Rook) rook).hasMoved()) {
            return false;
        }

        // Check if squares between king and rook are empty
        for (int c = Math.min(from.getCol(), rookPos.getCol()) + 1; c < Math.max(from.getCol(),
                rookPos.getCol()); c++) {
            if (board.getPieceAt(new Position(from.getRow(), c)) != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public char getSymbol() {
        return (color == Color.WHITE) ? '♔' : '♚';
    }
}