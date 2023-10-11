package ru.cs.vsu.eliseev.repository;

import ru.cs.vsu.eliseev.models.Channel;

import java.util.List;

public interface ChannelRepository {
    List<Channel> getAll();
    void add(Channel channel);
    Channel getByID(int id);
    void removeByID(int id);
    void update(int id, Channel channel);
}
