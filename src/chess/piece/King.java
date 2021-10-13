package chess.piece;

public class King extends Piece{
    public King(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "K";
    }
}
