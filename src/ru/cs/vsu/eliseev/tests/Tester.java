package ru.cs.vsu.eliseev.tests;


import ru.cs.vsu.eliseev.database.ConnectionManager;
import ru.cs.vsu.eliseev.models.Channel;


public class Tester {

    public static void main(String args[]) {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
//        connectionManager.executeUpdate("UPDATE CHANNEL SET REVIEW = 'Test Review' WHERE IDCHANNEL = 10");
        Channel channel = new Channel(10, "HCT", 3, "Horror");
        System.out.println("INSERT INTO CHANNEL (idchannel, name, numberOfChannel, review) VALUES ( "
                + channel.getId() + " , '" + channel.getName() + "', " + channel.getNumberOfChannel() + ", '" + channel.getReview() + ");");
    }
}
