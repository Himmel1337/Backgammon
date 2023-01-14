package cz.mendelu.pjj.project;

import javafx.scene.image.ImageView;

public class ChessPiece {
    private Color color;
    private int positionX;
    private int positionY;
    private int id;

    private ImageView imageView;

    ChessPiece(Color color, int positionX, int positionY, int id, ImageView imageView){
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.id = id;
        this.imageView = imageView;
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


    public int id() {
        return id;
    }
}
