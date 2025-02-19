<%@ page import="java.util.List" %>
<%@ page import="org.main.criptoapi.histoCrypto.HistoCrypto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<HistoCrypto> histoCryptoList = (List<HistoCrypto>) request.getAttribute("histoCrypto");
    List<HistoCrypto> actuelles = (List<HistoCrypto>) request.getAttribute("actuelles");
%>

<html>
<head>
    <title>Evolution des cours des crypto-monnaies</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-moment@latest"></script>
</head>
<body>
<canvas id="myChart"></canvas>
<div>
    <script>
        const ctx = document.getElementById('myChart').getContext('2d');
        const moment = window.moment; // Access Moment.js from the global scope

        // Préparation des données pour le graphique
        const dataByCrypto = {};

        <% for (HistoCrypto histoCrypto : histoCryptoList) { %>
        (function() {
            const idCrypto = "<%= histoCrypto.getIdCrypto().getId() %>";
            if (!dataByCrypto[idCrypto]) {
                dataByCrypto[idCrypto] = {
                    label: "<%= histoCrypto.getIdCrypto().getNom() %>",
                    data: [],
                    borderColor: `rgba(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, 1)`,
                    borderWidth: 1
                };
            }
            dataByCrypto[idCrypto].data.push({
                x: moment("<%= histoCrypto.getDaty() %>").toISOString(),
                y: <%= histoCrypto.getValeur() %>
            });
        })();
        <% } %>

        const datasets = Object.values(dataByCrypto);

        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                datasets: datasets
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            parser: 'YYYY-MM-DDTHH:mm:ssZ',
                            unit: 'second',
                            displayFormats: {
                                second: 'HH:mm:ss'
                            }
                        },
                        title: {
                            display: true,
                            text: 'Temps'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Valeur'
                        }
                    }
                }
            }
        });

        // Rediriger vers /dashboard toutes les 10 secondes
        setTimeout(function() {
            window.location.href = "/dashboard/";  // Redirige vers le contrôleur /dashboard
        }, 10000);  // 10 secondes (10000 ms)
    </script>
</div>
<div>
    <table>
        <thead>
            <tr>
                <th>IdCrypto</th>
                <th>Date</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (int i = 0; i < actuelles.size(); i++) {
                    %>
                        <tr>
                            <td><%= actuelles.get(i).getIdCrypto().getId() %></td>
                            <td><%= actuelles.get(i).getDaty() %></td>
                            <td><%= actuelles.get(i).getValeur() %></td>
                        </tr>
                    <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
