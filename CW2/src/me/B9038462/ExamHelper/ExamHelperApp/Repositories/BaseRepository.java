package me.B9038462.ExamHelper.ExamHelperApp.Repositories;

import javafx.util.Pair;
import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseBuilder.Database;
import me.B9038462.ExamHelper.ExamHelperApp.Database.QueryBuilder.Query;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BaseRepository<T> implements IBaseRepository<T> {

    private Database database;
    private String tableName;
    private Class<T> type;

    public BaseRepository(Class type, Database database, String tableName) {
        this.database = database;
        this.tableName = tableName;
        this.type = type;
    }

    @Override
    public ArrayList<T> getAll() throws SQLException {
        ArrayList<T> models = new ArrayList<T>();
        ResultSet rs =  new Query(this.database.getSQLConnection()).select(this.tableName).execute();
        ResultSetMetaData resultMetaData = rs.getMetaData();

        //Construct record to object and add to list
        while (rs.next()) {
            HashMap<String, Pair<Object, Integer>> modelData = new HashMap<String, Pair<Object, Integer>>();

            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                String name = resultMetaData.getColumnName(i);
                T value = (T) rs.getObject(i);
                int type = resultMetaData.getColumnType(i);

                modelData.put(name.toLowerCase(), new Pair<>(value, type));
            }

            T model = null;
            try {
                model = (T) new GenericModel(this.type, modelData).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
            models.add(model);
        }
        return models;
    }

    @Override
    public T getById(int ID) throws SQLException {
        List<T> found = this.getAll().stream()
                .filter(model -> {
                    int modelID = -1;
//                    Get the ID of the model. Every model must have an ID.
                    try {
                        Field idField = model.getClass().getDeclaredField("ID");
                        idField.setAccessible(true);

                        modelID = (int) idField.get(model);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return modelID == ID;
                }).collect(Collectors.toList());

        return found.size() > 0 ? found.get(0) : null;
    }

    @Override
    public void create(T entity) {
        //TODO
    }

    @Override
    public void update(T entity) {
        //TODO
    }

    @Override
    public void delete(T entity) {
        //TODO
    }

    @Override
    public void save() {
        //TODO
    }
}
