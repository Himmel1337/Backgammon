package cz.mendelu.pjj.project;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @org.junit.Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @org.junit.Test
    public void canMove() {
        assertTrue(board.canMove(5, Color.White));
        assertTrue(board.canMove(8, Color.Black));
        assertFalse(board.canMove(12, Color.Black));
        assertFalse(board.canMove(24, Color.White));
    }

    @org.junit.Test
    public void move() {
        board.move(board.chessPiecesWhite().get(0), 12);
        assertEquals(12, board.chessPiecesWhite().get(0).positionX());
        board.move(board.chessPiecesWhite().get(0), 6);
        assertEquals(6, board.chessPiecesWhite().get(0).positionX());

        board.move(board.chessPiecesBlack().get(0), 12);
        assertEquals(24, board.chessPiecesBlack().get(0).positionX());
        board.move(board.chessPiecesBlack().get(0), 15);
        assertEquals(15, board.chessPiecesBlack().get(0).positionX());
    }
}