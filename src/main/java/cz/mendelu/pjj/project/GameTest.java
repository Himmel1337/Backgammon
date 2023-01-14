package cz.mendelu.pjj.project;

import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {

    Game game;
    ImageView im1 = new ImageView();
    ChessPiece chessPiece = new ChessPiece(Color.Black, 1, 1, 1, im1);
    Map<ImageView, ChessPiece> chessPieces = new HashMap<>();




    @org.junit.Before
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @org.junit.Test
    public void canMove() {
        assertTrue(game.canMove(5));
        assertTrue(game.canMove(8));
        assertFalse(game.canMove(-1));
        assertFalse(game.canMove(26));
    }

    @org.junit.Test
    public void move() {

        chessPieces.put(im1, chessPiece);

        ChessPiece c = chessPieces.get(im1);
        c.setPositionX(5);
        assertEquals(5, c.positionX());
    }
}