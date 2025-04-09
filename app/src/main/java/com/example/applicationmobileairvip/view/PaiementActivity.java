package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;

public class PaiementActivity extends AppCompatActivity {

    private EditText editNumeroCarte, editNomCarte, editExpiration, editCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);

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
                Toast.makeText(this, " Paiement effectué avec succès !", Toast.LENGTH_LONG).show();

                // Exemple d’un vol simulé (à adapter avec les vrais vols)
                //Vol volAchete = new Vol("AI245", "YUL", "CDG", "2025-04-07", "13:30", 30500.0); --  a modifier

                // Ajouter le vol à l'historique
                //VolReserveManager.ajouterVol(volAchete);

                // Redirection vers l'activité de confirmation
                Intent intent = new Intent(this, ConfirmationActivity.class);
                startActivity(intent);
                finish(); // Fermer la page de paiement
            }
        });
    }

    private boolean validerChamps(String numero, String nom, String expiration, String cvv) {
        if (TextUtils.isEmpty(numero) || numero.length() != 16) {
            Toast.makeText(this, "Numéro de carte invalide", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(nom)) {
            Toast.makeText(this, "Nom sur la carte requis", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(expiration) || !expiration.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            Toast.makeText(this, "Date d'expiration invalide (format MM/AA)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(cvv) || cvv.length() < 3 || cvv.length() > 4) {
            Toast.makeText(this, "Code de sécurité invalide", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}



