package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.repository.ChannelRepository;

import java.util.ArrayList;
import java.util.List;

public class ChannelRepositoryInMemory implements ChannelRepository {
    private final List<Channel> channels;

    private static ChannelRepositoryInMemory INSTANCE;

    public static ChannelRepositoryInMemory getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ChannelRepositoryInMemory();
        }
        return INSTANCE;
    }

    private ChannelRepositoryInMemory() {
        this.channels = new ArrayList<>();
    }

    @Override
    public List<Channel> getAll() {
        return new ArrayList<>(channels);
    }

    @Override
    public void add(Channel channel) {
        channels.add(channel);
    }

    @Override
    public Channel getByID(int id) {
        for (Channel ch : channels) {
            if (id == ch.getId())
                return ch;
        }
        return null;
    }

    @Override
    public void removeByID(int id) {
        channels.removeIf(ch -> id == ch.getId());
    }

    @Override
    public void update(int id, Channel channel) {
        for (Channel ch : channels) {
            if (id == ch.getId()) {
                channels.remove(ch);
                channels.add(channel);
                return;
            }
        }
        throw new IllegalArgumentException("Channel with id = " + id + " not exists");
    }
}
