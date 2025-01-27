<%@ page import="org.main.criptoapi.fonds.Fond" %>
<script src="../../assets/js/config.js"></script><link rel="stylesheet" type="text/css" href="../../assets/vendor/css/rtl/core.css" class="template-customizer-core-css"><link rel="stylesheet" type="text/css" href="../../assets/vendor/css/rtl/theme-default.css" class="template-customizer-theme-css"><link rel="stylesheet" type="text/css" href="../../assets/vendor/css/rtl/core.css" class="template-customizer-core-css"><link rel="stylesheet" type="text/css" href="../../assets/vendor/css/rtl/theme-default.css" class="template-customizer-theme-css">

<%
    double fond= (double) request.getAttribute("fond");

%>
<div class="container-xxl flex-grow-1 container-p-y">

    <div class="row">
        <div class="col-xxl-12 mb-6 order-0">
            <div class="card">
                <div class="d-flex align-items-start row">
                    <div class="col-sm-7">
                        <div class="card-body">
                            <h3 class="card-title text-heading">Profil</h3>

                            <h6 class="card-title text-primary mb-3">Nom</h6>
                            <p class="mb-6">Jhon</p>

                            <h6 class="card-title text-primary mb-3">Prenom</h6>
                            <p class="mb-6">Doe</p>

                            <h6 class="card-title text-primary mb-3">Date naissance</h6>
                            <p class="mb-6">25-03-2009</p>

                            <h6 class="card-title text-primary mb-3">Genre</h6>
                            <p class="mb-6">M</p>

                            <h6 class="card-title text-primary mb-3">Email</h6>
                            <p class="mb-6">JhonDoe@gmail.com</p>

                        </div>
                    </div>
                    <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-6">
                            <img src="../../assets/img/illustrations/man-with-laptop.png" height="175" class="scaleX-n1-rtl" alt="View Badge User">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xxl-12 col-lg-12 col-md-6">
            <div class="row">
                <div class="col-lg-6 col-md-12 col-6 mb-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="card-title d-flex align-items-start justify-content-between mb-4">
                                <div class="avatar flex-shrink-0">
                                    <img src="../../assets/img/icons/unicons/wallet-info.png" alt="wallet info" class="rounded">
                                </div>
                            </div>
                            <p class="mb-1">Fond</p>
                            <h4 class="card-title mb-3"><%=fond%></h4>
                            <small class="text-success fw-medium"><i class="bx bx-up-arrow-alt"></i> +28.42%</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-12 col-6 mb-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="card-title d-flex align-items-start justify-content-between mb-4">
                                <div class="avatar flex-shrink-0">
                                    <img src="../../assets/img/icons/unicons/paypal.png" alt="paypal" class="rounded">
                                </div>
                            </div>
                            <p class="mb-1">Crypto</p>
                            <h4 class="card-title mb-3">$4,679</h4>
                            <small class="text-success fw-medium"><i class="bx bx-up-arrow-alt"></i> +28.42%</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Total Revenue -->

        <!--/ Total Revenue -->
        <div class="col-12 col-md-8 col-lg-12 col-xxl-4 order-3 order-md-2">

        </div>
    </div>


</div>