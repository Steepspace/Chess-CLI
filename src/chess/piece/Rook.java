package chess.piece;

import chess.board.Board;

/**
 * Governs the state of the rook.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Rook extends Piece{
    /**
     * Constructor
     * @param white the color of the piece
     */
    public Rook(boolean white){
        super(white);
    }

    /**
     * Copy Constructor
     * @param rook the rook object to be copied
     */
    public Rook(Rook rook){
        super(rook);
    }

    /**
     * Update the reach of the piece given the location of the piece and the Board state.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param board Board object which contains all the pieces and their locations
     */
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

    /**
     * @return the pieces id
     */
    public String toString(){
        return super.toString() + "R";
    }
}
