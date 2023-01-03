package cz.mendelu.pjj.project;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;

    private static Random random = new Random();

    public Game(){
        this.board = new Board();
    }

    public static int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * The turn of the game with checking and changing the position of the chess piece
     * @param color player color, where to go now
     */
    public void turn(Color color){

        boolean checkTurn = true;
        int dice1 = roll();
        int dice2 = roll();
        Scanner in = new Scanner(System.in);
        int choseChess = 0;
        int choseDice = 0;
        int position = 0;
        ChessPiece chessPiece = null;
        System.out.println(color + " player's move");
        System.out.println("Dice 1: " + dice1 + " Dice 2: " + dice2);

        while(checkTurn){
            boolean сheckChose = true;

            while(сheckChose){
                System.out.println("Choose сhess piece (1-15): ");
                choseChess = in.nextInt();
                if (choseChess >= 1 && choseChess <= 15)  {
                    сheckChose = false;
                    if(color == Color.White){
                        chessPiece = board.chessPiecesWhite().get(choseChess - 1);
                    } else if(color == Color.Black) {
                        chessPiece = board.chessPiecesBlack().get(choseChess - 1);
                    } else {
                        throw new IllegalArgumentException("Error: color is not exist");
                    }

                    if(chessPiece.statusWin() == true) {
                        сheckChose = true;
                        System.out.println("The chess piece is already in the winning position");
                    }
                }
                else System.out.println("Wrong choose сhess piece");
            }

            сheckChose = true;

            while(сheckChose){
                System.out.println("Choose dices (1-2): ");
                choseDice = in.nextInt();
                if (choseDice >= 1 && choseDice <= 2)  сheckChose = false;
                else System.out.println("Wrong choose dice");

                if(color == Color.White){
                    chessPiece = board.chessPiecesWhite().get(choseChess - 1);
                } else if(color == Color.Black) {
                    chessPiece = board.chessPiecesBlack().get(choseChess - 1);
                } else {
                    throw new IllegalArgumentException("Error: color is not exist");
                }

                if(chessPiece.statusWin() == true) {
                    сheckChose = true;
                    System.out.println("The chess piece is already in the winning position");
                }
            }

            position = chessPiece.positionX();

            position -= (choseDice == 1 ? dice1 : dice2);

            if (position < board.minPosition()) {
                position = board.maxPosition() + position;
            }

            if(board.canMove(position, color) == true){
                board.move(chessPiece, position);
                checkTurn = false;
            }
        }

        checkTurn = true;

        while (checkTurn) {

            boolean сheckChose = true;
            System.out.println("Second dice");

            while(сheckChose){
                System.out.println("Choose сhess piece (1-15): ");
                choseChess = in.nextInt();
                if (choseChess >= 1 && choseChess <= 15)  сheckChose = false;
                else System.out.println("Wrong choose сhess piece");
            }

            if(color == Color.White){
                chessPiece = board.chessPiecesWhite().get(choseChess - 1);
                position = chessPiece.positionX();
            } else {
                chessPiece = board.chessPiecesBlack().get(choseChess - 1);
                position = chessPiece.positionX();
            }

            position -= (choseDice == 1 ? dice2 : dice1);

            if (position < board.minPosition()) position = board.maxPosition() + position;

            if(board.canMove(position, color) == true){
                board.move(chessPiece, position);
                checkTurn = false;
                System.out.println("End turn");
            }
        }
    }

    /**
     * Check if the player has won
     * @param color player color, where to go now to check victory
     */
    public boolean win(Color color){
        if (color == Color.White){
            int winWhite = 0;
            for (ChessPiece chess : this.board.chessPiecesWhite()){
                if (chess.statusWin() == true){
                    winWhite++;
                }
            }
            if (winWhite == this.board.countChessPiece()){
                System.out.println("White win");
                return true;
            }
        } else if (color == Color.Black){
            int winBlack = 0;
            for (ChessPiece chess : this.board.chessPiecesBlack()){
                if (chess.statusWin() == true){
                    winBlack++;
                }
            }
            if (winBlack == this.board.countChessPiece()){
                System.out.println("Black win");
                return true;
            }
        } else {
            throw new IllegalArgumentException("Color is not exist");
        }
        return false;
    }

    public Board board() {
        return board;
    }

//    public static void main(String[] args){
//        Game game = new Game();
//        boolean win = false;
//        while(win == false){
//            game.turn(Color.White);
//            game.turn(Color.Black);
//            if (game.win(Color.White) == true) win = true;
//            if (game.win(Color.Black) == true) win = true;
//        }
//
//    }
}
