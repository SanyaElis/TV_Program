package ru.cs.vsu.eliseev.tests;


import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.implementation.TVShowRepositoryDB;

import java.time.DayOfWeek;
import java.util.Objects;


public class Tester {
    public static void main(String args[]) {
        TVShow newShow1 = new TVShow(12, "Simpsons", DayOfWeek.MONDAY, "21:30:00", "23:00:00", "Funny", 6);
        TVShow newShow = new TVShow(12, "Simpsons", DayOfWeek.MONDAY, "21:30:00", "23:00:00", "Funny", 6);
        System.out.println(Objects.equals(newShow, newShow1));
    }
}
