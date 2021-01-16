<%@ include file = "header.jsp" %>
<%@ include file = "navigasyon.jsp" %>

<div id="contact">
    <div class="hero-text w-100 text-white px-2 px-sm-0">
        <h2 class="display-4 mt-5" style="font-weight: bold">İLETİŞİM</h2>
    </div>
</div>
<section class="container mb-4">
    <h2 class="h1-responsive font-weight-bold text-center my-4">Bize Yazın</h2>
    <p class="text-center w-responsive mx-auto mb-5">Sormak istediğiniz bir şey mi var? Lütfen doğrudan bizimle iletişime geçmekten çekinmeyin. Ekibimiz birkaç saat içinde size geri dönecektir.</p>
    
    <%        
    String req = request.getParameter("durum");
        if (req != null) {
            if (req.equals("basarili")) {
                out.print("<div class=' alert alert-success mt-3' role='alert'>Mesajınız başarıyla gönderilmiştir.</div>");
            }
        }
    %>
    
    <div class="row">
        <div class="col-md-9 mb-md-0 mb-5">
            <form id="contact-form" method="post" name="contact-form" action="gostermesajekle">
                <div class="row">
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <label for="mesaj_adsoyad" class="">İsim Soyisim</label>
                            <input type="text" id="mesaj_adsoyad" name="mesaj_adsoyad" class="form-control">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <label for="mesaj_email" class="">E-mail</label>
                            <input type="text" id="mesaj_email" name="mesaj_email" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="md-form mb-0">
                            <label for="mesaj_konu" class="">Konu</label>
                            <input type="text" id="mesaj_konu" name="mesaj_konu" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="md-form">
                            <label for="mesaj_icerik">Mesaj</label>
                            <textarea type="text" id="mesaj_icerik" name="mesaj_icerik" rows="5" class="form-control md-textarea"></textarea>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-warning shadow-none btn-lg btn-block mt-4 mb-5" style="font-weight: 600;">Gönder</button>
            </form>
        </div>
        <div class="col-md-3 text-center">
            <ul class="list-unstyled mb-0">
                <li><i class="fas fa-map-marker-alt fa-2x"></i>
                    <p>İstanbul, KA 34734, TR</p>
                </li>

                <li><i class="fas fa-phone mt-4 fa-2x"></i>
                    <p>+ 01 234 567 89</p>
                </li>

                <li><i class="fas fa-envelope mt-4 fa-2x"></i>
                    <p>iletisim@hawkeye.com</p>
                </li>
            </ul>
        </div>
    </div>
</section>
<%@ include file = "footer.jsp" %>