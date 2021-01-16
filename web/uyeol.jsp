<%@ include file = "header.jsp" %>

<section class="text-center">
    <form class="form-signin" action="gosteruyeol" method="POST" name="UyeOl" onsubmit="return UyeolKontrol()">
        <a href="ucakbileti">
            <img class="mt-3 mb-5" src="assets/images/logo.png" alt="" width="300" height="50">
        </a>
        <h1 class="h3 mb-3 font-weight-600">Kayıt Olun</h1>
        <%
            String req = request.getParameter("durum");
            if (req != null) {
                if (req.equals("basarili")) {
                    out.print("<div class='alert alert-success' role='alert'>Kayıt başarılı. Giriş yapınız.</div>");
                } else {
                    out.print("<div class='alert alert-warning' role='alert'>Bu Email kullanılıyor.</div>");
                }
            }
        %>
        <label for="kullanici_ad" class="sr-only">Ad</label>
        <input type="text" id="kullanici_ad" name="kullanici_ad" class="form-control mb-3" placeholder="Ad" required autofocus>
        
        <label for="kullanici_soyad" class="sr-only">Soyad</label>
        <input type="text" id="kullanici_soyad" name="kullanici_soyad" class="form-control mb-3" placeholder="Soyad" required>
        
        <label for="kullanici_email" class="sr-only">E-mail</label>
        <input type="email" id="kullanici_email" name="kullanici_email" class="form-control mb-3" placeholder="E-mail" required>
        
        <label for="kullanici_sifre1" class="sr-only">Şifre</label>
        <input type="password" id="kullanici_sifre1" name="kullanici_sifre1" class="form-control mb-3" placeholder="Şifre" required>
        
        <label for="kullanici_sifre2" class="sr-only">Şifre Onayla</label>
        <input type="password" id="kullanici_sifre2" name="kullanici_sifre2" class="form-control mb-3" placeholder="Şifre Onayla" required>        
                            
        <div class="alert alert-warning" role="alert" id="1" style="display:none">Şifre en az 8 karakter uzunluğunda olmalıdır.</div>
        <div class="alert alert-warning" role="alert" id="2" style="display:none">Şifreler uyuşmuyor.</div>
        <button class="btn btn-lg btn-warning btn-block shadow-none" type="submit">Kayıt Ol</button>

        <div class="mt-3 mb-3">
            <span>Zaten üye misin ? </span><a href="giris" class="text-decoration-none" style="color: #FF7F00">Giriş Yap</a>
        </div>
    </form>
</section>

<script>
    function UyeolKontrol() {
        var formUyeOl = document.forms["UyeOl"];
        var sifre1 = formUyeOl["kullanici_sifre1"];
        var sifre2 = formUyeOl["kullanici_sifre2"];
        sifre1 = sifre1.value;
        sifre2 = sifre2.value;
        if (sifre1.length < 8) {
            $("#1").css("display", "");
            return false;
        } else {
            $("#1").css("display", "none");
        }
        if (sifre1 === sifre2) {
            return true;
        } else {
            $("#2").css("display", "");
        }
        return false;
    }
</script>

<%@ include file = "footer.jsp" %>