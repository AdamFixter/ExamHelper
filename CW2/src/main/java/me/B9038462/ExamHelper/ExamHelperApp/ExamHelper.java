package me.B9038462.ExamHelper.ExamHelperApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;

public class ExamHelper extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Setup db
        DatabaseManager dm = DatabaseManager.getManager();
        dm.init();

        Parent root = FXMLLoader.load(getClass().getResource("Presentation/Views/main.fxml"));
        primaryStage.setTitle("Exam Helper");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
