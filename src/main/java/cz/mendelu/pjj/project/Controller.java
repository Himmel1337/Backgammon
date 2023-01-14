package cz.mendelu.pjj.project;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    @FXML
    private Label dice1, dice2, winWhite, winBlack;
    int countWinWhite = 0, countWinBlack = 0;

    private static IntegerProperty diceNumber1 = new SimpleIntegerProperty();
    private static IntegerProperty diceNumber2 = new SimpleIntegerProperty();

    ArrayList<Double> pozitionX = new ArrayList<Double>(
            List.of(212.0, 259.5, 307.5, 354.0, 401.0, 448.5, 528.0, 576.0, 624.0, 670.0, 716.0, 765.0));

    ArrayList<Double> pozitionLowY = new ArrayList<Double>(
            List.of(636.0, 588.0, 540.0, 492.0, 444.0));

    ArrayList<Double> pozitionHiY = new ArrayList<Double>(
            List.of(84.0, 132.0, 180.0, 228.0, 276.0));

    private double whiteWinCordinate = 100.0;
    private double blackWinCordinateY = 600.0;


    @FXML
    private ImageView white1, white2, white3, white4, white5, white6, white7, white8, white9, white10, white11, white12, white13, white14, white15;

    @FXML
    private ImageView black1, black2, black3, black4, black5, black6, black7, black8, black9, black10, black11, black12, black13, black14, black15;

    @FXML
    protected void onRollClick() {
        int x1 = Game.roll();
        int x2 = Game.roll();
        diceNumber1.setValue(x1);
        diceNumber2.setValue(x2);
        dice1.setText(String.valueOf(x1));
        dice2.setText(String.valueOf(x2));
    }

    @FXML
    protected void clickFigure(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        onTranslateTransition(imageView);
    }

    @FXML
    private void onTranslateTransition(ImageView imageView) {
        TranslateTransition tt =
                new TranslateTransition(Duration.millis(1000),
                        imageView);

        ChessPiece chessPiece = Game.chessPieces.get(imageView);
        int howChangePozition = 0;
        
        if (diceNumber1.get() != 0) {
            howChangePozition = diceNumber1.get();
            diceNumber1.setValue(0);
        } else if (diceNumber2.get() != 0) {
            howChangePozition = diceNumber2.get();
            diceNumber2.setValue(0);
        } else {
            System.out.println("Please roll dice");
        }

        if (howChangePozition != 0) {
            int changePozition = 0;
            int countChess = 0;
            double y1 = 0;
            boolean anotherChess = true;

            if (chessPiece.color() == Color.White) changePozition = chessPiece.positionX() + howChangePozition;
            else if (chessPiece.color() == Color.Black) changePozition = chessPiece.positionX() - howChangePozition;


            if (Game.canMove(changePozition)) {

                for (ChessPiece c : Game.chessPieces.values()) {
                    if (c.positionX() == (changePozition)) {
                        countChess++;
                        if (c.color() != chessPiece.color()) {
                            anotherChess = false;
                        }
                    }
                }
                if (countChess < 5 && anotherChess == true) {


                    System.out.println(howChangePozition);

                    if (changePozition >= 13) y1 = pozitionHiY.get(countChess);
                    else if (changePozition <= 12) y1 = pozitionLowY.get(countChess);

                    imageView.setY(y1);
                    if (chessPiece.color() == Color.White) {
                        if (changePozition == 25) {
                            imageView.setX(whiteWinCordinate);
                            imageView.setY(whiteWinCordinate);
                            countWinWhite++;
                            winWhite.setText(String.valueOf(countWinWhite));
                        } else if (chessPiece.positionX() <= 12 && changePozition > 12) {
                            if (changePozition == 13) {
                                imageView.setX(pozitionX.get(0));
                            } else {
                                imageView.setX(pozitionX.get(howChangePozition - 1));
                            }
                        } else if (chessPiece.positionX() > 12) {
                            double x1 = pozitionX.get(pozitionX.indexOf(imageView.getX()) + howChangePozition);
                            imageView.setX(x1);
                        } else if (chessPiece.positionX() < 12) {
                            double x1 = pozitionX.get(pozitionX.indexOf(imageView.getX()) - howChangePozition);
                            imageView.setX(x1);
                        }
                    } else if (chessPiece.color() == Color.Black) {
                        if (changePozition == 0) {
                            imageView.setX(whiteWinCordinate);
                            imageView.setY(blackWinCordinateY);
                            countWinBlack++;
                            winBlack.setText(String.valueOf(countWinBlack));
                        } else if (chessPiece.positionX() >= 13 && changePozition < 13) {
                            if (changePozition == 12) {
                                imageView.setX(pozitionX.get(0));
                            } else {
                                imageView.setX(pozitionX.get(howChangePozition - 1));
                            }
                        } else if (chessPiece.positionX() > 13) {
                            double x1 = pozitionX.get(pozitionX.indexOf(imageView.getX()) - howChangePozition);
                            imageView.setX(x1);
                        } else if (chessPiece.positionX() < 13) {
                            double x1 = pozitionX.get(pozitionX.indexOf(imageView.getX()) + howChangePozition);
                            imageView.setX(x1);
                        }
                    }
                    Game.posun(imageView, changePozition);
                } else {
                    System.out.println("Wrong turn");
                }
            } else {
                System.out.println("Wrong turn");
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Game.putChess(white1, new ChessPiece(Color.White, 12, 1, 1, white1));
        Game.putChess(white2, new ChessPiece(Color.White, 12, 2, 2, white2));
        Game.putChess(white3, new ChessPiece(Color.White, 12, 3, 3, white3));
        Game.putChess(white4, new ChessPiece(Color.White, 12, 4, 4, white4));
        Game.putChess(white5, new ChessPiece(Color.White, 12, 5, 5, white5));
        Game.putChess(white6, new ChessPiece(Color.White, 1, 1, 6, white6));
        Game.putChess(white7, new ChessPiece(Color.White, 1, 2, 7, white7));
        Game.putChess(white8, new ChessPiece(Color.White, 19, 1, 8, white8));
        Game.putChess(white9, new ChessPiece(Color.White, 19, 2, 9, white9));
        Game.putChess(white10, new ChessPiece(Color.White, 19, 3, 10, white10));
        Game.putChess(white11, new ChessPiece(Color.White, 19, 4, 11, white11));
        Game.putChess(white12, new ChessPiece(Color.White, 19, 5, 12, white12));
        Game.putChess(white13, new ChessPiece(Color.White, 17, 1, 13, white13));
        Game.putChess(white14, new ChessPiece(Color.White, 17, 2, 14, white14));
        Game.putChess(white15, new ChessPiece(Color.White, 17, 3, 15, white15));

        Game.putChess(black1, new ChessPiece(Color.Black, 13, 1, 16, black1));
        Game.putChess(black2, new ChessPiece(Color.Black, 13, 2, 17, black2));
        Game.putChess(black3, new ChessPiece(Color.Black, 13, 3, 18, black3));
        Game.putChess(black4, new ChessPiece(Color.Black, 13, 4, 19, black4));
        Game.putChess(black5, new ChessPiece(Color.Black, 13, 5, 20, black5));
        Game.putChess(black6, new ChessPiece(Color.Black, 24, 1, 21, black6));
        Game.putChess(black7, new ChessPiece(Color.Black, 24, 2, 22, black7));
        Game.putChess(black8, new ChessPiece(Color.Black, 6, 1, 23, black8));
        Game.putChess(black9, new ChessPiece(Color.Black, 6, 2, 24, black9));
        Game.putChess(black10, new ChessPiece(Color.Black, 6, 3, 25, black10));
        Game.putChess(black11, new ChessPiece(Color.Black, 6, 4, 26, black11));
        Game.putChess(black12, new ChessPiece(Color.Black, 6, 5, 27, black12));
        Game.putChess(black13, new ChessPiece(Color.Black, 8, 1, 28, black13));
        Game.putChess(black14, new ChessPiece(Color.Black, 8, 2, 29, black14));
        Game.putChess(black15, new ChessPiece(Color.Black, 8, 3, 30, black15));
    }
}