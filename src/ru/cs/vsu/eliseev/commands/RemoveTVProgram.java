package ru.cs.vsu.eliseev.commands;

import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.TVShowService;
import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

import java.util.List;
import java.util.Scanner;

public class RemoveTVProgram implements Command{

    private final TVShowService tvShowService;

    private static RemoveTVProgram INSTANCE;

    public static RemoveTVProgram getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new RemoveTVProgram();
        }
        return INSTANCE;
    }

    private RemoveTVProgram(){
        this.tvShowService = TVShowServiceInMemory.getInstance();
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("Введите id телепередачи для удаления");
        int id = Integer.parseInt(sc.next());
        tvShowService.removeByID(id);
    }

    @Override
    public String toString() {
        return "Удалить телепередачу";
    }
}
