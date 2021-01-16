package model;

public class Havaalani_ulke {
    protected int havaalani_ulke_id;
    protected String havaalani_ulke_ad;

    public Havaalani_ulke() {
    }
    
    public Havaalani_ulke(String havaalani_ulke_ad) {
        this.havaalani_ulke_ad = havaalani_ulke_ad;
    }
    
    public Havaalani_ulke(int havaalani_ulke_id, String havaalani_ulke_ad) {
        this.havaalani_ulke_id = havaalani_ulke_id;
        this.havaalani_ulke_ad = havaalani_ulke_ad;
    }

    public int getHavaalani_ulke_id() {
        return havaalani_ulke_id;
    }

    public String getHavaalani_ulke_ad() {
        return havaalani_ulke_ad;
    }

    public void setHavaalani_ulke_id(int havaalani_ulke_id) {
        this.havaalani_ulke_id = havaalani_ulke_id;
    }

    public void setHavaalani_ulke_ad(String havaalani_ulke_ad) {
        this.havaalani_ulke_ad = havaalani_ulke_ad;
    }
    
}
