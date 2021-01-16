<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Ülke Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gosterulkeekle">
                    <div class="form-group">
                        <label for="havaalani_ulke_ad">Ülke Adı</label>
                        <input type="text" class="form-control" id="havaalani_ulke_ad" name="havaalani_ulke_ad" placeholder="Ülke Adı" required>
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