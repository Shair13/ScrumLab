<%--
  Created by IntelliJ IDEA.
  User: mikok
  Date: 29.08.2023
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<%@ include file="header.jsp" %>
<h1>Lorem Ipsum</h1>
<section class="newsletter-section padding-small">
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>Lorem ipsum dolor sie amet</h1>
      </div>
      <div class="col-5">
        <div class="input-group mb-3">
          <input type="text" class="form-control border-0 rounded-0" placeholder=""
                 aria-label="Recipient's username" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2">
              <a href="index.html">Lorem</a>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="padding-medium story bg-light" id="about">
  <div class="container d-flex justify-content-center align-items-center">
    <div class="row">
      <div class="col-4 mr-4">
        <div class="div-img">
        </div>
      </div>

      <div class="col-7 ml-4">
        <h1 class="pb-1">Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
          volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed. Lorem ipsum dolor sit
          amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat volutpat. Donec
          placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
    </div>
  </div>
</section>
<%@ include file="footer.jsp" %>