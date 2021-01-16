<%@ include file = "header.jsp" %>
<section class="text-center mb-5">
    <form class="form-signin" action="gostergiris" method="post">
        <a href="ucakbileti">
            <img class="mt-3 mb-5" src="assets/images/logo.png" alt="" width="300" height="50">
        </a>
        <h1 class="h3 mb-3 font-weight-600">Tekrar Hoş Geldiniz</h1>
        <%
            String req = request.getParameter("durum");
            if (req != null) {
                out.print("<div class='alert alert-warning' role='alert'>Giriş başarısız.</div>");
            }
            String r = request.getParameter("rezervasyon");
            if (r != null) {
                out.print("<div class='alert alert-warning' role='alert'>Rezervasyon işlemini gerçekleştirmek için giriş yapınız.</div>");
            }
        %>
        <label for="kullanici_email" class="sr-only">E-mail</label>
        <input type="email" id="kullanici_email" name="kullanici_email" class="form-control mb-3" placeholder="E-mail" required autofocus>
        
        <label for="kullanici_sifre" class="sr-only">Şifre</label>
        <input type="password" id="kullanici_sifre" name="kullanici_sifre" class="form-control" placeholder="Şifre" required>
                
        <button class="btn btn-lg btn-warning btn-block shadow-none" type="submit">Giriş Yap</button>
    </form>
        
    <div class="mt-3 mb-3" style="font-weight: 600" >
        <a href="sifremiunuttum" class="text-decoration-none" style="color: darkblue">Şifrenizi mi unuttunuz ?</a>
    </div>
    <div class="mt-3 mb-3">
        <span>Henüz üye olmadın mı ? </span><a href="uyeol" class="text-decoration-none" style="color: #FF7F00">Üye Ol</a>
    </div>
        
</section>
<%@ include file = "footer.jsp" %>