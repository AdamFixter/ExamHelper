package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Application.Controllers.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.QuestionMarkType;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.QuestionType;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.QuestionRepository.QuestionRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {

    private QuestionRepository questionRepository;
    private ObservableList<Question> questions;

    @FXML private TableView questionTable;
    @FXML private TextField questionDescription;
    @FXML private TextField questionAnswer;
    @FXML private TextField questionTag;
    @FXML private TextField questionPoints;
    @FXML private ComboBox questionType;
    @FXML private Button cloneQuestionBtn;
    @FXML private Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.questionRepository = new QuestionRepository();
        this.questions = FXCollections.observableArrayList();

        try {
            questions.addAll(questionRepository.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.createTableView();

        this.questionType.getItems().addAll(QuestionType.values());
    }

    private void createTableView() {
        questionTable.setItems(questions);

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

        TableColumn answer = new TableColumn("Answer");
        answer.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Answer"));

        questionTable.getColumns().addAll(idCol, descriptionCol, typeCol, markTypeCol, points, answer);
    }

    public void createQuestion(ActionEvent actionEvent) {
        Question question = new Question(
                this.questions.size() + 1,
                questionDescription.getText(),
                questionAnswer.getText(),
                QuestionType.valueOf(questionType.getValue().toString()),
                QuestionMarkType.AUTO,
                Integer.parseInt(questionPoints.getText()));
        questionRepository.create(question);

        this.questions.add(question);
    }

    public void addTagsPopup(ActionEvent actionEvent) {
    }

    public void clearTags(ActionEvent actionEvent) {
    }

    public void cloneQuestion(ActionEvent actionEvent) {
        Question questionClone = ((Question) questionTable.getSelectionModel().getSelectedItem());
        if (questionClone != null) {
            questionDescription.setText(questionClone.getDescription());
            questionAnswer.setText(questionClone.getAnswer());
            questionType.setValue(questionClone.getType());
            questionPoints.setText(String.valueOf(questionClone.getPoints()));

            questionRepository.delete(questionClone);
        } else {
            errorLbl.setText("You need to select a question to clone");
        }
    }
}
