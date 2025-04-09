package com.example.applicationmobileairvip.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

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

        // Fixe le texte à "Aller simple" et empêche le clic

        // Lancer la recherche de vols
        btnRechercher.setOnClickListener(v -> {
            String from = fromText.getText().toString().trim();
            String to = toText.getText().toString().trim();

            if (from.isEmpty() || to.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer les codes IATA de départ et d'arrivée", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, VolListActivity.class);
            intent.putExtra("DEPART_IATA", from);
            intent.putExtra("ARRIVEE_IATA", to);
            startActivity(intent);
        });

        // Navigation entre les activités via la barre inférieure
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

