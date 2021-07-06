package me.B9038462.ExamHelper.ExamHelperApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;

public class ExamHelper extends Application {

//    - question types: (https://www.qualtrics.com/support/survey-platform/survey-module/editing-questions/question-types-guide/question-types-overview/)
//          - MULTIPLE_CHOICE
//          - RANK_ORDER
//    - a logon screen (Account)
//    - different views of the system (for teachers and pupils, permission based per account.)
//
//    Each scene must be given a permission code. PERMISSION_XXXX
//
//
//    SceneManager - Handles a list of scenes
//    Scene - Override javafx scene class and add permission attribute. (If user doesn't have permission. Throw error here)
//
//    If there is more time. Upgrade error/validation handling overall. Sqllite, Controllers

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
