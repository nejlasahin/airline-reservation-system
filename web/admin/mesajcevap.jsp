<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Mesaj Cevapla</h3>
            </div>
            <div class="card-body">
                <form class="form" autocomplete="off" method="post" action="gostermesajcevapla">
                    <input type="hidden" name="mesaj_id" id="mesaj_id" value="<c:out value='${mesaj.mesaj_id}' />" />
                    <div class="form-group">
                        <label for="mesaj_adsoyad">Gönderen Ad Soyad</label>
                        <input type="text" class="form-control" id="mesaj_adsoyad" name="mesaj_adsoyad" value="<c:out value='${mesaj.mesaj_adsoyad}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="mesaj_tarih">Tarih</label>
                        <input type="text" class="form-control" id="mesaj_tarih" name="mesaj_tarih" value="<c:out value='${mesaj.mesaj_tarih}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="mesaj_email">Gönderen Email</label>
                        <input type="text" class="form-control" id="mesaj_email" name="mesaj_email" value="<c:out value='${mesaj.mesaj_email}' />" readonly>
                    </div>                           
                    <div class="form-group">
                        <label for="mesaj_konu">Konu</label>
                        <input type="text" class="form-control" id="mesaj_konu" name="mesaj_konu" value="<c:out value='${mesaj.mesaj_konu}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="mesaj_icerik">İçerik</label>
                        <textarea class="form-control" id="mesaj_icerik" name="mesaj_icerik" rows="5" readonly><c:out value='${mesaj.mesaj_icerik}' /></textarea>
                    </div>
                    <div class="form-group">
                        <label for="cevap_baslik">Cevap Başlığı</label>
                        <input type="text" class="form-control" id="cevap_baslik" name="cevap_baslik" required>
                    </div>
                    <div class="form-group">
                        <label for="cevap_icerik">Cevap İçeriği</label>
                        <textarea class="form-control" id="cevap_icerik" name="cevap_icerik" rows="5" required></textarea>
                    </div>    
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Cevapla</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>