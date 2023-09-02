<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mrbla
  Date: 31.08.2023
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ include file="head.jsp"%>
<body>
<%@ include file="headerAdmin.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="sideMenuAdmin.jsp" %>
        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="#" class="btn btn-success rounded-1 pt-0 pb-0 pr-4 pl-4" onclick="document.getElementById('addForm').submit();">Zapisz</a>
                        <a href="#" class="btn btn-success rounded-1 pt-0 pb-0 pr-4 pl-4 ml-1" id = "cancel" >Anuluj</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <form id="addForm" method="post" action="/app/recipe/plan/add">
                        <div class="form-group row">
                            <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-5">
                                <select class="form-control" id="choosePlan" name="recipePlanPlanId">
                                    <c:forEach items="${plans}" var="plan">
                                        <option value="${plan.id}">${plan.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 label-size col-form-label">
                                Nazwa posiłku
                            </label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" value="" id="name" placeholder="Nazwa posiłku" name="recipePlanMealName">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number" class="col-sm-2 label-size col-form-label">
                                Numer posiłku
                            </label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" value="" id="number" placeholder="Numer posiłki" name="recipePlanDisplayOrder">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="recipie" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-5">
                                <select class="form-control" id="recipie" name="recipePlanRecipeId">
                                    <c:forEach items="${recipes}" var="recipe">
                                        <option value="${recipe.id}">${recipe.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-5">
                                <select class="form-control" id="day" name="recipePlanDayNameId">
                                    <c:forEach items="${days}" var="day">
                                        <option value="${day.id}">${day.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

// Obsługa przycisku "Cancel"
<script>
    document.getElementById("cancel").addEventListener("click", function() {
        window.location.href = "/app/dashboard";
    });
</script>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>


</body>
</html>
