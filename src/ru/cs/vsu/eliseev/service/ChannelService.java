package ru.cs.vsu.eliseev.service;

import ru.cs.vsu.eliseev.models.Channel;

import java.util.List;

public interface ChannelService {
    void addChannel(Channel channel);
    void changeNumber(int id, int newNumber);
    void changeReview(int id, String review);
    void changeName(int id, String name);
    Channel findByID(int id);
    void removeChannelById(int id);
    List<Channel> getAll();
}
