<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Visualisation Google</title>
        <!-- On charge JQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages: ["corechart"]});
            // AprÃ¨s le chargement de la page, on fait l'appel AJAX

            google.setOnLoadCallback(doAjaxArticleCat);
            google.setOnLoadCallback(doAjaxGeo);
            google.setOnLoadCallback(doAjaxCustomer);

            // Fonction draw par catÃ©gorie d'article
            function drawArticleCat(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: 'CA par catÃ©gorie',
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartArticleCat'));
                chart.draw(data, options);
            }

            // Fonction draw par zone gÃ©ographique
            function drawGeo(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: 'CA par zone gÃ©ographique',
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartGeo'));
                chart.draw(data, options);
            }

            // Fonction draw par client
            function drawCustomer(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: 'CA par clients',
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartCustomer'));
                chart.draw(data, options);
            }

            // Afficher les ventes par catÃ©gorie
            function doAjaxArticleCat() {
                $.ajax({
                    // serialize() renvoie tous les paramÃ¨tres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    url: "admin",
                    dataType: "json",
                    success: // La fonction qui traite les rÃ©sultats
                            function (result) {
                                // On reformate le rÃ©sultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des donnÃ©es
                                chartData.push(["CatÃ©gorie d'article", "CA"]);
                                for (var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawArticleCat(chartData);
                            },
                    error: showError
                });
            }

            // Afficher les ventes par zone gÃ©ographique
            function doAjaxGeo() {
                $.ajax({
                    // serialize() renvoie tous les paramÃ¨tres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    url: "admin",
                    dataType: "json",
                    success: // La fonction qui traite les rÃ©sultats
                            function (result) {
                                // On reformate le rÃ©sultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des donnÃ©es
                                chartData.push(["Zone gÃ©ographique", "CA"]);
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
                    // serialize() renvoie tous les paramÃ¨tres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    url: "admin",
                    dataType: "json",
                    success: // La fonction qui traite les rÃ©sultats
                            function (result) {
                                // On reformate le rÃ©sultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des donnÃ©es
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


            // Fonction qui traite les erreurs de la requÃªte
            function showError(xhr, status, message) {
                alert("Erreur: " + status + " : " + message);
            }

        </script>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <center>
        <body>
            <!-- pÃ©riode (date de dÃ©but / date de fin) sur laquelle doit porter
    la statistique. -->
            <h1>
                <b>PAGE ADMINISTRATEUR</b>
            </h1>
            <form method="POST">
                <p> 
                    <br> Si vous voulez les CA sur une certaine pÃ©riode veuillez la sÃ©lectionner<br/>
                    <br>(sinon ils seront affichÃ©s par dÃ©faut sans critÃ¨res de date)</br> 
                </p>
                <div>
                    <label for="dateDeb">Date de dÃ©but :</label>
                    <input type="date" id="party" name="dateDeb">
                    <span class="validity"></span>
                </div>
                <div>
                    <label for="dateFin">Date de fin :</label>
                    <input type="date" id="party" name="dateFin">
                    <span class="validity"></span>
                </div>
                <div>
                    <input type="submit">
                </div>
            </form>
            <!-- Le graphique apparaÃ®t ici -->
            <div class="graph" id="piechartArticleCat" style="width: 900px; height: 500px;"></div>
            <a id = "jsonData" href='admin' target="_blank">Voir les donnÃ©es brutes (JSON)</a><br>
            <div class="graph" id="piechartGeo" style="width: 900px; height: 500px;"></div>
            <a id = "jsonData" href='admin' target="_blank">Voir les donnÃ©es brutes (JSON)</a><br>
            <div class="graph" id="piechartCustomer" style="width: 900px; height: 500px;"></div>
            <a id = "jsonData" href='admin' target="_blank">Voir les donnÃ©es brutes (JSON)</a><br>
        </body>
    </center>
</html>

