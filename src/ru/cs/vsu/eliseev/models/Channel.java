package ru.cs.vsu.eliseev.models;


public class Channel {
    private Integer id;
    private final String name;
    private final int numberOfChannel;
    private final String review;

    public Channel(int id, String name, int numberOfChannel, String review) {
        this.id = id;
        this.name = name;
        this.numberOfChannel = numberOfChannel;
        this.review = review;
    }

    public Channel(String name, int numberOfChannel, String review) {
        this.id = null;
        this.name = name;
        this.numberOfChannel = numberOfChannel;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfChannel() {
        return numberOfChannel;
    }

    public String getReview() {
        return review;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfChannel=" + numberOfChannel +
                ", review='" + review + '\'' +
                '}';
    }

    /*todo use this
    public boolean addTVShow(TVShow newTVShow) {
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
    }*/
}
