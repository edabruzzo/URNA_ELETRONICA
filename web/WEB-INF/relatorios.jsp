<%--
  Created by IntelliJ IDEA.
  User: martin
  Date: 11/20/19
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Relatórios de Votação</title>
    <meta name="viewport" content="width = device-width, initial-scale = 1.0">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <%-- Chart.js CDN --%>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>

</head>
<body>

<div class="container text-center">

    <h1 class="text-center">Relatórios</h1>

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


    <div class="row">
        <div class="col-5">
            <canvas id="graficoVotosPartido" width="400px" height="400px"></canvas>
        </div>
        <div class="col-5">
            <canvas id="graficoVotosCandidato" width="400px" height="400px"></canvas>
        </div>
    </div>

</div>

<script>
    let ctx = document.getElementById('graficoVotosPartido');
    let graficoPorPartido = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach var="voto" items="${listaVotosPorPartido}">
                "<c:out value="${voto.key}"/>",
                </c:forEach>
            ],
            datasets: [{
                label: "# Votos por partido",
                data: [
                    <c:forEach var="voto" items="${listaVotosPorPartido}">
                    <c:out value="${voto.value}"/>,
                    </c:forEach>
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });


    let ctxCandidato = document.getElementById('graficoVotosCandidato');
    let graficoVotosCandidato = new Chart(ctxCandidato, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach var="voto" items="${listaVotosPorCandidato}">
                "<c:out value="${voto.key}"/>",
                </c:forEach>
            ],
            datasets: [{
                label: "# Votos por Candidato",
                data: [
                    <c:forEach var="voto" items="${listaVotosPorCandidato}">
                    <c:out value="${voto.value}"/>,
                    </c:forEach>
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

</script>

</body>
</html>
