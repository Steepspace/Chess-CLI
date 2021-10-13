package chess.piece;

public class Queen extends Piece{
    public Queen(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "Q";
    }
}
