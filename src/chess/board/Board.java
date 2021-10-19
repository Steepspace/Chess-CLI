package chess.board;

import chess.piece.*;

public class Board {
    private Piece[][] board;

    public Board(){
        this.board = new Piece[8][8];
        reset();
    }

    public void reset(){
        // White
        for(int i = 0; i < 8; ++i) this.board[i][1] = new Pawn(true);

        this.board[0][0] = new Rook(true);
        this.board[7][0] = new Rook(true);

        this.board[1][0] = new Knight(true);
        this.board[6][0] = new Knight(true);

        this.board[2][0] = new Bishop(true);
        this.board[5][0] = new Bishop(true);

        this.board[3][0] = new Queen(true);
        this.board[4][0] = new King(true);

        // Black
        for(int i = 0; i < 8; ++i) this.board[i][6] = new Pawn(false);

        this.board[0][7] = new Rook(false);
        this.board[7][7] = new Rook(false);

        this.board[1][7] = new Knight(false);
        this.board[6][7] = new Knight(false);

        this.board[2][7] = new Bishop(false);
        this.board[5][7] = new Bishop(false);

        this.board[3][7] = new Queen(false);
        this.board[4][7] = new King(false);

        // rest of the board is empty
        for(int x = 0; x < 8; ++x){
            for(int y = 2; y < 6; ++y) this.board[x][y] = null;
        }
        updateReach();
    }

    public Piece getPiece(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7) return null;
        return this.board[x][y];
    }

    public boolean movePiece(int x0, int y0, int x, int y){
        Piece piece = this.board[x0][y0];
        if(piece == null) return false;

        if(piece.canReach(x,y)){
            // check if enpassant capture is taking place
            if(piece instanceof Pawn && y0 != y && this.board[x][y] == null){
                if(piece.isWhite()) this.board[x][y-1] = null;
                else this.board[x][y+1] = null;
            }
            this.board[x][y] = piece;
            this.board[x0][y0] = null;
            // to ensure that pawns cannot travel two squares forward multiple times
            // to ensure that casteling cannot happen if either the king or rooks has moved
            if(!piece.isMoved()) piece.setMoved();
            // if a pawn has moved 2 squares forward then put an enpassant flag on it
            // checking if adjacent piece is also a pawn of opposite color
            if(piece instanceof Pawn && Math.abs(y0-y) == 2 &&
               ((this.getPiece(x-1, y) instanceof Pawn && (this.board[x-1][y].isWhite() ^ piece.isWhite())) ||
                (this.getPiece(x+1, y) instanceof Pawn && (this.board[x+1][y].isWhite() ^ piece.isWhite()))))
                ((Pawn)piece).setEnpassant(true);
            updateReach();
            // next time the same piece cannot be added for enpassant since the move is only avaliable for one turn
            if(piece instanceof Pawn) ((Pawn)piece).setEnpassant(false);
            return true;
        }

        return false;
    }

    private void updateReach(){
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Piece piece = this.board[i][j];
                if(piece != null) piece.updateReach(i, j, this);
            }
        }
    }

    public String toString(){
        String board_s = "";
        for (int i = 7; i >= 0; --i) {
            for (int j = 0; j < 8; ++j) {
                if(this.board[j][i] == null){
                    if((i+j) % 2 == 0) board_s += "## ";
                    else board_s += "   ";
                }
                else board_s += this.board[j][i].toString() + " ";
            }
            board_s += i+1+"\n";
        }
        for (char i = 'a'; i < 'i'; ++i) board_s += " "+i+" ";
        return board_s;
    }
}
