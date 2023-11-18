package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

import java.util.Scanner;

public class ChangeChannelForTVShow implements Command{
    private final ru.cs.vsu.eliseev.service.TVShowService tvShowService;
    private static ChangeChannelForTVShow INSTANCE;

    public static ChangeChannelForTVShow getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new ChangeChannelForTVShow();
        }
        return INSTANCE;
    }

    private ChangeChannelForTVShow(){
        this.tvShowService = TVShowServiceInMemory.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id программы которой вы хотите поменять канал:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новый канал:");
        int newChannel = Integer.parseInt(sc.next());
        tvShowService.changeChannel(id, newChannel);
    }

    @Override
    public String toString() {
        return "Изменить канал для показа телепередачи";
    }
}
