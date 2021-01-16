<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="today" value="<%=new java.util.Date()%>" />
<%@ include file = "header.jsp" %>
<%@ include file = "navigasyon.jsp" %>
<main class="main_search">
    <div class="search">
        <div class="container fill_height">
            <div class="row fill_height">
                <div class="col fill_height">
                    <div class="search_tabs_container">                     
                        <form action="ucussorgulama" name="ucussorgulama" method="post" id="search_form_1" class="search_panel_content d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-between justify-content-start" onsubmit="return Kontrol()">
                            <div class="search_item">
                                <div>Gidiş Yeri</div>
                                <select class="search_input" id="gidis" name="gidis" required>
                                    <c:forEach var="havaalani" items="${havaalaniliste}">
                                        <c:choose>
                                        <c:when test= "${havaalani.havaalani_id == rezervasyon.havaalani_kalkis_id}">
                                            <option value="<c:out value="${havaalani.havaalani_id}" />" selected><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="<c:out value="${havaalani.havaalani_id}" />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>
                                        </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="search_item">
                                <div>Varış Yeri</div>
                                <select class="search_input" id="varis" name="varis" required>
                                    <c:forEach var="havaalani" items="${havaalaniliste}">
                                        <c:choose>
                                        <c:when test= "${havaalani.havaalani_id == rezervasyon.havaalani_varis_id}">
                                            <option value="<c:out value="${havaalani.havaalani_id}" />" selected><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>                                                                                 
                                        </c:when>
                                        <c:otherwise>
                                            <option value="<c:out value="${havaalani.havaalani_id}" />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>
                                        </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="search_item">
                                <div>Gidiş Tarihi</div>
                                <input type="date" class="search_input" name="gidis_tarih" id="gidis_tarih" value="<c:out value="${rezervasyon.ucus_tarih}" />" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />" required>
                            </div>
                            <div class="search_item">
                                <div>Yetişkin</div>
                                <select name="yetiskin" id="yetiskin" class="dropdown_item_select search_input" required>
                                    <c:choose>                                                       
                                            <c:when test = "${rezervasyon.yetiskin_sayi==1}">
                                                <option selected>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </c:when>        
                                            <c:when test = "${rezervasyon.yetiskin_sayi==2}">
                                                <option>1</option>
                                                <option selected>2</option>
                                                <option>3</option>
                                            </c:when>         
                                            <c:otherwise>
                                                <option>1</option>
                                                <option>2</option>
                                                <option selected>3</option>
                                            </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <input type="hidden" value="<c:out value="${rezervasyon.yetiskin_sayi}" /> " id="yetiskin_sayi" name="yetiskin_sayi">
                            <input type="hidden" value="<c:out value="${rezervasyon.cocuk_sayi}" /> " id="cocuk_sayi" name="cocuk_sayi">
                            <div class="search_item">
                                <div>Çocuk</div>
                                <select name="cocuk" id="cocuk" class="dropdown_item_select search_input" required>
                                    <c:choose>                                                       
                                            <c:when test = "${rezervasyon.cocuk_sayi==0}">
                                                <option selected>0</option>
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </c:when>  
                                            <c:when test = "${rezervasyon.cocuk_sayi==1}">
                                                <option>0</option>
                                                <option selected>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </c:when>   
                                            <c:when test = "${rezervasyon.cocuk_sayi==2}">
                                                <option>0</option>
                                                <option>1</option>
                                                <option selected>2</option>
                                                <option>3</option>
                                            </c:when>         
                                            <c:otherwise>
                                                <option>0</option>
                                                <option>1</option>
                                                <option>2</option>
                                                <option selected>3</option>
                                            </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <button class="search_button">Yeniden Uçuş Ara</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script>
    function Kontrol(){
        var formKontrol=document.forms["ucussorgulama"];
        var gidis= formKontrol["gidis"];
        var varis= formKontrol["varis"];
        gidis=gidis.value;
        varis=varis.value; 
        if(gidis===varis){
            swal({
                title: "Hata",
                text: "Gidiş Yeri ve Varış Yeri aynı olamaz!",
                icon: "warning",
                button: "Tamam!",
            });
            return false;
        }else {
            return true;
        }
        
    }
    
</script>

