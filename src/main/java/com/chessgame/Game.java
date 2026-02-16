package src.main.java.com.chessgame;

import java.util.Scanner;

public class Game {
	public void play() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Board board = new Board();
			Piece.Color currentPlayer = Piece.Color.WHITE;
			board.printBoard();
			while(true) {
				if (board.isCheckMate(currentPlayer)) {
					System.out.println("Checkmate! " + currentPlayer.opponent() + " wins!");
					break;
				}
				if(board.isStaleMate(currentPlayer)) {
					System.out.println("Stalemate! It's a draw.");
					break;
				}
				System.out.println(currentPlayer + "'s turn. Enter move (e.g., e2 e4):");
				String fromPos = scanner.next();
				String toPos = scanner.next();
				int[] from = board.fromChessNotation(fromPos);
				int[] to = board.fromChessNotation(toPos);
				int fromRow = from[0];
				int fromCol = from[1];
				int toRow = to[0];
				int toCol = to[1];
				Piece piece = board.getPiece(fromRow, fromCol);
				if (piece == null) {
					System.out.println("No piece at that position!");
					continue;
				}
				if (piece.getColor() != currentPlayer) {
					System.out.println("That's not your piece!");
					continue;
				}
				if (piece.isValidMove(fromRow, fromCol, toRow, toCol, board)) {
					board.movePiece(fromRow, fromCol, toRow, toCol, scanner);
					board.printBoard();
					currentPlayer = (currentPlayer == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
				} else {
					System.out.println("Invalid move!");
				}
			}
			System.out.println("Play again? (y/n)");
			String answer = scanner.next();
			if (!answer.equalsIgnoreCase("y")) {
				break;
			}
		}
		scanner.close();
	}
}
