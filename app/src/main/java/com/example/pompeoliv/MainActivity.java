package com.example.pompeoliv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView labelPumpStatus, labelHumidity, labelTemperature;
    private Button buttonStart, buttonStop, buttonAutoMode;
    private boolean isAutoModeEnabled = false;
    private int humidity = 65;
    private int temperature = 22;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelPumpStatus = findViewById(R.id.label_pump_status);
        labelHumidity = findViewById(R.id.label_humidity);
        labelTemperature = findViewById(R.id.label_temperature);
        buttonStart = findViewById(R.id.button_start);
        buttonStop = findViewById(R.id.button_stop);
        buttonAutoMode = findViewById(R.id.button_auto_mode);

        buttonStart.setOnClickListener(v -> setPumpStatus("En marche"));

        buttonStop.setOnClickListener(v -> setPumpStatus("En arrêt"));

        buttonAutoMode.setOnClickListener(v -> toggleAutoMode());

        findViewById(R.id.button_increase_humidity).setOnClickListener(v -> {
            humidity += 5;
            updateConditions();
            if (isAutoModeEnabled) checkAutoMode();
        });

        findViewById(R.id.button_decrease_humidity).setOnClickListener(v -> {
            humidity -= 5;
            updateConditions();
            if (isAutoModeEnabled) checkAutoMode();
        });

        findViewById(R.id.button_increase_temperature).setOnClickListener(v -> {
            temperature += 1;
            updateConditions();
            if (isAutoModeEnabled) checkAutoMode();
        });

        findViewById(R.id.button_decrease_temperature).setOnClickListener(v -> {
            temperature -= 1;
            updateConditions();
            if (isAutoModeEnabled) checkAutoMode();
        });
    }

    private void setPumpStatus(String status) {
        labelPumpStatus.setText("État actuel : " + status);
    }

    private void updateConditions() {
        labelHumidity.setText("Humidité :\n " + humidity + "%");
        labelTemperature.setText("Température :\n " + temperature + "°C");
    }

    private void toggleAutoMode() {
        isAutoModeEnabled = !isAutoModeEnabled;
        buttonAutoMode.setText(isAutoModeEnabled ? "Désactiver Mode Auto" : "Activer Mode Automatique");

        if (isAutoModeEnabled) {
            checkAutoMode();
        }
    }

    private void checkAutoMode() {
        if (temperature < 34 && humidity > 60) {
            setPumpStatus("En arrêt");
        } else {
            setPumpStatus("En marche");
        }
    }
}
