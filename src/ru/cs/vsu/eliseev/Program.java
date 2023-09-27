package ru.cs.vsu.eliseev;

import java.util.List;

public class Program {
    private List<Channel> channels;


    public boolean addChannel(Channel newChannel){
        channels.add(newChannel);
        return true;
    }
}
