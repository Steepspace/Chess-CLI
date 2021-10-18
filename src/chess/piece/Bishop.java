package chess.piece;

import chess.board.Board;

public class Bishop extends Piece{
    public Bishop(boolean white){
        super(white);
    }

    public void updateReach(int x, int y, Board board){
        reach.clear();
        // update topright
        setReach(x, y, 1, 1, board);

        // update bottomright
        setReach(x, y, 1, -1, board);

        // update bottomleft
        setReach(x, y, -1, -1, board);

        // update topleft
        setReach(x, y, -1, 1, board);
    }

    public String toString(){
        return super.toString() + "B";
    }
}
