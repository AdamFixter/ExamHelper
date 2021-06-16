package me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamQuestionRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Database.QueryBuilder.Query;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.BaseRepository;

public class ExamQuestionRepository extends BaseRepository<ExamQuestion> implements IExamQuestionRepository<ExamQuestion> {
    public ExamQuestionRepository() {
        super(ExamQuestion.class, DatabaseManager.getManager().getExamHelperDB(), "ExamQuestion");
    }

    @Override
    public void create(ExamQuestion entity) {
        new Query(DatabaseManager.getManager().getExamHelperDB().getSQLConnection()).insert("ExamQuestion")
                .insert("ID, ExamID, QuestionID")
                .value(entity.getID())
                .value(entity.getExamID())
                .value(entity.getQuestionID())
                .execute();
    }
}
