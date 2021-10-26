package chess.piece;

import chess.board.Board;

/**
 * Governs the state of the pawn.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Pawn extends Piece{
    /**
     * true if the pawn can be captured through enpassant, false otherwise.
     */
    private boolean enpassant;
    /**
     * Constructor
     * @param white the color of the piece
     */
    public Pawn(boolean white){
        super(white);
        this.enpassant = false;
    }

    /**
     * Copy Constructor
     * @param pawn the pawn object to be copied
     */
    public Pawn(Pawn pawn){
        super(pawn);
        this.enpassant = pawn.isEnpassant();
    }

    /**
     * Update the reach of the piece given the location of the piece and the Board state.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param board Board object which contains all the pieces and their locations
     */
    public void updateReach(int x, int y, Board board){
        reach.clear();

        if(isWhite()){
            if(y+1 < 8){
                // can move 1 square forward
                if(board.getPiece(x,y+1) == null) reach.add(x+(y+1)*8);
                // can move 2 square forward
                if(!isMoved() && board.getPiece(x,y+2) == null) reach.add(x+(y+2)*8);

                // can capture left
                if(board.getPiece(x-1, y+1) != null && !board.getPiece(x-1, y+1).isWhite()) reach.add((x-1)+(y+1)*8);
                // can capture right
                if(board.getPiece(x+1, y+1) != null && !board.getPiece(x+1, y+1).isWhite()) reach.add((x+1)+(y+1)*8);

                //can capture with enpassant left
                if((board.getPiece(x-1, y) instanceof Pawn) && !board.getPiece(x-1, y).isWhite() && ((Pawn)board.getPiece(x-1, y)).isEnpassant()) reach.add((x-1)+(y+1)*8);
                //can capture with enpassant right
                if((board.getPiece(x+1, y) instanceof Pawn) && !board.getPiece(x+1, y).isWhite() && ((Pawn)board.getPiece(x+1, y)).isEnpassant()) reach.add((x+1)+(y+1)*8);
            }
        }

        else{
            if(y-1 > -1){
                // can move 1 square forward
                if(board.getPiece(x,y-1) == null) reach.add(x+(y-1)*8);
                // can move 2 square forward
                if(!isMoved() && board.getPiece(x,y-2) == null) reach.add(x+(y-2)*8);

                // can capture left
                if(board.getPiece(x-1, y-1) != null && board.getPiece(x-1, y-1).isWhite()) reach.add((x-1)+(y-1)*8);
                // can capture right
                if(board.getPiece(x+1, y-1) != null && board.getPiece(x+1, y-1).isWhite()) reach.add((x+1)+(y-1)*8);

                //can capture with enpassant left
                if((board.getPiece(x-1, y) instanceof Pawn) && board.getPiece(x-1, y).isWhite() && ((Pawn)board.getPiece(x-1, y)).isEnpassant()) reach.add((x-1)+(y-1)*8);
                //can capture with enpassant right
                if((board.getPiece(x+1, y) instanceof Pawn) && board.getPiece(x+1, y).isWhite() && ((Pawn)board.getPiece(x+1, y)).isEnpassant()) reach.add((x+1)+(y-1)*8);
            }
        }
    }

    /**
     * Get enpassant
     * @return true if the pawn can be captured using enpassant and false otherwise.
     */
    public boolean isEnpassant(){
        return this.enpassant;
    }

    /**
     * Set the pawn's enpassant status.
     * @param enpassant, true if the pawn can be captured using enpassant and false otherwise.
     */
    public void setEnpassant(boolean enpassant){
       this.enpassant = enpassant;
    }

    /**
     * @return the pieces id
     */
    public String toString(){
        return super.toString() + "p";
    }
}
