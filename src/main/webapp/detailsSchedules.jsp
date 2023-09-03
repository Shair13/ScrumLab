<%--
  Created by IntelliJ IDEA.
  User: szer13
  Date: 30.08.2023
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@ include file="head.jsp" %>

<body>

<%@ include file="headerAdmin.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="sideMenuAdmin.jsp" %>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <div id="planId" class="d-none">${plan.id}</div>
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${planExists}">
                            <c:forEach items="${planDays}" var="day" varStatus="dayCounter">
                                <c:set var="dayIdx" value="${dayCounter.index}"/>
                                <table class="table">
                                    <thead>
                                    <tr class="d-flex">
                                        <th class="col-2">${day.name}</th>
                                        <th class="col-7"></th>
                                        <th class="col-1"></th>
                                        <th class="col-2"></th>
                                    </tr>
                                    </thead>

                                    <tbody class="text-color-lighter">
                                    <c:forEach items="${planMeals[dayIdx]}" var="meal" varStatus="mealCounter">
                                        <tr class="d-flex">
                                            <div id="recipePlanId" class="d-none">${meal.id}</div>
                                            <td class="col-2" id="mealName">${meal.mealName}</td>
                                            <td class="col-7">${planRecipes[dayIdx][mealCounter.index].name} </td>
                                            <td class="col-1 center">
                                                <a href="#" class="btn btn-danger rounded-0 text-light m-1" id="deleteButton"
                                                   data-toggle="modal"
                                                   data-target="#deleteMessage">Usuń</a>
                                            </td>
                                            <td class="col-2 center">
                                                <a href="/app/recipe/details?id=${planRecipes[dayIdx][mealCounter.index].id}"
                                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2 class="dashboard-content-title">
                                <span>Do tego planu nie zostały jeszczce dodane przepisy.</span>
                            </h2>
                        </c:otherwise>
                    </c:choose>

                    <div class="modal fade" id="deleteMessage" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Usuń przepis z planu</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    Czy na pewno chcesz usunąć ten plan?
                                </div>
                                <div class="modal-footer">

                                    <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal">Anuluj
                                    </button>
                                    <a href="/app/plan/details/delete?recipePlanId="
                                       class="btn btn-danger rounded-0 text-light m-1"
                                       data-target="#deleteMessage">Usuń</a>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/deleteRecipePlan.js"></script>
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
