package ru.cs.vsu.eliseev.tests;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;

public class Tester {
    public static void main(String[] args) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String timeString = "18:45";
//        LocalTime parsedTime = LocalTime.parse(timeString, formatter);
//        System.out.println("Время после разбора: " + parsedTime);
        Time testTime = Time.valueOf("18:45:00");
        System.out.println("Sql Time: " + testTime);
        DayOfWeek newDay = DayOfWeek.MONDAY;
        System.out.println(newDay);
    }
}
