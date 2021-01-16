package model;

public class Firma{
    protected int firma_id;
    protected String firma_ad;
    protected String firma_logo;

    public Firma() {
    }

    public Firma(int firma_id, String firma_ad) {
        this.firma_id = firma_id;
        this.firma_ad = firma_ad;
    }

    public Firma(String firma_ad, String firma_logo) {
        this.firma_ad = firma_ad;
        this.firma_logo = firma_logo;
    }

    public Firma(int firma_id, String firma_ad, String firma_logo) {
        this.firma_id = firma_id;
        this.firma_ad = firma_ad;
        this.firma_logo = firma_logo;
    }

    public Firma(String firma_ad) {
        this.firma_ad = firma_ad;
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

    public String getFirma_logo() {
        return firma_logo;
    }

    public void setFirma_logo(String firma_logo) {
        this.firma_logo = firma_logo;
    }    
}
