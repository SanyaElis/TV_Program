package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.util.Scanner;

public class AddChannel implements Command{

    private final ChannelService channelService;
    private static AddChannel INSTANCE;

    public static AddChannel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AddChannel();
        }
        return INSTANCE;
    }

    private AddChannel() {
        this.channelService = ChannelServiceInMemory.getINSTANCE();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите название канала который вы хотите добавить: (String)");
        String name = sc.next();
        System.out.println("Введите номер канала который вы хотите добавить: (int)");
        int number = Integer.parseInt(sc.next());
        System.out.println("Введите описание канала который вы хотите добавить: (String)");
        String review = sc.next();
        channelService.add(new Channel(name, number, review));
    }

    @Override
    public String toString() {
        return "Добавить новый канал";
    }
}
