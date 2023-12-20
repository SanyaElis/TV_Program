package ru.cs.vsu.eliseev.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.models.TVShow;
import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.TVShowService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;
import ru.cs.vsu.eliseev.service.implementation.TVShowServiceInMemory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;

@WebServlet(name="TVShowServlet", urlPatterns={
        "/tvShow",
        "/tvShow/edit",
        "/tvShow/update",
        "/tvShow/delete",
        "/tvShow/new",
        "/tvShow/list",
        "/tvShow/insert",
})
public class TVShowServlet extends HttpServlet {

    private ChannelService channelService;

    private TVShowService tvShowService;

    public TVShowServlet() {
        channelService = ChannelServiceInMemory.getINSTANCE();
        tvShowService = TVShowServiceInMemory.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/tvShow/insert" -> insertTVShow(request, response);
                case "/tvShow/update" -> updateTVShow(request, response);
            }
        } catch(SQLException ex) {
            throw new ServletException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/tvShow/new" -> showNewForm(request, response);
                case "/tvShow/delete" -> deleteTVShow(request, response);
                case "/tvShow/edit" -> showEditForm(request, response);
                default -> listTVShow(request, response);
            }
        } catch(SQLException ex) {
            throw new ServletException();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Channel> channelList = channelService.getAll();
        request.setAttribute("channelList", channelList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/tvShow/tvShow-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TVShow tvShow = tvShowService.findByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tvShow/tvShow-form.jsp");

        request.setAttribute("tvShow", tvShow);

        List<Channel> channelList = channelService.getAll();
        request.setAttribute("channelList", channelList);

        dispatcher.forward(request, response);
    }

    private void insertTVShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String genre = request.getParameter("genre");
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(request.getParameter("dayOfWeek"));
        String timeOfStart = request.getParameter("timeOfStart");
        String timeOfEnd = request.getParameter("timeOfEnd");
        String description = request.getParameter("description");
        int channelID = Integer.parseInt(request.getParameter("channelID"));

        tvShowService.add(new TVShow(genre, dayOfWeek, timeOfStart, timeOfEnd, description, channelID));

        response.sendRedirect("list");
    }

    private void deleteTVShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tvShowService.removeByID(id);
        response.sendRedirect("list");
    }

    private void updateTVShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");
        int channelID = Integer.parseInt(request.getParameter("channelID"));

        tvShowService.changeGenre(id, genre);
        tvShowService.changeDescription(id, description);
        tvShowService.changeChannel(id, channelID);
        response.sendRedirect("list");
    }

    private void listTVShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<TVShow> tvShowList = tvShowService.getAll();
        request.setAttribute("listTVShows", tvShowList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tvShow/tvShow-list.jsp");
        dispatcher.forward(request, response);
    }

}