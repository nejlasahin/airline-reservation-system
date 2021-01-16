<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Şehir Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gostersehirguncelle">
                    <input type="hidden" name="havaalani_sehir_id" id="havaalani_sehir_id" value="<c:out value='${sehir.havaalani_sehir_id}' />" />
                    <div class="form-group">
                        <label for="havaalani_sehir_ad">Şehir Adı</label>
                        <input type="text" class="form-control" id="havaalani_sehir_ad" name="havaalani_sehir_ad" value="<c:out value='${sehir.havaalani_sehir_ad}' />" required>
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