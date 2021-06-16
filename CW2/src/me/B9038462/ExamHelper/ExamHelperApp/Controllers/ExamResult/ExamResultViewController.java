package me.B9038462.ExamHelper.ExamHelperApp.Controllers.ExamResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.B9038462.ExamHelper.ExamHelperApp.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamResult;
import me.B9038462.ExamHelper.ExamHelperApp.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamQuestionRepository.ExamQuestionRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamResultRepository.ExamResultRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ExamResultViewController implements Initializable {
    private ExamResultRepository examResultRepository;
    private ExamQuestionRepository examQuestionRepository;

    private ObservableList<ExamResult> examResults;
    private ObservableList<ExamQuestion> examQuestions;

    private Exam exam;
    @FXML
    private TableView resultStoreTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void initExam(Exam exam) {
        this.examResultRepository = new ExamResultRepository();
        this.examQuestionRepository = new ExamQuestionRepository();

        this.examResults = FXCollections.observableArrayList();
        this.examQuestions = FXCollections.observableArrayList();


        try {
            examQuestions.addAll(examQuestionRepository.getAll());
            examResults.addAll(examResultRepository.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.exam = exam;
        this.createTableView();
    }
    private void createTableView() {
        ObservableList<ExamResult> filteredResults = examResults.stream()
                .filter(examResult -> examResult.getExamID() == this.exam.getID())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        resultStoreTableView.setItems(filteredResults);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("ID"));

        TableColumn examIDCol = new TableColumn("ExamID");
        examIDCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("ExamID"));

        TableColumn markCol = new TableColumn("TotalPoints");
        markCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("TotalPoints"));

        resultStoreTableView.getColumns().addAll(idCol, examIDCol, markCol);
    }
}
