# Uçak Rezervasyon Sistemi
Bu proje İnternet Mühendisliği dersinde almış olduğum proje ödevim için hazırlanmıştır. 

---

## Anasayfa
![](./README/anasayfa.jpg)

## Admin
![](./README/admin.jpg)

---

## Gerekli Kütüphaneler
![](./README/kutuphaneler.JPG)

### mysql-connector-java-5.1.23-bin.jar
Uygulamamızı MySQL veri tabanına bağlamak için kullanılır.

### JSTL 1.2.2
JSP sayfalarında JSTL ile çalışmak için kullanılır. JSTL, temel etiketler ve işlevlerden oluşan bir koleksiyondur. Bu etiketler ve işlevler, JSP kodunu verimli bir şekilde yazmamızı sağlar.

### javax.mail.jar
E-postayı JavaMail API kullanarak göndermek için jar dosyasını projemize dahil ediyoruz.

### commons-fileupload-1.4.jar
Form verileri POST isteğinde alınan, bir dosya veya form öğesine erişim sağlar. Dosya yükleme işlemi için kullanıyoruz.

---

## Mimari
![](./README/mimari.png)

Projede MVC mimarisi kullanılmıştır.
MVC, Model, View ve Controller anlamına gelir.

- **Model - DAO :** Uygulamada kullanılan verileri temsil eder. Veritabanına erişim, sınıflar, veritabanı ilişkileri gibi veri ile ilgili işlemlerin olduğu bölüm.

![](./README/model-dao.JPG)

- **View :** Projenin kullanıcılara sunulacak olan JSP dosyaları yer almaktadır. Bir görevi de, kullanıcılardan alınan istekleri Controller’ a iletmektir.

`Admin Sayfaları`

![](./README/admin-sayfa.JPG)

`Kullanıcı Sayfaları`

![](./README/kullanici-sayfa.JPG)

- **Controller :** Controller, MVC’de projenin iç süreçlerini kontrol eden bölümdür. Bu bölümde View ile Model arasındaki bağlantı kurulur. Kullanıcılardan gelen istekler (request) Controller’larda değerlendirilir, isteğin detayına göre hangi işlemlerin yapılacağı ve kullanıcıya hangi View’ın döneceği (response) belirtilir.

![](./README/controller.JPG)

## Senaryo (Adminin oluşturulan uçuşlardan birini silmesi)

![](./README/senaryo.png)

View bu değişiklik isteğini algılayıp bunu Controller iletecek.

![](./README/senaryo2.JPG)

![](./README/senaryo3.JPG)

Controller tüm kontrolleri yaptıktan sonra verinin silinmesi için DAO bölümüne güncelleme gönderir. 

![](./README/senaryo4.JPG)

DAO bu güncellemeyi veri üzerine yansıttıktan sonra tekrardan Controller bu güncellemenin yapıldığı bilgisini gönderir ve bu doğrultuda View güncellenir.

---

## Oluşturulan Sınıflar ve Açıklaması

### Cevap

![](./README/cevap.JPG)

- **Cevap.java :** Cevap tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **CevapDAO.java :** Cevap tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; cevapların listelenmesi, cevapların incelenmesi,  gelen mesaja cevap verilmesi, cevabın veritabanından silinmesi.

- **CevapServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen mesaj cevaplama, cevapları listeleme, cevap silme veya cevap inceleme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Firma 

![](./README/firma.JPG)

- **Firma.java :** Firma tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **FirmaDAO.java :** Firma tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; firmaların listelenmesi, firma eklenmesi,  firmanın bilgilerinin güncellenmesi, firmanın veritabanından silinmesi.

- **FirmaServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen firmaları listeleme, yeni firma oluşturma, firma güncelleme, firma silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Havaalanı

![](./README/havaalani.JPG)

- **Havaalani.java :** Havaalani tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **HavaalaniDAO.java :** Havaalani tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; havaalanlarının listelenmesi, havaalanı eklenmesi, havaalanının bilgilerinin güncellenmesi, havaalanının veritabanından silinmesi. 

- **HavaalaniServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen havaalanları listeleme, yeni havaalanı oluşturma, havaaalanı güncelleme, havaalanı silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Havaalanı Şehir

![](./README/havaalani_sehir.JPG)

- **Havaalani_sehir.java :** Havaalani_sehir tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **Havaalani_sehirDAO.java :** Havaalani_sehir tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; şehirlerin listelenmesi, şehir eklenmesi, şehir bilgilerinin güncellenmesi, şehrin veritabanından silinmesi. 

- **Havaalani_sehirServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen şehirleri listeleme, yeni şehir oluşturma, şehir güncelleme, şehir silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Havaalanı Ülke

![](./README/havaalani_ulke.JPG)

- **Havaalani_ulke.java :** Havaalani_ulke tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **Havaalani_ulkeDAO.java :** Havaalani_ulke tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; ülkelerin listelenmesi, ülke eklenmesi, ülke bilgilerinin güncellenmesi, ülkenin veritabanından silinmesi.  

- **Havaalani_ulkeServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen ülkeleri listeleme, yeni ülke oluşturma, ülke güncelleme, ülke silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Kullanıcılar

![](./README/kullanicilar.JPG)

- **Kullanici.java :** Kullanicilar tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **KullaniciDAO.java :** Kullanicilar tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; kullanıcıların listelenmesi, sisteme giriş yaparken şifrenin kontrol edilmesi, unutulan şifrenin veritabanından çekilmesi, kullanıcı bilgilerinin güncellenmesi, yeni kullanıcı / admin oluşturulması, kullanıcının / adminin veritabanından silinmesi. 

