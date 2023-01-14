package cz.mendelu.pjj.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class Game {
    static Map<ImageView, ChessPiece> chessPieces = new HashMap<>();

    public static final int minPosition = 1;
    public static final int maxPosition = 24;
    public static final int countChessPiece = 15;

    private static IntegerProperty diceNumber = new SimpleIntegerProperty();
    public IntegerProperty getDiceNumber(){return diceNumber;};

    private static Random random = new Random();

    public static int roll() {
        return random.nextInt(6) + 1;
    }

    public static void posun(ImageView imageView) {
        ChessPiece chessPiece = chessPieces.get(imageView);

        System.out.println(chessPiece.positionX());
        System.out.println("id: " + chessPiece.id());

        if (chessPiece.color() == Color.White) {
            chessPiece.setPositionX(chessPiece.positionX() + 1);
        } else if (chessPiece.color() == Color.Black) {
            chessPiece.setPositionX(chessPiece.positionX() - 1);
        }

        System.out.println(chessPiece.positionX());
    }



//    public static void move(ChessPiece chessPiece, int position){
//
//    }

    /**
     * Check if the player has won
     * @param color player color, where to go now to check victory
     */
    public boolean win(Color color){
        if (color == Color.White){
            int winWhite = 0;
            for (ChessPiece chess : chessPieces.values()){
                if (chess.statusWin() == true){
                    winWhite++;
                }
            }
            if (winWhite == this.countChessPiece){
                System.out.println("White win");
                return true;
            }
        } else if (color == Color.Black){
            int winBlack = 0;
            for (ChessPiece chess : chessPieces.values()){
                if (chess.statusWin() == true){
                    winBlack++;
                }
            }
            if (winBlack == this.countChessPiece){
                System.out.println("Black win");
                return true;
            }
        } else {
            throw new IllegalArgumentException("Color is not exist");
        }
        return false;
    }

    public static void putChess(ImageView imageView, ChessPiece chessPiece) {
       chessPieces.put(imageView, chessPiece);
    }

    /**
     * The function checks if there is a chess piece of the opposite color in position x
     * @param changedPosition position to be checked
     * @return If there is no chess piece of the opposite color in position x, return True, if there is, return False
     */

    public static boolean canMove(int changedPosition){
        if (changedPosition >= minPosition && changedPosition <= maxPosition) return true;
        return false;
    }

    public static Map<ImageView, ChessPiece> chessPiecesWhite() {
        return chessPieces;
    }

    /**
     * The turn of the game with checking and changing the position of the chess piece
     * @param color player color, where to go now
     */
    public static void turn(Color color){

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
                        chessPiece = chessPieces.get(choseChess - 1);
                    } else if(color == Color.Black) {
                        chessPiece = chessPieces.get(choseChess - 1);
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
                    chessPiece = chessPieces.get(choseChess - 1);
                } else if(color == Color.Black) {
                    chessPiece = chessPieces.get(choseChess - 1);
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

            if (position < minPosition) {
                position = maxPosition + position;
            }

            if(canMove(position) == true){
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
                chessPiece = chessPieces.get(choseChess - 1);
                position = chessPiece.positionX();
            } else {
                chessPiece = chessPieces.get(choseChess - 1);
                position = chessPiece.positionX();
            }

            position -= (choseDice == 1 ? dice2 : dice1);

            if (position < minPosition) position = maxPosition + position;

            if(canMove(position) == true){
                checkTurn = false;
                System.out.println("End turn");
            }
        }
    }


}

