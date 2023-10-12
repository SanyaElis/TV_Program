package ru.cs.vsu.eliseev.repository;

import ru.cs.vsu.eliseev.models.TVShow;

import java.util.List;

public interface Repository <T> {
    List<T> getAll();
    void add(T object);
    T getByID(int id);
    void removeByID(int id);
    void update(int id, T newObject);
}
