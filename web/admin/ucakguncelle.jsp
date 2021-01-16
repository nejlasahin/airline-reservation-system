<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
    <div class="container-fluid">
        <div class="col-md-8 mx-auto mb-5">
                    <div class="card card-outline-secondary">
                        <div class="card-header">
                            <h3 class="mb-0 text-white">Uçak Güncelle</h3>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" method="post" autocomplete="off" action="gosterucakguncelle">
                                <input type="hidden" name="ucak_id" id="ucak_id" value="<c:out value='${ucak.ucak_id}' />" />
                                <div class="form-group">
                                    <label for="ucak_ad">Uçak Adı</label>
                                    <input type="text" class="form-control" id="ucak_ad" name="ucak_ad" placeholder="Uçak Adı" value="<c:out value='${ucak.ucak_ad}' />" required>
                                </div>
                                <div class="form-group">
                                    <label for="firma_id">Uçak Firması</label>
                                    <select class="form-control" id="firma_id" name="firma_id" required>
                                        <c:forEach var="firma" items="${firma}">
                                            <c:choose>
                                            <c:when test= "${firma.firma_id == ucak.firma_id}">
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
                                    <label for="ucak_koltuk">Uçak Koltuk Sayısı</label>
                                    <input type="text" class="form-control" id="ucak_koltuk" name="ucak_koltuk" placeholder="Uçak Koltuk Sayısı" value="<c:out value='${ucak.ucak_koltuk}' />" required>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark btn-block">Güncelle</button>
                                </div>
                            </form>
                        </div>
                    </div>
    </div>
<%@ include file = "footer.jsp" %>