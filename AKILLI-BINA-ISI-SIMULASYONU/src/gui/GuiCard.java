package gui;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;


public class GuiCard extends VBox {

    private ProgressBar tempBar;
    private Runnable onSil;
    private final Oda oda;
    private final Label gerekliEnerjiLabel;
    private final Label sicakliklabel;

    public GuiCard(Oda oda, Runnable deleteCallback) {
        this(oda); // mevcut tek parametreli constructor’ı çağırır
        this.onSil = deleteCallback; // veya deleteCallback alanın hangi isimse ona ata

        tempBar = new ProgressBar(0);
        tempBar.setPrefWidth(180);

        setStyle("""
            -fx-background-radius: 14;
            -fx-border-radius: 14;
            -fx-border-color: #2f3640;
            -fx-background-color: #f5f6fa;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 10, 0, 3, 3);
        """);
    }

    public GuiCard(Oda oda) {
        this.oda = oda;

        setSpacing(8);
        setPadding(new Insets(12));
        setPrefWidth(220);
        setStyle("-fx-border-color: black;");

        Label labelismi = new Label("Oda: " + oda.isim);
        Label labelhacim = new Label("Hacim: " + oda.hacim + " m³");
        Label onceliklabel = new Label("Öncelik: " + oda.oncelik);

        gerekliEnerjiLabel = new Label("Enerji: 0 kWh");
        sicakliklabel = new Label("Sıcaklık: " + oda.simdikiSicaklik + " °C");

        Button silBtn = new Button("Sil");
        silBtn.setOnAction(e -> {
            if (onSil != null) onSil.run();
        });

        getChildren().addAll(
                labelismi,
                labelhacim,
                onceliklabel,
                gerekliEnerjiLabel,
                sicakliklabel,
                silBtn
        );
    }

    public void setOnSil(Runnable r) {
        this.onSil = r;
    }

    public void refreshTemperature() {
        sicakliklabel.setText(
                String.format("Sıcaklık: %.1f °C", oda.simdikiSicaklik)
        );

        double oran = (oda.simdikiSicaklik - 10) / (oda.hedefSicaklik - 10);
        tempBar.setProgress(Math.min(1, Math.max(0, oran)));
    }

    public void setAllocated(double VerilecekEnerji) {
        gerekliEnerjiLabel.setText(
                String.format("Enerji: %.2f kWh", VerilecekEnerji)
        );
    }

    public void updateColor(double VerilecekEnerji) {

        if (oda.simdikiSicaklik >= oda.comfortMin && oda.simdikiSicaklik <= oda.comfortMax) {
            // Konforlu
            setStyle("-fx-background-color:#dff9fb; -fx-border-color:black;");
        }
        else if (VerilecekEnerji > 0) {
            // Isıtılıyor
            setStyle("""
                -fx-background-color: #2ecc71;
                -fx-background-radius: 14;
                -fx-border-radius: 14;
                -fx-border-color: #2f3640;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 10, 0, 3, 3);
            """);
        }
        else {
            // Soğuyor
            setStyle("""
                -fx-background-color: #e74c3c;
                -fx-background-radius: 14;
                -fx-border-radius: 14;
                -fx-border-color: #2f3640;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 10, 0, 3, 3);
            """);
        }
    }

}
