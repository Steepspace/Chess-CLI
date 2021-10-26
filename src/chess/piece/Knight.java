package chess.piece;

import chess.board.Board;

/**
 * Governs the state of the knight.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Knight extends Piece{
    /**
     * Constructor
     * @param white the color of the piece
     */
    public Knight(boolean white){
        super(white);
    }

    /**
     * Copy Constructor
     * @param knight the knight object to be copied
     */
    public Knight(Knight knight){
        super(knight);
    }

    /**
     * Update the reach of the piece given the location of the piece and the Board state.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param board Board object which contains all the pieces and their locations
     */
    public void updateReach(int x, int y, Board board){
       reach.clear();
       // right
       if(x+1 < 8 && y+2 < 8 && (board.getPiece(x+1, y+2) == null || (board.getPiece(x+1, y+2).isWhite() ^ this.isWhite()))) reach.add((x+1)+(y+2)*8);
       if(x+2 < 8 && y+1 < 8 && (board.getPiece(x+2, y+1) == null || (board.getPiece(x+2, y+1).isWhite() ^ this.isWhite()))) reach.add((x+2)+(y+1)*8);
       if(x+2 < 8 && y-1 > -1 && (board.getPiece(x+2, y-1) == null || (board.getPiece(x+2, y-1).isWhite() ^ this.isWhite()))) reach.add((x+2)+(y-1)*8);
       if(x+1 < 8 && y-2 > -1 && (board.getPiece(x+1, y-2) == null || (board.getPiece(x+1, y-2).isWhite() ^ this.isWhite()))) reach.add((x+1)+(y-2)*8);
       //left
       if(x-1 > -1 && y-2 > -1 && (board.getPiece(x-1, y-2) == null || (board.getPiece(x-1, y-2).isWhite() ^ this.isWhite()))) reach.add((x-1)+(y-2)*8);
       if(x-2 > -1 && y-1 > -1 && (board.getPiece(x-2, y-1) == null || (board.getPiece(x-2, y-1).isWhite() ^ this.isWhite()))) reach.add((x-2)+(y-1)*8);
       if(x-2 > -1 && y+1 < 8 && (board.getPiece(x-2, y+1) == null || (board.getPiece(x-2, y+1).isWhite() ^ this.isWhite()))) reach.add((x-2)+(y+1)*8);
       if(x-1 > -1 && y+2 < 8 && (board.getPiece(x-1, y+2) == null || (board.getPiece(x-1, y+2).isWhite() ^ this.isWhite()))) reach.add((x-1)+(y+2)*8);
    }

    /**
     * @return the pieces id
     */
    public String toString(){
        return super.toString() + "N";
    }
}
