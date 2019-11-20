<%--
    Document   : votacao
    Created on : 16/11/2019, 21:10:56
    Author     : Emm, Martin
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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    </head>

    <body>

        
        
            <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}" tabindex="-1"
                   aria-disabled="true">Home</a>
            </li>
            <li class="page-item disabled">
                <a class="page-link" href="${pageContext.request.contextPath}/votacao">Votação</a>
            </li>
            <li class="page-item ">
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
        
        
        
        
        
        
        <div class="container text-center">

            <h1 class="text-center">SIMULADOR URNA ELETRONICA</h1>

            <h4 style="color: red">${stringErro}</h4>
            <h4 style="color: green">${stringSucesso}</h4>

            <div class="row">

                <div class="col-4">

                    <div class="mb-2">

                        <form id="form-voto" method="post" 
                              action="${pageContext.servletContext.contextPath}/votacao">

                            <div class="form-group">
                                <label for="exemploIdentificador">Digite seu identificador abaixo.</label>
                                <input id="identificador" name="identificador" class="w-100" type="title" placeholder="RG" value="">
                                <small id="identificador-help" class="form-text text-muted">Lembre-se de escrevê-lo corretamente, ou seu voto não será contabilizado.</small>
                            </div>

                            <div>        
                                <label >Selecione o tipo de eleição </label>
                                <select name="idEleicao">
                                    <c:forEach items="${listaEleicoes}" var="eleicao">
                                        <option value="${eleicao.id_eleicao}"
                                                >${eleicao.cargo} 
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exemploVoto">Abaixo está o número de seu candidato.</label>
                                <input id="painel" name="painel" 
                                       class="form-control w-100" type="title" 
                                       placeholder="Número de seu candidato"
                                       value=""
                                       readonly/>
                                <small id="painel-help" class="form-text text-muted">Utilize os botões abaixo para realizar seu
                                    voto.</small>
                            </div>
                        </form>
                    </div>

                    <table class="table">
                        <tr>
                            <td><input type="button" class="btn btn-dark w-100" class="btn btn-dark" value="1"
                                       onclick="dis('1')"/>
                            </td>
                            <td><input type="button" class="btn btn-dark w-100" value="2" onclick="dis('2')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="3" onclick="dis('3')"/></td>
                        </tr>
                        <tr>
                            <td><input type="button" class="btn btn-dark w-100" value="4" onclick="dis('4')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="5" onclick="dis('5')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="6" onclick="dis('6')"/></td>
                        </tr>
                        <tr>
                            <td><input type="button" class="btn btn-dark w-100" value="7" onclick="dis('7')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="8" onclick="dis('8')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="9" onclick="dis('9')"/></td>
                        </tr>
                        <tr>
                            <td><input type="button" class="btn btn-dark w-100" value=""/></td>
                            <td><input type="button" class="btn btn-dark w-100" value="0" onclick="dis('0')"/></td>
                            <td><input type="button" class="btn btn-dark w-100" value=""/></td>
                        </tr>
                        <tr>
                            <td><input type="button" class="btn btn-dark w-100" value="BRANCO" onclick="branco()"/></td>
                            <td><input id="enviar-voto" type="button" 
                                       style="color: green"
                                       class="btn btn-dark w-100" value="VOTAR"
                                       onclick="document.getElementById('form-voto').submit()"/></td>
                            <td><input type="button" class="btn btn-dark w-100" style="color: orange"
                                       value="CORRIGIR" onclick="corrigir()"/></td>

                        </tr>
                    </table>
                </div>

                <div class="col-8">
                    <table id="tabelaCandidatos" class="table table-sm">

                        <thead class="thead-dark w-100">
                            <tr>
                                
                                <th scope="col">Nome Candidato</th>
                                <th scope="col">Numero do Candidato</th>
                                <th scope="col">Eleição</th>
                                <th scope="col">Partido</th>
                                <th scope="col">Imagem</th>

                            </tr>
                        </thead>


                        <c:forEach var="candidato" items="${listaCandidatos}">
                            <tr>
                                <td><c:out value="${candidato.nomeCandidato}"/></td>
                                <td><c:out value="${candidato.numeroCandidato}"/></td>
                                <td><c:out value="${candidato.eleicaoCandidato()}"/></td>
                                <td><c:out value="${candidato.partido.nomeCompleto}"/></td>
                                <td>
                            
                            <img src="<c:url value="./image/figuras/${candidato.numeroCandidato}.jpg"/>"
                            </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

                              <!--
        <div>
            <div>
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
            </div><div class="slick-slide" style="width: 650px;">
                <img id="lamina5" src="image/candidatos/95DF.jpg">
            </div>
        </div>
-->
        <script>

            let numero_voto = [];



            function dis(numero) {
                if (numero_voto.length > 3 || numero_voto.includes('BRANCO'))
                    return;

                numero_voto.push(numero);
                painel.value = numero_voto.join("");
            }

            function branco() {
                corrigir();
                dis('BRANCO')
            }

            function corrigir() {
                numero_voto = [];
                painel.value = "";
            }
            
            
        

        </script>


    </body>
</html>