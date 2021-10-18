package chess.piece;

import chess.board.Board;

public class Queen extends Piece{
    public Queen(boolean white){
        super(white);
    }

    public void updateReach(int x, int y, Board board){
        reach.clear();
        // update top
        setReach(x, y, 0, 1, board);

        // update topright
        setReach(x, y, 1, 1, board);

        // update right
        setReach(x, y, 1, 0, board);

        // update bottomright
        setReach(x, y, 1, -1, board);

        // update bottom
        setReach(x, y, 0, -1, board);

        // update bottomleft
        setReach(x, y, -1, -1, board);

        // update left
        setReach(x, y, -1, 0, board);

        // update topleft
        setReach(x, y, -1, 1, board);
    }

    public String toString(){
        return super.toString() + "Q";
    }
}
