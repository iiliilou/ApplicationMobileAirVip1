package com.example.applicationmobileairvip.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private EditText fromText, toText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        Button btnRechercher = findViewById(R.id.btnRechercher);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //  Recherche de vols
        btnRechercher.setOnClickListener(v -> {
            String from = fromText.getText().toString().trim();
            String to = toText.getText().toString().trim();

            //  Validation simple
            if (from.isEmpty() || to.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer les codes IATA de départ et d'arrivée", Toast.LENGTH_SHORT).show();
                return;
            }

            //  Vérifie que ce sont bien des codes IATA de 3 lettres
            if (from.length() != 3 || to.length() != 3) {
                Toast.makeText(this, "Les codes IATA doivent contenir exactement 3 lettres", Toast.LENGTH_SHORT).show();
                return;
            }

            //  Met en majuscules pour cohérence avec l'API
            Intent intent = new Intent(this, VolListActivity.class);
            intent.putExtra("DEPART_IATA", from.toUpperCase());
            intent.putExtra("ARRIVEE_IATA", to.toUpperCase());
            startActivity(intent);
        });

        //  Barre de navigation inférieure
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_status) {
                startActivity(new Intent(this, VolListActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            return false;
        });
    }
}
