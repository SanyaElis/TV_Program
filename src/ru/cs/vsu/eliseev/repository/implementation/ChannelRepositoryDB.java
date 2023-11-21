package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.database.ConnectionManager;
import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.repository.ChannelRepository;

import java.util.List;

public class ChannelRepositoryDB implements ChannelRepository {
    ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Channel> getAll() {
        return null;
    }

    @Override
    public void add(Channel channel) {
        try {
            if (channel.getId() == null) {
                connectionManager.executeUpdate("INSERT INTO CHANNEL (name, numberOfChannel, review) VALUES ( '"
                        + channel.getName() + "', " + channel.getNumberOfChannel() + ", '" + channel.getReview() + "');");
                return;
            }
            connectionManager.executeUpdate("INSERT INTO CHANNEL (idchannel, name, numberOfChannel, review) VALUES ( "
                    + channel.getId() + " , '" + channel.getName() + "', " + channel.getNumberOfChannel() + ", '" + channel.getReview() + "');");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
