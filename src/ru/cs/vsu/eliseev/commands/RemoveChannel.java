package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.util.Scanner;

public class RemoveChannel implements Command{

    private final ChannelService channelService;
    private static RemoveChannel INSTANCE;

    public static RemoveChannel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RemoveChannel();
        }
        return INSTANCE;
    }

    private RemoveChannel() {
        this.channelService = ChannelServiceInMemory.getINSTANCE();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id канала который нужно удалить:");
        int id = Integer.parseInt(sc.next());
        channelService.removeByID(id);
    }

    @Override
    public String toString() {
        return "Удалить канал";
    }
}
