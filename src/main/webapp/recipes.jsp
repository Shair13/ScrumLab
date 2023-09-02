<<<<<<< HEAD
<%--
  Created by IntelliJ IDEA.
  User: szer13
  Date: 02.09.2023
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
=======
>>>>>>> develop
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<%@ include file="head.jsp" %>

<body class="recipes-section">

<%@ include file="header.jsp" %>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<section class="mr-4 ml-4">
    <table class="table">
        <thead>
        <tr class="d-flex text-color-darker">
            <th scope="col" class="col-1">ID</th>
            <th scope="col" class="col-5">NAZWA</th>
            <th scope="col" class="col-5">OPIS</th>
            <th scope="col" class="col-1">AKCJE</th>
        </tr>
        </thead>
        <tbody class="text-color-lighter">
        <c:forEach var="recipe" items="${recipes}">
        <tr class="d-flex">
            <th scope="row" class="col-1">${recipe.id}</th>
            <td class="col-5">${recipe.name}</td>
            <td class="col-5">${recipe.description}</td>
            <td class="col-1"><a href="recipe/details?id=${recipe.id}" class="btn btn-info rounded-0 text-light">Szczegóły</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>
