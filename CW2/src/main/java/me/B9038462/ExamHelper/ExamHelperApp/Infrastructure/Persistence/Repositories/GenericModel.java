package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories;

import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class GenericModel<T> {
    private Class<T> modelClass;
    private Field[] fields;

    private HashMap<String, Pair<Object, Integer>> data;

    public GenericModel(Class<T> modelClass, HashMap<String, Pair<Object, Integer>> data) {
        this.modelClass = modelClass;
        this.fields = modelClass.getDeclaredFields();
        this.data = data;
    }

    /***
     * Gets all the values that exists within the model class.
     * @return An array of objects
     */
    private Object[] getParameterValues() throws Exception {
        ArrayList<Object> paramKeys = new ArrayList<>();

        for (Field field : this.fields) {
            Pair<Object, Integer> props = this.data.get(field.getName().toLowerCase());

            if (props == null) {
                throw new Error(String.format("Couldn't find the field '%s' within the '%s' model.", field.getName(), this.modelClass.getSimpleName()));
            }

            Object value = props.getKey();

            if (field.getType().isEnum()) {
                Enum[] fieldEnums = ((Class<Enum>) field.getType()).getEnumConstants();
                int enumID = Integer.parseInt(value.toString());

                paramKeys.add(fieldEnums[enumID]);
//            } else if (field.getType().equals(Types.INTEGER)) {
//                System.out.println("Found int " + value);
//                paramKeys.add((int) Integer.parseInt(value.toString()));
            } else {
                paramKeys.add(value);
            }
        }
        Object[] paramValues = new Object[paramKeys.size()];
        paramKeys.toArray(paramValues);

        return paramValues;
    }

    /**
     * Returns an array of classes that can then be used to construct the model.
     * @return An array of classes
     */
    private Class[] getParameterClasses() {
        ArrayList<Class<?>> paramTypes = new ArrayList<Class<?>>();

//        paramTypes.addAll(Arrays.stream(this.fields).map(Field::getType).toList());
        paramTypes.addAll(Arrays.stream(this.fields).map(Field::getType).collect(Collectors.toList()));

        Class[] paramClasses = new Class[paramTypes.size()];
        paramTypes.toArray(paramClasses);

        return paramClasses;
    }

    public T build() throws Exception {
        Class[] paramClasses = this.getParameterClasses();
        Object[] paramValues = this.getParameterValues();
        return this.modelClass.getConstructor(paramClasses).newInstance(paramValues);
    }
}
