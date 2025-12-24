package gui;

import java.util.List;

public class EnerjiHesap {

    public static double disSicaklik = 10;

    public static void hesapla(Oda oda) {
// Dış ortamdan kaynaklı ısı kaybı
        double isiKaybiKatsayisi = 0.1;
        double kayip = (oda.hedefSicaklik - disSicaklik) * isiKaybiKatsayisi;

        if (oda.simdikiSicaklik >= oda.hedefSicaklik) {
            oda.gerekliEnerji = 0;
            return;
        }
// Oda hacmine göre soğut
        double efektifBaslangic = oda.simdikiSicaklik - kayip;
        double rho = 1.2;      // kg/m³ // oda yoğunluğu
        double c = 1.005;      // kJ/kg°C // hava özgül ısı

        double deltaT = oda.hedefSicaklik - efektifBaslangic;
        // deltaT ile odayı ısıtmak için gerekli enerji hesaplanır
        if (deltaT <= 0) {
            oda.gerekliEnerji = 0;
            return;
        }
        double mass = rho * oda.hacim;
        double Q = mass * c * deltaT;   // kJ
        double kWh = Q / 3600.0;

        oda.gerekliEnerji = kWh / oda.verimlilik;
    }
    // sonraki soğuma hacme dayalı değil
    public static double soguma(Oda oda, double dakika) {
        double k = 0.05; // ısı kayıp katsayısı
        return k * (oda.simdikiSicaklik - disSicaklik) * dakika;
    }

    public static double maliyetHesapla(double toplamKwh) {

        double birimFiyat = 2.59; // 1 kWh = 2.25 TL (örnek)
        return toplamKwh * birimFiyat;
    }
    // Verilen enerjiye göre sıcaklık artışı (kWh -> °C)
    public static double sicaklikArtisi(Oda oda, double verilenEnerji) {

        double rho = 1.2;   // kg/m³
        double c = 2.005;   // kJ/kg°C   // 1.005 // biraz arttırdım bir anda çok artmasın diye

        double mass = rho * oda.hacim;

        // kWh -> kJ
        double Q = verilenEnerji * 3600;

        // ΔT = Q / (m * c)
        return Q / (mass * c);
        //double zamanKatsayisi = 0.25; // 🔧 0.25 = yavaş ısınma
        //return (Q / (mass * c)) * zamanKatsayisi;

    }

}
