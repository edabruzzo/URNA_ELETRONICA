<%-- 
    Document   : eleitor
    Created on : 20/11/2019, 14:29:28
    Author     : Emm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Eleitor</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        

        <div class="col-4">
            
             <h2>Cadastro Eleitor</h2>
            <form id="form-eleitor" method="post"
                  action="${pageContext.servletContext.contextPath}/eleitor">

                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input id="nome" name="nome" class="w-100" type="title">
                </div>

                <div>        
                    <label for="idade">Idade:</label>
                    <input id="idade" name="idade" 
                           class="w-100" type="title">
                </div>

                <div>        
                    <label for="idade">RG:</label>
                    <input id="rg" name="rg" 
                           class="w-100" type="title" placeholder="RG" value="">
                </div>
                <div>        
                    <input type="submit" value="Cadastrar">
                    <input type="reset" value="Limpar">
                </div>

            </form>

        </div>

    </body>
</html>
