package ru.cs.vsu.eliseev.tests;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tester {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = "18:45";
        LocalTime parsedTime = LocalTime.parse(timeString, formatter);
        System.out.println("Время после разбора: " + parsedTime);
    }
}
