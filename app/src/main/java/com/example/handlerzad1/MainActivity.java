package com.example.handlerzad1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;
    private Button startButton;
    private int seconds = 0;
    private Handler handler = new Handler(); // Handler do obsługi opóźnień
    private boolean running = false; // Zmienna, która sprawdza, czy licznik jest uruchomiony

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timeTextView = findViewById(R.id.textView);
        startButton = findViewById(R.id.button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zmiana stanu "running" przy każdym kliknięciu przycisku
                running = !running; // Włączenie/wyłączenie licznika
                if (running) {
                    runTimer(); // Jeśli running == true, uruchamiamy licznik
                }
            }
        });
    }

    // Metoda odpowiedzialna za działanie licznika
    private void runTimer() {
        // Uruchomienie Runnable przez Handler
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Sprawdzamy, czy licznik jest uruchomiony
                if (running) {
                    // Zwiększamy ilość sekund i aktualizujemy TextView
                    seconds++;
                    timeTextView.setText(seconds + " sec"); // Wyświetlenie czasu na ekranie

                    // Powtórzenie działania z opóźnieniem 1000 ms (1 sekunda)
                    handler.postDelayed(this, 1000); // Rekurencyjne uruchomienie Runnable z opóźnieniem
                }
            }
        });
    }
}

