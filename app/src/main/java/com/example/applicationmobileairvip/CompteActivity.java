package com.example.applicationmobileairvip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompteActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        // Lier les vues
        TextView tvLastName = findViewById(R.id.tv_last_name);
        TextView tvFirstName = findViewById(R.id.tv_first_name);
        TextView tvUserEmail = findViewById(R.id.tv_user_email);
        TextView tvNumTel = findViewById(R.id.tv_num_tel);
        Button btnLogout = findViewById(R.id.btn_logout);

        // 🔁 Données fictives (à remplacer par une source réelle ou une session utilisateur)
        tvLastName.setText("Nom : mal");
        tvFirstName.setText("Prénom : mok");
        tvUserEmail.setText("Courriel : malmok@example.com");
        tvNumTel.setText("Téléphone : +1 514 123 4567");

        // 🔓 Action déconnexion
        btnLogout.setOnClickListener(v -> {
            // Exemple : retour à la page d’accueil ou de login
            Intent intent = new Intent(CompteActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // 🔽 Navigation du bas
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings); // sélectionne "Compte"

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
    }
}
