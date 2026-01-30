package Chess;

import Chess.pieces.Bishop;
import Chess.pieces.King;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Piece;
import Chess.pieces.Queen;
import Chess.pieces.Rook;

public class Board {
    private Piece[][] squares;

    public Board() {
        this(false);
    }

    private Board(boolean skipInit) {
        squares = new Piece[8][8];
        if (!skipInit) {
            initializeBoard();
        }
    }

    private void initializeBoard() {
        squares[0][0] = new Rook(Color.BLACK);
        squares[0][1] = new Knight(Color.BLACK);
        squares[0][2] = new Bishop(Color.BLACK);
        squares[0][3] = new Queen(Color.BLACK);
        squares[0][4] = new King(Color.BLACK);
        squares[0][5] = new Bishop(Color.BLACK);
        squares[0][6] = new Knight(Color.BLACK);
        squares[0][7] = new Rook(Color.BLACK);

        squares[7][0] = new Rook(Color.WHITE);
        squares[7][1] = new Knight(Color.WHITE);
        squares[7][2] = new Bishop(Color.WHITE);
        squares[7][3] = new Queen(Color.WHITE);
        squares[7][4] = new King(Color.WHITE);
        squares[7][5] = new Bishop(Color.WHITE);
        squares[7][6] = new Knight(Color.WHITE);
        squares[7][7] = new Rook(Color.WHITE);

        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Pawn(Color.BLACK);
            squares[6][i] = new Pawn(Color.WHITE);
        }

    }

    public void printBoard() {
        System.out.println("\n a b c d e f g h");
        System.out.println(" +-----------------+");

        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + "|");
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece == null) {
                    System.out.print(".");
                } else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println("|" + (8 - row));
        }
        System.out.println(" +-----------------+");
        System.out.println(" a b c d e f g h \n");
    }

    public Piece getPieceAt(Position pos) {
        return squares[pos.getRow()][pos.getCol()];
    }

    public Piece getPiece(Position pos) {
        return getPieceAt(pos);
    }

    public boolean isEmpty(Position pos) {
        return getPieceAt(pos) == null;
    }

    public boolean isEnemy(Position pos, Color color) {
        Piece piece = getPieceAt(pos);
        return piece != null && piece.getColor() != color;
    }

    public void setPieceAt(Position pos, Piece piece) {
        squares[pos.getRow()][pos.getCol()] = piece;
    }

    public boolean movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece == null || !piece.isValidMove(from, to, this)) {
            return false;
        }

        Board testBoard = this.clone();
        testBoard.setPieceAt(to, piece);
        testBoard.setPieceAt(from, null);

        if (testBoard.isInCheck(piece.getColor())) {
            return false;
        }

        setPieceAt(to, piece);
        setPieceAt(from, null);

        if (piece instanceof King && Math.abs(from.getCol() - to.getCol()) == 2) {
            // Handle castling
            if (to.getCol() == 6) { // Kingside
                Piece rook = getPieceAt(new Position(from.getRow(), 7));
                setPieceAt(new Position(from.getRow(), 5), rook);
                setPieceAt(new Position(from.getRow(), 7), null);
            } else if (to.getCol() == 2) { // Queenside
                Piece rook = getPieceAt(new Position(from.getRow(), 0));
                setPieceAt(new Position(from.getRow(), 3), rook);
                setPieceAt(new Position(from.getRow(), 0), null);
            }
        }

        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            if (pawn.isEnPassantCapture(from, to, this)) {
                int capturedRow = from.getRow();
                int capturedCol = to.getCol();
                setPieceAt(new Position(capturedRow, capturedCol), null);
            }
        }

        return true;

    }

    public boolean isInCheck(Color color) {
        return false;
    }

    @Override
    public Board clone() {
        Board cloned = new Board(true);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cloned.squares[row][col] = squares[row][col];
            }
        }
        return cloned;
    }
}
