package chess.board;

import chess.piece.Piece;

public class Cell {
    private Piece piece;

    public Cell(Piece piece){
        this.piece = piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public String toString(){
        if(piece == null) return null;
        return this.piece.toString();
    }
}
