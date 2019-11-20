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
        <link href = "css/bootstrap.min.css" rel = "stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <!--[if lt IE 9]>
        <script src = "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src = "https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <h1>SIMULADOR URNA ELETRONICA</h1>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src = "https://code.jquery.com/jquery.js"></script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src = "js/bootstrap.min.js"></script>


        <div style="padding: 5px;">
            <h3>
                <a href="${pageContext.request.contextPath}/criarInfra">Criar Infraestrutura</a>
                |
                <a href="${pageContext.request.contextPath}/votacao">Votacao</a>
                |
                <a href="${pageContext.request.contextPath}/candidatos">Candidatos</a>
                |
                <a href="${pageContext.request.contextPath}/eleicoes">Eleicoes</a>
                |
                <a href="index.jsp">Home</a>

            </h3>
        </div>  



    </body>
</html>
