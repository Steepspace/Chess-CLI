package chess.piece;

import chess.board.Board;

public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
    }

    public Knight(Knight knight){
        super(knight);
    }

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

    public String toString(){
        return super.toString() + "N";
    }
}
