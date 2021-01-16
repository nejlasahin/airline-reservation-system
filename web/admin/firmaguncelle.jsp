<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Firma Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="gosterfirmaguncelle" method="post"  autocomplete="off" enctype="multipart/form-data">
                    <input type="hidden" name="firma_id" id="firma_id" value="<c:out value='${firma.firma_id}' />" />
                    <input type="hidden" name="logo" id="logo" value="<c:out value='${firma.firma_logo}' />" />
                    <div class="form-group">
                        <label for="firma_ad">Ad</label>
                        <input type="text" class="form-control" id="firma_ad" name="firma_ad" placeholder="Firma Adı" value="<c:out value='${firma.firma_ad}' />" required>
                    </div>
                    <div class="form-group">   
                        <label for="firma_logo">Logo</label>
                        <input type="file" class="form-control-file" id="firma_logo" name="firma_logo" accept="image/*"  required>
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