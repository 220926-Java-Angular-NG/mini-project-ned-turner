package org.example.utils;

import java.util.List;

public interface CRUDDaoInterface<T> {


    int create(T t);

    T getById(int id);

    T update(T t);

}
