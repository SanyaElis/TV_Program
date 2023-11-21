package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.TVShowRepository;

import java.util.List;

public class TVShowRepositoryDB implements TVShowRepository {
    @Override
    public List<TVShow> getAll() {
        return null;
    }

    @Override
    public void add(TVShow tvShow) {

    }

    @Override
    public TVShow getByID(int id) {
        return null;
    }

    @Override
    public void removeByID(int id) {

    }

    @Override
    public void update(int id, TVShow newTVShow) {

    }
}
