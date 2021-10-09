package chess.board;

import chess.piece.*;

public class Board {
    private Cell[][] board;

    public Board(){
        this.board = new Cell[8][8];
        reset();
    }

    public void reset(){
        // White
        for(int i = 0; i < 8; ++i) this.board[i][1] = new Cell(new Pawn(true));

        this.board[0][0] = new Cell(new Rook(true));
        this.board[7][0] = new Cell(new Rook(true));

        this.board[1][0] = new Cell(new Knight(true));
        this.board[6][0] = new Cell(new Knight(true));

        this.board[2][0] = new Cell(new Bishop(true));
        this.board[5][0] = new Cell(new Bishop(true));

        this.board[3][0] = new Cell(new Queen(true));
        this.board[4][0] = new Cell(new King(true));

        // Black
        for(int i = 0; i < 8; ++i) this.board[i][6] = new Cell(new Pawn(false));

        this.board[0][7] = new Cell(new Rook(false));
        this.board[7][7] = new Cell(new Rook(false));

        this.board[1][7] = new Cell(new Knight(false));
        this.board[6][7] = new Cell(new Knight(false));

        this.board[2][7] = new Cell(new Bishop(false));
        this.board[5][7] = new Cell(new Bishop(false));

        this.board[3][7] = new Cell(new Queen(false));
        this.board[4][7] = new Cell(new King(false));

        // rest of the board is empty
        for(int x = 0; x < 8; ++x){
            for(int y = 2; y < 6; ++y) this.board[x][y] = new Cell(null);
        }
    }

    public Cell getCell(int x, int y){
        return this.board[x][y];
    }

    public String toString(){
        String board_s = "";
        for (int i = 7; i >= 0; --i) {
            for (int j = 0; j < 8; ++j) {
                if(this.board[j][i].getPiece() == null){
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
