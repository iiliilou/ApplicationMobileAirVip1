package com.example.applicationmobileairvip.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerTabButton = findViewById(R.id.registerTabButton);

        // Connexion
        loginButton.setOnClickListener(v -> {
            v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction(() -> v.animate().scaleX(1f).scaleY(1f).setDuration(100));

            String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                authenticateUser(email, password);
            }
        });

        // Lien vers l’inscription
        registerTabButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }

    private void authenticateUser(String email, String password) {
        // Compte d’essai (bypass API)
        if (email.equals("test@airvip.com") && password.equals("123456")) {
            SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
            prefs.edit()
                    .putString("token", "fake-token-test")
                    .putString("role", "client")
                    .putInt("user_id", 9999)
                    .apply();

            Toast.makeText(MainActivity.this, "Connexion réussie (mode test)", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }

        // Appel normal à l’API
        try {
            JSONObject userData = new JSONObject();
            userData.put("adresse_courriel", email);
            userData.put("mot_de_passe", password);

            ApiClient.post("/utilisateurs/sign-in", userData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject json = new JSONObject(response);
                        String token = json.getString("token");
                        String role = json.getString("role");
                        int userId = json.getInt("id");

                        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
                        prefs.edit()
                                .putString("token", token)
                                .putString("role", role)
                                .putInt("user_id", userId)
                                .apply();

                        Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish();
                    } catch (Exception e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_LONG).show());
                    }
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

