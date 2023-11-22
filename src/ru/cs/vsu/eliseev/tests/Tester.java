package ru.cs.vsu.eliseev.tests;


import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.repository.implementation.TVShowRepositoryDB;

import java.time.DayOfWeek;


public class Tester {
    public static void main(String args[]) {
//        ChannelRepositoryDB channelRepository = ChannelRepositoryDB.getINSTANCE();
//        Channel ch = new Channel("2x2", 9, "Cartoons");
//        channelRepository.removeByID(14);
        TVShowRepositoryDB showRepository = TVShowRepositoryDB.getINSTANCE();
        TVShow newTVShow = showRepository.getByID(17);
        TVShow tvShow = new TVShow("Cartoon", DayOfWeek.FRIDAY, "13:00:00", "14:00:00", "Funny", 13);
        showRepository.add(tvShow);
    }
}
