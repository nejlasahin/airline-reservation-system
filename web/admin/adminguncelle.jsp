<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">           
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Admin Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="gosteradminguncelle" method="post" autocomplete="off">
                    <input type="hidden" name="kullanici_id" id="kullanici_id" value="<c:out value='${kullanici.kullanici_id}' />" />
                    <div class="form-group">
                        <label for="kullanici_ad">Ad</label>
                        <input type="text" class="form-control" id="kullanici_ad" name="kullanici_ad" placeholder="Ad" value="<c:out value='${kullanici.kullanici_ad}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_soyad">Soyad</label>
                        <input type="text" class="form-control" id="kullanici_soyad" name="kullanici_soyad" placeholder="Soyad" value="<c:out value='${kullanici.kullanici_soyad}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_email">Email</label>
                        <input type="text" class="form-control" id="kullanici_email" name="kullanici_email" placeholder="Email" value="<c:out value='${kullanici.kullanici_email}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_sifre">Şifre</label>
                        <input type="password" class="form-control" id="kullanici_sifre" name="kullanici_sifre" placeholder="Şifre" value="<c:out value='${kullanici.kullanici_sifre}' />" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Güncelle</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>