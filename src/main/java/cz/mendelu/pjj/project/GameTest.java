package cz.mendelu.pjj.project;

import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @org.junit.Before
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @org.junit.Test
    public void win(){
        assertFalse(game.win(Color.White));
        for(ChessPiece chess : game.chessPieces.values()){
            chess.setStatusWin();
        }
        assertTrue(game.win(Color.White));
        assertFalse(game.win(Color.Black));
        for(ChessPiece chess : game.chessPieces.values()){
            chess.setStatusWin();
        }
        assertTrue(game.win(Color.Black));
    }


    @org.junit.Test
    public void turn() {
//
//        int inputUser = 1;
//        int position = 0;
//        position = game.homeWhitePosition - game.roll();
//        game.move(game.chessPiecesWhite().get(inputUser), position);
//
//        inputUser = 2;
//        position = game.homeWhitePosition - game.roll();
//        game.move(game.chessPiecesWhite().get(inputUser), position);
//
//        int n  = 0;
//        for(ChessPiece chess : game.chessPieces.values()){
//            if (game.homeWhitePosition == chess.positionX()){
//                n++;
//            }
//        }
//
//        assertEquals(game.countChessPiece - 2, n, 1);
//
//        inputUser = 3;
//        position = game.homeBlackPosition - game.roll();
//        game.move(game.chessPieces.get(inputUser), position);
//
//        inputUser = 4;
//        position = game.homeBlackPosition - game.roll();
//        game.move(game.chessPieces.get(inputUser), position);
//        n = 0;
//        for(ChessPiece chess : game.chessPieces.values()){
//            if (game.homeBlackPosition == chess.positionX()){
//                n++;
//            }
//        }
//        assertEquals(game.countChessPiece - 2, n, 1);
    }

    @org.junit.Test
    public void canMove() {
        assertTrue(game.canMove(5));
        assertTrue(game.canMove(8));
        assertFalse(game.canMove(12));
        assertFalse(game.canMove(24));
    }

    @org.junit.Test
    public void move() {
//        game.move(game.chessPiecesWhite().get(0), 12);
//        assertEquals(12, game.chessPiecesWhite().get(0).positionX());
//        game.move(game.chessPiecesWhite().get(0), 6);
//        assertEquals(6, game.chessPiecesWhite().get(0).positionX());
//
//        game.move(game.chessPieces.get(0), 12);
//        assertEquals(24, game.chessPieces.get(0).positionX());
//        game.move(game.chessPieces.get(0), 15);
//        assertEquals(15, game.chessPieces.get(0).positionX());
    }
}