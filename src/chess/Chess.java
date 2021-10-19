package chess;

import chess.board.Board;

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        // Testing toString in board
        Board board = new Board();
        System.out.println(board);
        System.out.println();

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        // Testing Basic Movement of Pieces
        int i = 0;
        while (true) {
            if(i%2 == 0) System.out.print("White's move: ");
            else System.out.print("Black's move: ");

            String[] input = in.nextLine().split(" ");
            int x0 = input[0].charAt(0)-'a';
            int y0 = Character.digit(input[0].charAt(1), 10)-1;
            int x = input[1].charAt(0)-'a';
            int y = Character.digit(input[1].charAt(1), 10)-1;

            if(board.getPiece(x0,y0) == null || ((i%2 == 0) ^ board.getPiece(x0,y0).isWhite()) || !board.movePiece(x0, y0, x, y)){
                System.out.println("Illegal move, try again");
                continue;
            }
            System.out.println();
            System.out.println(board);
            System.out.println();
            ++i;
        }
    }
}
