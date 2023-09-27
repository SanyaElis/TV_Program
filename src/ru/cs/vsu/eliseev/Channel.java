package ru.cs.vsu.eliseev;

import java.util.List;

public class Channel {
    private final List<TVShow> TVShows;
    private String name;
    private int numberOfChannel;
    private String review;


    public boolean addTVShow(TVShow newTVShow) {
        TVShows.add(newTVShow);
        return true;
    }

    public void renameChannel(String newName) {
        this.name = newName;
    }

    public void changeNumber(int newNumber){
        this.numberOfChannel = newNumber;
    }

    public void changeReview(String newReview){
        this.review = review;
    }

    public Channel(List<TVShow> TVShows, String name, int numberOfChannel, String review) {
        this.TVShows = TVShows;
        this.name = name;
        this.numberOfChannel = numberOfChannel;
        this.review = review;
    }
}
