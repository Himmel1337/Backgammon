package cz.mendelu.pjj.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

import java.util.*;

public class Game {
    static Map<ImageView, ChessPiece> chessPieces = new HashMap<>();

    public static final int minPosition = 0;
    public static final int maxPosition = 25;

    private static IntegerProperty diceNumber = new SimpleIntegerProperty();

    private static Random random = new Random();

    public static int roll() {
        return random.nextInt(6) + 1;
    }

    public static void move(ImageView imageView, int changePozition, int positionY) {
        ChessPiece chessPiece = chessPieces.get(imageView);
        if (chessPiece.color() == Color.White){
            if (changePozition == 25) chessPieces.remove(imageView);
        } else if (chessPiece.color() == Color.Black){
            if (changePozition == 0) chessPieces.remove(imageView);
        }
        chessPiece.setPositionX(changePozition);
        chessPiece.setPositionY(positionY);
    }


    public static void putChess(ImageView imageView, ChessPiece chessPiece) {
       chessPieces.put(imageView, chessPiece);
    }

    /**
     * The function checks if there is a chess piece in position x
     * @param changedPosition position to be checked
     * @return If there is no chess piece of the in position x, return True, if there is, return False
     */

    public static boolean canMove(int changedPosition){
        if (changedPosition >= minPosition && changedPosition <= maxPosition) return true;
        return false;
    }

}

