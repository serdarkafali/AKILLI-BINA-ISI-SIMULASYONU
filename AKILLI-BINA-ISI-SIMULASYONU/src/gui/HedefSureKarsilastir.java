

package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.*;


// tüm odaları konfora ulaştırmak için gereken süre hesaplar
public class HedefSureKarsilastir extends Application {

    private static final int MAX_SURE = 60;
    private static final double MAX_ENERJI = 35.0;

    @Override
    public void start(Stage stage) {

        int greedySure = greedySureHesapla();
        int dpSure = dpSureHesapla();
        int normalSure = normalSureHesapla();

        CategoryAxis x = new CategoryAxis();
        x.setLabel("Algoritma");

        NumberAxis y = new NumberAxis(0, 60, 5);
        y.setLabel("Tüm Odaların Konfora Ulaşma Süresi (dk)");

        BarChart<String, Number> chart = new BarChart<>(x, y);
        chart.setTitle("Hedef Süre Karşılaştırması");

        XYChart.Series<String, Number> s = new XYChart.Series<>();
        s.setName("Süre (dk)");

        s.getData().add(new XYChart.Data<>("Greedy", greedySure));
        s.getData().add(new XYChart.Data<>("Dinamik", dpSure));
        s.getData().add(new XYChart.Data<>("Normal", normalSure));

        chart.getData().add(s);
        stage.setTitle("Hedef Süre Analizi");
        Button geriBtn = new Button("← Ana Ekrana Dön");
        geriBtn.setOnAction(e -> {
            stage.close();
            try {
                new GuiEkrani().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(chart);
        root.setTop(geriBtn);

        BorderPane.setMargin(geriBtn, new Insets(10));

        stage.setScene(new Scene(root, 800, 500));
        stage.setMaximized(true);
        stage.show();
    }

    /* ---------------- GREEDY ---------------- */

    private int greedySureHesapla() {
        List<Oda> odalar = ornekOdaListesi();
        return dakikaSimulasyon(odalar, "GREEDY");
    }

    /* ---------------- DP ---------------- */

    private int dpSureHesapla() {
        List<Oda> odalar = ornekOdaListesi();
        return dakikaSimulasyon(odalar, "DP");
    }

    /* ---------------- NORMAL ---------------- */

    private int normalSureHesapla() {
        List<Oda> odalar = ornekOdaListesi();
        return dakikaSimulasyon(odalar, "NORMAL");
    }

    /* ---------------- ORTAK SİMÜLASYON ---------------- */

    private int dakikaSimulasyon(List<Oda> odalar, String tip) {

        for (int dakika = 1; dakika <= MAX_SURE; dakika++) {

            // soğuma
            for (Oda o : odalar) {
                o.simdikiSicaklik = Math.max(
                        10,
                        o.simdikiSicaklik - EnerjiHesap.soguma(o, 1)
                );
            }

            odalar.forEach(EnerjiHesap::hesapla);

            double kalan = MAX_ENERJI / 60.0;

            List<Oda> sirali = new ArrayList<>(odalar);

            if (tip.equals("GREEDY")) {
                sirali.sort((a, b) -> Double.compare(
                        b.oncelik / Math.max(b.gerekliEnerji, 0.001),
                        a.oncelik / Math.max(a.gerekliEnerji, 0.001)
                ));
            }
            else if (tip.equals("DP")) {
                sirali.sort(Comparator.comparingDouble(
                        o -> o.comfortMin - o.simdikiSicaklik
                ));
            }
            // NORMAL → sıralama yok

            double pay = kalan / sirali.size();

            for (Oda o : sirali) {

                if (kalan <= 0) break;
                if (o.simdikiSicaklik >= o.comfortMin) continue;

                double verilecek;

                if (tip.equals("NORMAL")) {
                    verilecek = Math.min(pay, o.gerekliEnerji);
                } else {
                    verilecek = Math.min(o.gerekliEnerji, kalan);
                }

                kalan -= verilecek;
                o.simdikiSicaklik += EnerjiHesap.sicaklikArtisi(o, verilecek);
            }

            // hepsi konforda mı?
            boolean hepsi = true;
            for (Oda o : odalar) {
                if (o.simdikiSicaklik < o.comfortMin) {
                    hepsi = false;
                    break;
                }
            }

            if (hepsi) return dakika;
        }

        return 60;
    }

    /* ---------------- ODA KOPYASI ---------------- */

    private List<Oda> ornekOdaListesi() {
        List<Oda> list = new ArrayList<>();


        list.add(new Oda("CEO Odası", 45, 19, 9));
        list.add(new Oda("Genel Müdür Odası", 45, 19, 9));
        list.add(new Oda("Büyük Toplantı Odası", 120, 20, 9));
        list.add(new Oda("Yazılım Ekibi", 90, 20, 9));
        list.add(new Oda("Operasyon", 90, 21, 9));
        list.add(new Oda("Satış", 90, 21, 9));
        list.add(new Oda("Elektrik / Mekanik Oda", 30, 19, 9));
        list.add(new Oda("Sunucu Odası", 60, 20, 9));
        list.add(new Oda("Network / IT Destek", 45, 18, 9));
        list.add(new Oda("Sistem İzleme Odası", 30, 21, 9));

        list.add(new Oda("Proje Odası", 45, 20, 8));
        list.add(new Oda("Müdür Odası", 30, 20, 8));

        list.add(new Oda("Resepsiyon", 70, 18, 7));
        list.add(new Oda("Sunum Salonu", 60, 20, 7));

        list.add(new Oda("İnsan Kaynakları", 120, 20, 6));
        list.add(new Oda("Muhasebe", 30, 19, 6));
        list.add(new Oda("Satın Alma", 30, 19, 6));

        list.add(new Oda("Sigara Alanı (Kapalı)", 30, 20, 4));
        list.add(new Oda("Arşiv Odası", 45, 20, 4));

        list.add(new Oda("WC", 30, 21, 2));

        return list;
    }

    public static void main(String[] args) {
        launch();
    }
}