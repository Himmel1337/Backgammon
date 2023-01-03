package cz.mendelu.pjj.project;

public class ChessPiece {
    private Color color;
    private int positionX;
    private int positionY;
    private boolean statusWin;
    private boolean statusMove;

    ChessPiece(Color color, int positionX, int positionY){
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.statusWin = false;
        this.statusMove = false;
    }

    public Color color() {
        return color;
    }

    public int positionX() {
        return positionX;
    }

    public int positionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getColor(){
        if(this.color == Color.White){
            return "white";
        } else {
            return "black";
        }
    }

    public boolean statusWin() {
        return statusWin;
    }

    public void setStatusWin() {
        this.statusWin = true;
    }

    public boolean statusMove() {
        return statusMove;
    }

    public void setStatusMove() {
        this.statusMove = true;
    }
}
