package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.*;

public class EnerjiKaybiKarsilastir extends Application {

    private static final int MAX_SURE = 60;
    private static final double MAX_ENERJI = 20.0;

    @Override
    public void start(Stage stage) {

        List<Double> greedy = enerjiKaybiZaman("GREEDY");
        List<Double> dp = enerjiKaybiZaman("DP");
        List<Double> normal = enerjiKaybiZaman("NORMAL");

        CategoryAxis x = new CategoryAxis();
        x.setLabel("Zaman (dk)");

        NumberAxis y = new NumberAxis();
        y.setLabel("Kümülatif Enerji Kaybı (göreceli)");

        LineChart<String, Number> chart = new LineChart<>(x, y);
        chart.setTitle("Zamana Göre Enerji Kaybı Karşılaştırması");

        XYChart.Series<String, Number> sGreedy = new XYChart.Series<>();
        sGreedy.setName("Greedy");

        XYChart.Series<String, Number> sDP = new XYChart.Series<>();
        sDP.setName("Dinamik");

        XYChart.Series<String, Number> sNormal = new XYChart.Series<>();
        sNormal.setName("Normal");

        for (int i = 0; i < MAX_SURE; i++) {
            String dakika = String.valueOf(i + 1);
            sGreedy.getData().add(new XYChart.Data<>(dakika, greedy.get(i)));
            sDP.getData().add(new XYChart.Data<>(dakika, dp.get(i)));
            sNormal.getData().add(new XYChart.Data<>(dakika, normal.get(i)));
        }
        stage.setTitle("Enerji Kaybı - Zaman");
        chart.getData().addAll(sGreedy, sDP, sNormal);

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

    /* ---------------- ANA SİMÜLASYON ---------------- */

    private List<Double> enerjiKaybiZaman(String tip) {

        List<Oda> odalar = ornekOdaListesi();
        List<Double> kayiplar = new ArrayList<>();

        double kümülatifKayip = 0;

        for (int dakika = 1; dakika <= MAX_SURE; dakika++) {

            // Soğuma ve enerji kaybı
            for (Oda o : odalar) {
                double soguma = EnerjiHesap.soguma(o, 1);
                o.simdikiSicaklik = Math.max(10, o.simdikiSicaklik - soguma);

                // 🔴 Enerji kaybı burada toplanıyor
                kümülatifKayip += soguma;
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

            kayiplar.add(kümülatifKayip);
        }

        return kayiplar;
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