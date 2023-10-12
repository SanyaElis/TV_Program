package ru.cs.vsu.eliseev.repository;

import ru.cs.vsu.eliseev.models.TVShow;

import java.util.List;

public interface TVShowRepository extends Repository<TVShow> {
    List<TVShow> getAll();
    void add(TVShow tvShow);
    TVShow getByID(int id);
    void removeByID(int id);
    void update(int id, TVShow newTVShow);
}
