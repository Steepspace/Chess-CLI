package chess.piece;

import chess.board.Board;
import java.util.ArrayList;

/**
 * Common functionality for all the pieces.
 * @author Apurva Narde
 * @author Max Geiger
 */
public abstract class Piece{

    /**
     * true if the piece is white and false if the piece is black
     */
    private boolean white;

    /**
     * Stores the vertices on the board to which the piece can reach
     * x = n % 8
     * y = n / 8
     */
    protected ArrayList<Integer> reach;

    /**
     * true if the piece has moved from the initial position or false otherwise
     */
    private boolean moved;

    /**
     * Constructor
     * @param white (true) to create white piece and (false) to create black piece
     */
    public Piece(boolean white){
        this.white = white;
        this.reach = new ArrayList<>();
        this.moved = false;
    }

    /**
     * Copy Constructor
     * @param piece piece object to copy
     */
    public Piece(Piece piece){
        this.white = piece.isWhite();
        this.reach = new ArrayList<Integer>(piece.getReach());
        this.moved = piece.isMoved();
    }

    /**
     * Get white
     * @return true if the piece is white or false if the piece is black
     */
    public boolean isWhite(){
        return this.white;
    }

    /**
     * Get moved
     * @return true of the piece has moved or false otherwise
     */
    public boolean isMoved(){
        return this.moved;
    }

    /**
     * Set moved to true to indicate that the piece has moved.
     */
    public void setMoved(){
        this.moved = true;
    }


    /**
     * Update the reach of the piece given the location of the piece and the Board state.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param board Board object which contains all the pieces and their locations
     */
    public abstract void updateReach(int x, int y, Board board);

    /**
     * Check if the piece can reach the given location.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @return true if the location is within the piece's reach or false otherwise.
     */
    public boolean canReach(int x, int y){
       int n = x+y*8;

       for(int v : reach){
           if(n == v) return true;
       }
       return false;
    }

    /**
     * Set the reach of the piece given location and the direction in which to check on the board. Used by Rook, Bishop and Queen.
     *
     * dx = 0, dy = 1: top
     * dx = 1, dy = 1: topright
     * dx = 1, dy = 0: right
     * dx = 1, dy = -1: bottomright
     * dx = 0, dy = -1: bottom
     * dx = -1, dy = -1: bottomleft
     * dx = -1, dy = 0: left
     * dx = -1, dy = 1: topleft
     *
     * @param x X coordiante of piece
     * @param y Y coordiante of piece
     * @param dx x-direction in which to move
     * @param dy y-direction in which to move
     * @param board The game board
     */
    protected void setReach(int x, int y, int dx, int dy, Board board){
        Piece piece = null;
        for(int i = x+dx, j = y+dy;; i += dx, j += dy){
            if(i == -1 || i == 8 || j == -1 || j == 8) break;
            piece = board.getPiece(i,j);
            if(piece == null || (piece.isWhite() ^ this.white)) reach.add(i+j*8);
            if(piece != null) break;
        }
    }

    /**
     * Get reach.
     * @return the reach which is an array list of integers.
     */
    public ArrayList<Integer> getReach(){
        return this.reach;
    }

    /**
     * @return the color of the piece
     */
    public String toString(){
        if(this.white){
            return "w";
        }
        return "b";
    }
}
