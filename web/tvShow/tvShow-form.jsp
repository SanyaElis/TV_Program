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
         style="background-color: #21ff0c">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/tvShow/list"
                   class="nav-link">TVShows</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${tvShow != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${tvShow == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${tvShow != null}">
                                Edit TvShow
                            </c:if>
                            <c:if test="${tvShow == null}">
                                Add New TvShow
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${tvShow != null}">
                        <input type="hidden" name="id" value="<c:out value='${tvShow.getId()}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Genre</label> <input type="text"
                                                                value="<c:out value='${tvShow.getGenre()}' />" class="form-control"
                                                                name="genre" required="required">
                    </fieldset>
                    <c:if test="${tvShow == null}">
                        <input type="hidden" name="id" value="<c:out value='${tvShow.getId()}' />" />
                        <fieldset class="form-group">
                            <label>DayOfWeek</label> <input type="text"
                                                                   value="<c:out value='${tvShow.getDayOfWeek()}' />" class="form-control"
                                                                   name="dayOfWeek" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>TimeOfStart</label> <input type="text"
                                                                   value="<c:out value='${tvShow.getTimeOfStart()}' />" class="form-control"
                                                                   name="timeOfStart" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>TimeOfEnd</label> <input type="text"
                                                                   value="<c:out value='${tvShow.getTimeOfEnd()}' />" class="form-control"
                                                                   name="timeOfEnd" required="required">
                        </fieldset>
                    </c:if>
                    <fieldset class="form-group">
                        <label>Description</label> <input type="text"
                                                               value="<c:out value='${tvShow.getDescription()}' />" class="form-control"
                                                               name="description" required="required">
                    </fieldset>

                    <select name="channelID">
                        <c:forEach items="${channelList}" var="channel">
                            <option value="${channel.getId()}">${channel.getName()}</option>
                        </c:forEach>
                    </select>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>