package chess.piece;

import chess.board.Board;

public class Rook extends Piece{
    public Rook(boolean white){
        super(white);
    }

    public void updateReach(int x, int y, Board board){
        reach.clear();
        // update top
        setReach(x, y, 0, 1, board);

        // update right
        setReach(x, y, 1, 0, board);

        // update bottom
        setReach(x, y, 0, -1, board);

        // update left
        setReach(x, y, -1, 0, board);
    }

    public String toString(){
        return super.toString() + "R";
    }
}
