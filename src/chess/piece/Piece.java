package chess.piece;

public abstract class Piece{
    private boolean white;

    public Piece(boolean white){
        this.white = white;
    }

    public boolean isWhite(){
        return this.white;
    }
    
    //contains logic to determine pieces move set
    public abstract boolean checkMove();
    
    public String toString(){
        if(this.white){
            return "w";
        }
        return "b";
    }
}
