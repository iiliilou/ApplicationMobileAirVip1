package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.api.ApiClient;

import org.json.JSONObject;

public class PaiementActivity extends AppCompatActivity {

    private EditText editNumeroCarte, editNomCarte, editExpiration, editCVV;
    private int volId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);

        // Récupérer le vol_id
        volId = getIntent().getIntExtra("vol_id", -1);

        // Lier les éléments du layout
        editNumeroCarte = findViewById(R.id.editNumeroCarte);
        editNomCarte = findViewById(R.id.editNomCarte);
        editExpiration = findViewById(R.id.editExpiration);
        editCVV = findViewById(R.id.editCVV);
        Button btnPayer = findViewById(R.id.btnPayer);

        // Action du bouton "Payer maintenant"
        btnPayer.setOnClickListener(view -> {
            String numeroCarte = editNumeroCarte.getText().toString().trim();
            String nomCarte = editNomCarte.getText().toString().trim();
            String expiration = editExpiration.getText().toString().trim();
            String cvv = editCVV.getText().toString().trim();

            if (validerChamps(numeroCarte, nomCarte, expiration, cvv)) {
                if (volId == -1) {
                    Toast.makeText(this, "Aucun vol sélectionné", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Récupérer l'email depuis SharedPreferences
                SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
                String email = prefs.getString("user_email", null);

                if (email == null) {
                    Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer le JSON pour la réservation
                try {
                    JSONObject json = new JSONObject();
                    json.put("email", email);
                    json.put("volId", volId);

                    // Envoi à l’API REST
                    ApiClient.post("/reservations", json, new ApiClient.ApiCallback() {
                        @Override
                        public void onSuccess(String response) {
                            runOnUiThread(() -> {
                                Toast.makeText(PaiementActivity.this, "Réservation confirmée !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PaiementActivity.this, TripsActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            });
                        }

                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(() ->
                                    Toast.makeText(PaiementActivity.this, "Erreur API : " + e.getMessage(), Toast.LENGTH_LONG).show());
                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(this, "Erreur lors de la création JSON : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validerChamps(String numero, String nom, String expiration, String cvv) {
        if (TextUtils.isEmpty(numero) || numero.length() != 16) {
            Toast.makeText(this, "Numéro de carte invalide", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(nom)) {
            Toast.makeText(this, "Nom requis", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(expiration) || !expiration.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            Toast.makeText(this, "Date invalide (format MM/AA)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(cvv) || cvv.length() < 3 || cvv.length() > 4) {
            Toast.makeText(this, "CVV invalide", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
