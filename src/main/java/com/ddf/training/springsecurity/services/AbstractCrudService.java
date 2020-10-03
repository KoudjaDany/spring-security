package com.ddf.training.springsecurity.services;

import java.util.List;

public interface AbstractCrudService<T> {
    T getDetails(Long id);
    T create(T entity);
    void delete(Long id);
    T update(T entity);
    List<T> listAll();
}