- **KullaniciServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen üye olma, sisteme giriş yapma, yeni şifre talebi,  sistemden çıkış yapma, kullanıcıların listelenmesi, kullanıcıların silinmesi, kullanıcı/admin bilgilerinin güncellenmesi işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Mesaj

![](./README/mesaj.JPG)

- **Mesaj.java :** Mesaj tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **MesajDAO.java :** Mesaj tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; gelen mesajların veritabanına kaydedilmesi, gelen mesajların listelenmesi, mesaj okunmuşsa okundu bilgisinin güncellenmesi, mesaja cevap verilmişse cevap bilgisinin güncellenmesi, mesajın veritabanından silinmesi.

- **MesajServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen mesajları listeleme,  gelen mesajın silinmesi, gelen mesajın veritabanına eklenmesi, iletişim sayfasına erişim sağlanması işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Rezervasyon

![](./README/rezervasyon.JPG)

- **Rezervasyon.java :** Rezervasyon tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **RezervasyonDAO.java :** Rezervasyon tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; kullanıcıdan alınan bilgilere göre uçuş listelenmesi, uçuştan 2 saat  önceye kadar iptal yada bilgi güncellemesi yapabilme işlemi, seçilen koltuğun dolu-boş olmasının kontrolü, veritabanına rezervasyon bilgilerinin eklenmesi, rezervasyon bilgilerinin güncellenmesi, seçilen uçuş bilgilerine erişim.

- **RezervasyonServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Uçuş sorgulama, rezervasyon listelenmesi, rezervasyonun iptal edilmesi, rezervasyon sorgulaması, rezervasyon oluşturulması, rezervasyon bilgilerinin güncellenmesi işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Uçak

![](./README/ucak.JPG)

- **Ucak.java :** Ucak tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişken olarak oluşturmuş hali , bu değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **:UcakDAO.java :** Ucak tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; uçakların listelenmesi, seçilen uçağın silinmesi, veritabanına uçak eklenmesi, uçak bilgilerinin güncellenmesi. 

- **UcakServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen uçakları listeleme, yeni uçak bilgileri oluşturma, uçak bilgileri güncelleme, uçak silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Uçuş

![](./README/ucus.JPG)

- **Ucus.java :** Ucus tablosunun modelini oluşturmaktadır.
İçerisinde tablodaki niteliklerin değişkenlerin getter setter metotları ve yapılandırıcı metotlar bulunmaktadır.

- **:UcusDAO.java :** Ucus tablosunun veri erişim sınıfıdır.
Gerçekleşen İşlemler; güncel ve geçmiş uçuşların listelenmesi, seçilen uçuşun silinmesi, seçilen uçağın müsaitlik durumuna göre veritabanına uçuş eklenmesi, uçuş bilgilerinin güncellenmesi. 

- **UcusServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. Kullanıcıdan gelen güncel veya geçmiş uçuşların listelenmesi, uçağın müsaitlik durumuna göre yeni uçuş bilgileri oluşturma, uçuş bilgilerinin güncelleme, oluşturulan uçuşu silme işlemlerinden kullanıcının isteğine göre hangi işlemin yapılacağını ve kullanıcıya hangi JSP sayfasının döneceğini belirtir.

### Anasayfa

`Kullanıcı Anasayfası`

![](./README/anasayfa1.png)

`Admin Anasayfası`

![](./README/admin2.JPG)

- **AnasayfalarServlet.java :** JSP sayfası ile Model - DAO sınıfı arasında bağlantı kurar. 
Kullanıcıdan gelen anasayfa görüntüleme işleminde eğer görüntülenmek istenen sayfa admin anasayfası ise veritabanından aylık rezervasyon sayısı, planlanan uçuşlar ve okunmamış mesajların sayısı çekilecektir ve kullanıcıya JSP sayfası olarak admin anasayfası dönecektir.
Kullanıcıdan gelen anasayfa görüntüleme işleminde eğer görüntülenmek istenen sayfa kullanıcı anasayfası ise veritabanında kayıtlı havayolları çekilecektir gidiş ve varış yeri olarak kullanıcıda görüntülenecektir  ve kullanıcıya JSP sayfası olarak kullanıcı anasayfası dönecektir.

---

## Kod Parçaları & Ekran Görüntüleri

# Giriş Yapma

```java
private void gostergiris(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_sifre = request.getParameter("kullanici_sifre");

            Boolean kontrol = kullaniciDAO.uyegiriskontrol(kullanici_email, kullanici_sifre);
            if (kontrol == true) {
                Kullanici uye = kullaniciDAO.uyegiris(kullanici_email, kullanici_sifre);
                int kullanici_yetki = uye.getKullanici_yetki();
                String kullanici_ad = uye.getKullanici_ad();
                String kullanici_soyad = uye.getKullanici_soyad();
                int kullanici_id = uye.getKullanici_id();

                session.setAttribute("kullanici_id", kullanici_id);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", kullanici_email);
                session.setAttribute("kullanici_yetki", kullanici_yetki);
                session.setAttribute("kullanici_sifre", kullanici_sifre);

                response.sendRedirect("ucakbileti");
            } else {
                response.sendRedirect("giris?durum=basarisiz");
            }
        } else {
            response.sendRedirect("ucakbileti");
        }
    }
```

