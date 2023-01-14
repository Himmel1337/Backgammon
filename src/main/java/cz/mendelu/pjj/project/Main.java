package cz.mendelu.pjj.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Pane pane;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
//        Parent root = loader.load();
        pane = (Pane) loader.getNamespace().get("pane");
//        root.setId("pane");
        primaryStage.setTitle("Backgamon");
        Scene scene = new Scene(loader.load(), 1024, 768);
//        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}