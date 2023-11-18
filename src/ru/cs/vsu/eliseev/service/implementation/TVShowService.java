package ru.cs.vsu.eliseev.service.implementation;

import ru.cs.vsu.eliseev.enums.DayOfWeek;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.ChannelRepository;
import ru.cs.vsu.eliseev.repository.TVShowRepository;
import ru.cs.vsu.eliseev.repository.implementation.ChannelRepositoryInMemory;
import ru.cs.vsu.eliseev.repository.implementation.TVShowRepositoryInMemory;

import java.util.ArrayList;
import java.util.List;

public class TVShowService implements ru.cs.vsu.eliseev.service.TVShowService {
    private final TVShowRepository tvShowRepository;
    private final ChannelRepository channelRepository;
    private int lastId;
    private static TVShowService INSTANCE;

    public static TVShowService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TVShowService();
        }
        return INSTANCE;
    }


    private TVShowService() {
        this.channelRepository = ChannelRepositoryInMemory.getINSTANCE();
        this.tvShowRepository = TVShowRepositoryInMemory.getINSTANCE();
        this.lastId = 0;
    }

    @Override
    public void add(TVShow tvShow) {
        if (!isChannelExists(tvShow.getChannelID())) {
            throw new IllegalArgumentException("Channel with id = " + tvShow.getChannelID() + " not exists");
        }
        if (!checkTime(tvShow.getDayOfWeek(), tvShow.getTime(), tvShow.getChannelID())) {
            int[][] timeShowToAdd = tvShow.getTime();
            throw new IllegalArgumentException("In time " + timeShowToAdd[0][0] + ":" +
                    timeShowToAdd[0][1] + " - " + timeShowToAdd[1][0] + ":" +
                    timeShowToAdd[1][1] + " channel shows another show");
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
        tvShowRepository.update(id, new TVShow(genre, show.getDayOfWeek(), show.getTimeOfStart(), show.getTimeOfEnd(), show.getDescription(), show.getChannelID()));
    }

    @Override
    public void changeDescription(int id, String description) {
        TVShow show = tvShowRepository.getByID(id);
        if (show == null)
            return;
        tvShowRepository.update(id, new TVShow(show.getGenre(), show.getDayOfWeek(), show.getTimeOfStart(), show.getTimeOfEnd(), description, show.getChannelID()));
    }

    @Override
    public void changeChannel(int id, int newChannelId) {
        TVShow show = tvShowRepository.getByID(id);
        if (show == null)
            return;
        if (!isChannelExists(newChannelId)) {
            throw new IllegalArgumentException("Channel with id = " + newChannelId + "not exists");
        }
        if (!checkTime(show.getDayOfWeek(), show.getTime(), newChannelId)) {
            int[][] timeShowToAdd = show.getTime();
            throw new IllegalArgumentException("In time " + timeShowToAdd[0][0] + ":" +
                    timeShowToAdd[0][1] + " - " + timeShowToAdd[1][0] + ":" +
                    timeShowToAdd[1][1] + " channel shows another show");
        }
        tvShowRepository.update(show.getId(), new TVShow(show.getGenre(), show.getDayOfWeek(), show.getTimeOfStart(), show.getTimeOfEnd(), show.getDescription(), newChannelId));
    }

    @Override
    public void removeByID(int ID) {
        if (tvShowRepository.getByID(ID) == null){
            System.out.println("TV Show with id = " + ID + " not exists");
        }
    }

    private boolean isChannelExists(int channelId) {
        return channelRepository.getByID(channelId) != null;
    }

    private boolean checkTime(DayOfWeek dayOfWeek, int[][] timeShowToAdd, int channelId) {
        List<TVShow> showsOnChannel = new ArrayList<>();
        for (TVShow shows : tvShowRepository.getAll()) {
            if (shows.getChannelID() == channelId && shows.getDayOfWeek() == dayOfWeek) {
                showsOnChannel.add(shows);
            }
        }
        if (showsOnChannel.size() == 0) {
            return true;
        }
        for (TVShow shows : showsOnChannel) {
            int[][] time = shows.getTime();
            if (time[0][0] < timeShowToAdd[0][0] && timeShowToAdd[1][0] < time[1][0]) {
                return false;
            }
            //if (time[0][0] == timeShowToAdd[0][0] && ) todo доделать
        }
        return true;
    }
}
