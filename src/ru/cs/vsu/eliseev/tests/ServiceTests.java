package ru.cs.vsu.eliseev.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.cs.vsu.eliseev.enums.DayOfWeek;
import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;
import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

public class ServiceTests {
    private final TVShowServiceInMemory tvShowService = TVShowServiceInMemory.getInstance();
    private final ChannelServiceInMemory channelService = ChannelServiceInMemory.getINSTANCE();

    @Test(expected = IllegalArgumentException.class)
    public void addWithoutChannel() {
        TVShow newShow = new TVShow("Horror", DayOfWeek.FRIDAY, "21:30", "23:00", "Scary", 3);
        tvShowService.add(newShow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addChannelWithSameId(){
        Channel newChannel = new Channel(13,"HCT", 3, "Horror");
        channelService.add(newChannel);
        Channel sameChannel = new Channel(13,"HCT", 3, "Horror");
        channelService.add(sameChannel);
    }
    @Test
    public void AddChannel() {
        int prevSize = channelService.getAll().size();
        Channel newChannel = new Channel("HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertEquals(prevSize  + 1, channelService.getAll().size());
    }

    @Test
    public void AddShowToChannel() {
        int prevSize = tvShowService.getAll().size();
        Channel newChannel = new Channel(3, "HCT", 3, "Horror");
        channelService.add(newChannel);
        TVShow newShow = new TVShow("Horror", DayOfWeek.FRIDAY, "21:30", "23:00", "Scary", 3);
        tvShowService.add(newShow);
        Assert.assertEquals(prevSize + 1, tvShowService.getAll().size());

    }
    @Test
    public void errorChannelFind(){
        Channel newChannel = new Channel(45, "HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertNull(channelService.findByID(4));
    }

    @Test
    public void channelFind(){
        Channel newChannel = new Channel(56, "HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertEquals(newChannel, channelService.findByID(56));
    }

    @Test
    public void findShow(){
        Channel newChannel = new Channel(78, "errorFindShow", 11, "errorFindShow");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(78,"errorFindShow", DayOfWeek.THURSDAY, "21:30", "23:00", "errorFindShow", 78);
        tvShowService.add(newShow);
        Assert.assertEquals(newShow, tvShowService.findByID(78));
    }

    @Test
    public void changeGenre() {
        Channel newChannel = new Channel(6, "2x2", 3, "Multiplication");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(12,"Simpsons", DayOfWeek.MONDAY, "21:30", "23:00", "Funny", 6);
        tvShowService.add(newShow);
        String newGenre = "News";
        tvShowService.changeGenre(12, newGenre);
        Assert.assertEquals(newGenre, tvShowService.findByID(12).getGenre());
    }

    @Test
    public void changeDescription() {
        Channel newChannel = new Channel(7, "2x2", 31, "Multiplication");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(13,"NewShow", DayOfWeek.WEDNESDAY, "21:30", "23:00", "Funny", 7);
        tvShowService.add(newShow);
        String newDescription = "Sad";
        tvShowService.changeGenre(13, newDescription);
        Assert.assertEquals(newDescription, tvShowService.findByID(13).getGenre());
    }
    @Test
    public void changeChannel() {
        Channel channel1 = new Channel(8, "2x2", 31, "Multiplication");
        Channel channel2 = new Channel(9, "9x9", 32, "Multiplication");
        channelService.add(channel1);
        channelService.add(channel2);
        TVShow newShow = new TVShow(14, "MyShow", DayOfWeek.WEDNESDAY, "11:30", "12:00", "Funny", 8);
        tvShowService.add(newShow);
        int newChannelId = 9;
        tvShowService.changeChannel(14, newChannelId);
        Assert.assertEquals(newChannelId, tvShowService.findByID(14).getChannelID());
    }


//    @Test
//    public void changeDescription() {
//    }
//
//    @Test
//    public void changeChannel() {
//    }
//
//    @Test
//    public void removeByID() {
//    }
}