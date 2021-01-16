package model;

public class Mesaj {
    int mesaj_id;
    String mesaj_adsoyad;
    String mesaj_email;
    String mesaj_konu;
    String mesaj_icerik;
    String mesaj_tarih;
    int mesaj_okunma;
    int mesaj_cevap;
    public Mesaj() {
    }

    public Mesaj(String mesaj_adsoyad, String mesaj_email, String mesaj_konu, String mesaj_icerik) {
        this.mesaj_adsoyad = mesaj_adsoyad;
        this.mesaj_email = mesaj_email;
        this.mesaj_konu = mesaj_konu;
        this.mesaj_icerik = mesaj_icerik;
    }
    public Mesaj(int mesaj_id, String mesaj_adsoyad, String mesaj_email, String mesaj_konu, String mesaj_icerik, String mesaj_tarih, int mesaj_okunma,int mesaj_cevap) {
        this.mesaj_id = mesaj_id;
        this.mesaj_adsoyad = mesaj_adsoyad;
        this.mesaj_email = mesaj_email;
        this.mesaj_konu = mesaj_konu;
        this.mesaj_icerik = mesaj_icerik;
        this.mesaj_tarih = mesaj_tarih;
        this.mesaj_okunma = mesaj_okunma;
        this.mesaj_cevap = mesaj_cevap;
    }

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
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
    
    
}
