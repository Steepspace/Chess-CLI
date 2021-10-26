package chess.board;

import chess.piece.*;

/**
 * This class handles the pieces on the board as well as manage valid moves including identification of check and checkmate.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Board {
    /**
     * holds all of the pieces at the respective location the board.
     */
    private Piece[][] board;

    /**
     * Default constructor creates a new board using 2D Piece array of size 8x8 and then calls reset() with sets the board to the inital state.
     */
    public Board(){
        this.board = new Piece[8][8];
        reset();
    }

    /**
     * Copy Constructor
     * @param board the specific board object to be copied thoroughly including the state of each piece whether it has moved or not
     */
    public Board(Board board){
        this.board = new Piece[8][8];

        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Piece piece = board.getPiece(i,j);
                if(piece instanceof Pawn) this.board[i][j] = new Pawn((Pawn)piece);
                if(piece instanceof Knight) this.board[i][j] = new Knight((Knight)piece);
                if(piece instanceof Bishop) this.board[i][j] = new Bishop((Bishop)piece);
                if(piece instanceof Rook) this.board[i][j] = new Rook((Rook)piece);
                if(piece instanceof Queen) this.board[i][j] = new Queen((Queen)piece);
                if(piece instanceof King) this.board[i][j] = new King((King)piece);
            }
        }
    }

    /**
     * resets the board to the inital state by placing the pieces in their respective starting positions as required by the rules of chess.
     */
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

    /**
     * Returns the Piece at the specified location on the board.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @return the specific piece at the location or null if the board coordinates are out of bound.
     */
    public Piece getPiece(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7) return null;
        return this.board[x][y];
    }

    /**
     * Promotes the pawn to the desired piece.
     * @param x x-coordinate on the board
     * @param y y-coordinate on the board
     * @param white (true) white, (false) black
     * @param type type of piece to promote to. Possible options are 'N', 'R', 'B'. Default queen is implied.
     */
    public void promote(int x, int y, boolean white, char type){
        switch(type){
        case 'R': this.board[x][y] = new Rook(white);
            break;
        case 'N': this.board[x][y] = new Knight(white);
            break;
        case 'B': this.board[x][y] = new Bishop(white);
            break;
            default: this.board[x][y] = new Queen(white);
        }
        updateReach();
    }

    /**
     * Move piece from initial cooridate to final coordiante if the move is legal.
     * @param x0 x-coordinate of the location to move from on the board
     * @param y0 y-coordinate of the location to move from on the board
     * @param x x-coordinate of the location to move to on the board
     * @param y y-coordinate of the location to move to on the board
     * @return true if the move is legal and false if the move is illegal
     */
    public boolean movePiece(int x0, int y0, int x, int y){
        Piece piece = this.board[x0][y0];
        if(piece == null) return false;

        if(piece.canReach(x,y)){
            // check if enpassant capture is taking place
            if(piece instanceof Pawn && this.board[x][y] == null){
                if(piece.isWhite()) this.board[x][y-1] = null;
                else this.board[x][y+1] = null;
            }
            // check if casteling is taking place
            if(piece instanceof King && Math.abs(x-x0) == 2){
                // right
                // move rook into place
                if(x > x0){
                    this.board[5][y] = this.board[7][y];
                    this.board[7][y] = null;
                }
                // left
                // move rook into place
                else{
                    this.board[3][y] = this.board[0][y];
                    this.board[0][y] = null;
                }
            }
            //move the piece to the new square
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

    /**
     * Helper method to update the reach of all pieces on the board.
     */
    private void updateReach(){
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Piece piece = this.board[i][j];
                if(piece != null) piece.updateReach(i, j, this);
            }
        }
    }

    /**
     * To check whether the king of a player is in check.
     * @param white (true) check if white's king is in check, (false) check if black's king is in check.
     * @return true if the king is in check or false if the king is not in check.
     */
    public boolean isCheck(boolean white){
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Piece piece = this.board[i][j];
                if(piece != null && piece.isWhite() != white){
                    for(int n : piece.getReach()){
                        if(this.board[n%8][n/8] instanceof King) return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * To check whether the king of a player checkmated.
     * @param white (true) check if white cannot stop check, (false) check if black cannot stop check.
     * @return true if the player cannot block check or false otherwise
     */
    public boolean isCheckMate(boolean white){
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Piece piece = this.board[i][j];
                if(piece != null && piece.isWhite() == white){
                    for(int n : piece.getReach()){
                        Board virtual = new Board(this);
                        virtual.movePiece(i, j, n%8, n/8);
                        if(!virtual.isCheck(white)) return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return print the board with all of it's pieces on the terminal.
     */
    public String toString(){
        String board_s = "";
        for (int i = 7; i >= 0; --i) {
            for (int j = 0; j < 8; ++j) {
                if(this.board[j][i] == null){
                    if((i+j) % 2 == 0) board_s += "## ";
                    else board_s += "   ";
                } else board_s += this.board[j][i].toString() + " ";
            }
            board_s += i+1+"\n";
        }
        for (char i = 'a'; i < 'i'; ++i) board_s += " "+i+" ";
        return board_s;
    }
}
