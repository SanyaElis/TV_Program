package ru.cs.vsu.eliseev.service.implementation;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.ChannelRepository;
import ru.cs.vsu.eliseev.repository.TVShowRepository;
import ru.cs.vsu.eliseev.repository.implementation.ChannelRepositoryInMemory;
import ru.cs.vsu.eliseev.repository.implementation.TVShowRepositoryInMemory;
import ru.cs.vsu.eliseev.service.TVShowService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TVShowServiceInMemory implements TVShowService {
    private static TVShowServiceInMemory INSTANCE;
    private final TVShowRepository tvShowRepository;
    private final ChannelRepository channelRepository;
    private int lastId;

    private TVShowServiceInMemory() {
        this.channelRepository = ChannelRepositoryInMemory.getINSTANCE();
        this.tvShowRepository = TVShowRepositoryInMemory.getINSTANCE();
        this.lastId = 0;
    }

    public static TVShowServiceInMemory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TVShowServiceInMemory();
        }
        return INSTANCE;
    }

    @Override
    public void add(TVShow tvShow) {
        if (!isChannelExists(tvShow.getChannelID())) {
            throw new IllegalArgumentException("Channel with id = " + tvShow.getChannelID() + " not exists");
        }
        if (!checkTime(tvShow)) {
            throw new IllegalArgumentException("In time " + tvShow.getTimeOfStart() + " - " + tvShow.getTimeOfEnd() +
                    " channel shows another show");
        }
        if (tvShow.getId() == null) {//todo проверка добавления с индексом lastId
            tvShow.setId(lastId++);
        }
        tvShowRepository.add(tvShow);
    }

    @Override
    public List<TVShow> getAll() {
        return tvShowRepository.getAll();
    }

    @Override
    public TVShow findByID(int id) {
        return tvShowRepository.getByID(id);
    }

    @Override
    public void changeGenre(int id, String genre) {
        TVShow show = tvShowRepository.getByID(id);
        if (show == null)
            return;
        tvShowRepository.update(id, new TVShow(id, genre, show.getDayOfWeek(), show.getTimeOfStart().toString(), show.getTimeOfEnd().toString(), show.getDescription(), show.getChannelID()));
    }

    @Override
    public void changeDescription(int id, String description) {
        TVShow show = tvShowRepository.getByID(id);
        if (show == null)
            return;
        tvShowRepository.update(id, new TVShow(show.getGenre(), show.getDayOfWeek(), show.getTimeOfStart().toString(), show.getTimeOfEnd().toString(), description, show.getChannelID()));
    }

    @Override
    public void changeChannel(int id, int newChannelId) {
        TVShow show = tvShowRepository.getByID(id);
        if (show == null)
            return;
        if (!isChannelExists(newChannelId)) {
            throw new IllegalArgumentException("Channel with id = " + newChannelId + "not exists");
        }
        if (!checkTime(show)) {
            throw new IllegalArgumentException("In time " + show.getTimeOfStart() + " - " + show.getTimeOfEnd() +
                    " channel shows another show");
        }
        tvShowRepository.update(show.getId(), new TVShow(show.getId(), show.getGenre(), show.getDayOfWeek(), show.getTimeOfStart().toString(), show.getTimeOfEnd().toString(), show.getDescription(), newChannelId));
    }

    @Override
    public void removeByID(int ID) {
        if (tvShowRepository.getByID(ID) == null) {
            System.out.println("TV Show with id = " + ID + " not exists");
        }
        tvShowRepository.removeByID(ID);
    }

    private boolean isChannelExists(int channelId) {
        return channelRepository.getByID(channelId) != null;
    }

    private boolean checkTime(TVShow showToAdd) {
        List<TVShow> showsOnChannel = new ArrayList<>();
        for (TVShow shows : tvShowRepository.getAll()) {
            if (shows.getChannelID() == showToAdd.getChannelID() && shows.getDayOfWeek() == showToAdd.getDayOfWeek()) {
                showsOnChannel.add(shows);
            }
        }
        if (showsOnChannel.size() == 0) {
            return true;
        }
        for (TVShow shows : showsOnChannel) {
            Time timeOfStart = shows.getTimeOfStart();
            Time timeOfEnd = shows.getTimeOfEnd();
            if (showToAdd.getTimeOfStart().after(timeOfStart) && showToAdd.getTimeOfStart().before(timeOfEnd)) {
                return false;
            }
            if (showToAdd.getTimeOfEnd().after(timeOfStart) && showToAdd.getTimeOfEnd().before(timeOfEnd)) {
                return false;
            }
        }
        return true;
    }
}
