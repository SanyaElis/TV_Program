package ru.cs.vsu.eliseev.service.implementation;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.ChannelRepository;
import ru.cs.vsu.eliseev.repository.TVShowRepository;
import ru.cs.vsu.eliseev.repository.implementation.ChannelRepositoryInMemory;
import ru.cs.vsu.eliseev.repository.implementation.TVShowRepositoryInMemory;
import ru.cs.vsu.eliseev.service.ChannelService;

import java.util.List;
import java.util.Objects;

public class ChannelServiceInMemory implements ChannelService {
    private final ChannelRepository channelRepository;
    private final TVShowRepository showRepository;
    private int lastId;
    private static ChannelServiceInMemory INSTANCE;

    public static ChannelServiceInMemory getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ChannelServiceInMemory();
        }
        return INSTANCE;
    }

    private ChannelServiceInMemory() {
        this.channelRepository = ChannelRepositoryInMemory.getINSTANCE();
        this.showRepository = TVShowRepositoryInMemory.getINSTANCE();
        this.lastId = 0;
    }

    @Override
    public void add(Channel channel) {
        List<Channel> channels = channelRepository.getAll();
        if (channels.size() != 0 && channel.getId() != null) {
            for (Channel ch : channels) {
                if (Objects.equals(ch.getId(), channel.getId())) {
                    throw new IllegalArgumentException("Channel with id = " + ch.getId() + " already exists");
                }
            }
        }
        if (channel.getId() == null) {
            channel.setId(lastId++);
            channelRepository.add(channel);
            return;
        }
        channelRepository.add(channel);
    }

    @Override
    public void changeNumber(int id, int newNumber) {
        Channel channel = channelRepository.getByID(id);
        channelRepository.update(id, new Channel(channel.getId(), channel.getName(), newNumber, channel.getReview()));
    }

    @Override
    public void changeReview(int id, String review) {
        Channel channel = channelRepository.getByID(id);
        channelRepository.update(id, new Channel(channel.getId(), channel.getName(), channel.getNumberOfChannel(), review));
    }

    @Override
    public void changeName(int id, String name) {
        Channel channel = channelRepository.getByID(id);
        channelRepository.update(id, new Channel(channel.getId(), name, channel.getNumberOfChannel(), channel.getReview()));
    }

    @Override
    public Channel findByID(int id) {
        return channelRepository.getByID(id);
    }

    @Override
    public void removeByID(int id) {//Cascade delete
        if (channelRepository.getByID(id) == null){
            System.out.println("Channel with id = " + id + " not exists");
        }
        channelRepository.removeByID(id);
        for (TVShow show : showRepository.getAll()) {
            if (show.getChannelID() == id) {
                showRepository.removeByID(show.getId());
            }
        }
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.getAll();
    }
}
