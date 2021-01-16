-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 16 Oca 2021, 21:05:52
-- Sunucu sürümü: 10.4.13-MariaDB
-- PHP Sürümü: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hawkeye`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cevap`
--

CREATE TABLE `cevap` (
  `cevap_id` int(11) NOT NULL,
  `mesaj_id` int(11) NOT NULL,
  `cevap_icerik` text NOT NULL,
  `cevap_baslik` varchar(500) NOT NULL,
  `cevap_tarih` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `firma`
--

CREATE TABLE `firma` (
  `firma_id` int(11) NOT NULL,
  `firma_ad` varchar(100) NOT NULL,
  `firma_logo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `havaalani`
--

CREATE TABLE `havaalani` (
  `havaalani_id` int(11) NOT NULL,
  `havaalani_ulke_id` int(11) NOT NULL,
  `havaalani_sehir_id` int(11) NOT NULL,
  `havaalani_ad` varchar(100) NOT NULL,
  `havaalani_kod` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `havaalani_sehir`
--

CREATE TABLE `havaalani_sehir` (
  `havaalani_sehir_id` int(11) NOT NULL,
  `havaalani_sehir_ad` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `havaalani_ulke`
--

CREATE TABLE `havaalani_ulke` (
  `havaalani_ulke_id` int(11) NOT NULL,
  `havaalani_ulke_ad` varchar(500) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `kullanici_id` int(11) NOT NULL,
  `kullanici_ad` varchar(100) DEFAULT NULL,
  `kullanici_soyad` varchar(100) DEFAULT NULL,
  `kullanici_email` varchar(100) DEFAULT NULL,
  `kullanici_sifre` varchar(100) DEFAULT NULL,
  `kullanici_yetki` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kullanicilar`
--

INSERT INTO `kullanicilar` (`kullanici_id`, `kullanici_ad`, `kullanici_soyad`, `kullanici_email`, `kullanici_sifre`, `kullanici_yetki`) VALUES
(3, 'Kullanıcı', 'Admin', 'admin@hawkeye.com', '123456', 2),
(4, 'Kullanıcı', 'Üye', 'kullanici@hawkeye.com', '123456', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `mesaj`
--

CREATE TABLE `mesaj` (
  `mesaj_id` int(11) NOT NULL,
  `mesaj_adsoyad` varchar(500) NOT NULL,
  `mesaj_email` varchar(500) NOT NULL,
  `mesaj_konu` varchar(500) NOT NULL,
  `mesaj_icerik` text NOT NULL,
  `mesaj_tarih` timestamp NOT NULL DEFAULT current_timestamp(),
  `mesaj_okunma` int(2) NOT NULL DEFAULT 0,
  `mesaj_cevap` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `rezervasyon`
--

CREATE TABLE `rezervasyon` (
  `rezervasyon_id` int(11) NOT NULL,
  `rezervasyon_tarih` timestamp NOT NULL DEFAULT current_timestamp(),
  `ucus_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `pnr_no` varchar(11) NOT NULL DEFAULT '45645',
  `yolcu_ad` varchar(500) NOT NULL,
  `yolcu_soyad` varchar(500) NOT NULL,
  `yolcu_email` varchar(500) NOT NULL,
  `yolcu_tel` varchar(11) NOT NULL,
  `yolcu_tc` varchar(11) NOT NULL,
  `yolcu_tip` int(5) NOT NULL,
  `yolcu_tarih` varchar(20) NOT NULL,
  `yolcu_ucret` decimal(6,2) NOT NULL,
  `koltuk_no` varchar(10) NOT NULL,
  `durum` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ucak`
--

CREATE TABLE `ucak` (
  `ucak_id` int(11) NOT NULL,
  `ucak_ad` varchar(500) NOT NULL,
  `ucak_koltuk` int(10) NOT NULL,
  `firma_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ucus`
--

CREATE TABLE `ucus` (
  `ucus_id` int(11) NOT NULL,
  `ucus_kalkis_id` int(11) NOT NULL,
  `ucus_varis_id` int(11) NOT NULL,
  `ucus_tarih` date NOT NULL,
  `ucus_saat` time NOT NULL,
  `ucus_sure` varchar(50) NOT NULL,
  `firma_id` int(11) NOT NULL,
  `ucak_id` int(11) NOT NULL,
  `ucus_ucret` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `cevap`
--
ALTER TABLE `cevap`
  ADD PRIMARY KEY (`cevap_id`);

--
-- Tablo için indeksler `firma`
--
ALTER TABLE `firma`
  ADD PRIMARY KEY (`firma_id`);

--
-- Tablo için indeksler `havaalani`
--
ALTER TABLE `havaalani`
  ADD PRIMARY KEY (`havaalani_id`);

--
-- Tablo için indeksler `havaalani_sehir`
--
ALTER TABLE `havaalani_sehir`
  ADD PRIMARY KEY (`havaalani_sehir_id`);

--
-- Tablo için indeksler `havaalani_ulke`
--
ALTER TABLE `havaalani_ulke`
  ADD PRIMARY KEY (`havaalani_ulke_id`);

--
-- Tablo için indeksler `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`kullanici_id`);

--
-- Tablo için indeksler `mesaj`
--
ALTER TABLE `mesaj`
  ADD PRIMARY KEY (`mesaj_id`);

--
-- Tablo için indeksler `rezervasyon`
--
ALTER TABLE `rezervasyon`
  ADD PRIMARY KEY (`rezervasyon_id`);

--
-- Tablo için indeksler `ucak`
--
ALTER TABLE `ucak`
  ADD PRIMARY KEY (`ucak_id`);

--
-- Tablo için indeksler `ucus`
--
ALTER TABLE `ucus`
  ADD PRIMARY KEY (`ucus_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `cevap`
--
ALTER TABLE `cevap`
  MODIFY `cevap_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Tablo için AUTO_INCREMENT değeri `firma`
--
ALTER TABLE `firma`
  MODIFY `firma_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Tablo için AUTO_INCREMENT değeri `havaalani`
--
ALTER TABLE `havaalani`
  MODIFY `havaalani_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `havaalani_sehir`
--
ALTER TABLE `havaalani_sehir`
  MODIFY `havaalani_sehir_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `havaalani_ulke`
--
ALTER TABLE `havaalani_ulke`
  MODIFY `havaalani_ulke_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `kullanici_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `mesaj`
--
ALTER TABLE `mesaj`
  MODIFY `mesaj_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Tablo için AUTO_INCREMENT değeri `rezervasyon`
--
ALTER TABLE `rezervasyon`
  MODIFY `rezervasyon_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Tablo için AUTO_INCREMENT değeri `ucak`
--
ALTER TABLE `ucak`
  MODIFY `ucak_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `ucus`
--
ALTER TABLE `ucus`
  MODIFY `ucus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
