<%--
  Created by IntelliJ IDEA.
  User: mikok
  Date: 29.08.2023
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="page-header">
  <nav class="navbar navbar-expand-lg justify-content-between">
    <a href="/" class="navbar-brand main-logo main-logo-smaller">
      Zaplanuj <span>Jedzonko</span>
    </a>
    <div class="d-flex justify-content-around">
      <h4 class="text-light mr-3">${sessionScope.user.firstName}</h4>
      <div class="dropdown">
        <button type="dropdown-button" class="circle-div text-center background-color: transparent" id="cancel">
          <i class="fas fa-user icon-user"></i>
        </button>
        <div class="dropdown-content">
          <a href="#" data-toggle="modal" data-target="#confirmLogout">Logout</a>
        </div>
      </div>
      <div class="modal fade" id="confirmLogout" tabindex="-1" role="dialog"
           aria-labelledby="exampleConfirmLogoutTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Potwierdzenie wylogowania</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              Czy na pewno chcesz się wylogować?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary"
                      data-dismiss="modal">Anuluj
              </button>
              <a href="/LogoutServlet"
                 class="btn btn-danger rounded-1 text-light m-1"
                 data-target="#deleteMessage">Wyloguj</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</header>