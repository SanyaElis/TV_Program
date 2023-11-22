package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.database.ConnectionManager;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.TVShowRepository;

import java.sql.ResultSet;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TVShowRepositoryDB implements TVShowRepository {
    private static ConnectionManager connectionManager;
    private static TVShowRepositoryDB INSTANCE;

    public static TVShowRepositoryDB getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TVShowRepositoryDB();
            connectionManager = ConnectionManager.getInstance();
        }
        return INSTANCE;
    }

    @Override
    public List<TVShow> getAll() {
        try{
            List<TVShow> tvShows = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM tvShow");
            while (rs.next()){
                tvShows.add(getTVShowFromResultSet(rs));
            }
            rs.close();
            return tvShows;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(TVShow tvShow) {
        try {
            if (tvShow.getId() == null) {
                connectionManager.executeUpdate("INSERT INTO tvshow (genre, timeOfStart, timeOfEnd, dayOfWeek, description, channelID) " +
                        "VALUES ('" + tvShow.getGenre() + "', '" + tvShow.getTimeOfStart() + "', '" + tvShow.getTimeOfEnd() + "', '"
                        + tvShow.getDayOfWeek() + "', '" + tvShow.getDescription() + "', " + tvShow.getChannelID() + ")");
                return;
            }
            connectionManager.executeUpdate("INSERT INTO tvshow (idtvshow, genre, timeOfStart, timeOfEnd, dayOfWeek, description, channelID) " +
                    "VALUES (" + tvShow.getId() + ", '" + tvShow.getGenre() + "', '" + tvShow.getTimeOfStart() + "', '" + tvShow.getTimeOfEnd() + "', '"
                    + tvShow.getDayOfWeek() + "', '" + tvShow.getDescription() + ",' " + tvShow.getChannelID() + ")");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public TVShow getByID(int id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM tvShow WHERE idTVShow = " + id);
            rs.next();
            TVShow tvShow = getTVShowFromResultSet(rs);
            rs.close();
            return tvShow;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeByID(int id) {
        try {
            connectionManager.executeUpdate("DELETE FROM tvShow WHERE idTVShow = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(int id, TVShow newTVShow) {
        try {
            connectionManager.executeUpdate("UPDATE tvShow SET GENRE = '" + newTVShow.getGenre() + "', timeOfStart = '"
                    + newTVShow.getTimeOfStart() + "', timeOfEnd = '" + newTVShow.getTimeOfEnd() + "', dayOfWeek = '"
                    + newTVShow.getDayOfWeek() + "', description = '" + newTVShow.getDescription() + "', channelID = "
                    + newTVShow.getChannelID() + " WHERE idTVShow = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private TVShow getTVShowFromResultSet(ResultSet rs){
        try {
            int ID = rs.getInt(1);
            String genre = rs.getString(2);
            Time timeOfStart = rs.getTime(3);
            Time timeOfEnd = rs.getTime(4);
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(rs.getString(5));
            String description = rs.getString(6);
            int channelID = rs.getInt(7);
            return new TVShow(ID, genre, dayOfWeek, timeOfStart.toString(), timeOfEnd.toString(), description, channelID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
