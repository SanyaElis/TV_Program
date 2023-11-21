package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.repository.ChannelRepository;

import java.util.List;

public class ChannelRepositoryDB implements ChannelRepository {
    @Override
    public List<Channel> getAll() {
        return null;
    }

    @Override
    public void add(Channel channel) {

    }

    @Override
    public Channel getByID(int id) {
        return null;
    }

    @Override
    public void removeByID(int id) {

    }

    @Override
    public void update(int id, Channel channel) {

    }
}
