<%--
  Created by IntelliJ IDEA.
  User: szer13
  Date: 30.08.2023
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@ include file="head.jsp" %>

<body>

<%@ include file="headerAdmin.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="sideMenuAdmin.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${recipeCount}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${plansCount}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <c:choose>
                    <c:when test="${planExists}">
                        <h2 class="dashboard-content-title">
                            <span>Ostatnio dodany plan: </span> ${plan.name}
                        </h2>

                        <c:forEach items="${planDays}" var="day" varStatus="dayCounter">
                            <c:set var="dayIdx" value="${dayCounter.index}"/>
                            <table class="table">
                                <thead>
                                <tr class="d-flex">
                                    <th class="col-2">${day.name}</th>
                                    <th class="col-8"></th>
                                    <th class="col-2"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${planMeals[dayIdx]}" var="meal" varStatus="mealCounter">
                                    <tr class="d-flex">
                                        <td class="col-2">${meal.mealName}</td>
                                        <td class="col-8">${planRecipes[dayIdx][mealCounter.index].name}</td>
                                        <td class="col-2">
                                            <a href="/app/recipe/details?id=${planRecipes[dayIdx][mealCounter.index].id}"
                                               class="btn btn-primary rounded-0">Szczegóły</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2 class="dashboard-content-title">
                            <span>Dodaj pierwszy plan lub dołącz przepis do istniejącego planu.</span>
                        </h2>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>