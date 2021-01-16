<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Uçuş Oluştur</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" name="ucus" autocomplete="off" action="gosterucusolustur" onsubmit="return Kontrol()">
                    <div class="form-group">
                        <label for="ucus_kalkis_id">Uçuş Kalkış</label>
                        <select class="form-control" id="ucus_kalkis_id" name="ucus_kalkis_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <option value="<c:out value='${havaalani.havaalani_id}' />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_varis_id">Uçuş Varış</label>
                        <select class="form-control" id="ucus_varis_id" name="ucus_varis_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <option value="<c:out value='${havaalani.havaalani_id}' />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_tarih">Uçuş Tarih</label>
                        <input type="date" class="form-control" id="ucus_tarih" name="ucus_tarih" required>
                    </div>
                    <div class="form-group">
                        <label for="ucus_saat">Uçuş Saat</label>
                        <input type="time" class="form-control" id="ucus_saat" name="ucus_saat" required>
                    </div>
                    <div class="form-group">
                        <label for="ucus_sure">Uçuş Süre</label>
                        <input type="time" class="form-control" id="ucus_sure" name="ucus_sure"required>
                    </div>

                    <div class="form-group">
                        <label for="firma_id">Firma Adı</label>
                        <select class="form-control" id="firma_id" name="firma_id" required>
                            <c:forEach var="firma" items="${firma}">
                                <option value="<c:out value='${firma.firma_id}' />"><c:out value="${firma.firma_ad}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="ucak_id">Uçak Adı</label>
                        <select class="form-control" id="ucak_id" name="ucak_id" required>
                            <c:forEach var="ucak" items="${ucak}">
                                <option value="<c:out value='${ucak.ucak_id}' />"><c:out value="${ucak.ucak_ad}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucus_ucret">Uçuş Ücreti</label>
                        <input type="number" min="1" step="any" class="form-control" id="ucus_ucret" name="ucus_ucret" placeholder="Uçuş Ücreti" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Oluştur</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function Kontrol() {
        var formKontrol = document.forms["ucus"];
        var gidis = formKontrol["ucus_kalkis_id"];
        var varis = formKontrol["ucus_varis_id"];
        gidis = gidis.value;
        varis = varis.value;
        if (gidis === varis) {
            swal({
                title: "Hata",
                text: "Gidiş Yeri ve Varış Yeri aynı olamaz!",
                icon: "warning",
                button: "Tamam!",
            });
            return false;
        } else {
            return true;
        }

    }

</script>
<%@ include file = "footer.jsp" %>