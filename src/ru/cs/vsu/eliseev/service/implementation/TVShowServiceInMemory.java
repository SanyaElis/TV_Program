package ru.cs.vsu.eliseev.service.implementation;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.TVShowService;

import java.util.Date;
import java.util.List;

public class TVShowServiceInMemory implements TVShowService {

    @Override
    public void addTVShow(TVShow tvShow) {

    }

    @Override
    public List<TVShow> getAll() {
        return null;
    }

    @Override
    public TVShow findByID(int id) {
        return null;
    }

    @Override
    public void changeGenre(int id, String genre) {

    }

    @Override
    public void changeDescription(int id, String description) {

    }

    @Override
    public void changeChannel(int newChannelId) {

    }

    @Override
    public void changeTimeOfStart(Date start) {

    }

    @Override
    public void changeTimeOfEnd(Date end) {

    }
}
