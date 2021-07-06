package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories;

import javafx.util.Pair;
import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseBuilder.Database;
import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.QueryBuilder.InsertQuery;
import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.QueryBuilder.Query;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    /**
     * Get all models from a table
     * @return An array list of models
     * @throws SQLException
     */
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

    /**
     * Get a model by id
     * @param ID - The ID of the model
     * @return The model
     * @throws SQLException
     */
    @Override
    public T getById(int ID) throws SQLException {
        List<T> found = this.getAll().stream()
                .filter(model -> {
                    int modelID = -1;

                    //Get the ID of the model. Every model must have an ID.
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
        HashMap<String, Object> values = new HashMap<String, Object>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            String col = StringUtils.capitalize(field.getName());
            Object value = null;

            try {
                Method method = entity.getClass().getMethod("get" + col);
                value = method.invoke(entity);

                if (field.getType().isEnum()) {
                    Enum e = (Enum) value;
                    value = e.ordinal();
                }

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            values.put(col, value);
        }
        
        String cols = values.keySet().stream().collect(Collectors.joining(", "));
        InsertQuery query = new Query(this.database.getSQLConnection())
                .insert(this.tableName)
                .insert(cols);

        values.forEach((k, v) -> query.value(v));

        query.execute();
    }

    @Override
    public void update(T entity) {
        int ID = getEntityID(entity);
        System.out.println("UPDATING ---- " + ID);
        new Query(this.database.getSQLConnection())
                .update(this.tableName)
                .set("Description", "Testing")
                .where("ID", ID)
                .execute();
    }

    @Override
    public void delete(T entity) {
        int ID = this.getEntityID(entity);

        new Query(this.database.getSQLConnection())
                .delete(this.tableName)
                .where("ID", ID)
                .execute();
    }

    @Override
    public void save() {
        //TODO
    }

    /**
     * Get the ID of the model from its type
     * @param entity
     * @return the id of the model. Returns -1 if not found.
     */
    public int getEntityID(T entity) {
        int value = -1;
        try {
            Method method = entity.getClass().getMethod("getID");
            value = (int) method.invoke(entity);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        return value;
    }
}
