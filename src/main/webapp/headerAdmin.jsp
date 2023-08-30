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
      <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
    </div>
  </nav>
</header>