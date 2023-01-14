package cz.mendelu.pjj.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

import java.util.*;

public class Game {
    static Map<ImageView, ChessPiece> chessPieces = new HashMap<>();

    public static final int minPosition = 0;
    public static final int maxPosition = 25;
    public static final int countChessPiece = 15;

    private static IntegerProperty diceNumber = new SimpleIntegerProperty();
    public IntegerProperty getDiceNumber(){return diceNumber;};

    private static Random random = new Random();

    public static int roll() {
        return random.nextInt(6) + 1;
    }

    public static void turn(ImageView imageView, int changePozition) {
        ChessPiece chessPiece = chessPieces.get(imageView);
        if (chessPiece.color() == Color.White){
            if (changePozition == 25) chessPieces.remove(imageView);
        } else if (chessPiece.color() == Color.Black){
            if (changePozition == 0) chessPieces.remove(imageView);
        }
        chessPiece.setPositionX(changePozition);
    }


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

}

