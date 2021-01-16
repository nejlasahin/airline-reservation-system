<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Uçak Listesi</span>
            <a href="ucakekle" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Uçak Ekle</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Uçak Adı</th>
                            <th>Uçak Firması</th>
                            <th>Uçak Koltuk Sayısı</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Uçak Adı</th>
                            <th>Uçak Firması</th>
                            <th>Uçak Koltuk Sayısı</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="ucak" items="${ucakliste}">
                            <tr>
                                <td><c:out value="${ucak.ucak_id}" /></td>
                                <td><c:out value="${ucak.ucak_ad}" /></td>
                                <td><c:out value="${ucak.firma_ad}" /></td>
                                <td><c:out value="${ucak.ucak_koltuk}" /></td>
                                <td>
                                    <a href="ucakguncelle?id=<c:out value='${ucak.ucak_id}' />">
                                        <button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> Düzenle</button>
                                    </a>
                                    <a href="ucaksil?id=<c:out value='${ucak.ucak_id}' />">
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