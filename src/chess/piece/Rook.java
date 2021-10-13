package chess.piece;

public class Rook extends Piece{
    public Rook(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "R";
    }
}
