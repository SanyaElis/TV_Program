<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>TV Program</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/channel/list"
                   class="nav-link">Channels</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of TvShows</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/tvShow/new" class="btn btn-success">Add
                New TVShow</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Genre</th>
                <th>DayOfWeek</th>
                <th>TimeOfStart</th>
                <th>TimeOfEnd</th>
                <th>Description</th>
                <th>ChannelID</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tvShow" items="${listTVShows}">

                <tr>
                    <td><c:out value="${tvShow.getId()}" /></td>
                    <td><c:out value="${tvShow.getGenre()}" /></td>
                    <td><c:out value="${tvShow.getDayOfWeek()}" /></td>
                    <td><c:out value="${tvShow.getTimeOfStart()}" /></td>
                    <td><c:out value="${tvShow.getTimeOfEnd()}" /></td>
                    <td><c:out value="${tvShow.getDescription()}" /></td>
                    <td><c:out value="${tvShow.getChannelID()}" /></td>
                    <td><a href="<%=request.getContextPath()%>/tvShow/edit?id=<c:out value='${tvShow.getId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/tvShow/delete?id=<c:out value='${tvShow.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>