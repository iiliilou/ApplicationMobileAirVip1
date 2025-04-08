package com.example.applicationmobileairvip;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText lastNameEditText, firstNameEditText, birthDateEditText;
    private EditText phoneEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Liaison des composants
        lastNameEditText = findViewById(R.id.lastNameEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        Button backToLoginButton = findViewById(R.id.backToLoginButton);

        // Bouton S'inscrire
        registerButton.setOnClickListener(v -> {
            String nom = lastNameEditText.getText().toString().trim();
            String prenom = firstNameEditText.getText().toString().trim();
            String naissance = birthDateEditText.getText().toString().trim(); // optionnel
            String telephone = phoneEditText.getText().toString().trim();     // optionnel
            String email = emailEditText.getText().toString().trim();
            String motdepasse = passwordEditText.getText().toString().trim();
            String confirmation = confirmPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(motdepasse) || TextUtils.isEmpty(confirmation)) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (!motdepasse.equals(confirmation)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(nom, prenom, email, motdepasse);
            }
        });

        // Retour à l'écran de login
        backToLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser(String nom, String prenom, String email, String motdepasse) {
        try {
            JSONObject userData = new JSONObject();
            userData.put("nom", nom);
            userData.put("prenom", prenom);
            userData.put("adresse_courriel", email);
            userData.put("mot_de_passe", motdepasse);
            userData.put("role", "client");

            ApiClient.post("/utilisateurs/sign-up", userData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(RegisterActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
