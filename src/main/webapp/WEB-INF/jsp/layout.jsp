<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String msg = ((String) request.getAttribute("msg"));

//  List<Utilisateur> users = ((List<Utilisateur>) request.getAttribute("users"));
//  DataTable<Utilisateur> table = new DataTable<>();
//  table.setData(users);
//  table.setTitre("Liste des Utilisateurs");
//  table.addColumn("#", "id");
//  table.addColumn("Nom", "username");
//  table.addColumn("Role", "role");
//  table.addAction("Valider", "/valider");
%>

<%
    String pg = (String) (request.getAttribute("page") != null ? request.getAttribute("page") + ".jsp" : "");
//    Utilisateur u = (Utilisateur) request.getAttribute("u");
//    if (u == null) {
//        u = ((Utilisateur) request.getSession().getAttribute("u"));
//        if (u == null) {
//            response.sendRedirect("/");
//            return;
//        }
//    }
    if (msg != null) {


%>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        alert("<%=msg%>")
    })
</script>
<%
    }
%>

<%--<!doctype html>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mr Meuble</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/fonts/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css"/>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.16/dist/typed.umd.js"></script>
    <style>
        .message {
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 5px;
            max-width: 80%;
            clear: both;
        }

        .user-message {
            background-color: #e0f2f7;
            float: right;
        }

        .bot-message {
            background-color: #f0f0f0;
            float: left;
        }
    </style>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/apex-charts/apex-charts.css"/>
    <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>

</head>
<body>
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <jsp:include page="sidebar/sidebar.jsp"/>

        <div class="layout-page">
            <nav class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme" id="layout-navbar">
                <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                    <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                        <i class="bx bx-menu bx-sm"></i>
                    </a>
                </div>

                <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">

                    <ul class="navbar-nav flex-row align-items-center ms-auto">
                        <!-- Place this tag where you want the button to render. -->
                        <li class="nav-item lh-1 me-3">
                            <%="haha"%>
                        </li>

                        <!-- User -->
                        <li class="nav-item navbar-dropdown dropdown-user dropdown">
                            <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                                <div class="avatar avatar-online">
                                    <img src="/assets/img/avatars/1.png" alt="" class="w-px-40 h-auto rounded-circle">
                                </div>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <div class="d-flex">
                                            <div class="flex-shrink-0 me-3">
                                                <div class="avatar avatar-online">
                                                    <img src="/assets/img/avatars/1.png" alt="" class="w-px-40 h-auto rounded-circle">
                                                </div>
                                            </div>
                                            <div class="flex-grow-1">
                                                <span class="fw-semibold d-block"><%="usename"%></span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>


                                <li>
                                    <a class="dropdown-item" href="/logout">
                                        <i class="bx bx-power-off me-2"></i>
                                        <span class="align-middle">Se Deconnecter</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!--/ User -->
                    </ul>
                </div>
            </nav>
            <div class="flex-grow-1 container-p-y container-fluid">
                <jsp:include page="<%=pg%>"/>
            </div>


        </div>
    </div>
</div>

<script src="/assets/vendor/libs/jquery/jquery.js"></script>
<script src="/assets/vendor/libs/popper/popper.js"></script>
<script src="/assets/vendor/js/bootstrap.js"></script>
<script src="/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="/assets/vendor/libs/apex-charts/apexcharts.js"></script>
<script src="/assets/vendor/libs/jquery/jquery.js"></script>

<!-- Main JS -->
<script src="/assets/js/main.js"></script>

<!-- Page JS -->
<script src="/assets/js/dashboards-analytics.js"></script>
</body>
</html>

<script src="${pageContext.request.contextPath}/assets/js/sweetalert2.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popover.js"></script>

<script>
    function showAlert(event, element, questions) {
        event.preventDefault();

        let html = '<ul>';
        questions.forEach((question) => {
            html += '<li>' + question + '</li>';
        })
        html += '</ul>';

        var lienHref = element.getAttribute('href');
        Swal.fire({
            title: 'Confirmation :',
            html: html,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#696cff',
            cancelButtonColor: '#ffab00',
            confirmButtonText: 'Valider',
            cancelButtonText: 'Annuler',
            width: '800px',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = lienHref;
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                console.log('Action annulée!!');
            }
        });
    }

    function showAlertBeforeSubmit(event, idForm, questions) {
        // event.preventDefault();

        let html = '<ul>';
        questions.forEach((question) => {
            html += '<li>' + question + '</li>';
        })
        html += '</ul>';

        Swal.fire({
            title: 'Confirmation :',
            html: html,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#696cff',
            cancelButtonColor: '#ffab00',
            confirmButtonText: 'Valider',
            cancelButtonText: 'Annuler',
            width: '800px',
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById(idForm).submit();
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                console.log('Action annulée!!');
            }
        });
    }
</script>

<%--suggestions --%>
<script>
    function formatResponseText(text) {
        return marked.parse(text)
    }

    function displayMessage(text) {
        const messageDiv = document.createElement('div');
        messageDiv.className = 'message bot-message';
        messageDiv.innerHTML = '<span id="typed-text"></span>';
        document.getElementById('rep-div').appendChild(messageDiv);
        const typed = new Typed('#typed-text', {
            strings: [text],
            typeSpeed: 7,
            showCursor: false,
            onComplete: () => {
                window.scrollTo(0, document.body.scrollHeight);
            }
        });

        window.scrollTo(0, document.body.scrollHeight);
    }

    $(document).ready(function() {
        const $overlay = $('<div id="overlay" style="display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 999;"></div>'); // z-index inférieur au loader
        $('body').append($overlay);
        const $loader = $('<div id="loader" style="display:none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000; border: 16px solid #f3f3f3; border-top: 16px solid #3498db; border-radius: 50%; width: 120px; height: 120px; animation: spin 2s linear infinite;"></div>');
        $('body').append($loader);
        $(".fetch-suggestions").on("click", function() {
            $("#rep-div").html("");
            $("#overlay").show();
            $("#loader").show();
            var id = $(this).data("id");
            var pourcentage = $(this).data("pourcentage");
            $.ajax({
                url: "http://localhost:8000/suggestions",
                type: "GET",
                data: {
                    id: id,
                    pourcentage: pourcentage
                },
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                success: function(response) {
                    console.log("ty no valiny " + response.solution);
                    const formatted = formatResponseText(response.solution);
                    displayMessage(formatted);
                },
                error: function(xhr, status, error) {
                    console.error("An error occurred: " + error);
                },
                complete: function() {
                    $("#overlay").hide();
                    $("#loader").hide();
                }
            });
        });
    });
</script>



