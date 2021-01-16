<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Uçuş Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gosterucusguncelle">
                    <input type="hidden" name="ucus_id" id="ucus_id" value="<c:out value='${ucus.ucus_id}' />" />
                    <div class="form-group">
                        <label for="ucus_kalkis_id">Uçuş Kalkış</label>
                        <select class="form-control" id="ucus_kalkis_id" name="ucus_kalkis_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <c:choose>
                                    <c:when test= "${havaalani.havaalani_id == ucus.ucus_kalkis_id}">
                                        <option value="<c:out value='${havaalani.havaalani_id}' />" selected><c:out value='${havaalani.havaalani_ad}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${havaalani.havaalani_id}' />"><c:out value='${havaalani.havaalani_ad}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_varis_id">Uçuş Varış</label>
                        <select class="form-control" id="ucus_varis_id" name="ucus_varis_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <c:choose>
                                    <c:when test= "${havaalani.havaalani_id == ucus.ucus_varis_id}">
                                        <option value="<c:out value='${havaalani.havaalani_id}' />" selected><c:out value='${havaalani.havaalani_ad}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${havaalani.havaalani_id}' />"><c:out value='${havaalani.havaalani_ad}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_tarih">Uçuş Tarih</label>
                        <input type="date" class="form-control" id="ucus_tarih" name="ucus_tarih" value="<c:out value='${ucus.ucus_tarih}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="ucus_saat">Uçuş Saat</label>
                        <input type="time" class="form-control" id="ucus_saat" name="ucus_saat" value="<c:out value='${ucus.ucus_saat}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="ucus_sure">Uçuş Süre</label>
                        <input type="time" class="form-control" id="ucus_sure" name="ucus_sure" value="<c:out value='${ucus.ucus_sure}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="firma_id">Firma Adı</label>
                        <select class="form-control" id="firma_id" name="firma_id" required>
                            <c:forEach var="firma" items="${firma}">
                                <c:choose>
                                    <c:when test= "${firma.firma_id == ucus.firma_id}">
                                        <option value="<c:out value='${firma.firma_id}' />" selected><c:out value='${firma.firma_ad}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${firma.firma_id}' />"><c:out value='${firma.firma_ad}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucak_id">Uçak Adı</label>
                        <select class="form-control" id="ucak_id" name="ucak_id" required>
                            <c:forEach var="ucak" items="${ucak}">
                                <c:choose>
                                    <c:when test= "${ucak.ucak_id == ucus.ucak_id}">
                                        <option value="<c:out value='${ucak.ucak_id }' />" selected><c:out value='${ucak.ucak_ad}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${ucak.ucak_id}' />"><c:out value='${ucak.ucak_ad}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_ucret">Uçuş Ücreti</label>
                        <input type="number" min="1" step="any" class="form-control" id="ucus_ucret" name="ucus_ucret" placeholder="Uçuş Ücreti" value="<c:out value='${ucus.ucus_ucret}' />"required>
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