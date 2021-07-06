package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Application.Controllers.Exam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Application.Controllers.ExamResult.ExamResultViewController;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamQuestionRepository.ExamQuestionRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamRepository.ExamRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExamViewController implements Initializable {

    private ExamRepository examRepository;
    private ExamQuestionRepository examQuestionRepository;

    private ObservableList<Exam> exams;
    private ObservableList<ExamQuestion> examQuestions;


    @FXML private TableView examStoreTableView;
    @FXML private Label errorLbl;
    @FXML private Button createExamBtn;
    @FXML private Button editExamBtn;
    @FXML private Button startExamBtn;
    @FXML private Button viewExamResultBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.examRepository = new ExamRepository();
        this.examQuestionRepository = new ExamQuestionRepository();

        this.exams = FXCollections.observableArrayList();
        this.examQuestions = FXCollections.observableArrayList();


        try {
            examQuestions.addAll(examQuestionRepository.getAll());
            exams.addAll(examRepository.getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.createTableView();



//        //table.getSelectionModel().selectedItemProperty().get();
    }

    private void createTableView() {
        examStoreTableView.setItems(exams);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("ID"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Name"));

        examStoreTableView.getColumns().addAll(idCol, nameCol);
    }


    public void createExam(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("me/B9038462/ExamHelper/ExamHelperApp/Presentation/Views/Exam/create.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Create Exam");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void editExam(ActionEvent actionEvent) {
        errorLbl.setText("Coming Soon");
    }

    public void startExam(ActionEvent actionEvent) {
        Exam exam = (Exam)this.examStoreTableView.getSelectionModel().getSelectedItem();
        if (exam != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("me/B9038462/ExamHelper/ExamHelperApp/Presentation/Views/Exam/main.fxml"));

                Scene scene = new Scene(loader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setTitle("Exam");
                stage.setScene(scene);

                ExamMainController controller = loader.getController();
                controller.initExam(exam);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            errorLbl.setText("Please select an exam");
        }
    }

    public void viewExamResult(ActionEvent actionEvent) {
        Exam exam = (Exam)this.examStoreTableView.getSelectionModel().getSelectedItem();
        if (exam != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("me/B9038462/ExamHelper/ExamHelperApp/Presentation/Views/ExamResult/view.fxml"));

                Scene scene = new Scene(loader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setTitle("View Exam");
                stage.setScene(scene);

                ExamResultViewController controller = loader.getController();
                controller.initExam(exam);

                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            errorLbl.setText("Please select an exam");
        }
    }
}
