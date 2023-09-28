package ru.geekbrains.clinicservice.services;

import java.util.List;

public interface Repository<T, TId> {
    int create(T item);

    int update(T item);

    int delete(TId id);

    T getById(TId id);

    List<T> getAll();
}
