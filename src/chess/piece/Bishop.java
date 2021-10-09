package chess.piece;

public class Bishop extends Piece{
    public Bishop(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "B";
    }
}
