package ru.cs.vsu.eliseev.models;


import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Channel channel = (Channel) obj;
        return Objects.equals(channel.getId(), id) && Objects.equals(channel.getName(), name)
                && channel.getNumberOfChannel() == numberOfChannel && Objects.equals(channel.getReview(), review);
    }
}
