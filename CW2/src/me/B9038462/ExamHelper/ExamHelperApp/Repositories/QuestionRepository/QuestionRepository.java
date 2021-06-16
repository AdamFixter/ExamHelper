package me.B9038462.ExamHelper.ExamHelperApp.Repositories.QuestionRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Database.QueryBuilder.Query;
import me.B9038462.ExamHelper.ExamHelperApp.Models.*;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.BaseRepository;

public class QuestionRepository extends BaseRepository<Question> implements IQuestionRepository<Question> {
    public QuestionRepository() {
        super(Question.class, DatabaseManager.getManager().getExamHelperDB(), "QuestionStore");
    }

    @Override
    public void create(Question entity) {
        new Query(DatabaseManager.getManager().getExamHelperDB().getSQLConnection()).insert("QuestionStore")
                .insert("ID, Description, Type, MarkType, Points")
                .value(entity.getID())
                .value(entity.getDescription())
                .value(entity.getType().ordinal())
                .value(entity.getMarkType().ordinal())
                .value(entity.getPoints())
                .execute();
    }
}
