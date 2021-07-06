package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories;

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
