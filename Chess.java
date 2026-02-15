
import java.util.Scanner;

public class Chess {
     public static void main(String[] args){
        Board board = new Board();
        board.printBoard();

        Piece.Color currentPlayer = Piece.Color.WHITE;
        try( Scanner scanner = new Scanner(System.in);){

        while (true) {
         System.out.println(currentPlayer + "'s turn. Enter move (fromRow fromCol toRow toCol):");
         int fromRow = scanner.nextInt();
         int fromCol = scanner.nextInt();
         int toRow = scanner.nextInt();
         int toCol = scanner.nextInt();

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
            board.movePiece(fromRow, fromCol, toRow, toCol);
            board.printBoard();

            currentPlayer = (currentPlayer == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
         } else {
            System.out.println("Invalid move!");
         }
        }
        }

   
    }      
 }


    

