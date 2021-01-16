<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Mesaj Listesi</span>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Gönderen Ad Soyad</th>
                            <th>Konu</th>
                            <th>Tarih</th>
                            <th>Durum</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Gönderen Ad Soyad</th>
                            <th>Konu</th>
                            <th>Tarih</th>
                            <th>Durum</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="mesaj" items="${mesajliste}">
                            <tr>
                                <td><c:out value="${mesaj.mesaj_id}" /></td>
                                <td><c:out value="${mesaj.mesaj_adsoyad}" /></td>
                                <td><c:out value="${mesaj.mesaj_konu}" /></td>
                                <td><c:out value="${mesaj.mesaj_tarih}" /></td>
                                <c:choose>
                                    <c:when test= "${mesaj.mesaj_okunma == 1}">
                                        <td>
                                            <span style="color: black"><i class="fas fa-eye"></i></span>
                                                <c:choose>
                                                    <c:when test= "${mesaj.mesaj_cevap == 0}">
                                                    <span style="color: red" class="ml-4"><i class="fas fa-times-circle"></i></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <span style="color: green" class="ml-4"><i class="fas fa-check-circle"></i></span>
                                                    </c:otherwise>
                                                </c:choose>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <span style="color: black"><i class="fas fa-eye-slash"></i></span>
                                                <c:choose>
                                                    <c:when test= "${mesaj.mesaj_cevap == 0}">
                                                    <span style="color: red" class="ml-4"><i class="fas fa-times-circle"></i></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <span style="color: green" class="ml-4"><i class="fas fa-check-circle"></i></span>
                                                    </c:otherwise>
                                                </c:choose>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <a href="mesajcevapla?id=<c:out value='${mesaj.mesaj_id}' />">
                                        <button class="btn btn-success btn-sm"><i class="far fa-edit"></i> Cevapla</button>
                                    </a>
                                    <a href="mesajsil?id=<c:out value='${mesaj.mesaj_id}' />">
                                        <button class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> Sil</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>