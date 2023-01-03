package cz.mendelu.pjj.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onRollClick() {welcomeText.setText(String.valueOf(Game.roll()));}
}