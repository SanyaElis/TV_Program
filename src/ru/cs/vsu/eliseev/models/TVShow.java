package ru.cs.vsu.eliseev.models;

import ru.cs.vsu.eliseev.enums.DayOfWeek;


public class TVShow {
    private Integer id;
    private final String genre;//(фильм, мультфильм, информационная передача и т.п.)
    private final DayOfWeek dayOfWeek;
    private final String timeOfStart;//hh:mm example: 09:28
    private final String timeOfEnd;
    private final String description;
    private final int channelID;//foreign key

    public TVShow(int id, String genre, DayOfWeek dayOfWeek, String timeOfStart, String timeOfEnd, String description, int channelID) {
        this.id = id;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
        this.description = description;
        this.channelID = channelID;
    }

    public TVShow(String genre, DayOfWeek dayOfWeek, String timeOfStart, String timeOfEnd, String description, int channelID) {
        this.id = null;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
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

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }

    public String getDescription() {
        return description;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int[][] getTime() {//todo replace with java.sql.Time
        String[] start = timeOfStart.split(":");
        String[] end = timeOfEnd.split(":");
        int[][] time = new int[2][2];
        if (start.length == 2 && end.length == 2) {
            time[0][0] = Integer.parseInt(start[0]);
            time[0][1] = Integer.parseInt(start[1]);
            time[1][0] = Integer.parseInt(end[0]);
            time[1][1] = Integer.parseInt(end[1]);
        }
        return time;
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
}
