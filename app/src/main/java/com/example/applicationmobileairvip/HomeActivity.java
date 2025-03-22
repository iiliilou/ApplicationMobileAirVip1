package com.example.applicationmobileairvip;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private TextView fromText, toText;
    private EditText departureDateEditText, returnDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Récupération des vues
        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        departureDateEditText = findViewById(R.id.editDateDepart);
        returnDateEditText = findViewById(R.id.editDateRetour);
        Button btnRechercher = findViewById(R.id.btnRechercher);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Bouton de recherche
        btnRechercher.setOnClickListener(v -> {
            String from = fromText.getText().toString().trim();
            String to = toText.getText().toString().trim();
            String depart = departureDateEditText.getText().toString().trim();
            String retour = returnDateEditText.getText().toString().trim();

            Toast.makeText(this,
                    "Recherche : " + from + " ➡ " + to + "\nDépart : " + depart + " | Retour : " + retour,
                    Toast.LENGTH_LONG).show();
        });

        // Navigation du bas
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_reserver) {
                Toast.makeText(this, "Réserver", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_status) {
                Toast.makeText(this, "Statut vol", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_trips) {
                Toast.makeText(this, "Voyages", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });
    }
}

