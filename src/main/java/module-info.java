module com.example.Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens cz.mendelu.pjj.project to javafx.fxml;
    exports cz.mendelu.pjj.project;
}