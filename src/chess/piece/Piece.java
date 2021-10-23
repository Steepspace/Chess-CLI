package chess.piece;

import chess.board.Board;
import java.util.ArrayList;

public abstract class Piece{
    private boolean white;
    // store n
    // x = n % 8
    // y = n / 8
    protected ArrayList<Integer> reach;
    private boolean moved;

    public Piece(boolean white){
        this.white = white;
        this.reach = new ArrayList<>();
        this.moved = false;
    }

    public Piece(Piece piece){
        this.white = piece.isWhite();
        this.reach = new ArrayList<Integer>(piece.getReach());
        this.moved = piece.isMoved();
    }

    public boolean isWhite(){
        return this.white;
    }

    public boolean isMoved(){
        return this.moved;
    }

    public void setMoved(){
        this.moved = true;
    }
    
    //contains logic to determine pieces move set
    //public abstract boolean checkMove(int x, int y);

    public abstract void updateReach(int x, int y, Board board);

    public boolean canReach(int x, int y){
       int n = x+y*8;

       for(int v : reach){
           if(n == v) return true;
       }
       return false;
    }

    /*
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
     * @return
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

    public ArrayList<Integer> getReach(){
        return this.reach;
    }

    public String toString(){
        if(this.white){
            return "w";
        }
        return "b";
    }
}
