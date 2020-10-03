package com.ddf.training.springsecurity.services;

public interface AbstractCrudService<T> {
    T getDetails(Long id);
    T create(T entity);
    void delete(Long id);
    T update(T entity);
}
