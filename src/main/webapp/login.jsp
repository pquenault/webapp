<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <style>
            body {
                height:100%;
                padding-bottom: 30%;
                font-family: Verdana, Geneva, sans-serif;
                margin-top:20%;
                background: -webkit-linear-gradient(bottom right, #E3B621, white);
                background: -o-linear-gradient(bottom right, #E3B621, white);
                background: -moz-linear-gradient(bottom right, #E3B621, white);
                background: linear-gradient(to bottom right, #E3B621, white);
               
            }
            #errorMessage{
                color:red;
            }
            #connectButton {
                margin-top: 2%;
                background: none;
                border: none;
                background-color: orange;
                padding: 10px;
                box-shadow : 2px 2px 2px #82909D;
            }

            #connectButton:hover {
                padding: 12px;
                box-shadow : 5px 2px 2px #82909D;
            }
        </style>
    </head>
    <center>
        <body>
            <h1>Micro-Market</h1>

            <!-- POST pour ne pas afficher le mdp dans la requête -->
            <form action="<c:url value="appController"/>" method="POST">
                <div>
                    <label>
                        <p>Identifiant</p>
                        <input type="text" name="login" required="true">
                    </label>
                </div>
                <div>
                    <label>
                        <p>Mot de passe</p>
                        <input type="password" name="password" required="true">
                    </label>
                </div>
                <div>
                    <button id="connectButton" type="submit" name="action" value="logIn">Se connecter</button>
                </div>
            </form>
            <%--  On montre un éventuel message d'erreur --%>
            <div id="errorMessage"><p>${message}</p></div>
        </body>
    </center>
</html>
