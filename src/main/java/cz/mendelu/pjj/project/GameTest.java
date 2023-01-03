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
        for(ChessPiece chess : game.board().chessPiecesWhite()){
            chess.setStatusWin();
        }
        assertTrue(game.win(Color.White));
        assertFalse(game.win(Color.Black));
        for(ChessPiece chess : game.board().chessPiecesBlack()){
            chess.setStatusWin();
        }
        assertTrue(game.win(Color.Black));
    }


    @org.junit.Test
    public void turn() {

        int inputUser = 1;
        int position = 0;
        position = game.board().homeWhitePosition() - game.roll();
        game.board().move(game.board().chessPiecesWhite().get(inputUser), position);

        inputUser = 2;
        position = game.board().homeWhitePosition() - game.roll();
        game.board().move(game.board().chessPiecesWhite().get(inputUser), position);

        int n  = 0;
        for(ChessPiece chess : game.board().chessPiecesWhite()){
            if (game.board().homeWhitePosition() == chess.positionX()){
                n++;
            }
        }

        assertEquals(game.board().countChessPiece() - 2, n, 1);

        inputUser = 3;
        position = game.board().homeBlackPosition() - game.roll();
        game.board().move(game.board().chessPiecesBlack().get(inputUser), position);

        inputUser = 4;
        position = game.board().homeBlackPosition() - game.roll();
        game.board().move(game.board().chessPiecesBlack().get(inputUser), position);
        n = 0;
        for(ChessPiece chess : game.board().chessPiecesBlack()){
            if (game.board().homeBlackPosition() == chess.positionX()){
                n++;
            }
        }
        assertEquals(game.board().countChessPiece() - 2, n, 1);
    }
}