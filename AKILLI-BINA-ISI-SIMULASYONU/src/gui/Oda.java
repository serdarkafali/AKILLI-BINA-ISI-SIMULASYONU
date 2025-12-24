package gui;

public class Oda {

    public String isim;
    public double hacim;
    public double oncelik;
    public double verimlilik = 0.85;

    public transient double simdikiSicaklik;
    public transient double hedefSicaklik;
    public transient double gerekliEnerji;
    public transient double verilenEnerji;
    public double minSicaklik = 16; // kritik alt sınır
    public double comfortMin = 23.0;
    public double comfortMax = 25.5;
    // json için gerekli cons yapmak
    public Oda() {}

    public Oda(String isim, double hacim, double simdikiSicaklik, double oncelik) {
        this.isim = isim;
        this.hacim = hacim;
        this.simdikiSicaklik = simdikiSicaklik;
        this.hedefSicaklik = simdikiSicaklik + 5;
        this.oncelik = oncelik;
    }
}
