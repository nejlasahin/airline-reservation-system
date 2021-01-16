package model;

public class Havaalani_sehir {
    protected int havaalani_sehir_id;
    protected String havaalani_sehir_ad;

    public Havaalani_sehir() {
    }

    public Havaalani_sehir(String havaalani_sehir_ad) {
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }

    public Havaalani_sehir(int havaalani_sehir_id, String havaalani_sehir_ad) {
        this.havaalani_sehir_id = havaalani_sehir_id;
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }

    public int getHavaalani_sehir_id() {
        return havaalani_sehir_id;
    }

    public void setHavaalani_sehir_id(int havaalani_sehir_id) {
        this.havaalani_sehir_id = havaalani_sehir_id;
    }

    public String getHavaalani_sehir_ad() {
        return havaalani_sehir_ad;
    }

    public void setHavaalani_sehir_ad(String havaalani_sehir_ad) {
        this.havaalani_sehir_ad = havaalani_sehir_ad;
    }
}
