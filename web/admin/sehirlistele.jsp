<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Şehir Listesi</span>
            <a href="sehirekle" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Şehir Ekle</a>                      
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Şehir Adı</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Şehir Adı</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="sehir" items="${sehirliste}">
                            <tr>
                                <td><c:out value="${sehir.havaalani_sehir_id}" /></td>
                                <td><c:out value="${sehir.havaalani_sehir_ad}" /></td>
                                <td>
                                    <a href="sehirguncelle?id=<c:out value='${sehir.havaalani_sehir_id}' />">
                                        <button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> Düzenle</button>
                                    </a>
                                    <a href="sehirsil?id=<c:out value='${sehir.havaalani_sehir_id}' />">
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