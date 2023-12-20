package ru.cs.vsu.eliseev.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.cs.vsu.eliseev.models.Channel;
import ru.cs.vsu.eliseev.service.ChannelService;
import ru.cs.vsu.eliseev.service.implementation.ChannelServiceInMemory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="ChannelServlet",
        urlPatterns={
                "/channel",
                "/channel/edit",
                "/channel/update",
                "/channel/delete",
                "/channel/new",
                "/channel/list",
                "/channel/insert",
        })
public class ChannelServlet extends HttpServlet {

    private ChannelService channelService;


    public ChannelServlet() {
        channelService = ChannelServiceInMemory.getINSTANCE();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/channel/insert" -> insertChannel(request, response);
                case "/channel/update" -> updateChannel(request, response);
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
                case "/channel/new" -> showNewForm(request, response);
                case "/channel/delete" -> deleteChannel(request, response);
                case "/channel/edit" -> showEditForm(request, response);
                default -> listChannel(request, response);
            }
        } catch(SQLException ex) {
            throw new ServletException();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/channel/channel-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Channel channel = channelService.findByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/channel/channel-form.jsp");
        request.setAttribute("channel", channel);
        dispatcher.forward(request, response);
    }

    private void insertChannel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int numberOfChannel = Integer.parseInt(request.getParameter("numberOfChannel"));
        String review = request.getParameter("review");
        channelService.add(new Channel(name, numberOfChannel, review));
        response.sendRedirect("list");
    }

    private void deleteChannel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        channelService.removeByID(id);
        response.sendRedirect("list");
    }

    private void updateChannel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int numberOfChannel = Integer.parseInt(request.getParameter("numberOfChannel"));
        String review = request.getParameter("review");
        channelService.changeName(id, name);
        channelService.changeNumber(id, numberOfChannel);
        channelService.changeReview(id, review);
        response.sendRedirect("list");
    }

    private void listChannel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Channel> channelList = channelService.getAll();
        request.setAttribute("listChannels", channelList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/channel/channel-list.jsp");
        dispatcher.forward(request, response);
    }

}