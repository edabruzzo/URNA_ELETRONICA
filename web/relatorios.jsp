<%--
  Created by IntelliJ IDEA.
  User: martin
  Date: 11/20/19
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

    <%-- Underscore.js --%>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore-min.js"--%>
    <%--            integrity="sha256-G7A4JrJjJlFqP0yamznwPjAApIKPkadeHfyIwiaa9e0=" crossorigin="anonymous"></script>--%>

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
                <a class="page-link" href="${pageContext.request.contextPath}/votacao">Votação</a>
            </li>
            <li class="page-item disabled">
                <a class="page-link" href="${pageContext.request.contextPath}/relatorios">Relatórios</a>
            </li>
        </ul>
    </nav>

    <div class="row mb-2">

        <%--@elvariable id="listaVotosPorPartido" type="java.util.HashMap"--%>
        <%--@elvariable id="listaVotosPorCandidato" type="java.util.HashMap"--%>
        <c:forEach var="voto" items="${listaVotosPorPartido}">
            <div class="col">
                <button class="btn btn-primary" onclick="
                        update_partido_chart([
                <c:forEach var="dataset" items="${listaVotosPorPartido[voto.key]}"><c:out value="${dataset.value}"/>,
                </c:forEach> ],
                        [
                <c:forEach var="dataset" items="${listaVotosPorPartido[voto.key]}">'<c:out value="${dataset.key}"/>',
                </c:forEach> ]);
                        update_candidato_chart([
                <c:forEach var="dataset" items="${listaVotosPorCandidato[voto.key]}"><c:out value="${dataset.value}"/>,
                </c:forEach> ],
                        [
                <c:forEach var="dataset" items="${listaVotosPorCandidato[voto.key]}">'<c:out value="${dataset.key}"/>',
                </c:forEach> ])
                        ">
                    <c:out value="${voto.key}"/>
                </button>
            </div>
        </c:forEach>
    </div>

    <div class="row mb-5">
        <div class="col-6">
            <canvas id="graficoVotosPartido" width="400px" height="400px"></canvas>
        </div>
        <div class="col-6">
            <canvas id="graficoVotosCandidato" width="400px" height="400px"></canvas>
        </div>
    </div>

    <div class="row">
        <table id="tabelaVotosPorCandidato" class="table table-sm">

            <thead class="thead-dark w-100">
            <tr>
                <th scope="col">Nome do Candidato</th>
                <th scope="col">Quantidade de votos</th>

            </tr>
            </thead>
            <c:forEach var="voto" items="${listaVotosPorCandidato}">
                <tr>
                    <td><c:out value="${voto.key}"/></td>
                    <td><c:out value="${voto.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

<script>

    function update_partido_chart(array, labels) {
        graficoPorPartido.data.labels = labels;
        graficoPorPartido.data.datasets[0].data = array;
        graficoPorPartido.update();
    }

    function update_candidato_chart(array, labels) {
        graficoVotosCandidato.data.labels = labels;
        graficoVotosCandidato.data.datasets[0].data = array;
        graficoVotosCandidato.update();
    }

    let ctx = document.getElementById('graficoVotosPartido');
    let graficoPorPartido = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [{
                label: "# Votos por partido",
                data: [],
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
            labels: [],
            datasets: [{
                label: "# Votos por Candidato",
                data: [],
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