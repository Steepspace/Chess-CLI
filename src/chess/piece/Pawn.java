package chess.piece;

public class Pawn extends Piece{
    public Pawn(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "p";
    }
}
