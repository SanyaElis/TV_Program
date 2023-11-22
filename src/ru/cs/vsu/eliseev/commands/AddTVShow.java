package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.TVShowService;
import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

import java.time.DayOfWeek;
import java.util.Scanner;

public class AddTVShow implements Command{

    private final TVShowService tvShowService;

    private static AddTVShow INSTANCE;

    public static AddTVShow getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new AddTVShow();
        }
        return INSTANCE;
    }

    private AddTVShow(){
        this.tvShowService = TVShowServiceInMemory.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите жанр телепередачи которую вы хотите добавить: (String)");
        String genre = sc.next();
        System.out.println("Введите день недели в который показывается программа");
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(sc.next());
        System.out.println("Введите время начала телепередачи которую вы хотите добавить: hh:mm");
        String start = sc.next() + ":00";
        System.out.println("Введите время конца телепередачи которую вы хотите добавить: hh:mm");
        String end = sc.next() + ":00";
        System.out.println("Введите описание телепередачи которую вы хотите добавить: (String)");
        String description = sc.next();
        System.out.println("Введите канал на котором будет показываться телепередачи которую вы хотите добавить: (int)");
        int numOfChannel = Integer.parseInt(sc.next());
        tvShowService.add(new TVShow(genre, dayOfWeek, start, end, description, numOfChannel));
    }

    @Override
    public String toString() {
        return "Добавить телепередачу";
    }
}
