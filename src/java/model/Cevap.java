package model;

public class Cevap {
    int cevap_id;
    int mesaj_id;
    String cevap_icerik;
    String cevap_baslik;
    String cevap_tarih;
    String mesaj_adsoyad;
    String mesaj_email;
    String mesaj_konu;
    String mesaj_icerik;
    String mesaj_tarih;               
    int mesaj_okunma;
    int mesaj_cevap;

    public Cevap() {
    }

    public Cevap(int cevap_id, int mesaj_id, String cevap_icerik, String cevap_baslik, String cevap_tarih, String mesaj_adsoyad, String mesaj_email, String mesaj_konu, String mesaj_icerik, String mesaj_tarih) {
        this.cevap_id = cevap_id;
        this.mesaj_id = mesaj_id;
        this.cevap_icerik = cevap_icerik;
        this.cevap_baslik = cevap_baslik;
        this.cevap_tarih = cevap_tarih;
        this.mesaj_adsoyad = mesaj_adsoyad;
        this.mesaj_email = mesaj_email;
        this.mesaj_konu = mesaj_konu;
        this.mesaj_icerik = mesaj_icerik;
        this.mesaj_tarih = mesaj_tarih;
    }
    
    public Cevap(int mesaj_id, String cevap_icerik, String cevap_baslik) {
        this.mesaj_id = mesaj_id;
        this.cevap_icerik = cevap_icerik;
        this.cevap_baslik = cevap_baslik;
    }

    public String getMesaj_adsoyad() {
        return mesaj_adsoyad;
    }

    public void setMesaj_adsoyad(String mesaj_adsoyad) {
        this.mesaj_adsoyad = mesaj_adsoyad;
    }

    public String getMesaj_email() {
        return mesaj_email;
    }

    public void setMesaj_email(String mesaj_email) {
        this.mesaj_email = mesaj_email;
    }

    public String getMesaj_konu() {
        return mesaj_konu;
    }

    public void setMesaj_konu(String mesaj_konu) {
        this.mesaj_konu = mesaj_konu;
    }

    public String getMesaj_icerik() {
        return mesaj_icerik;
    }

    public void setMesaj_icerik(String mesaj_icerik) {
        this.mesaj_icerik = mesaj_icerik;
    }

    public String getMesaj_tarih() {
        return mesaj_tarih;
    }

    public void setMesaj_tarih(String mesaj_tarih) {
        this.mesaj_tarih = mesaj_tarih;
    }

    public int getMesaj_okunma() {
        return mesaj_okunma;
    }

    public void setMesaj_okunma(int mesaj_okunma) {
        this.mesaj_okunma = mesaj_okunma;
    }

    public int getMesaj_cevap() {
        return mesaj_cevap;
    }

    public void setMesaj_cevap(int mesaj_cevap) {
        this.mesaj_cevap = mesaj_cevap;
    }
    
    public int getCevap_id() {
        return cevap_id;
    }

    public void setCevap_id(int cevap_id) {
        this.cevap_id = cevap_id;
    }

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public String getCevap_icerik() {
        return cevap_icerik;
    }

    public void setCevap_icerik(String cevap_icerik) {
        this.cevap_icerik = cevap_icerik;
    }

    public String getCevap_baslik() {
        return cevap_baslik;
    }

    public void setCevap_baslik(String cevap_baslik) {
        this.cevap_baslik = cevap_baslik;
    }

    public String getCevap_tarih() {
        return cevap_tarih;
    }

    public void setCevap_tarih(String cevap_tarih) {
        this.cevap_tarih = cevap_tarih;
    }
    
    
}
