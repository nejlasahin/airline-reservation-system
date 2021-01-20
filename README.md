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

![](./README/admin-sayfa.JPG)

`Admin Sayfaları`

![](./README/kullanici-sayfa.JPG)

`Kullanıcı Sayfaları`

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


