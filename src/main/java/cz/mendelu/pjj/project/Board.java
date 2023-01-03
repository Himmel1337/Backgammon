package cz.mendelu.pjj.project;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<ChessPiece> chessPiecesWhite = new ArrayList<>();
    private List<ChessPiece> chessPiecesBlack = new ArrayList<>();

    private final int minPosition = 1;
    private final int maxPosition = 24;
    private final int homeWhitePosition = 12;
    private final int homeBlackPosition = 24;
    private final int countChessPiece = 15;
    public Board(){
        for(int i = 0; i < this.countChessPiece; i++){
            this.chessPiecesWhite.add(new ChessPiece(Color.White, this.homeWhitePosition, i));
            this.chessPiecesBlack.add(new ChessPiece(Color.Black, this.homeBlackPosition, i));
        }
    }

    /**
     * The function checks if there is a chess piece of the opposite color in position x
     * @param changedPosition position to be checked
     * @param color of the checked chess piece
     * @return If there is no chess piece of the opposite color in position x, return True, if there is, return False
     */

    public boolean canMove(int changedPosition, Color color){

        if(color == Color.White){
            for (ChessPiece c : chessPiecesBlack()){
                if (c.positionX() == changedPosition) return false;
            }
        } else if (color == Color.Black){
            for (ChessPiece c : chessPiecesWhite()){
                if (c.positionX() == changedPosition) return false;
            }
        } else {
            throw new IllegalArgumentException("Error: color is not exist");
        }
        return true;
    }

    /**
     * Procedure to change the position of the chess piece
     * @param chessPiece whose position needs to be changed
     * @param position you need to change the position
     */
    public void move(ChessPiece chessPiece, int position){
        int p = position;

        if (p < minPosition) throw new IllegalArgumentException("Error: position x < min");
        if (p > maxPosition) throw new IllegalArgumentException("Error: position x > max");

        if (canMove(p, chessPiece.color()) == true){
            int positionY = 0;
            if (chessPiece.color() == Color.White){

                    chessPiece.setPositionX(p);
                    chessPiece.setPositionY(0);

                    if (chessPiece.positionX() > this.homeWhitePosition) chessPiece.setStatusMove();

                    if (chessPiece.statusMove() == true){
                        if (chessPiece.positionX() <= this.homeWhitePosition) {
                            chessPiece.setStatusWin();
                            chessPiece.setPositionX(this.homeWhitePosition);
                        }
                    }

                for (ChessPiece c : chessPiecesWhite){
                    if (c.positionX() == chessPiece.positionX()) positionY++;
                }

            } else if (chessPiece.color() == Color.Black){

                    chessPiece.setPositionX(p);
                    chessPiece.setPositionY(0);

                if (chessPiece.positionX() < this.homeWhitePosition) chessPiece.setStatusMove();

                if (chessPiece.statusMove() == true){
                    if (chessPiece.positionX() <= this.homeBlackPosition && chessPiece.positionX() >= this.homeWhitePosition) {
                        chessPiece.setStatusWin();
                        chessPiece.setPositionX(this.homeBlackPosition);
                    }
                }

                for (ChessPiece c : chessPiecesBlack){
                    if (c.positionX() == chessPiece.positionX()) positionY++;
                }
            } else {
                throw new IllegalArgumentException("Error: color is not exist");
            }
            chessPiece.setPositionY(positionY);
        } else {
            System.out.println("Wrong move. This position has a chess piece of the opposite color");
        }
    }

    public List<ChessPiece> chessPiecesWhite() {
        return chessPiecesWhite;
    }

    public List<ChessPiece> chessPiecesBlack() {
        return chessPiecesBlack;
    }

    public int minPosition() {
        return minPosition;
    }

    public int maxPosition() {
        return maxPosition;
    }

    public int homeWhitePosition() {
        return homeWhitePosition;
    }

    public int homeBlackPosition() {
        return homeBlackPosition;
    }

    public int countChessPiece() { return countChessPiece; }
}
