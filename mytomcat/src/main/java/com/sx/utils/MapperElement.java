package com.sx.utils;

/**
 * @author shengx
 * @date 2020/3/26 21:15
 */
public class MapperElement<T> {
    private String name;
    private T Object;

    public MapperElement(String name, T object) {
        this.name = name;
        Object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getObject() {
        return Object;
    }

    public void setObject(T object) {
        Object = object;
    }
}
