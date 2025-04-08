package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // 🔽 Gestion de la BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_status) {
                startActivity(new Intent(this, VolListActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            }
            return false;
        });

        // 🔐 Bouton "Mon compte"
        LinearLayout layoutAccount = findViewById(R.id.layout_account);
        layoutAccount.setOnClickListener(v -> {
            // À remplacer si tu as une page MonCompteActivity
            Intent intent = new Intent(this, CompteActivity.class);
            startActivity(intent);
        });

        // 🚪 Bouton "Déconnexion"
        LinearLayout layoutLogout = findViewById(R.id.layout_logout);
        layoutLogout.setOnClickListener(v -> {
            // Exemple : retour à l'écran de login
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}

