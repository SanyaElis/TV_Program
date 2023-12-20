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
         style="background-color: #54e346">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/tvShow/list"
                   class="nav-link">TV Shows</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Channels</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/channel/new" class="btn btn-success">Add
                New Channel</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Number of Channel</th>
                <th>Review</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="channel" items="${listChannels}">

                <tr>
                    <td><c:out value="${channel.getId()}" /></td>
                    <td><c:out value="${channel.getName()}" /></td>
                    <td><c:out value="${channel.getNumberOfChannel()}" /></td>
                    <td><c:out value="${channel.getReview()}" /></td>
                    <td><a href="<%=request.getContextPath()%>/channel/edit?id=<c:out value='${channel.getId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/channel/delete?id=<c:out value='${channel.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>