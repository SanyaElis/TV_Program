package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.util.Scanner;

public class ChangeNumberOfChannel implements Command{

    private ChannelService channelService;
    private static ChangeNumberOfChannel INSTANCE;

    public static ChangeNumberOfChannel getINSTANCE (){
        if (INSTANCE == null){
            INSTANCE = new ChangeNumberOfChannel();
        }
        return INSTANCE;
    }

    private ChangeNumberOfChannel() {
        this.channelService = ChannelServiceInMemory.getINSTANCE();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id канала которому вы хотите поменять номер:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новый номер:");
        int newNumber = Integer.parseInt(sc.next());
        channelService.changeNumber(id, newNumber);
    }

    @Override
    public String toString() {
        return "Поменять номер канала";
    }
}
