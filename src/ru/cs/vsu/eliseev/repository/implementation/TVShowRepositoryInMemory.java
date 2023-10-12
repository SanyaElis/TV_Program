package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.TVShowRepository;

import java.util.ArrayList;
import java.util.List;

public class TVShowRepositoryInMemory implements TVShowRepository {

    private final List<TVShow> tvShows;

    private static TVShowRepositoryInMemory INSTANCE;

    public static TVShowRepositoryInMemory getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new TVShowRepositoryInMemory();
        }
        return INSTANCE;
    }


    private TVShowRepositoryInMemory() {
        this.tvShows = new ArrayList<>();
    }

    @Override
    public List<TVShow> getAll() {
        return new ArrayList<>(tvShows);
    }

    @Override
    public void add(TVShow tvShow) {
        tvShows.add(tvShow);
    }

    @Override
    public TVShow getByID(int id) {
        for (TVShow show : tvShows) {
            if (id == show.getId())
                return show;
        }
        return null;
    }

    @Override
    public void removeByID(int id) {
        tvShows.removeIf(show -> id == show.getId());
    }

    @Override
    public void update(int id, TVShow newTVShow) {
        for (TVShow show : tvShows) {
            if (id == show.getId()) {
                tvShows.remove(show);
                tvShows.add(newTVShow);
            }
        }
        throw new IllegalArgumentException("TV Show with id = " + id + " not exists");
    }
}
