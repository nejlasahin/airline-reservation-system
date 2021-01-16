<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm border-bottom">
        <a class="navbar-brand" href="ucakbileti">
            <img src="assets/images/logo.png" width="240" height="40" class="d-inline-block align-top" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav ml-5">
                <li class="nav-item active">
                    <a class="nav-link" href="ucakbileti">Uçak Bileti</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="iletisim">İletişim</a>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item active">
                <%
                        if (session.getAttribute("kullanici_yetki")==null) {
                            out.print("<a class='nav-link btn shadow-none px-4' href='giris'>Giriş Yap</a>");
                        }else if (session.getAttribute("kullanici_yetki").equals(1)) {
                            out.print("<li class='nav-item dropdown no-arrow active'>"
                                    + "<a class='nav-link dropdown-toggle' href='#' id='userDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                                    + "<span class='mr-2 d-none d-lg-inline text-gray-600'>Hesabım</span>"
                                    + "</a>"
                                    + "<div class='dropdown-menu dropdown-menu-right shadow animated--grow-in ' aria-labelledby='userDropdown'>"
                                    + "<a class='dropdown-item' href='profil'>"
                                    + "<i class='fas fa-user fa-sm fa-fw mr-2 text-gray-400'></i>"
                                    + "Profil</a>"
                                    + "<a class='dropdown-item' href='rezervasyonislemlerim'>"
                                    +"<i class='fas fa-cogs fa-sm fa-fw mr-2 text-gray-400'></i>"
                                    +"Rezervasyon <br> İşlemlerim"
                                +"</a>"
                                +"<div class='dropdown-divider'></div>"
                                +"<a class='dropdown-item'href='cikis'>"
                                    +"<i class='fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400'></i>"
                                    +"Çıkış"
                                +"</a>"
                            +"</div>"
                        +"</li>");
                    }else {
                            out.print("<li class='nav-item dropdown no-arrow active'>"
                                    + "<a class='nav-link dropdown-toggle' href='#' id='userDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                                    + "<span class='mr-2 d-none d-lg-inline text-gray-600'>Hesabım</span>"
                                    + "</a>"
                                    + "<div class='dropdown-menu dropdown-menu-right shadow animated--grow-in ' aria-labelledby='userDropdown'>"
                                    + "<a class='dropdown-item' href='admin/panel'>"
                                    +"<i class='fas fa-cogs fa-sm fa-fw mr-2 text-gray-400'></i>"
                                    +"Admin Paneli"
                                +"</a>"
                                +"<div class='dropdown-divider'></div>"
                                +"<a class='dropdown-item'href='cikis'>"
                                    +"<i class='fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400'></i>"
                                    +"Çıkış"
                                +"</a>"
                            +"</div>"
                        +"</li>");
                    }
                %>
                </li>
                <%
                    if (session.getAttribute("kullanici_yetki")==null) {
                        out.print("<li class='nav-item active'>"
                                + "<a class='nav-link btn btn-outline-warning shadow-none px-4' href='uyeol'>Üye Ol</a>"
                                + "<li>");
                    }
                %>
            </ul>
        </div>
    </nav>