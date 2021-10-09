package chess.piece;

public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
    }

    public String toString(){
        return super.toString() + "N";
    }
}
