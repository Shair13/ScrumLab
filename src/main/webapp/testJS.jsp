<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 02.09.2023
  Time: 06:41
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

        <div class="m-4 p-3 width-medium">
          <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <div class="row border-bottom border-3 p-1 m-1">
              <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
              </div>
              <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/plan/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj
                  plan</a>
              </div>
            </div>

            <div class="schedules-content">
              <table class="table border-bottom">
<%--                <thead>--%>
<%--                <tr class="d-flex">--%>
<%--                  <th class="col-1">ID</th>--%>
<%--                  <th class="col-2">NAZWA</th>--%>
<%--                  <th class="col-7">OPIS</th>--%>
<%--                  <th class="col-2 center">AKCJE</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <c:forEach items="${plansList}" var="plan" varStatus="planCounter">--%>
<%--                  <tr class="d-flex">--%>
<%--                    <td class="col-1">${plan.id}</td>--%>
<%--                    <td class="col-2">${plan.name}</td>--%>
<%--                    <td class="col-7">${plan.description}</td>--%>
<%--                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">--%>

<%--                      <a href="#" class="btn btn-danger rounded-0 text-light m-1" data-toggle="modal"--%>
<%--                         data-target="#modalCentered">Usuń</a>--%>
<%--                      <div class="modal fade" id="modalCentered" tabindex="-1" role="dialog"--%>
<%--                           aria-labelledby="exampleModalCenterTitle" aria-hidden="true">--%>
<%--                        <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--                          <div class="modal-content">--%>
<%--                            <div class="modal-header">--%>
<%--                              <h5 class="modal-title">Usuń plan</h5>--%>
<%--                              <button type="button" class="close" data-dismiss="modal"--%>
<%--                                      aria-label="Close">--%>
<%--                                <span aria-hidden="true">&times;</span>--%>
<%--                              </button>--%>
<%--                            </div>--%>
<%--                            <div class="modal-body">--%>
<%--                              Czy na pewno chcesz usunąć ${plan.name}?--%>
<%--                            </div>--%>
<%--                            <div class="modal-footer">--%>
<%--                              <button type="button" class="btn btn-secondary"--%>
<%--                                      data-dismiss="modal">Anuluj--%>
<%--                              </button>--%>
<%--                              <a href="#" class="btn btn-danger rounded-0 text-light m-1"--%>
<%--                                 data-toggle="modal"--%>
<%--                                 data-target="#modalCentered">Usuń</a>--%>
<%--                            </div>--%>
<%--                          </div>--%>
<%--                        </div>--%>
<%--                      </div>--%>

<%--                      <a href="/app/plan/details?planId=${plan.id}"--%>
<%--                         class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>--%>
<%--                      <a href="/app-edit-schedules.html"--%>
<%--                         class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>--%>

<%--                    </td>--%>
<%--                  </tr>--%>
<%--                </c:forEach>--%>

                <a href="#" class="btn btn-danger rounded-0 text-light m-1" data-toggle="modal"
                   data-target="#modalCentered">Usuń</a>

              </table>

            </div>
          </div>
        </div>
      </div>
    </section>




</body>

<script src="app/plan/js/deletePlanMessage.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</html>
