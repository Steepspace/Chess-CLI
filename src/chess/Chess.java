package chess;

import chess.board.Board;
import chess.piece.Pawn;

import java.util.Scanner;
import java.io.File;

/**
 * Application class which takes in user input for moves and processes them.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Chess {

    /**
     * main method of the driver class which runs the application.
     * @param args command line arguments provided (expected none)
     */
    public static void main(String[] args) {
        // Testing toString in board
        Board board = new Board();
        System.out.println(board);
        System.out.println();

        // Using Scanner to get user input
        Scanner in = new Scanner(System.in);

        // Testing Basic Movement of Pieces
        int i = 0;
        while (true) {
            boolean white = i%2 == 0;
            if(white) System.out.print("White's move: ");
            else System.out.print("Black's move: ");

            String[] input = in.nextLine().split(" ");

            // check for resignation
            if(input[0].equals("resign")){
                if(white) System.out.println("Black wins");
                else System.out.println("White wins");
                break;
            }

            int x0 = input[0].charAt(0)-'a';
            int y0 = Character.digit(input[0].charAt(1), 10)-1;
            int x = input[1].charAt(0)-'a';
            int y = Character.digit(input[1].charAt(1), 10)-1;

            Board virtual = new Board(board);

            if(virtual.getPiece(x0,y0) == null || white != virtual.getPiece(x0,y0).isWhite() || !virtual.movePiece(x0, y0, x, y) || virtual.isCheck(virtual.getPiece(x,y).isWhite())){
                System.out.println("Illegal move, try again");
                continue;
            }

            // Check for draw
            if(input.length == 3 && input[2].equals("draw?")){
                if(white) System.out.print("Black's move: ");
                else System.out.print("White's move: ");
                in.nextLine();
                System.out.println("draw");
                break;
            }

            // Check for promotion
            if(virtual.getPiece(x,y) instanceof Pawn && (y == 7 || y == 0)){
                char type = 'Q';
                if(input.length == 3) type = input[2].charAt(0);
                virtual.promote(x, y, white, type);
            }

            board = virtual;

            System.out.println();
            System.out.println(board);
            System.out.println();
            ++i;
            if(board.isCheck(!white)){
                if(board.isCheckMate(!white)) {
                    System.out.println("Checkmate");
                    if(white) System.out.println("White wins");
                    else System.out.println("Black wins");
                    break;
                }
                else System.out.println("Check");
            }
        }

        // // Using Scanner for reading from file
        // Scanner in = null;

        // try {
        //     in = new Scanner(new File("moves.txt"));
        // } catch (Exception e) {}

        // // Testing Basic Movement of Pieces
        // int i = 0;
        // while (in.hasNextLine()) {
        //     boolean white = i%2 == 0;
        //     if(white) System.out.print("White's move: ");
        //     else System.out.print("Black's move: ");

        //     String[] input = in.nextLine().split(" ");
        //     int x0 = input[0].charAt(0)-'a';
        //     int y0 = Character.digit(input[0].charAt(1), 10)-1;
        //     int x = input[1].charAt(0)-'a';
        //     int y = Character.digit(input[1].charAt(1), 10)-1;

        //     System.out.println(input[0] + " " + input[1]);

        //     Board virtual = new Board(board);

        //     if(virtual.getPiece(x0,y0) == null || white != virtual.getPiece(x0,y0).isWhite() || !virtual.movePiece(x0, y0, x, y) || virtual.isCheck(virtual.getPiece(x,y).isWhite())){
        //         System.out.println("Illegal move, try again");
        //         continue;
        //     }

        //     // Check for promotion
        //     if(virtual.getPiece(x,y) instanceof Pawn && (y == 7 || y == 0)){
        //         char type = 'Q';
        //         if(input.length == 3) type = input[2].charAt(0);
        //         virtual.promote(x, y, white, type);
        //     }

        //     board = virtual;

        //     System.out.println();
        //     System.out.println(board);
        //     System.out.println();
        //     ++i;
        //     if(board.isCheck(!white)){
        //         if(board.isCheckMate(!white)) {
        //             System.out.println("Checkmate");
        //             if(white) System.out.println("White wins");
        //             else System.out.println("Black wins");
        //             break;
        //         }
        //         else System.out.println("Check");
        //     }
        // }
    }
}
