<%-- 
    Document   : urna.jsp
    Created on : 17/11/2019, 10:22:22
    Author     : Emm
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<!DOCTYPE html>

<html>
    <head>
        <title>Simulador</title>
        <link type="text/css" rel="stylesheet" href="css/ui-lightness/jquery-ui-1.10.4.css">
        <link type="text/css" rel="stylesheet" href="css/lib/slick.css">
        <link type="text/css" rel="stylesheet" href="css/urna.css">
        <link type="text/css" rel="stylesheet" href="css/deputado.css">
        <script type="text/javascript" src="js/lib/jquery.min.js"></script>
        <script type="text/javascript" src="js/lib/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/lib/jquery.pulse.min.js"></script>
        <script type="text/javascript" src="js/lib/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="js/lib/slick.min.js"></script>
        <script type="text/javascript" src="js/lib/jquery.noty.packaged.min.js"></script>
        <script type="text/javascript" src="js/urna.js"></script>
        <script type="text/javascript" src="js/depFederal.js"></script>
    </head>
    <body bgcolor="#ffffff">
        <div class="listaCandidatos slick-initialized slick-slider" title="Consulte a liste de candidatos" style="top: 5px;">
					<div class="slick-list draggable" tabindex="0">
                                            <div class="slick-track" style="opacity: 1; width: 3250px; transform: translate3d(0px, 0px, 0px)">
                                             <a href="${pageContext.request.contextPath}/candidatos">Candidatos</a>
                                            </div>
                                            <!--
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
                                                </div><div class="slick-slide" style="width: 650px;">
                                                    <img id="lamina5" src="image/candidatos/95DF.jpg">
                                                </div>-->
                                        </div>
        </div>
					
	<button type="button" class="slick-prev slick-disabled" style="display: block;">Previous</button>
        <button type="button" class="slick-next" style="display: block;">Next</button>
        </div>
        
        <div id="conteudo">
            <img id="tela" src="image/tela.jpg" width="451" height="423" border="0">
            <img id="topo" src="image/topo.jpg" width="192" height="183" border="0">
            <img id="faixaDir" src="image/faixaDir.jpg" width="38" height="357" border="0">
            <img id="ladoEsqTec" src="image/ladoEsqTec.jpg" width="19" height="160" border="0">
            <img id="n_1" src="image/n1.jpg" width="51" height="41" border="0" style="cursor: pointer;">
            <img id="n_2" src="image/n2.jpg" width="48" height="41" border="0" style="cursor: pointer;">
            <img id="n_3" src="image/n3.jpg" width="48" height="41" border="0" style="cursor: pointer;">
            <img id="ladoDirTec" src="image/ladoDirTec.jpg" width="26" height="152" border="0">
            <img id="n_4" src="image/n4.jpg" width="51" height="42" border="0" style="cursor: pointer;">
            <img id="n_5" src="image/n5.jpg" width="48" height="42" border="0" style="cursor: pointer;">
            <img id="n_6" src="image/n6.jpg" width="48" height="42" border="0" style="cursor: pointer;">
            <img id="n_7" src="image/n7.jpg" width="51" height="41" border="0" style="cursor: pointer;">
            <img id="n_8" src="image/n8.jpg" width="48" height="41" border="0" style="cursor: pointer;">
            <img id="n_9" src="image/n9.jpg" width="48" height="41" border="0" style="cursor: pointer;">
            <img id="ptabaixo7" src="image/ptabaixo7.jpg" width="51" height="36" border="0">
            <img id="n_0" src="image/n0.jpg" width="56" height="36" border="0" style="cursor: pointer;">
            <img id="ptabaixo9" src="image/ptabaixo9.jpg" width="40" height="28" border="0">
            <img id="confirma" src="image/confirma.jpg" width="66" height="49" border="0" style="cursor: pointer;">
            <img id="branco" src="image/branco.jpg" width="63" height="41" border="0" style="cursor: pointer;">
            <img id="corrige" src="image/corrige.jpg" width="63" height="41" border="0" style="cursor: pointer;">
            <img id="abaixoTec" src="image/abaixoTec.jpg" width="226" height="27" border="0">
        </div>
        <div id="painel">
            <div id="fase">Treinamento</div>
            <div id="cabecalho" style="display: block;">SEU VOTO PARA</div>
            <div id="cxFoto" style="display: none;"><img id="foto" src="" width="135" height="145"></div>
            <div id="cargo">Deputado Federal</div>
            <span id="habilitaNumeros">true</span>
            <div id="numeros">
                <div id="numeroLabel" style="display: block;">Número:</div>
                <div id="cxNumero1" style="opacity: 1;">1</div>
                <div id="cxNumero2" style="opacity: 1;">1</div>
                <div id="cxNumero3" style="opacity: 1;">2</div>
                <div id="cxNumero4" style="opacity: 1;">3</div>
            </div>
            <div id="avisoErrado" style="display: block;">NÚMERO ERRADO</div>
            <div id="avisoInexistente" style="display: none;">CANDIDATO INEXISTENTE</div>
            <div id="avisoNulo" style="color: rgb(90, 90, 90); display: block;">VOTO NULO</div>
            <div id="avisoLegenda" style="color: rgb(90, 90, 90); display: none;">VOTO DE LEGENDA</div>
            <div id="avisoBranco" style="color: rgb(90, 90, 90);">VOTO EM BRANCO</div>
            <div id="candidato" style="display: none;">
                <div id="candidatoLabel">Nome:</div>
                <div id="candidatoNome"></div>
            </div>
            <div id="partido">
                <div id="partidoLabel" style="display: none;">Partido:</div>
                <div id="partidoNome" style="display: none;"></div>
            </div>
            <div id="resultado"></div>
            <div id="regua" style="display: block; width: 545px;"></div>
            <span id="habilitaConfirma">true</span>
            <span id="habilitaCorrige">true</span>

	        <div id="instrucoes" style="display: block;">
                <div id="obs" style="color: rgb(90, 90, 90); display: none;">(voto de legenda)</div>
                <span id="textoInstrucoes">Aperte a tecla:</span><br>
                <span id="verde">CONFIRMA</span><span id="restoVerde">para CONFIRMAR este voto</span><br>
                <span id="laranja">CORRIGE</span><span id="restoLaranja">para REINICIAR este voto</span><br>
            </div>
        </div>
        <div id="reiniciarVotacao">
            <a href="TSE_TESTE.html" target="_parent">Nova simulação</a>
        </div>
        <audio id="audioOps">
            <source src="sons/ops.mp3" type="audio/mp3">
            <source src="sons/ops.wav" type="audio/wav">
            <div style="display:none">
                    <object id="mediaPlayer" type="audio/mpeg" width="1" height="1">
                        <param name="src" value="sons/ops.mp3">
                    </object>
            </div>
        </audio>
        <audio id="audioInter">
            <source src="sons/inter.mp3" type="audio/mp3">
            <source src="sons/inter.wav" type="audio/wav">
            <div style="display:none">
                <object id="mediaPlayer" type="audio/mpeg" width="1" height="1">
                    <param name="src" value="sons/inter.mp3">
                </object>
            </div>
        </audio>
    

</body>
</html>