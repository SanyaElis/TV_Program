package ru.cs.vsu.eliseev.service;

import ru.cs.vsu.eliseev.models.TVShow;

import java.util.Date;
import java.util.List;

public interface TVShowService {
    void addTVShow(TVShow tvShow);
    List<TVShow> getAll();
    TVShow findByID(int id);
    void changeGenre(int id, String genre);
    void changeDescription(int id, String description);
    void changeChannel(int newChannelId);
    void changeTimeOfStart(Date start);
    void changeTimeOfEnd(Date end);
}
