package com.learning.aop;

import com.learning.aop.custom_annotation.Init;
import com.learning.aop.custom_annotation.JsonElement;
import com.learning.aop.custom_annotation.JsonSerializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;


public class ObjectToJsonConverter {



    private void checkIfSerializable(Object object) throws JsonSerializationException {
        if(Objects.isNull(object)){
            throw new JsonSerializationException("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();
        if(!clazz.isAnnotationPresent(JsonSerializable.class)){
            throw new JsonSerializationException("The class " + clazz.getName() + " is not serializable");
        }
    }


    private void initializeObject(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = object.getClass();

        for(Method method : clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(Init.class)){
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }


    private String getJsonString(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();
        HashMap<String,  String> jsonElementsMap = new HashMap();
        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(JsonElement.class)){
                jsonElementsMap.put(field.getAnnotation(JsonElement.class).key(), (String) field.get(object));

            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";


    }

    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }
}
