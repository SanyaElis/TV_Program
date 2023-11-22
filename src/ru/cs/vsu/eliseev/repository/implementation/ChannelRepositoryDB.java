package ru.cs.vsu.eliseev.repository.implementation;

import ru.cs.vsu.eliseev.database.ConnectionManager;
import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.repository.ChannelRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChannelRepositoryDB implements ChannelRepository {

    private static ConnectionManager connectionManager;
    private static ChannelRepositoryDB INSTANCE;

    public static ChannelRepositoryDB getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ChannelRepositoryDB();
            connectionManager = ConnectionManager.getInstance();
        }
        return INSTANCE;
    }

    @Override
    public List<Channel> getAll() {
        try{
            List<Channel> channels = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM CHANNEL");
            while (rs.next()){
                channels.add(getChannelFromResultSet(rs));
            }
            rs.close();
            return channels;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Channel getByID(int id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM CHANNEL WHERE idChannel = " + id);
            rs.next();
            Channel channel = getChannelFromResultSet(rs);
            rs.close();
            return channel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeByID(int id) {
        try {
            connectionManager.executeUpdate("DELETE FROM CHANNEL WHERE idChannel = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(int id, Channel channel) {
        try {
            connectionManager.executeUpdate("UPDATE CHANNEL SET NAME = '" + channel.getName() + "', numberOfChannel = "
                    + channel.getNumberOfChannel() + ", REVIEW = '" + channel.getReview() + "' WHERE idchannel = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Channel getChannelFromResultSet(ResultSet rs) {
        try {
            int ID = rs.getInt(1);
            String name = rs.getString(2);
            int numberOfChannel = rs.getInt(3);
            String review = rs.getString(4);
            return new Channel(ID, name, numberOfChannel, review);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
