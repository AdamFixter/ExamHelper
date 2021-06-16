package me.B9038462.ExamHelper.ExamHelperApp.Controllers.Exam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.B9038462.ExamHelper.ExamHelperApp.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamResult;
import me.B9038462.ExamHelper.ExamHelperApp.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamQuestionRepository.ExamQuestionRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamResultRepository.ExamResultRepository;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.QuestionRepository.QuestionRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExamMainController implements Initializable {

    private QuestionRepository questionRepository;
    private ExamQuestionRepository examQuestionRepository;
    private ExamResultRepository examResultRepository;

    private ArrayList<ExamQuestion> examQuestions;
    private ArrayList<Question> questions;
    private ArrayList<ExamResult> examResults;

    private Exam exam;
    private int currentQuestion = 1;
    private int totalPoints;

    @FXML private Label questionDescriptionLbl;
    @FXML private Button finishExamBtn;
    @FXML private Button nextQuestionBtn;
    @FXML private TextField answerTextField;

    @FXML private VBox answerBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void initExam(Exam exam) {
        this.examQuestions = new ArrayList<ExamQuestion>();
        this.questions = new ArrayList<Question>();
        this.exam = exam;

        this.questionRepository = new QuestionRepository();
        this.examQuestionRepository = new ExamQuestionRepository();
        this.examResultRepository = new ExamResultRepository();

        try {
            this.examResults = this.examResultRepository.getAll();
            this.examQuestionRepository.getAll().stream()
                    .filter(examQuestion -> examQuestion.getExamID() == this.exam.getID())
                    .forEach(examQuestion -> {
                        try {
                            Question question = this.questionRepository.getById(examQuestion.getQuestionID());
                            this.questions.add(question);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.formatQuestion(this.currentQuestion);
    }

    private void formatQuestion(int questionIndex) {
        boolean isLastQuestion = this.currentQuestion >= this.questions.size();
        this.nextQuestionBtn.setDisable(isLastQuestion);
        this.finishExamBtn.setVisible(isLastQuestion);

        Question question = this.questions.get(questionIndex-1);
        questionDescriptionLbl.setText(question.getDescription());
    }
    private void markQuestion() {
        Question question = this.questions.get(this.currentQuestion - 1);
        String[] answers = question.getAnswer().split(",");

        for (String answer : answers) {
            if (this.answerTextField.getText().toLowerCase().equals(answer.toLowerCase())) {
                this.totalPoints += question.getPoints();
            }
            break;
        }

    }
    public void finishExam(ActionEvent actionEvent) {
        this.markQuestion();
        ExamResult examResult = new ExamResult(this.examResults.size() + 1, this.exam.getID(), this.totalPoints);
        this.examResultRepository.create(examResult);

        Stage stage = (Stage) this.finishExamBtn.getScene().getWindow();
        stage.close();
    }

    public void nextQuestion(ActionEvent actionEvent) {
        this.markQuestion();
        this.answerTextField.clear();
        this.currentQuestion = this.currentQuestion + 1;
        this.formatQuestion(this.currentQuestion);
    }


//    public void createExam(ActionEvent actionEvent) {
//        Exam exam = new Exam(this.exams.size() + 1, examName.getText());
//        examRepository.create(exam);
//
//        for (int i = 0; i < examQuestionStoreTableView.getItems().size(); i++) {
//            Question question = (Question) examQuestionStoreTableView.getItems().get(i);
//
//            ExamQuestion examQuestion = new ExamQuestion(this.examQuestions.size() + i, exam.getID(), question.getID());
//            examQuestionRepository.create(examQuestion);
//        }
//        this.exams.add(exam);
//    }

}
