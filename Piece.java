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
    
    }

