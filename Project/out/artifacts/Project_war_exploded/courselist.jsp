<%--
  Created by IntelliJ IDEA.
  User: FABY_
  Date: 24/04/2020
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="images/book_PNG2105.ico">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="styles.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista Corsi</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-md-8" align="center">
        <h1>LISTA CORSI</h1>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Corso</th>
                <th scope="col">ID docente</th>
            </tr>
            </thead>
            <c:forEach items="${lista_corsi}" var="corso">
                <tbody>
                <tr>
                    <th scope="row">${corso.titolo}</th>
                    <td>${corso.id_docente}</td>
                    <p>
                        <a href="/deleteCourse?id=${Corso.id_docente}">
                            elimina corso
                        </a>
                    </p>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/addCourse" role="button">Aggiungi corso</a>
    </div>
</div>
</body>
</html>
