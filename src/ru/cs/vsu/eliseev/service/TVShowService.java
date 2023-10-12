package ru.cs.vsu.eliseev.service;

import ru.cs.vsu.eliseev.models.TVShow;

import java.util.Date;
import java.util.List;

public interface TVShowService extends Service<TVShow>{
    void changeGenre(int id, String genre);
    void changeDescription(int id, String description);
    void changeChannel(int id, int newChannelId);
}
