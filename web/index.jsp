<%-- 
    Document   : index.jsp
    Created on : 16/11/2019, 09:32:22
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<!DOCTYPE html>
<html>
    <head>
        <title>Sistema de Votacao</title>
        <meta name = "viewport" content = "width = device-width, initial-scale = 1.0">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <!--[if lt IE 9]>
        <script src = "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src = "https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <h1>SIMULADOR URNA ELETRONICA</h1>


    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}" tabindex="-1"
                   aria-disabled="true">Home</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/criarInfra">Criar Infraestrutura</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/votacao">Votação</a>
            </li>
            <li class="page-item disabled">
                <a class="page-link" href="${pageContext.request.contextPath}/relatorios">Relatórios</a>
            </li>
            <%--        <li class="page-item">--%>
            <%--            <a class="page-link" href="${pageContext.request.contextPath}/criarInfra">Criar Infraestrutura</a>--%>
            <%--        </li>--%>
            <%--        <li class="page-item">--%>
            <%--            <a class="page-link" href="${pageContext.request.contextPath}/criarInfra">Criar Infraestrutura</a>--%>
            <%--        </li>--%>
        </ul>
    </nav>


    </body>
</html>
