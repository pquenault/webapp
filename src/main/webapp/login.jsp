<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <style>
            div p {
                color:red;
            }
        </style>
    </head>
    <body>
        <h1>Accéder à l'application</h1>

        <!-- POST pour ne pas afficher le mdp dans la requête -->
        <form action="<c:url value="appController"/>" method="POST">
            <label>
                <p>Identifiant</p>
                <input type="text" name="login" required="true">
            </label>
            <label>
                <p>Mot de passe</p>
                <input type="password" name="password" required="true">
            </label>
            <button type="submit" name="action" value="logIn">Se connecter</button>
        </form>
        <%--  On montre un éventuel message d'erreur --%>
        <div><p>${message}</p></div>
    </body>
</html>
