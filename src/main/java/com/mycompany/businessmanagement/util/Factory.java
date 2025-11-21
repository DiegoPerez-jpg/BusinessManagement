package com.mycompany.businessmanagement.util;

public class Factory {
    public static <T> T create(Class<T> clazz){
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
