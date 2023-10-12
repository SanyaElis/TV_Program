package ru.cs.vsu.eliseev.repository;

import ru.cs.vsu.eliseev.models.Channel;

import java.util.List;

public interface ChannelRepository extends Repository<Channel> {
    List<Channel> getAll();
    void add(Channel channel);
    Channel getByID(int id);
    void removeByID(int id);
    void update(int id, Channel channel);
}
