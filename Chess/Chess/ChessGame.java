// ChessGame.java - Main game class
package Chess;

import java.util.Scanner;
import Chess.pieces.Bishop;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Piece;
import Chess.pieces.Queen;
import Chess.pieces.Rook;

public class ChessGame {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private boolean gameOver;

    public ChessGame() {
        initializeGame();
    }

    private void initializeGame() {
        board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        currentPlayer = whitePlayer;
        gameOver = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Java Chess!");
        System.out.println("Enter moves in format: e2 e4");
        System.out.println("Type 'quit' to exit or 'resign' to resign");

        while (!gameOver) {
            board.printBoard();
            System.out.println("\n" + currentPlayer.getColor() + "'s turn");

            System.out.print("Enter your move: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Game ended by player.");
                break;
            }

            if (input.equalsIgnoreCase("resign")) {
                System.out.println(currentPlayer.getColor() + " resigns! Game over.");
                break;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid format! Use: e2 e4");
                    continue;
                }

                Position from = new Position(parts[0]);
                Position to = new Position(parts[1]);

                if (makeMove(from, to)) {
                    Color opponent = (currentPlayer.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;

                    // Check for checkmate/stalemate
                    if (isCheckmate(opponent)) {
                        board.printBoard();
                        System.out.println("Checkmate! " + currentPlayer.getColor() + " wins!");
                        gameOver = true;
                    } else if (isStalemate(opponent)) {
                        board.printBoard();
                        System.out.println("Stalemate! Game drawn.");
                        gameOver = true;
                    } else {
                        switchPlayer();
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private boolean makeMove(Position from, Position to) {
        Piece piece = board.getPiece(from);

        if (piece == null) {
            System.out.println("No piece at " + from);
            return false;
        }

        if (piece.getColor() != currentPlayer.getColor()) {
            System.out.println("That's not your piece!");
            return false;
        }

        if (board.movePiece(from, to)) {
            // Handle pawn promotion
            if (piece instanceof Pawn) {
                Pawn pawn = (Pawn) piece;
                if (pawn.shouldPromote(to)) {
                    promotePawn(to);
                }
            }
            return true;
        }

        return false;
    }

    private void promotePawn(Position position) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Promote pawn to (Q/R/B/N): ");
            String choice = scanner.nextLine().toUpperCase();

            Piece newPiece = null;
            switch (choice) {
                case "Q":
                    newPiece = new Queen(currentPlayer.getColor());
                    break;
                case "R":
                    newPiece = new Rook(currentPlayer.getColor());
                    break;
                case "B":
                    newPiece = new Bishop(currentPlayer.getColor());
                    break;
                case "N":
                    newPiece = new Knight(currentPlayer.getColor());
                    break;
                default:
                    System.out.println("Invalid choice, promoting to Queen by default");
                    newPiece = new Queen(currentPlayer.getColor());
            }

            board.setPieceAt(position, newPiece);
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    private boolean isCheckmate(Color color) {
        // Simplified checkmate detection
        return board.isInCheck(color) && !hasLegalMoves(color);
    }

    private boolean isStalemate(Color color) {
        return !board.isInCheck(color) && !hasLegalMoves(color);
    }

    private boolean hasLegalMoves(Color color) {
        // Check if any piece of given color has legal moves
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                if (piece != null && piece.getColor() == color) {
                    Position from = new Position(row, col);

                    // Check all possible destination squares
                    for (int destRow = 0; destRow < 8; destRow++) {
                        for (int destCol = 0; destCol < 8; destCol++) {
                            Position to = new Position(destRow, destCol);

                            // Skip same square
                            if (from.equals(to)) {
                                continue;
                            }

                            // Test if move is valid and doesn't leave king in check
                            if (piece.isValidMove(from, to, board)) {
                                Board testBoard = board.clone();
                                testBoard.setPieceAt(to, piece);
                                testBoard.setPieceAt(from, null);

                                if (!testBoard.isInCheck(color)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.start();
    }
}