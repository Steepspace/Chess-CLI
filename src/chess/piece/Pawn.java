package chess.piece;

import chess.board.Board;

public class Pawn extends Piece{
    private boolean enpassant;
    public Pawn(boolean white){
        super(white);
        this.enpassant = false;
    }

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

    public boolean isEnpassant(){
        return this.enpassant;
    }

    public void setEnpassant(boolean enpassant){
       this.enpassant = enpassant;
    }

    public String toString(){
        return super.toString() + "p";
    }
}
