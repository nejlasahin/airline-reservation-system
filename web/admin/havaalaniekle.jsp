<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Havaalanı Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gosterhavaalaniekle">
                    <div class="form-group">
                        <label for="havaalani_ad">Ad</label>
                        <input type="text" class="form-control" id="havaalani_ad" name="havaalani_ad" placeholder="Havaalanı Adı" required>
                    </div>
                    <div class="form-group">
                        <label for="havaalani_kod">Kod</label>
                        <input type="text" class="form-control" id="havaalani_kod" name="havaalani_kod" placeholder="Havaalanı Kodu" required>
                    </div>
                    <div class="form-group">
                        <label for="havaalani_sehir_id">Şehir</label>
                        <select class="form-control" id="havaalani_sehir_id" name="havaalani_sehir_id" required>
                            <c:forEach var="sehir" items="${havaalanisehir}">
                                <option value="<c:out value='${sehir.havaalani_sehir_id}' />"><c:out value="${sehir.havaalani_sehir_ad}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="havaalani_ulke_id">Ülke</label>
                        <select class="form-control" id="havaalani_ulke_id" name="havaalani_ulke_id" required>
                            <c:forEach var="ulke" items="${havaalaniulke}">
                                <option value="<c:out value='${ulke.havaalani_ulke_id}' />"><c:out value="${ulke.havaalani_ulke_ad}" /></option>
                            </c:forEach>
                        </select>
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