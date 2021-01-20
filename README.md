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

- Model - DAO : Uygulamada kullanılan verileri temsil eder. Veritabanına erişim, sınıflar, veritabanı ilişkileri gibi veri ile ilgili işlemlerin olduğu bölüm.
![](./README/model-dao.JPG)

- View : Projenin kullanıcılara sunulacak olan JSP dosyaları yer almaktadır. Bir görevi de, kullanıcılardan alınan istekleri Controller’ a iletmektir.
![](./README/admin-sayfa.JPG)
![](./README/kullanici-sayfa.JPG)
