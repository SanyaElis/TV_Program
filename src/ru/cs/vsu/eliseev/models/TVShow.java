package ru.cs.vsu.eliseev.models;

import java.util.Date;

public class TVShow {
    private final Integer id;
    private final String genre;//(фильм, мультфильм, информационная передача и т.п.)
    private final Date timeOfStart;
    private final Date timeOfEnd;
    private final String description;
    private final int channelID;//foreign key

    public TVShow(int id, String genre, Date timeOfStart, Date timeOfEnd, String description, int channelID) {
        this.id = id;
        this.genre = genre;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
        this.description = description;
        this.channelID = channelID;
    }

    public TVShow(String genre, Date timeOfStart, Date timeOfEnd, String description, int channelID) {
        this.channelID = channelID;
        this.id = null;
        this.genre = genre;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getGenre() {
        return genre;
    }

    public Date getTimeOfStart() {
        return timeOfStart;
    }

    public Date getTimeOfEnd() {
        return timeOfEnd;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", timeOfStart=" + timeOfStart +
                ", timeOfEnd=" + timeOfEnd +
                ", description='" + description + '\'' +
                ", channelID=" + channelID +
                '}';
    }
}
