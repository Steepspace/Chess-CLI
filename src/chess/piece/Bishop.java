package chess.piece;

import chess.board.Board;

/**
 * Governs the state of the bishop.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Bishop extends Piece{

    /**
     * Constructor
     * @param white the color of the piece
     */
    public Bishop(boolean white){
        super(white);
    }

    /**
     * Copy Constructor
     * @param bishop the bishop object to be copied
     */
    public Bishop(Bishop bishop){
        super(bishop);
    }

    /**
     * Update the reach of the piece given the location of the piece and the Board state.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param board Board object which contains all the pieces and their locations
     */
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

    /**
     * @return the pieces id
     */
    public String toString(){
        return super.toString() + "B";
    }
}
