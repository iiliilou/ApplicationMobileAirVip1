package com.example.applicationmobileairvip.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.api.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class CompteActivity extends AppCompatActivity {

    private TextView tvLastName, tvFirstName, tvUserEmail;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        // Lier les vues
        tvLastName = findViewById(R.id.tv_last_name);
        tvFirstName = findViewById(R.id.tv_first_name);
        tvUserEmail = findViewById(R.id.tv_user_email);
        Button btnLogout = findViewById(R.id.btn_logout);

        // Récupérer user_id depuis SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1) {
            loadUserInfo(userId);
        } else {
            Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show();
        }

        // Déconnexion
        btnLogout.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            Intent intent = new Intent(CompteActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Navigation
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
    }

    private void loadUserInfo(int userId) {
        ApiClient.get("/utilisateurs/" + userId, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject user = new JSONObject(response);
                    String nom = user.getString("nom");
                    String prenom = user.getString("prenom");
                    String email = user.getString("adresse_courriel");

                    runOnUiThread(() -> {
                        tvLastName.setText("Nom : " + nom);
                        tvFirstName.setText("Prénom : " + prenom);
                        tvUserEmail.setText("Courriel : " + email);
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(CompteActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(CompteActivity.this, "Erreur de connexion : " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}
