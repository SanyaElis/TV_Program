package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.service.implementation.ChannelService;

import java.util.List;
import java.util.Scanner;

public class PrintAllChannels implements Command {

    private final ru.cs.vsu.eliseev.service.ChannelService channelService;
    private static PrintAllChannels INSTANCE;

    public static PrintAllChannels getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PrintAllChannels();
        }
        return INSTANCE;
    }

    private PrintAllChannels() {
        this.channelService = ChannelService.getINSTANCE();

    }


    @Override
    public void execute(Scanner sc) {
        List<Channel> channels = channelService.getAll();
        if (channels.size() == 0) {
            System.out.println("Каналов нет, добавьте их");
            return;
        }
        for (Channel channel : channels) {
            System.out.println(channel);
        }
    }

    @Override
    public String toString() {
        return "Вывести все каналы";
    }
}
