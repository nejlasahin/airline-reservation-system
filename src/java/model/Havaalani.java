package model;

public class Havaalani{
    protected int havaalani_id;
    protected int havaalani_ulke_id;
    protected int havaalani_sehir_id;
    protected String havaalani_ad;
    protected String havaalani_kod;
    protected String havaalani_ulke_ad;
    protected String havaalani_sehir_ad;

    public Havaalani() {
    }

    public Havaalani(int havaalani_id, String havaalani_ad, String havaalani_kod) {
        this.havaalani_id = havaalani_id;
        this.havaalani_ad = havaalani_ad;
        this.havaalani_kod = havaalani_kod;
    }

    public Havaalani(int havaalani_id, int havaalani_ulke_id, int havaalani_sehir_id, String havaalani_ad, String havaalani_kod) {
        this.havaalani_id = havaalani_id;
        this.havaalani_ulke_id = havaalani_ulke_id;
        this.havaalani_sehir_id = havaalani_sehir_id;
        this.havaalani_ad = havaalani_ad;
        this.havaalani_kod = havaalani_kod;
    }
   
    public Havaalani(int havaalani_ulke_id, int havaalani_sehir_id, String havaalani_ad, String havaalani_kod) {
        this.havaalani_ulke_id = havaalani_ulke_id;
        this.havaalani_sehir_id = havaalani_sehir_id;
        this.havaalani_ad = havaalani_ad;
        this.havaalani_kod = havaalani_kod;
    }
    
    public Havaalani(int havaalani_ulke_id, int havaalani_sehir_id, String havaalani_ad, String havaalani_kod, String havaalani_ulke_ad, String havaalani_sehir_ad) {
        this.havaalani_ulke_id = havaalani_ulke_id;
        this.havaalani_sehir_id = havaalani_sehir_id;
        this.havaalani_ad = havaalani_ad;
        this.havaalani_kod = havaalani_kod;
        this.havaalani_ulke_ad = havaalani_ulke_ad;
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }
    
    public Havaalani(int havaalani_id, int havaalani_ulke_id, int havaalani_sehir_id, String havaalani_ad, String havaalani_kod, String havaalani_ulke_ad, String havaalani_sehir_ad) {
        this.havaalani_id = havaalani_id;
        this.havaalani_ulke_id = havaalani_ulke_id;
        this.havaalani_sehir_id = havaalani_sehir_id;
        this.havaalani_ad = havaalani_ad;
        this.havaalani_kod = havaalani_kod;
        this.havaalani_ulke_ad = havaalani_ulke_ad;
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }

    public int getHavaalani_id() {
        return havaalani_id;
    }

    public void setHavaalani_id(int havaalani_id) {
        this.havaalani_id = havaalani_id;
    }

    public int getHavaalani_ulke_id() {
        return havaalani_ulke_id;
    }

    public void setHavaalani_ulke_id(int havaalani_ulke_id) {
        this.havaalani_ulke_id = havaalani_ulke_id;
    }

    public int getHavaalani_sehir_id() {
        return havaalani_sehir_id;
    }

    public void setHavaalani_sehir_id(int havaalani_sehir_id) {
        this.havaalani_sehir_id = havaalani_sehir_id;
    }

    public String getHavaalani_ad() {
        return havaalani_ad;
    }

    public void setHavaalani_ad(String havaalani_ad) {
        this.havaalani_ad = havaalani_ad;
    }

    public String getHavaalani_kod() {
        return havaalani_kod;
    }

    public void setHavaalani_kod(String havaalani_kod) {
        this.havaalani_kod = havaalani_kod;
    }

    public String getHavaalani_ulke_ad() {
        return havaalani_ulke_ad;
    }

    public void setHavaalani_ulke_ad(String havaalani_ulke_ad) {
        this.havaalani_ulke_ad = havaalani_ulke_ad;
    }

    public String getHavaalani_sehir_ad() {
        return havaalani_sehir_ad;
    }

    public void setHavaalani_sehir_ad(String havaalani_sehir_ad) {
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }
    
}
