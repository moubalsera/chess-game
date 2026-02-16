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
        Piece movingPiece = board[fromRow][fromCol];
        if (movingPiece == null) return;

         if (movingPiece.getPieceType() == Piece.Type.KING &&
             Math.abs(toCol - fromCol) == 2) {
                if (toCol > fromCol) {

                    Piece rook = board[fromRow][7];
                    board[fromRow][5] = rook;
                    board[fromRow][7] = null;
                }

                else {

                    Piece rook = board[fromRow][0];
                    board[fromRow][3] = rook;
                    board[fromRow][0] = null;
                }
        }

        board [toRow][toCol] = movingPiece;
        board[fromRow][fromCol] = null;
        movingPiece.markAsMoved();
        

        //en passant pawn movement
        if( movingPiece.getPieceType() == Piece.Type.PAWN && 
            Math.abs(fromRow - toRow) == 2){ 
            
            enPassantTargetRow = (fromRow + toRow) / 2;
            enPassantTargetCol = toCol;


        } else {
            enPassantTargetRow = -1;
            enPassantTargetCol = -1;
        }

        if (movingPiece.getPieceType() == Piece.Type.PAWN &&
            Math.abs(fromCol - toCol) == 1 &&
            getPiece(toRow, toCol) == null) {
                int capturedPawnRow = (movingPiece.getColor() == Piece.Color.WHITE) ? toRow + 1 : toRow - 1;
                board[capturedPawnRow][toCol] = null;
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

    public boolean squareUnderAttack(int row, int col, Piece.Color color) {
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                
                Piece p = getPiece(i,j);

                if (p == null) continue;
                
                if (p.getColor() != color &&
                    p.canAttackSquare(i, j, row, col, this)) {
                    return true;
                }

            }
        }

        return false;
    }

    public boolean isKingInCheck (Piece.Color color, Board board) {
        int kingRow = -1;
        int kingCol = -1;
        boolean foundKing = false;
        for (int i = 0; i < BOARD_SIZE && !foundKing; i++){
            for (int j = 0; j < BOARD_SIZE && !foundKing; j++){
                Piece p = board.getPiece(i, j);
                if (p != null && p.getPieceType() == Piece.Type.KING && p.getColor() == color){
                     kingRow = i;
                     kingCol = j;
                     foundKing = true;
                }
            }   
        }

        if (kingRow == -1) return false;

         for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                Piece p = board.getPiece(i, j);
                if (p != null && p.getColor() != color && p.isValidMove(i, j, kingRow, kingCol, board)) {
                    return true;
                 }
            }
            }   
        return false;
        }
    
    public boolean isCheckMate(Piece.Color color) {

        if (!isKingInCheck(color, this)){
            return false;
        }

        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Piece piece = getPiece(fromRow, fromCol);

                if (piece != null && piece.getColor() == color) {

                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {

                            if (piece.isValidMove(fromRow, fromCol, toRow, toCol, this)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean isStaleMate(Color color) {

        
    }
    
    
    }
