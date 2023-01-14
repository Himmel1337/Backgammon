package cz.mendelu.pjj.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ControllerTest {

    Game game;
    int diceNumber1 = 0;
    int diceNumber2 = 0;
    ImageView im1 = new ImageView();
    ChessPiece chessPiece = new ChessPiece(Color.Black, 1, 1, 1, im1);
    Map<ImageView, ChessPiece> chessPieces = new HashMap<>();


    @org.junit.Test
    public void switchDice() {
        onRollClick();
        int x1 = diceNumber1;
        int x2 = diceNumber2;
        diceNumber1 = x2;
        diceNumber2 = x1;
        assertEquals(x2, diceNumber1);
        assertEquals(x1, diceNumber2);
    }

    @org.junit.Test
    public void onRollClick() {
        int x1 = Game.roll();
        int x2 = Game.roll();
        diceNumber1 = x1;
        diceNumber2 = x2;
        assertEquals(x1, diceNumber1);
        assertEquals(x2, diceNumber2);
    }

    @org.junit.Test
    public void turn() {
        chessPieces.put(im1, chessPiece);

        ChessPiece c = chessPieces.get(im1);
        c.setPositionX(5);
        assertEquals(5, c.positionX());
    }
}