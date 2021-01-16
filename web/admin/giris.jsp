<%@ include file = "header.jsp" %>
<body class="bg-gradient-white">

<div class="container">
    <div class="row justify-content-center">
        <a href="../" class="img-fluid mb-5 mt-5">
            <img src="../assets/images/logo.png" width="360" height="60" class="d-inline-block align-top" alt="">
        </a>
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Tekrar Hoş Geldin</h1>
                                    <%
                                        String req = request.getParameter("durum");
                                        if (req != null) {
                                            out.print("<div class='alert alert-warning' role='alert'>Giriş Başarısız.</div>");
                                        }
                                    %>
                                </div>
                                <form class="user" method="post" action="gostergiris">
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user"
                                               id="admin_email" name="admin_email" placeholder="E-mail">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" id="admin_sifre" name="admin_sifre" placeholder="Şifre">
                                    </div>
                                    <button type="submit" class="btn btn-dark btn-user btn-block">
                                        Giriş Yap
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="js/sb-admin-2.min.js"></script>

</body>
</html>