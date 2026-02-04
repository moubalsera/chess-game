public class Piece {
    public enum Color { WHITE, BLACK }
    public enum Type { KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN }

    //instance fields
    private final Color color;
    private final Type pieceType;
  
    //constructor
    public Piece(Color color, Type pieceType){
        this.color = color;
        this.pieceType = pieceType;

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
    
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Board board){
        if  (pieceType == Type.PAWN) {
            //WHITE_PAWN_ROW = 6;
            //BLACK_PAWN_ROW = 1;
            //check bounds
            if (toCol < 0 || toCol > 7 || toRow < 0 || toRow > 7){
                return false;
            }
            //move forward
            int direction = (color == Color.BLACK) ? 1 : -1;
            if ( toRow - fromRow == direction && toCol == fromCol && board.isEmpty(toRow, toCol)){
                return true;
            }
            //starting move
            int startRow = (color == Color.BLACK) ? 1 : 6;
            if (toCol == fromCol && toRow - fromRow == 2 * direction && fromRow == startRow && board.isEmpty(toRow, toCol) && board.isEmpty((fromRow + direction), toCol)){
                return true;
            }

            //diagonal capture
            if (Math.abs(toCol - fromCol) == 1 && toRow - fromRow == direction && board.hasEnemyPiece(toRow, toCol, color)){
                return true;
            }

            //Pawn promotion
            //En passant capture

            return false;
            

            }
        return false;
            
        
    }
    }

