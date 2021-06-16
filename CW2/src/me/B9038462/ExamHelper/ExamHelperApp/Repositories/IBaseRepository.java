package me.B9038462.ExamHelper.ExamHelperApp.Repositories;

import me.B9038462.ExamHelper.ExamHelperApp.Models.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IBaseRepository<T>  {
    ArrayList<T> getAll() throws SQLException;
    T getById(int ID) throws SQLException;
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    void save();
}
