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

            // Exécuté à la fin du chargement de la page
            $(document).ready(
                    function () {
                        doAjaxArticleCat();
                        doAjaxGeo();
                        doAjaxCustomer;
                    }
            );

            // Fonction draw par catÃ©gorie d'article
            function drawArticleCat(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: "CA par catégorie",
                    is3D: false
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechartArticleCat'));
                chart.draw(data, options);
            }

            // Fonction draw par zone gÃ©ographique
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

            // Afficher les ventes par catÃ©gorie
            function doAjaxArticleCat() {
                $.ajax({
                    // serialize() renvoie tous les paramÃ¨tres saisis dans le formulaire
                    //data: $("#codeForm").serialize(),
                    data: {"action": "category"},
                    url: "appController",
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
                    data: {"action": "state"},
                    url: "appController",
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
                    data: {"action": "customer"},
                    url: "appController",
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
    </head>
    <body>
        <div>
            <form action="<c:url value="appController"/>" method="GET">
                <button type="submit" name="action" value="logOut">Se déconnecter</button>
            </form>
        </div>
        <!-- pÃ©riode (date de dÃ©but / date de fin) sur laquelle doit porter
la statistique. -->
        <h1>
            <b>PAGE ADMINISTRATEUR</b> 
        </h1>

        <!-- POUR FAIRE CE FORMULAIRE INSPIRE TOI DE USER.JSP -->
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

        <!-- Les graphiques -->
        <div class="graph" id="piechartArticleCat" style="width: 900px; height: 500px;"></div>
        <div class="graph" id="piechartGeo" style="width: 900px; height: 500px;"></div>
        <div class="graph" id="piechartCustomer" style="width: 900px; height: 500px;"></div>
    </body>
</html>
