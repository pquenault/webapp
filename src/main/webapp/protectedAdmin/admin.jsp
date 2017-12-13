<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Visualisation Google</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">

            google.charts.load('current', {'packages': ['corechart']});
            google.load("visualization", "1", {packages: ["corechart"]});

            google.setOnLoadCallback(doAjaxArticleCat);
            google.setOnLoadCallback(doAjaxGeo);
            google.setOnLoadCallback(doAjaxCustomer);

            // Exécuté à la fin du chargement de la page

            /*
             $(document).ready(
             function () {
             doAjaxArticleCat();
             doAjaxGeo();
             doAjaxCustomer();
             }
             );
             */
            // Fonction draw par catégorie d'article
            function drawArticleCat(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: "CA par catégorie",
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartArticleCat'));
                chart.draw(data, options);
            }

            // Fonction draw par zone géographique
            function drawGeo(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: "CA par zone géographique",
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartGeo'));
                chart.draw(data, options);
            }

            // Fonction draw par client
            function drawCustomer(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: "CA par client",
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartCustomer'));
                chart.draw(data, options);
            }

            // Afficher les ventes par catégorie
            function doAjaxArticleCat() {
                $.ajax({
                    // serialize() renvoie tous les paramètres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    data: {"action": "category"},
                    url: "appController",
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                            function (result) {
                                // On reformate le résultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des données
                                chartData.push(["Catégorie d'article", "CA"]);
                                for (var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawArticleCat(chartData);
                            },
                    error: showError
                });
            }

            // Afficher les ventes par zone géographique
            function doAjaxGeo() {
                $.ajax({
                    // serialize() renvoie tous les paramètres saisis dans le formulaire
                    data: {"action": "state"},
                    url: "appController",
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                            function (result) {
                                // On reformate le résultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des données
                                chartData.push(["Zone géographique", "CA"]);
                                for (var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawGeo(chartData);
                            },
                    error: showError
                });
            }

            // Afficher les ventes par client
            function doAjaxCustomer() {
                $.ajax({
                    // serialize() renvoie tous les paramètres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    data: {"action": "customer"},
                    url: "appController",
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                            function (result) {
                                // On reformate le résultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des données
                                chartData.push(["Client", "CA"]);
                                for (var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawCustomer(chartData);
                            },
                    error: showError
                });
            }


            // Fonction qui traite les erreurs de la requête
            function showError(xhr, status, message) {
                alert("Erreur: " + status + " : " + message);
            }

        </script>
        <style>
            body {
                font-family: Verdana, Geneva, sans-serif;
            }

            h1 {
                color : orange;
            }

            #dateForm {
                margin-bottom: 5%;
            }

            #connectButton {
                background: none;
                border: none;
                background-color: orange;
                padding: 10px;
                box-shadow : 2px 2px 2px #82909D;
                position: relative;
                float: left;
            }

            #connectButton:hover {
                padding: 12px;
                box-shadow: 10px 5px 5px #525C65;
                position: relative;
                float: left;
            }

        </style>
    </head>
    <center>
        <body>
            <form action="<c:url value="appController"/>" method="GET">
                <button id = "connectButton" type="submit" name="action" value="logOut">Se déconnecter</button>
            </form>
            <!-- pÃ©riode (date de dÃ©but / date de fin) sur laquelle doit porter
    la statistique. -->
            <h1>
                <b>PAGE ADMINISTRATEUR</b> 
            </h1>
            <!-- POUR FAIRE CE FORMULAIRE INSPIRE TOI DE USER.JSP -->
            <form id = "dateForm" method="POST">
                <p> 
                    <br> Si vous voulez les CA sur une certaine période veuillez la sélectionner<br/>
                    <br>(sinon ils seront affichés par défaut sans critères de date)</br> 
                </p>
                <div>
                    <label for="dateDeb">Date de début :</label>
                    <input type="date" id="party" name="dateDeb">
                    <span class="validity"></span>
                </div>
                <div>
                    <label for="dateFin">Date de fin :</label>
                    <input type="date" id="party" name="dateFin">
                    <span class="validity"></span>
                </div>

            </form>

            <!-- Les graphiques -->
            <div class="graph" id="piechartArticleCat" style="width: 900px; height: 500px;"></div>
            <div class="graph" id="piechartGeo" style="width: 900px; height: 500px;"></div>
            <div class="graph" id="piechartCustomer" style="width: 900px; height: 500px;"></div>
            <p>
                <a href="https://google-developers.appspot.com/chart/interactive/docs/gallery/piechart#3D" target="_blank">visualisations Google</a>
                pour plus de détails
            </p>
        </body>
    </center>
</html>
