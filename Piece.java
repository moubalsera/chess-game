public class Piece {
    public enum Color { 
        WHITE, 
        BLACK;

        public Color opponent() {
            return this == WHITE ? BLACK : WHITE;
        }

     }
    public enum Type { KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN }

    //instance fields
    private final Color color;
    private final Type pieceType;
    private boolean hasMoved;
  
    //constructor
    public Piece(Color color, Type pieceType){
        this.color = color;
        this.pieceType = pieceType;
        this.hasMoved = false;
    }

    //methods
    @Override public String toString(){
        switch(pieceType){
            case KING: return color == Color.WHITE ? "\u2654" : "\u265A";
            case QUEEN: return color == Color.WHITE ? "\u2655" : "\u265B";
            case KNIGHT: return color == Color.WHITE ? "\u2658" : "\u265E";
            case BISHOP: return color == Color.WHITE ? "\u2657" : "\u265D";
            case ROOK: return color == Color.WHITE ? "\u2656" : "\u265C";
            case PAWN: return color == Color.WHITE ? "\u2659" : "\u265F";
            default: return "?";
        }
    }
    public Color getColor(){
        return color;
    }
    public Type getPieceType(){
        return pieceType;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void markAsMoved() {
        this.hasMoved = true;
    }
    
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Board board){
        //check bounds
        if (toCol < 0 || toCol > 7 || toRow < 0 || toRow > 7){
            return false;
            }

        boolean pieceMoveValid = false;

        switch (pieceType){
            case PAWN:  
                pieceMoveValid = isValidPawnMove(fromRow, fromCol, toRow, toCol, board);
                break;
            case KING:  
                pieceMoveValid = isValidKingMove(fromRow, fromCol, toRow, toCol, board);
                break;
            case BISHOP:    
                pieceMoveValid = isValidBishopMove(fromRow, fromCol, toRow, toCol, board);
                break;
            case ROOK:  
                pieceMoveValid = isValidRookMove(fromRow, fromCol, toRow, toCol, board);
                break;
            case QUEEN: 
                pieceMoveValid = isValidBishopMove(fromRow, fromCol, toRow, toCol, board) || isValidRookMove(fromRow, fromCol, toRow, toCol, board);
                break;
            case KNIGHT: 
                pieceMoveValid = isValidKnightMove(fromRow, fromCol, toRow, toCol, board);
                break;
        }

        if (!pieceMoveValid) return false;

        Piece movingPiece = board.getPiece(fromRow, fromCol);
        Piece capturedPiece = board.getPiece(toRow, toCol);

        board.setPiece(toRow, toCol, movingPiece);
        board.setPiece(fromRow, fromCol, null);

        boolean kingInCheck = board.isKingInCheck(movingPiece.getColor(), board);

        board.setPiece(fromRow, fromCol, movingPiece);
        board.setPiece(toRow, toCol, capturedPiece);

        return !kingInCheck;

        }

    
    private boolean isValidPawnMove(int fromRow, int fromCol, int toRow, int toCol, Board board) {

            int direction = (color == Color.BLACK) ? 1 : -1;
            int startRow = (color == Color.BLACK) ? 1 : 6;

            //move forward
            if (toRow - fromRow == direction &&
                toCol == fromCol &&
                board.isEmpty(toRow, toCol)){
                return true;
            }

            //Two-square start
            if (toCol == fromCol &&
                toRow - fromRow == 2 * direction &&
                fromRow == startRow &&
                board.isEmpty(toRow, toCol) &&
                board.isEmpty((fromRow + direction), toCol)) {
                return true;
            }

            //diagonal capture
            if (Math.abs(toCol - fromCol) == 1 &&
                toRow - fromRow == direction &&
                board.hasEnemyPiece(toRow, toCol, color)){
                return true;
            }

            //En passant capture
            if (Math.abs(toCol - fromCol) == 1 &&
                toRow - fromRow == direction &&
                board.isEnPassantSquare(toRow, toCol) &&
                board.isEmpty(toRow, toCol)){
                return true;
            }

            return false;
    }

    private boolean isValidKingMove(int fromRow, int fromCol, int toRow, int toCol, Board board) {

            int rowDiff = Math.abs(toRow - fromRow);
            int colDiff = Math.abs(toCol - fromCol);
            
            if ((rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0)){
                return  isValidToSquare(toRow, toCol, board);
            }

            if (rowDiff == 0 && colDiff == 2) {
                if (hasMoved()) return false;
                if (board.isKingInCheck(color, board)) return false;
                if (toCol > fromCol) {
                    return canCastleKingSide(fromRow, fromCol, board);

                } else {
                    return canCastleQueenSide(fromRow, fromCol, board);
                }
            }
            return false;

          
            
    }

    private boolean isValidBishopMove(int fromRow, int fromCol, int toRow, int toCol, Board board) {

        int colDiff = Math.abs(fromCol - toCol);
        int rowDiff =Math.abs(fromRow - toRow);
        if (colDiff == rowDiff && rowDiff != 0) {
            
            int rowDirection = (toRow - fromRow) / rowDiff;
            int colDirection = (toCol - fromCol) / colDiff;
            for (int i = 1; i < colDiff; i++){
                
                if (!board.isEmpty(fromRow + i*rowDirection, fromCol + i*colDirection)) {
                    return false;
                }
            }

            if (isValidToSquare(toRow, toCol, board)) {
                return true;
            }
            
            return false;
        }

            return false;

    }
    
    private boolean isValidRookMove(int fromRow, int fromCol, int toRow, int toCol, Board board) {

        int rowDiff = Math.abs(fromRow - toRow);
        int colDiff = Math.abs(fromCol - toCol);

            if (fromRow != toRow && fromCol != toCol) {
                    return false;
                }

            if (fromCol == toCol && rowDiff > 0) {
                int rowDirection = (toRow - fromRow)/rowDiff;
                for (int i=1; i < rowDiff; i++){
                    if (!(board.isEmpty(fromRow + i * rowDirection, toCol))) {
                        return false;
                    }
                }
            }

            if (fromRow == toRow && colDiff > 0) {
                int colDirection = (toCol - fromCol)/colDiff;
                for (int i=1; i < colDiff; i++){
                    if (!board.isEmpty(toRow, fromCol + i * colDirection)) {
                        return false;
                    }
                }

            }

            
            if (isValidToSquare(toRow, toCol, board)) {
                return true;
            }
            

            return false;
    }
    
    private boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol, Board board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if ( colDiff == 2 && rowDiff == 1 || colDiff == 1 && rowDiff == 2) {
                return isValidToSquare(toRow, toCol, board);
            }

        return false;

    }

    private boolean isValidToSquare (int toRow, int toCol, Board board) {
        Piece target = board.getPiece (toRow, toCol);
        return target == null || target.getColor() != color;
    }

    public boolean canAttackSquare(int fromRow, int fromCol, int toRow, int toCol, Board board){
        switch (pieceType) {
        case PAWN:
            return isValidPawnMove(fromRow, fromCol, toRow, toCol, board);

        case ROOK:
            return isValidRookMove(fromRow, fromCol, toRow, toCol, board);

        case BISHOP:
            return isValidBishopMove(fromRow, fromCol, toRow, toCol, board);

        case KNIGHT:
            return isValidKnightMove(fromRow, fromCol, toRow, toCol, board);

        case QUEEN:
            return isValidBishopMove(fromRow, fromCol, toRow, toCol, board)
                || isValidRookMove(fromRow, fromCol, toRow, toCol, board);

        case KING:
            
            int rowDiff = Math.abs(toRow - fromRow);
            int colDiff = Math.abs(toCol - fromCol);
            return rowDiff <= 1 && colDiff <= 1;
    }

    return false;

    }

    public boolean canCastleKingSide(int kingRow, int kingCol, Board board) {
        if (hasMoved) return false;

        Piece rook = board.getPiece(kingRow, 7);
        if (rook == null || rook.getPieceType() != Type.ROOK || rook.hasMoved()) return false;

        for (int col = kingCol + 1; col < 7; col++) {
            if (!board.isEmpty(kingRow, col)) return false;
        }

        for (int col = kingCol; col <= kingCol + 2; col++) {
            if (board.squareUnderAttack(kingRow, col, color)) return false;
        }

        return true;
    }

    public boolean canCastleQueenSide(int kingRow, int kingCol, Board board) {
        if (hasMoved) return false;

        Piece rook = board.getPiece(kingRow, 0);
        if (rook == null || rook.getPieceType() != Type.ROOK || rook.hasMoved()) return false;

    
        for (int col = kingCol - 1; col > 0; col--) {
        if (!board.isEmpty(kingRow, col)) return false;
        }

        for (int col = kingCol; col >= kingCol - 2; col--) {
        if (board.squareUnderAttack(kingRow, col, color)) return false;
        }

        return true;
    }
}
        
      
    
    

