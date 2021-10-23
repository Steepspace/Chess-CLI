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
    public boolean canCastle(int x, int y, Board board, boolean right){
        if (isMoved())
            return false;

        if (right) {
            if (board.getPiece(7, y) instanceof Rook && !board.getPiece(7, y).isMoved() && board.getPiece(5, y) == null && board.getPiece(6, y) == null) {
                for (int i = 0; i < 8; ++i) {
                    for (int j = 0; j < 8; ++j) {
                        Piece piece = board.getPiece(i, j);
                        if (piece != null && piece.isWhite() != isWhite()
                                && (piece.canReach(5, y) || piece.canReach(6, y)))
                            return false;
                    }
                }
            }
        }
        else{
            if (board.getPiece(0, y) instanceof Rook && !board.getPiece(0, y).isMoved() && board.getPiece(1, y) == null && board.getPiece(2, y) == null && board.getPiece(3, y) == null) {
                for (int i = 0; i < 8; ++i) {
                    for (int j = 0; j < 8; ++j) {
                        Piece piece = board.getPiece(i, j);
                        if (piece != null && piece.isWhite() != isWhite()
                                && (piece.canReach(2, y) || piece.canReach(3, y)))
                            return false;
                    }
                }
            }
        }
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

       // add casteling
       // right
       if(canCastle(x, y, board, true)) reach.add((x+2)+y*8);
       // left
       if(canCastle(x, y, board, false)) reach.add((x-2)+y*8);
    }

    public String toString(){
        return super.toString() + "K";
    }
}
