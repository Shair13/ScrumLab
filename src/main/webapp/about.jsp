<%--
  Created by IntelliJ IDEA.
  User: mikok
  Date: 29.08.2023
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="head.jsp"%>
<%@ include file="header.jsp" %>
<section class="section-more padding-small">
  <div class="container d-flex justify-content-between">
    <div class="mr-4">
      <h1 class="pb-3">Lorem ipsum dolor sit amet</h1>
      <h4 class="pt-1">consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
        magna aliqua.</h4>
    </div>
    <div class="ml-4 align-self-center">
      <button class="btn btn-color rounded-0 mt-4 pl-4 pr-4">
        <a href="index.html">Lorem ipsum</a>
      </button>
    </div>
  </div>
</section>

<section class="padding-small details bg-light">
  <div class="container">
    <div class="row">
      <div class="col text-center">
        <i class="fas fa-check icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
      <div class="col text-center pr-4 pl-4 mr-4 ml-4">
        <i class="far fa-clock icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
      <div class="col text-center">
        <i class="fas fa-list icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
    </div>
  </div>
</section>
<%@ include file="footer.jsp" %>