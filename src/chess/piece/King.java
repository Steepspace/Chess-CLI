package chess.piece;

import chess.board.Board;

public class King extends Piece{
    public King(boolean white){
        super(white);
    }

    public King(King king){
        super(king);
    }

    // ADD MORE LOGIC
    public boolean canCastle(){
        if(!isMoved()) return false;
        return true;
    }

    public void updateReach(int x, int y, Board board){
        reach.clear();
       // right
       if(y+1 < 8 && (board.getPiece(x, y+1) == null || (board.getPiece(x, y+1).isWhite() ^ this.isWhite()))) reach.add((x)+(y+1)*8);
       if(x+1 < 8 && y+1 < 8 && (board.getPiece(x+1, y+1) == null || (board.getPiece(x+1, y+1).isWhite() ^ this.isWhite()))) reach.add((x+1)+(y+1)*8);
       if(x+1 < 8 && (board.getPiece(x+1, y) == null || (board.getPiece(x+1, y).isWhite() ^ this.isWhite()))) reach.add((x+1)+(y)*8);
       if(x+1 < 8 && y-1 > -1 && (board.getPiece(x+1, y-1) == null || (board.getPiece(x+1, y-1).isWhite() ^ this.isWhite()))) reach.add((x+1)+(y-1)*8);
       //left
       if(y-1 > -1 && (board.getPiece(x, y-1) == null || (board.getPiece(x, y-1).isWhite() ^ this.isWhite()))) reach.add((x)+(y-1)*8);
       if(x-1 > -1 && y-1 > -1 && (board.getPiece(x-1, y-1) == null || (board.getPiece(x-1, y-1).isWhite() ^ this.isWhite()))) reach.add((x-1)+(y-1)*8);
       if(x-1 > -1 && (board.getPiece(x-1, y) == null || (board.getPiece(x-1, y).isWhite() ^ this.isWhite()))) reach.add((x-1)+(y)*8);
       if(x-1 > -1 && y+1 < 8 && (board.getPiece(x-1, y+1) == null || (board.getPiece(x-1, y+1).isWhite() ^ this.isWhite()))) reach.add((x-1)+(y+1)*8);
    }

    public String toString(){
        return super.toString() + "K";
    }
}
