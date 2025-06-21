package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.BaseEntity;

import java.util.List;

public interface BaseService <T extends BaseEntity>{
    T getById(Long id);
    T save(T entity);
    List<T> getAll();
//    T update(T entity); // Todo: Пока не уверен нужна ли эта общая настройка
    void deleteById(Long id);
}
