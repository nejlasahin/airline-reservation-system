<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-white">Rezervasyon Listesi</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Rezervasyon Tarihi</th>
                            <th>PNR</th>
                            <th>Soyad</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Rezervasyon Tarihi</th>
                            <th>PNR</th>
                            <th>Soyad</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="rezervasyon" items="${rezervasyonliste}">
                            <tr>
                                <td><c:out value="${rezervasyon.rezervasyon_id}" /></td>
                                <td><c:out value="${rezervasyon.rezervasyon_tarih}" /></td>
                                <td><c:out value="${rezervasyon.pnr_no}" /></td>
                                <td><c:out value="${rezervasyon.yolcu_soyad}" /></td>
                                <td>
                                    <a data-toggle="modal" href="#modal<c:out value="${rezervasyon.rezervasyon_id}" />">
                                        <button class="btn btn-success btn-sm"><i class="fas fa-file-alt"></i> İncele</button>
                                    </a>
                                    <a href="rezervasyoniptal?id=<c:out value="${rezervasyon.rezervasyon_id}" />">
                                        <button class="btn btn-danger btn-sm"><i class="fas fa-times"></i> İptal</button>
                                    </a>
                                </td>
                            </tr>
                        <div class="modal fade" id="modal<c:out value="${rezervasyon.rezervasyon_id}" />" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="modal<c:out value="${rezervasyon.rezervasyon_id}" />">İncele</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p style="color:#FF7F00">Uçuş Bilgileri</p>
                                        <p><img class="img-fluid" src="<%=request.getContextPath()%>/assets/data/<c:out value="${rezervasyon.firma_logo}" />" style="width: 1.5em"> <c:out value="${rezervasyon.firma_ad}" /> - <c:out value="${rezervasyon.ucak_ad}" /></p>
                                            <p><b>PNR No : </b><c:out value="${rezervasyon.pnr_no}" /></p>
                                            <p><b>Uçuş Tarihi : </b><c:out value="${rezervasyon.ucus_tarih}" /></p>
                                            <p><b>Nereden : </b><c:out value="${rezervasyon.kalkis_ad}" /> - <c:out value="${rezervasyon.kalkis_sehir}" /> - <c:out value="${rezervasyon.kalkis_kod}" /></p>
                                            <p><b>Nereye : </b><c:out value="${rezervasyon.varis_ad}" /> - <c:out value="${rezervasyon.varis_sehir}" /> - <c:out value="${rezervasyon.varis_kod}" /></p>
                                            <p><b>Kalkış Saati : </b><c:out value="${rezervasyon.ucus_saat}" /></p>
                                            <p><b>Varış Saati : </b><c:out value="${rezervasyon.varis_saat}" /></p>
                                            <p><b>Uçuş Saati : </b><c:out value="${rezervasyon.ucus_s}" /> sa <c:out value="${rezervasyon.ucus_d}" />  dk</p>
                                            <p><b>Koltuk No : </b><c:out value="${rezervasyon.koltuk_no}" /></p>
                                            <p style="color:#FF7F00">Yolcu Bilgileri</p>
                                            <p><b>Ad Soyad : </b><c:out value="${rezervasyon.yolcu_ad}" /> <c:out value="${rezervasyon.yolcu_soyad}" /></p>
                                            <p><b>TC Kimlik : </b><c:out value="${rezervasyon.yolcu_tc}" /></p>
                                            <p><b>Doğum Tarihi : </b><c:out value="${rezervasyon.yolcu_tarih}" /></p>
                                            <c:choose>
                                                <c:when test= "${rezervasyon.yolcu_tip > 0}">
                                                    <p><b>Yolcu Tip : </b>Çocuk</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p><b>Yolcu Tip : </b>Yetişkin</p>
                                                </c:otherwise>
                                            </c:choose>

                                            <p style="color:#FF7F00">İletişim Bilgileri</p>
                                            <p><b>Telefon : </b><c:out value="${rezervasyon.yolcu_tel}" /></p>
                                            <p><b>Email : </b><c:out value="${rezervasyon.yolcu_email}" /></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>