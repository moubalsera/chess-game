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

        //en passant pawn movement
        if(Math.abs(fromRow - toRow) == 2
        ){ enPassantTargetRow = (fromRow + toRow) / 2;
            enPassantTargetCol = toCol;

        } else {
            enPassantTargetRow = -1;
            enPassantTargetCol = -1;
        }
    }

    public Piece getPiece(int row, int col){
        return board[row][col];
    }

    public boolean isEmpty(int row, int col){
        return board[row][col] == null;
    }

    public boolean hasEnemyPiece(int row, int col, Piece.Color color){
        return board[row][col] != null && board[row][col].getColor()!= color;
    }

    public void promotePawn(int row, int col, Piece.Type newType, Board board){
        Piece pawn = board.getPiece(row, col);

        if (pawn == null) return;
        if (pawn.getPieceType() != Piece.Type.PAWN) return;

        board.setPiece(row, col, new Piece(pawn.getColor(), newType));
    }

    public void setPiece(int row, int col, Piece piece){
        board[row][col] = piece;
    }

     int enPassantTargetRow = -1;
     int enPassantTargetCol = -1;
    public boolean isEnPassantSquare(int row, int col){
       return row == enPassantTargetRow && col == enPassantTargetCol;
    }

    public boolean isKingInCheck () {
        //CURRENTLY HERE
    }



    
}
