package ru.cs.vsu.eliseev.service;

import ru.cs.vsu.eliseev.models.TVShow;

import java.util.List;

public interface Service <T> {
    void add(T item);
    List<T> getAll();
    T findByID(int id);
    void removeByID(int id);
}
