package com.epam.algorithms;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsTest {
    static interface Bean<T, V> {

    }
    static class MyGenericBean<T, V> implements Bean<T, V> {

    }
    static class MyNonGenericBean implements Bean<String, Integer> {

    }
    public static void main(String[] args) {
        printGenericTypes(new MyGenericBean<String, Integer>());
        printGenericTypes(new MyNonGenericBean());
    }

    public static void printGenericTypes(Object instance) {
        Type[] genericInterfaces = instance.getClass().getGenericInterfaces();
        System.out.println("");
        for(Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                System.out.println(genericInterface.getTypeName());
                Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    System.out.printf("\t- %s\n", genericType);
                }
            }
        }
        System.out.println("");
    }
}
