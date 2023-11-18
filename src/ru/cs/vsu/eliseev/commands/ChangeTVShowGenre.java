package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.service.implementation.TVShowService;

import java.util.Scanner;

public class ChangeTVShowGenre implements Command {

    private final ru.cs.vsu.eliseev.service.TVShowService tvShowService;
    private static ChangeTVShowGenre INSTANCE;

    public static ChangeTVShowGenre getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new ChangeTVShowGenre();
        }
        return INSTANCE;
    }

    private ChangeTVShowGenre(){
        this.tvShowService = TVShowService.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id программы которую хотите поменять:");
        int id = Integer.parseInt(sc.next());
        System.out.println("Введите новый жанр:");
        String newGenre = sc.next();
        tvShowService.changeGenre(id, newGenre);
    }

    @Override
    public String toString() {
        return "Сменить жанр телепередаче";
    }
}
