public class Board {
    //Constants
    private static final int BOARD_SIZE = 8;
    private static final int WHITE_BACK_ROW = 7;
    private static final int BLACK_BACK_ROW = 0;
    private static final int WHITE_PAWN_ROW = 6;
    private static final int BLACK_PAWN_ROW = 1;

    //instance field
    private final Piece[][] board;
    
    //constructor
    public Board(){
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        setUpBoard();
    }

    //methods
    public void setUpBoard(){
        Piece.Type[] firstRow = {Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.QUEEN, Piece.Type.KING, Piece.Type.BISHOP, Piece.Type.KNIGHT, Piece.Type.ROOK}; 

        for (int i=0; i<BOARD_SIZE; i++){
            board[WHITE_BACK_ROW][i] = new Piece(Piece.Color.WHITE, firstRow[i]);
            board[BLACK_BACK_ROW][i] = new Piece(Piece.Color.BLACK, firstRow[i]);
        }

        for (int i =0; i<BOARD_SIZE; i++){
            board[WHITE_PAWN_ROW][i] = new Piece(Piece.Color.WHITE, Piece.Type.PAWN);
            board[BLACK_PAWN_ROW][i] = new Piece(Piece.Color.BLACK, Piece.Type.PAWN);
        }

    }

    public void printBoard(){
        for (int i=0; i<BOARD_SIZE; i++){
            for (int j=0; j<BOARD_SIZE; j++){
                System.out.print(board[i][j] == null ? ". " : board[i][j] + " ");
            }
            System.out.println(); 
        }

    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol){
        board [toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = null;
    }
}
