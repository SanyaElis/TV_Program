package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

import java.util.Scanner;

public class ChangeTVShowDescription implements Command{

    private final ru.cs.vsu.eliseev.service.TVShowService tvShowService;
    private static ChangeTVShowDescription INSTANCE;

    public static ChangeTVShowDescription getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new ChangeTVShowDescription();
        }
        return INSTANCE;
    }

    private ChangeTVShowDescription(){
        this.tvShowService = TVShowServiceInMemory.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id программы которую хотите поменять:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новый жанр:");
        String newDescription = sc.next();
        tvShowService.changeDescription(id, newDescription);
    }

    @Override
    public String toString() {
        return "Изменить описание телепрограммы";
    }
}
