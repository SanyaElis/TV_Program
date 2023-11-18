package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.implementation.TVShowService;

import java.util.List;
import java.util.Scanner;

public class PrintAllTVShow implements Command{

    private final ru.cs.vsu.eliseev.service.TVShowService tvShowService;

    private static PrintAllTVShow INSTANCE;

    public static PrintAllTVShow getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new PrintAllTVShow();
        }
        return INSTANCE;
    }

    private PrintAllTVShow(){
        this.tvShowService = TVShowService.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        List<TVShow> shows = tvShowService.getAll();
        if (shows.size() == 0) {
            System.out.println("Телепрограмм нет, добавьте их");
            return;
        }
        for (TVShow show: shows) {
            System.out.println(show);
        }
    }

    @Override
    public String toString() {
        return "Вывести все программы";
    }
}
