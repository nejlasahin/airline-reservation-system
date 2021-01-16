package model;

public class Ucak {
    int ucak_id;
    String ucak_ad;
    int ucak_koltuk;
    int firma_id;
    String firma_ad;

    public Ucak() {
    }

    public Ucak(int ucak_id, String ucak_ad) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
    }

    public Ucak(String ucak_ad, int ucak_koltuk, int firma_id) {
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.firma_id = firma_id;
    }
    
    public Ucak(int ucak_id, String ucak_ad, int ucak_koltuk, int firma_id) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.firma_id = firma_id;
    }

    public Ucak(int ucak_id, String ucak_ad, int ucak_koltuk, String firma_ad) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.firma_ad = firma_ad;
    }

    public int getUcak_id() {
        return ucak_id;
    }

    public void setUcak_id(int ucak_id) {
        this.ucak_id = ucak_id;
    }

    public String getUcak_ad() {
        return ucak_ad;
    }

    public void setUcak_ad(String ucak_ad) {
        this.ucak_ad = ucak_ad;
    }

    public int getUcak_koltuk() {
        return ucak_koltuk;
    }

    public void setUcak_koltuk(int ucak_koltuk) {
        this.ucak_koltuk = ucak_koltuk;
    }

    public int getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(int firma_id) {
        this.firma_id = firma_id;
    }

    public String getFirma_ad() {
        return firma_ad;
    }

    public void setFirma_ad(String firma_ad) {
        this.firma_ad = firma_ad;
    }
    
    
    
}
