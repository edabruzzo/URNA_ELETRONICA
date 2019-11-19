<%-- 
    Document   : votacao
    Created on : 16/11/2019, 21:10:56
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Votacao</title>
    <meta name="viewport" content="width = device-width, initial-scale = 1.0">

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <!-- for styling -->
    <style>
        .title {
            margin-bottom: 10px;
            text-align: center;
            width: 210px;
            color: white;
            border: solid black 2px;
        }

        input[type="button"] {
            background-color: black;
            color: white;
            border: solid black 2px;
            width: 100%
        }

        input[type="text"] {
            background-color: white;
            border: solid black 2px;
            width: 100%
        }


    </style>

</head>

<body>

<h1>SIMULADOR URNA ELETRONICA</h1>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<div style="float:right">
    <div class="slick-slide slick-active" style="width: 650px;">
        <img id="lamina1" src="image/candidatos/91DF.jpg">
    </div>
    <div class="slick-slide" style="width: 650px;">
        <img id="lamina2" src="image/candidatos/92DF.jpg">
    </div>
    <div class="slick-slide" style="width: 650px;">
        <img id="lamina3" src="image/candidatos/93DF.jpg">
    </div>
    <div class="slick-slide" style="width: 650px;">
        <img id="lamina4" src="image/candidatos/94DF.jpg">
    </div>
    <div class="slick-slide" style="width: 650px;">
        <img id="lamina5" src="image/candidatos/95DF.jpg">
    </div>
</div>

<aside style="float:left">
    <table border="1" width="100%">
        <tr>
            <th>Nome Candidato</th>
            <th>Numero do Candidato</th>
            <th>Partido</th>

        </tr>

        <c:forEach var="candidato" items="${listaCandidatos}">
            <tr>
                <td><c:out value="${candidato.nomeCandidato}"/></td>
                <td><c:out value="${candidato.numeroCandidato}"/></td>
                <td><c:out value="${candidato.partido.nomeCompleto}"/></td>
            </tr>
        </c:forEach>
    </table>
</aside>


<!-- create table -->
<div style="width: 500px">

    <table style="width: 100%">

        <tr><input type="title" value=" PAINEL DE VOTACAO" onclick=""/></tr>
        <tr>
            <!-- create button and assign value to each button -->
            <!-- dis("1") will call function dis to display value -->
            <td><input type="button" value="1" onclick="dis('1')"/></td>
            <td><input type="button" value="2" onclick="dis('2')"/></td>
            <td><input type="button" value="3" onclick="dis('3')"/></td>
        </tr>
        <tr>
            <td><input type="button" value="4" onclick="dis('4')"/></td>
            <td><input type="button" value="5" onclick="dis('5')"/></td>
            <td><input type="button" value="6" onclick="dis('6')"/></td>
        </tr>
        <tr>
            <td><input type="button" value="7" onclick="dis('7')"/></td>
            <td><input type="button" value="8" onclick="dis('8')"/></td>
            <td><input type="button" value="9" onclick="dis('9')"/></td>
        </tr>
        <tr>
            <td><input type="button" value=" "/></td>
            <td><input type="button" value="0" onclick="dis('0')"/></td>
            <!-- solve function call function solve to evaluate value -->
            <td><input type="button" value=" "/></td>

        </tr>
    </table>
</div>


</body>
</html>