<section class="container flight pt-2">
    <div class="row">
        <div class="col-md-12 py-1 pt-2 bg-blue-search rounded text-left mb-2 shadow-sm">
            <div class="row d-flex justify-content-between align-items-center">
                <div class="col pt-1" style="font-size: 22px; margin: 10px">
                    <p class="font-weight-bold text-dark mb-0">         
                        <span class="s-ticketnum font-weight-bold"><i class="fas fa-plane-departure" style="color: #FF7F00"></i></span>

                        <c:forEach var="havaalani" items="${havaalaniliste}">
                                <c:choose>
                                        <c:when test= "${havaalani.havaalani_id == rezervasyon.havaalani_kalkis_id}">
                                            <c:out value="${havaalani.havaalani_sehir_ad}" />                                                                                 
                                        </c:when>             
                                </c:choose>
                        </c:forEach> 
                        <span class="s-ticketnum font-weight-bold"><i class="fas fa-long-arrow-alt-right" style="color: #FF7F00"></i></span> 
                        <c:forEach var="havaalani" items="${havaalaniliste}">
                                <c:choose>
                                        <c:when test= "${havaalani.havaalani_id == rezervasyon.havaalani_varis_id}">
                                            <c:out value="${havaalani.havaalani_sehir_ad}" />                                                                                 
                                        </c:when>             
                                </c:choose>
                        </c:forEach>
                    </p>
                </div>
                <div class="col text-right">
                    <span class="text-dark"> Tarih : </span>
                    <span class="text-dark font-weight-bold" > <c:out value="${rezervasyon.ucus_tarih}" /></span>
                </div>
            </div>
        </div>
        <c:if test="${empty ucussorgulama}">
        <div class="mx-auto mb-5 mt-5">
            <i class="fas fa-exclamation text-dark fa-4x mb-3" style="margin-left: 120px;"></i>
            <h2>Uçuş Bulunamadı</h2>
            <p>Uçuş tarihini değiştirmeyi deneyebilirsiniz.</p>
        </div>
        </c:if>
        <c:forEach var="ucus" items="${ucussorgulama}">
        <div class="col-md-12" style="opacity: 1;">
            <div class="card z-depth-1 mb-3 searchcell" xid="1">
                <div class="card-body p-0">
                    <div class="row mx-0 cursor-pointer selectbtn-go">
                        <div class="col-md-9 rounded-left pt-1">
                            <div class="row pt-3 d-flex justify-content-center align-items-center text-lg-left text-center">
                                <div class="col-md-8 col-sm-8 col-12 mb-1 text-sm-left"><img src="<%=request.getContextPath()%>/assets/data/<c:out value='${ucus.firma_logo}' />" class="img-fluid w-36"> <span class="font-weight-normal"><c:out value="${ucus.firma_ad}" /></span></div>
                                <div class="col-md-4 col-sm-4 col-12 mb-1 text-lg-right"><span class="font-weight-normal" style="color: #f44444"></span></div>
                            </div>
                            <div class="row">   

                                <div class="col-md-4 text-right px-0 pb-5">
                                    <p class="font-weight-normal px-2 mb-0 h4"><c:out value="${ucus.kalkis_kod}" /></p>
                                    <p class="font-weight-normal px-2 mb-0 filtre-title"><c:out value="${ucus.kalkis_ad}" /></p>
                                    <p class="font-weight-normal px-2 h4"><c:out value='${ucus.ucus_saat}' /></p>
                                </div>

                                <div class="col-md-4 text-center px-0">
                                    <div class="d-inline-flex col-example">
                                        <div class="divider-small mx-2 my-auto"></div>
                                        <i class="fas fa-plane text-green-light fa-lg mr-3" aria-hidden="true"></i>
                                    </div>
                                    <div class="text-center align-items-center justify-content-center py-0 mb-4">                                            
                                        <p class="time-gri py-0"><c:out value='${ucus.ucus_s}' /> sa <c:out value='${ucus.ucus_d}'/> dk</p>
                                    </div>
                                </div>

                                <div class="col-md-4 text-left mb-0 px-0">
                                    <p class="font-weight-normal px-2 mb-0 h4"><c:out value="${ucus.varis_kod}" /></p>
                                    <p class="font-weight-normal px-2 mb-0 filtre-title"><c:out value="${ucus.varis_ad}" /></p>
                                    <p class="font-weight-normal px-2 h4"><c:out value='${ucus.varis_saat}' /></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 text-right bg-filtre-title py-0">
                            <p class="h3 font-weight-normal mt-5"><c:out value="${ucus.ucus_ucret}" /> ₺</p>

                            <a href="rezervasyonolustur?id=<c:out value='${ucus.ucus_id}' />&yetiskin=<c:out value='${rezervasyon.yetiskin_sayi}' />&cocuk=<c:out value='${rezervasyon.cocuk_sayi}' />" class="btn btn-warning btn-rounded text-white text-right selectbtn-go">Seç</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</section>

<%@ include file = "footer.jsp" %>