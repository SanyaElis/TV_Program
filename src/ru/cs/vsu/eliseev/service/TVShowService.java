package ru.cs.vsu.eliseev.service;

import ru.cs.vsu.eliseev.models.TVShow;

public interface TVShowService extends Service<TVShow>{
    void changeGenre(int id, String genre);
    void changeDescription(int id, String description);
    void changeChannel(int id, int newChannelId);
}
