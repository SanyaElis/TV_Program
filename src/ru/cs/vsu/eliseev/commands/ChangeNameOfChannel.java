package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.util.Scanner;

public class ChangeNameOfChannel implements Command {

    private ChannelService channelService;
    private static ChangeNameOfChannel INSTANCE;

    public static ChangeNameOfChannel getINSTANCE (){
        if (INSTANCE == null){
            INSTANCE = new ChangeNameOfChannel();
        }
        return INSTANCE;
    }

    private ChangeNameOfChannel() {
        this.channelService = ChannelServiceInMemory.getINSTANCE();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id канала которому вы хотите поменять название:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новое название:");
        String newName = sc.next();
        channelService.changeReview(id, newName);
    }

    @Override
    public String toString() {
        return "Сменить название канала";
    }
}
