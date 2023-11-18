package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.enums.DayOfWeek;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.implementation.TVShowService;

import java.util.Scanner;

public class AddTVShow implements Command{

    private final ru.cs.vsu.eliseev.service.TVShowService tvShowService;

    private static AddTVShow INSTANCE;

    public static AddTVShow getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new AddTVShow();
        }
        return INSTANCE;
    }

    private AddTVShow(){
        this.tvShowService = TVShowService.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите жанр телепередачи которую вы хотите добавить: (String)");
        String genre = sc.next();
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        System.out.println("Введите время начала телепередачи которую вы хотите добавить: hh:mm");
        String start = sc.next();
        System.out.println("Введите время конца телепередачи которую вы хотите добавить: hh:mm");
        String end = sc.next();
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
