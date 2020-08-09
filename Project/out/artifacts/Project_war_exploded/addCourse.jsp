<%--
  Created by IntelliJ IDEA.
  User: FABY_
  Date: 24/04/2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <title>Aggiungi corso</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-md-8" align="center">
        <h1>Inserisci corso</h1>
    </div>
</div>
<div class="row justify-content-center">
    <form method = "POST">
        <input name="title" type="text">
        <input name="id" type="text">
        <input type="submit" value="save">
    </form>
</div>
<div class="row justify-content-center">
    <a class="btn btn-primary" href="courseList" role="button" style="margin-top: 20">Torna alla lista corsi</a>
</div>
</body>
</html>

