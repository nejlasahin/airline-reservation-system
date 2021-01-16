<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Şehir Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gostersehirekle">
                    <div class="form-group">
                        <label for="havaalani_sehir_ad">Şehir Adı</label>
                        <input type="text" class="form-control" id="havaalani_sehir_ad" name="havaalani_sehir_ad" placeholder="Şehir Adı" required>
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