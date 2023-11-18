package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.util.Scanner;

public class ChangeReviewOfChannel implements Command{
    private final ru.cs.vsu.eliseev.service.ChannelService channelService;
    private static ChangeReviewOfChannel INSTANCE;

    public static ChangeReviewOfChannel getINSTANCE (){
        if (INSTANCE == null){
            INSTANCE = new ChangeReviewOfChannel();
        }
        return INSTANCE;
    }

    private ChangeReviewOfChannel(){
        this.channelService = ChannelServiceInMemory.getINSTANCE();
    }


    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id канала которому вы хотите поменять описание:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новое описание:");
        String newReview = sc.next();
        channelService.changeReview(id, newReview);
    }

    @Override
    public String toString() {
        return "Сменить описание канала";
    }
}
