package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class GuiEkrani extends Application {
    private static final double maxEnergy = 100.0;

    private final Map<Oda, GuiCard> cardMap = new HashMap<>();

    private FlowPane pane = new FlowPane();

    // sonradan eklenen dp greedy karşılaştırması
    private FlowPane paneGreedy = new FlowPane();
    private FlowPane paneDP = new FlowPane();

    private final List<Oda> odalarGreedy = new ArrayList<>();
    private final List<Oda> odalarDP = new ArrayList<>();

    private final Map<Oda, GuiCard> cardMapGreedy = new HashMap<>();
    private final Map<Oda, GuiCard> cardMapDP = new HashMap<>();

    private double toplamEnerjiGreedy = 0;
    private double toplamEnerjiDP = 0;


    private Label greedyEnerjiLabel = new Label("Greedy Enerji: 0 kWh");
    private Label greedyMaliyetLabel = new Label("Greedy Maliyet: 0 ₺");

    private Label dpEnerjiLabel = new Label("DP Enerji: 0 kWh");
    private Label dpMaliyetLabel = new Label("DP Maliyet: 0 ₺");

    private Label dakikaLabel = new Label("Geçen Süre: 0 dk");

    private Timeline simTimeline;
    private int simMinute = 0;   // kaç dakika geçti
    private final int SIM_DURATION = 60; // 60 dk = 1 saat
    private int dakikaGecti = 0;
    private VBox analizPanel = new VBox(10);

    @Override
    public void start(Stage stage) {

        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);


        greedyEnerjiLabel.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");
        greedyMaliyetLabel.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");
        dpEnerjiLabel.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");
        dpMaliyetLabel.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");

        dakikaLabel.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");


        Oda o1 =(new Oda("CEO Odası", 45, 19, 9));
        Oda o2 =(new Oda("Genel Müdür Odası", 45, 19, 9));
        Oda o3 =(new Oda("Büyük Toplantı Odası", 120, 20, 9));
        Oda o4 =(new Oda("Yazılım Ekibi", 90, 20, 9));
        Oda o5 =(new Oda("Operasyon", 90, 21, 9));
        Oda o6 =(new Oda("Satış", 90, 21, 9));
        Oda o7 =(new Oda("Elektrik / Mekanik Oda", 30, 19, 9));
        Oda o8 =(new Oda("Sunucu Odası", 60, 20, 9));
        Oda o9 =(new Oda("Network / IT Destek", 45, 18, 9));
        Oda o10 =(new Oda("Sistem İzleme Odası", 30, 21, 9));

        Oda o11 =(new Oda("Proje Odası", 45, 20, 8));
        Oda o12 =(new Oda("Müdür Odası", 30, 20, 8));

        Oda o13 = (new Oda("Resepsiyon", 70, 18, 7));
        Oda o14 = (new Oda("Sunum Salonu", 60, 20, 7));

        Oda o15 =(new Oda("İnsan Kaynakları", 120, 20, 6));
        Oda o16 =(new Oda("Muhasebe", 30, 19, 6));
        Oda o17 =(new Oda("Satın Alma", 30, 19, 6));

        Oda o18 =(new Oda("Sigara Alanı (Kapalı)", 30, 20, 4));
        Oda o19 =(new Oda("Arşiv Odası", 45, 20, 4));

        Oda o20 =(new Oda("WC", 30, 21, 2));
/// //// db için
        Oda o21 =(new Oda("CEO Odası", 45, 19, 9));
        Oda o22 =(new Oda("Genel Müdür Odası", 45, 19, 9));
        Oda o23 =(new Oda("Büyük Toplantı Odası", 120, 20, 9));
        Oda o24 =(new Oda("Yazılım Ekibi", 90, 20, 9));
        Oda o25 =(new Oda("Operasyon", 90, 21, 9));
        Oda o26 =(new Oda("Satış", 90, 21, 9));
        Oda o27 =(new Oda("Elektrik / Mekanik Oda", 30, 19, 9));
        Oda o28 =(new Oda("Sunucu Odası", 60, 20, 9));
        Oda o29 =(new Oda("Network / IT Destek", 45, 18, 9));
        Oda o30 =(new Oda("Sistem İzleme Odası", 30, 21, 9));

        Oda o31 =(new Oda("Proje Odası", 45, 20, 8));
        Oda o32 =(new Oda("Müdür Odası", 30, 20, 8));

        Oda o33 = (new Oda("Resepsiyon", 70, 18, 7));
        Oda o34 = (new Oda("Sunum Salonu", 60, 20, 7));

        Oda o35 =(new Oda("İnsan Kaynakları", 120, 20, 6));
        Oda o36 =(new Oda("Muhasebe", 30, 19, 6));
        Oda o37 =(new Oda("Satın Alma", 30, 19, 6));

        Oda o38 =(new Oda("Sigara Alanı (Kapalı)", 30, 20, 4));
        Oda o39 =(new Oda("Arşiv Odası", 45, 20, 4));

        Oda o40 =(new Oda("WC", 30, 21, 2));



        odalarGreedy.add(o1);
        odalarGreedy.add(o2);
        odalarGreedy.add(o3);
        odalarGreedy.add(o4);
        odalarGreedy.add(o5);
        odalarGreedy.add(o6);
        odalarGreedy.add(o7);
        odalarGreedy.add(o8);
        odalarGreedy.add(o9);
        odalarGreedy.add(o10);
        odalarGreedy.add(o11);
        odalarGreedy.add(o12);
        odalarGreedy.add(o13);
        odalarGreedy.add(o14);
        odalarGreedy.add(o15);
        odalarGreedy.add(o16);
        odalarGreedy.add(o17);
        odalarGreedy.add(o18);
        odalarGreedy.add(o19);
        odalarGreedy.add(o20);

        odalarDP.add(o21);
        odalarDP.add(o22);
        odalarDP.add(o23);
        odalarDP.add(o24);
        odalarDP.add(o25);
        odalarDP.add(o26);
        odalarDP.add(o27);
        odalarDP.add(o28);
        odalarDP.add(o29);
        odalarDP.add(o30);
        odalarDP.add(o31);
        odalarDP.add(o32);
        odalarDP.add(o33);
        odalarDP.add(o34);
        odalarDP.add(o35);
        odalarDP.add(o36);
        odalarDP.add(o37);
        odalarDP.add(o38);
        odalarDP.add(o39);
        odalarDP.add(o40);

        kartEkleGreedy(o1);
        kartEkleGreedy(o2);
        kartEkleGreedy(o3);
        kartEkleGreedy(o4);
        kartEkleGreedy(o5);
        kartEkleGreedy(o6);
        kartEkleGreedy(o7);
        kartEkleGreedy(o8);
        kartEkleGreedy(o9);
        kartEkleGreedy(o10);
        kartEkleGreedy(o11);
        kartEkleGreedy(o12);
        kartEkleGreedy(o13);
        kartEkleGreedy(o14);
        kartEkleGreedy(o15);
        kartEkleGreedy(o16);
        kartEkleGreedy(o17);
        kartEkleGreedy(o18);
        kartEkleGreedy(o19);
        kartEkleGreedy(o20);

        kartEkleDP(o21);
        kartEkleDP(o22);
        kartEkleDP(o23);
        kartEkleDP(o24);
        kartEkleDP(o25);
        kartEkleDP(o26);
        kartEkleDP(o27);
        kartEkleDP(o28);
        kartEkleDP(o29);
        kartEkleDP(o30);
        kartEkleDP(o31);
        kartEkleDP(o32);
        kartEkleDP(o33);
        kartEkleDP(o34);
        kartEkleDP(o35);
        kartEkleDP(o36);
        kartEkleDP(o37);
        kartEkleDP(o38);
        kartEkleDP(o39);
        kartEkleDP(o40);


        Button baslat = new Button("Simülasyonu Başlat");
        baslat.setOnAction(e -> startSimulation());

        Button btn1 = new Button("K.Enerji Farkı");
        btn1.setOnAction(e -> new EnerjiKaybiKarsilastir().start(stage));

        Button btn2 = new Button("Ortalama Sıcaklık");
        btn2.setOnAction(e -> new OrtalamaSicaklikZaman().start(stage));

        Button btn3 = new Button("Odaları ısıtma süre");
        btn3.setOnAction(e -> new HedefSureKarsilastir().start(stage));

        Button btn4 = new Button("Konfor Oranı");

        btn4.setOnAction(e -> {new KonforOraniZamanGrafik().start(stage);
        });



        HBox top = new HBox(
                15,
                baslat,
                new Separator(),
                greedyEnerjiLabel,
                greedyMaliyetLabel,
                new Separator(),
                dpEnerjiLabel,
                dpMaliyetLabel,
                dakikaLabel,
                btn1,
                btn2,
                btn3,
                btn4
        );
        top.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(top);
        paneGreedy.setPadding(new Insets(10));
        paneDP.setPadding(new Insets(10));

        paneGreedy.setHgap(10);
        paneGreedy.setVgap(10);
        paneDP.setHgap(10);
        paneDP.setVgap(10);

        VBox left = new VBox(new Label("GREEDY"), paneGreedy);
        VBox right = new VBox(new Label("DİNAMİK PROGRAMLAMA"), paneDP);

        left.setSpacing(5);
        right.setSpacing(5);
        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        left.setPrefWidth(550);
        right.setPrefWidth(550);

        ScrollPane scrollGreedy = new ScrollPane(paneGreedy);
        ScrollPane scrollDP = new ScrollPane(paneDP);

        scrollGreedy.setFitToWidth(true);
        scrollDP.setFitToWidth(true);

        scrollGreedy.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollDP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        paneGreedy.setPrefWrapLength(520);
        paneDP.setPrefWrapLength(520);


        HBox center = new HBox(left, right);
        center.setSpacing(20);

        root.setCenter(center);

        analizPanel.setPadding(new Insets(10));
        analizPanel.setStyle("-fx-border-color: gray;");

        root.setBottom(analizPanel);
        stage.setScene(new Scene(root, 1100, 600));
        stage.setTitle("Akıllı Bina Isı Simülasyonu");
        stage.setMaximized(true);
        stage.show();
    }

    private void startSimulation() {

        dakikaGecti = 0;
        toplamEnerjiGreedy = 0;
        toplamEnerjiDP = 0;

        if (simTimeline != null) {
            simTimeline.stop();
        }

        simMinute = 0;

        simTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> simStep())
        );

        simTimeline.setCycleCount(SIM_DURATION);
        simTimeline.play();
    }




    private void kartEkleGreedy(Oda oda) {
        GuiCard card = new GuiCard(oda, null);
        cardMapGreedy.put(oda, card);
        paneGreedy.getChildren().add(card);
    }

    private void kartEkleDP(Oda oda) {
        GuiCard card = new GuiCard(oda, null);
        cardMapDP.put(oda, card);
        paneDP.getChildren().add(card);
    }

    public static void main(String[] args) {
        launch();
    }
    private void simStep() {

        dakikaGecti++;
        dakikaLabel.setText("Geçen Süre: " + dakikaGecti + " dk");

        // Soğuma
        for (Oda o : odalarGreedy) {
            o.simdikiSicaklik = Math.max(
                    10,
                    o.simdikiSicaklik - EnerjiHesap.soguma(o, 1)
            );
        }
        // Enerji ihtiyacını güncelle
        odalarGreedy.forEach(EnerjiHesap::hesapla);

        double dakikaEnerji = maxEnergy / 60.0;
        double remaining = dakikaEnerji;

        // Greedy sıralama
        List<Oda> sirali = new ArrayList<>(odalarGreedy);
        sirali.sort((a, b) -> Double.compare(
                b.oncelik / Math.max(b.gerekliEnerji, 0.001),
                a.oncelik / Math.max(a.gerekliEnerji, 0.001)
        ));

        for (Oda o : sirali) {

            GuiCard c = cardMapGreedy.get(o);
            if (c == null) continue;

            double VerilecekEnerji = 0;

            // Konforda tutmaya çalış
            if (o.simdikiSicaklik >= o.comfortMin && o.simdikiSicaklik <= o.comfortMax) {

                VerilecekEnerji = Math.min(remaining, 0.01);
                remaining -= VerilecekEnerji;
                toplamEnerjiGreedy += VerilecekEnerji;

                o.simdikiSicaklik += EnerjiHesap.sicaklikArtisi(o, VerilecekEnerji);
            }
            // Soğuk ->> greede göre ısıt
            else if (o.simdikiSicaklik < o.comfortMin && remaining > 0) {

                VerilecekEnerji = Math.min(o.gerekliEnerji, remaining);
                remaining -= VerilecekEnerji;
                toplamEnerjiGreedy += VerilecekEnerji;

                o.simdikiSicaklik += EnerjiHesap.sicaklikArtisi(o, VerilecekEnerji);
            }
            c.setAllocated(VerilecekEnerji);
            c.updateColor(VerilecekEnerji);
            c.refreshTemperature();
        }
        greedyEnerjiLabel.setText(
                String.format("Greedy Enerji: %.2f kWh / %.2f", toplamEnerjiGreedy, maxEnergy)
        );

        greedyMaliyetLabel.setText(
                String.format("Greedy Maliyet: %.2f ₺",
                        EnerjiHesap.maliyetHesapla(toplamEnerjiGreedy))
        );
        
        simStepDP();
    }
    private void simStepDP() {
 // tam dp kullanılmıyor tam dp bütün olasıklıkları hesaba katıp yapar bunda sadece maksimum oda sıcaklığa ulaştırmak için hesaplama yapar
        for (Oda o : odalarDP) {
            o.simdikiSicaklik = Math.max(
                    10,
                    o.simdikiSicaklik - EnerjiHesap.soguma(o, 1)
            );
        }

        odalarDP.forEach(EnerjiHesap::hesapla);
        double dakikaEnerji = maxEnergy / 60.0;
        double remaining = dakikaEnerji;

        List<Oda> sirali = new ArrayList<>(odalarDP);
        sirali.sort((a, b) -> Double.compare(
                (a.comfortMin - a.simdikiSicaklik),
                (b.comfortMin - b.simdikiSicaklik)
        ));

        for (Oda o : sirali) {

            GuiCard c = cardMapDP.get(o);
            if (c == null) continue;

            double verilecek = 0;

            if (o.simdikiSicaklik < o.comfortMin && remaining > 0) {

                double ihtiyac = o.gerekliEnerji;
                if (ihtiyac <= 0) continue;

                verilecek = Math.min(ihtiyac, remaining);

                double artacakSicaklik =
                        EnerjiHesap.sicaklikArtisi(o, verilecek);

                //  comfortMax üstüne çıkma
                if (o.simdikiSicaklik + artacakSicaklik > o.comfortMax) {
                    verilecek = 0;
                }

                if (verilecek > 0) {
                    remaining -= verilecek;
                    toplamEnerjiDP += verilecek-0.01;
                    o.simdikiSicaklik +=
                            EnerjiHesap.sicaklikArtisi(o, verilecek);
                }
            }

            c.setAllocated(verilecek);
            c.updateColor(verilecek);
            c.refreshTemperature();
        }
        dpEnerjiLabel.setText(
                String.format("DP Enerji: %.2f kWh / %.2f", toplamEnerjiDP, maxEnergy)
        );

        dpMaliyetLabel.setText(
                String.format("DP Maliyet: %.2f ₺",
                        EnerjiHesap.maliyetHesapla(toplamEnerjiDP))
        );

    }

}
