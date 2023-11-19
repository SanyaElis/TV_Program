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
        TVShow newShow = new TVShow("Horror", DayOfWeek.FRIDAY, "21:30:00", "23:00:00", "Scary", 3);
        tvShowService.add(newShow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addChannelWithSameId() {
        Channel newChannel = new Channel(13, "HCT", 3, "Horror");
        channelService.add(newChannel);
        Channel sameChannel = new Channel(13, "HCT", 3, "Horror");
        channelService.add(sameChannel);
    }

    @Test
    public void AddChannel() {
        int prevSize = channelService.getAll().size();
        Channel newChannel = new Channel("HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertEquals(prevSize + 1, channelService.getAll().size());
    }

    @Test
    public void AddShowToChannel() {
        int prevSize = tvShowService.getAll().size();
        Channel newChannel = new Channel(3, "HCT", 3, "Horror");
        channelService.add(newChannel);
        TVShow newShow = new TVShow("Horror", DayOfWeek.FRIDAY, "21:30:00", "23:00:00", "Scary", 3);
        tvShowService.add(newShow);
        Assert.assertEquals(prevSize + 1, tvShowService.getAll().size());

    }

    @Test
    public void errorChannelFind() {
        Channel newChannel = new Channel(45, "HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertNull(channelService.findByID(4));
    }

    @Test
    public void channelFind() {
        Channel newChannel = new Channel(56, "HCT", 3, "Horror");
        channelService.add(newChannel);
        Assert.assertEquals(newChannel, channelService.findByID(56));
    }

    @Test
    public void findShow() {
        Channel newChannel = new Channel(78, "errorFindShow", 11, "errorFindShow");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(78, "errorFindShow", DayOfWeek.THURSDAY, "21:30:00", "23:00:00", "errorFindShow", 78);
        tvShowService.add(newShow);
        Assert.assertEquals(newShow, tvShowService.findByID(78));
    }

    @Test
    public void changeGenre() {
        Channel newChannel = new Channel(6, "2x2", 3, "Multiplication");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(12, "Simpsons", DayOfWeek.MONDAY, "21:30:00", "23:00:00", "Funny", 6);
        tvShowService.add(newShow);
        String newGenre = "News";
        tvShowService.changeGenre(12, newGenre);
        Assert.assertEquals(newGenre, tvShowService.findByID(12).getGenre());
    }

    @Test
    public void changeDescription() {
        Channel newChannel = new Channel(7, "2x2", 31, "Multiplication");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(13, "NewShow", DayOfWeek.WEDNESDAY, "21:30:00", "23:00:00", "Funny", 7);
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
        TVShow newShow = new TVShow(14, "MyShow", DayOfWeek.WEDNESDAY, "11:30:00", "12:00:00", "Funny", 8);
        tvShowService.add(newShow);
        int newChannelId = 9;
        tvShowService.changeChannel(14, newChannelId);
        Assert.assertEquals(newChannelId, tvShowService.findByID(14).getChannelID());
    }

    @Test
    public void changeNumberOfChannel() {
        Channel newChannel = new Channel(10, "None", 12, "None");
        channelService.add(newChannel);
        int newNumber = 404;
        channelService.changeNumber(10, newNumber);
        Assert.assertEquals(newNumber, channelService.findByID(10).getNumberOfChannel());

    }

    @Test
    public void changeReviewOfChannel() {
        Channel newChannel = new Channel(11, "Test", 111, "Test");
        channelService.add(newChannel);
        String newReview = "NEW REVIEW";
        channelService.changeReview(11, newReview);
        Assert.assertEquals(newReview, channelService.findByID(11).getReview());
    }

    @Test
    public void changeNameOfChannel() {
        Channel newChannel = new Channel(12, "Previous name", 222, "Test");
        channelService.add(newChannel);
        String newName = "New name";
        channelService.changeName(12, newName);
        Assert.assertEquals(newName, channelService.findByID(12).getName());
    }

    @Test
    public void removeChannel() {
        Channel channelForRemove = new Channel(14, "Removed channel", 505, "Removed channel");
        channelService.add(channelForRemove);
        channelService.removeByID(14);
        Assert.assertNull(channelService.findByID(14));
    }

    @Test
    public void removeChannelWithShows() {
        Channel newChannel = new Channel(15, "Channel with Shows", 101, "Channel with Shows");
        channelService.add(newChannel);
        TVShow newShow = new TVShow(14, "Show for remove", DayOfWeek.SUNDAY, "11:30:00", "12:00:00", "Show", 15);
        tvShowService.add(newShow);
        channelService.removeByID(15);
        Assert.assertNull(tvShowService.findByID(14));
    }
    @Test
    public void removeTVShow() {
        Channel newChannel = new Channel(16, "Channel 16", 1616, "Channel 16");
        channelService.add(newChannel);
        TVShow newTVShow = new TVShow(16, "Delete", DayOfWeek.SUNDAY, "14:00:00", "14:30:00", "test remove", 16);
        tvShowService.add(newTVShow);
        Assert.assertEquals(newTVShow, tvShowService.findByID(16));
        tvShowService.removeByID(16);
        Assert.assertNull(tvShowService.findByID(16));
    }
    @Test(expected = IllegalArgumentException.class)
    public void addTvShowDuringAnother() {
        Channel newChannel = new Channel(17, "Channel 17", 1717, "Channel 17");
        channelService.add(newChannel);
        TVShow firstShow = new TVShow(17, "17", DayOfWeek.TUESDAY, "08:00:00", "10:00:00", "17", 17);
        TVShow secondShow = new TVShow(18, "18", DayOfWeek.TUESDAY, "09:00:00", "11:00:00", "18", 17);
        tvShowService.add(firstShow);
        tvShowService.add(secondShow);
    }
}