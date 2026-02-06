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
        //check bounds
        if (toCol < 0 || toCol > 7 || toRow < 0 || toRow > 7){
            return false;
            }

        if  (pieceType == Type.PAWN) {
            
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

        if (pieceType == Type.KING) {

            int rowDiff = Math.abs(toRow - fromRow);
            int colDiff = Math.abs(toCol - fromCol);
            
            if ((rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0)){
                return  board.isEmpty(toRow, toCol) ||
                        board.hasEnemyPiece(toRow, toCol, color);
            }
            return false;
            
        }

        if (pieceType == Type.BISHOP) {
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

                Piece target = board.getPiece(toRow, toCol);
                if (target == null ||
                    target.getColor() != color ) {
                        return true;
                    }
                
                return false;
            }

            return false;

        }
        
        if (pieceType == Type.ROOK) {
            // to be implemented in the next session

                }
            



        }
        
        return false;
    
    }
        
    }    
    
    

