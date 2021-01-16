package model;

public class Kullanici {

    protected int kullanici_id;
    protected String kullanici_ad;
    protected String kullanici_soyad;
    protected String kullanici_email;
    protected String kullanici_sifre;
    protected int kullanici_yetki;

    public Kullanici() {
    }

    public Kullanici(int kullanici_id, String kullanici_sifre) {
        this.kullanici_id = kullanici_id;
        this.kullanici_sifre = kullanici_sifre;
    }

    public Kullanici(String kullanici_email, String kullanici_sifre) {
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
    }

    public Kullanici(int kullanici_id, String kullanici_ad, String kullanici_soyad, String kullanici_email, String kullanici_sifre) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
    }

    public Kullanici(String kullanici_ad, String kullanici_soyad, String kullanici_email, int kullanici_yetki) {
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_yetki = kullanici_yetki;
    }

    public Kullanici(String kullanici_ad, String kullanici_soyad, String kullanici_email, String kullanici_sifre, int kullanici_yetki) {
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_yetki = kullanici_yetki;
    }

    public Kullanici(int kullanici_id, String kullanici_ad, String kullanici_soyad, String kullanici_email, int kullanici_yetki) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_yetki = kullanici_yetki;
    }

    public Kullanici(int kullanici_id, String kullanici_ad, String kullanici_soyad, String kullanici_email, String kullanici_sifre, int kullanici_yetki) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_yetki = kullanici_yetki;
    }

    public Kullanici(String kullanici_ad, String kullanici_soyad, String kullanici_email, String kullanici_sifre) {
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
    }
    
    public Kullanici(int kullanici_id, String kullanici_ad, String kullanici_soyad, String kullanici_email) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.kullanici_email = kullanici_email;
    }
    
    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_ad() {
        return kullanici_ad;
    }

    public void setKullanici_ad(String kullanici_ad) {
        this.kullanici_ad = kullanici_ad;
    }

    public String getKullanici_soyad() {
        return kullanici_soyad;
    }

    public void setKullanici_soyad(String kullanici_soyad) {
        this.kullanici_soyad = kullanici_soyad;
    }

    public String getKullanici_email() {
        return kullanici_email;
    }

    public void setKullanici_email(String kullanici_email) {
        this.kullanici_email = kullanici_email;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public int getKullanici_yetki() {
        return kullanici_yetki;
    }

    public void setKullanici_yetki(int kullanici_yetki) {
        this.kullanici_yetki = kullanici_yetki;
    }
    
    
}