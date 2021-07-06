package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Application.Controllers.Exam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamQuestionRepository.ExamQuestionRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamRepository.ExamRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.QuestionRepository.QuestionRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;
    private ExamQuestionRepository examQuestionRepository;

    private ObservableList<Exam> exams;
    private ObservableList<ExamQuestion> examQuestions;
    private ObservableList<Question> questionsForExam;
    private ObservableList<Question> questions;

    @FXML private TableView questionStoreTableView;
    @FXML private TableView examQuestionStoreTableView;
    @FXML private TextField examName;
    @FXML private TextField questionDescriptionFilter;

    @FXML private Button addQuestionBtn;
    @FXML private Button removeQuestionBtn;
    @FXML private Button createExamBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.examRepository = new ExamRepository();
        this.questionRepository = new QuestionRepository();
        this.examQuestionRepository = new ExamQuestionRepository();

        this.exams = FXCollections.observableArrayList();
        this.examQuestions = FXCollections.observableArrayList();
        this.questionsForExam = FXCollections.observableArrayList();
        this.questions = FXCollections.observableArrayList();


        try {
            exams.addAll(examRepository.getAll());
            examQuestions.addAll(examQuestionRepository.getAll());
            questions.addAll(questionRepository.getAll());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.createQuestionTable(questionStoreTableView, questions);
        this.createQuestionTable(examQuestionStoreTableView, questionsForExam);

        //Add filter -> https://www.youtube.com/watch?v=FeTrcNBVWtg
        FilteredList<Question> filteredData = new FilteredList(this.questions, p -> true);
        this.questionDescriptionFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((question) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                System.out.println(question.getDescription().contains(newValue.toLowerCase()));
                return question.getDescription().contains(newValue.toLowerCase());
            });
        });

        SortedList<Question> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.questionStoreTableView.comparatorProperty());
        this.questionStoreTableView.setItems(sortedData);
    }

    public void createQuestionTable(TableView table, ObservableList<Question> questions) {
        table.setItems(questions);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("ID"));

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Description"));

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Type"));

        TableColumn markTypeCol = new TableColumn("Mark Type");
        markTypeCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("MarkType"));

        TableColumn points = new TableColumn("Points");
        points.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Points"));


        table.getColumns().addAll(idCol, descriptionCol, typeCol, markTypeCol, points);
    }


    public void createExam(ActionEvent actionEvent) {
        Exam exam = new Exam(this.exams.size() + 1, examName.getText());
        examRepository.create(exam);

        for (int i = 0; i < examQuestionStoreTableView.getItems().size(); i++) {
            Question question = (Question) examQuestionStoreTableView.getItems().get(i);

            ExamQuestion examQuestion = new ExamQuestion(this.examQuestions.size() + i, exam.getID(), question.getID());
            examQuestionRepository.create(examQuestion);
        }
        this.exams.add(exam);
    }

    public void addQuestionToExam(ActionEvent actionEvent) {
        Question question = (Question)this.questionStoreTableView.getSelectionModel().getSelectedItem();
        if (question != null) {
            this.questions.remove(question);
            this.questionsForExam.add(question);
        }
    }

    public void removeQuestionFromExam(ActionEvent actionEvent) {
        Question question = (Question)this.examQuestionStoreTableView.getSelectionModel().getSelectedItem();
        if (question != null) {
            this.questionsForExam.remove(question);
            this.questions.add(question);
        }
    }
}
