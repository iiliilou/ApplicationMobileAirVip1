package com.example.applicationmobileairvip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FlightStatusActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status);

        // Lier les vues
        TextView tvNumeroVol = findViewById(R.id.numeroVol);
        TextView tvHeureDecollage = findViewById(R.id.heureDecollage);
        TextView tvDateVol = findViewById(R.id.dateVol);
        TextView tvDepart = findViewById(R.id.aeroportDepart);
        TextView tvArrivee = findViewById(R.id.aeroportArrivee);
        TextView tvStatut = findViewById(R.id.retardStatut);

        // 🔁 Exemple de données simulées ( les passer par Intent plus tard)
        tvNumeroVol.setText("Vol : AI245");
        tvHeureDecollage.setText("Heure de décollage : 13h30");
        tvDateVol.setText("Date : 7 avril 2025");
        tvDepart.setText("Départ : YUL (Montréal)");
        tvArrivee.setText("Arrivée : CDG (Paris)");

        boolean enRetard = false; //  changer ça dynamiquement selon l’API

        if (enRetard) {
            tvStatut.setText("Statut :  Retardé de 45 min");
            tvStatut.setTextColor(Color.parseColor("#D32F2F")); // rouge
        } else {
            tvStatut.setText("Statut :  À l'heure");
            tvStatut.setTextColor(Color.parseColor("#4CAF50")); // vert
        }

        // 🔽 Navigation du bas
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_status); // active l'onglet "Statut"

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, CompteActivity.class));
                return true;
            }
            return false;
        });
    }
}

