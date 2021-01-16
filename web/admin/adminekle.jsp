<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">           
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Admin Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="gosteradminekle" method="post" autocomplete="off">
                    <div class="form-group">
                        <label for="kullanici_ad">Ad</label>
                        <input type="text" class="form-control" id="kullanici_ad" name="kullanici_ad" placeholder="Ad" required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_soyad">Soyad</label>
                        <input type="text" class="form-control" id="kullanici_soyad" name="kullanici_soyad" placeholder="Soyad"  required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_email">Email</label>
                        <input type="text" class="form-control" id="kullanici_email" name="kullanici_email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="kullanici_sifre">Şifre</label>
                        <input type="password" class="form-control" id="kullanici_sifre" name="kullanici_sifre" placeholder="Şifre" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Ekle</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>