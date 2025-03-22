package com.example.applicationmobileairvip;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        Button backToLoginButton = findViewById(R.id.backToLoginButton); // 🆕

        // Action bouton S’inscrire
        registerButton.setOnClickListener(v -> {
            String nom = lastNameEditText.getText().toString().trim();
            String prenom = firstNameEditText.getText().toString().trim();
            String naissance = birthDateEditText.getText().toString().trim();
            String telephone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String motdepasse = passwordEditText.getText().toString().trim();
            String confirmation = confirmPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(naissance)
                    || TextUtils.isEmpty(telephone) || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(motdepasse) || TextUtils.isEmpty(confirmation)) {

                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();

            } else if (!motdepasse.equals(confirmation)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();

            } else {
                // TODO : enregistrement futur
                Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                finish(); // Retour à la page de connexion
            }
        });

        // 🔙 Bouton "J’ai déjà un compte"
        backToLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // ferme RegisterActivity pour éviter retour avec le bouton back
        });
    }
}
