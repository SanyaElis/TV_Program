package ru.cs.vsu.eliseev.models;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Objects;


public class TVShow {
    private Integer id;
    private final String genre;//(фильм, мультфильм, информационная передача и т.п.)
    private final DayOfWeek dayOfWeek;
    private final Time timeOfStart;//hh:mm:ss example: 09:28:00
    private final Time timeOfEnd;
    private final String description;
    private final int channelID;//foreign key

    public TVShow(int id, String genre, DayOfWeek dayOfWeek, String timeOfStart, String timeOfEnd, String description, int channelID) {
        this.id = id;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.timeOfStart = Time.valueOf(timeOfStart);//todo replace with parser
        this.timeOfEnd = Time.valueOf(timeOfEnd);
        this.description = description;
        this.channelID = channelID;
    }

    public TVShow(String genre, DayOfWeek dayOfWeek, String timeOfStart, String timeOfEnd, String description, int channelID) {
        this.id = null;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.timeOfStart = Time.valueOf(timeOfStart);
        this.timeOfEnd = Time.valueOf(timeOfEnd);
        this.description = description;
        this.channelID = channelID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getGenre() {
        return genre;
    }

    public Time getTimeOfStart() {
        return timeOfStart;
    }

    public Time getTimeOfEnd() {
        return timeOfEnd;
    }

    public String getDescription() {
        return description;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", timeOfStart='" + timeOfStart + '\'' +
                ", timeOfEnd='" + timeOfEnd + '\'' +
                ", description='" + description + '\'' +
                ", channelID=" + channelID +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        TVShow show = (TVShow) obj;
        return Objects.equals(show.getId(), id) && Objects.equals(show.getGenre(), genre)
                && show.getDayOfWeek() == dayOfWeek && show.getTimeOfStart() == timeOfStart
                && show.getTimeOfEnd() == timeOfEnd && Objects.equals(show.getDescription(), description)
                && show.getChannelID() == channelID;
    }
}
