package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;

public class BillingActivity extends AppCompatActivity {

    private EditText editPrenom, editNom, editAdresse, editVille,
            editProvince, editCodePostal, editPays, editTelephone;

    private int volId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        // Récupérer le vol_id
        volId = getIntent().getIntExtra("vol_id", -1);

        // Lier les champs
        editPrenom = findViewById(R.id.editPrenom);
        editNom = findViewById(R.id.editNom);
        editAdresse = findViewById(R.id.editAdresse);
        editVille = findViewById(R.id.editVille);
        editProvince = findViewById(R.id.editProvince);
        editCodePostal = findViewById(R.id.editCodePostal);
        editPays = findViewById(R.id.editPays);
        editTelephone = findViewById(R.id.editTelephone);
        Button btnConfirmerAdresse = findViewById(R.id.btnConfirmerAdresse);

        // Action du bouton
        btnConfirmerAdresse.setOnClickListener(v -> {
            String prenom = editPrenom.getText().toString().trim();
            String nom = editNom.getText().toString().trim();
            String adresse = editAdresse.getText().toString().trim();
            String ville = editVille.getText().toString().trim();
            String province = editProvince.getText().toString().trim();
            String codePostal = editCodePostal.getText().toString().trim();
            String pays = editPays.getText().toString().trim();
            String telephone = editTelephone.getText().toString().trim();

            if (validerChamps(prenom, nom, adresse, ville, province, codePostal, pays, telephone)) {
                if (volId == -1) {
                    Toast.makeText(this, "Aucun vol associé à cette commande", Toast.LENGTH_LONG).show();
                    return;
                }

                // Rediriger vers PaiementActivity avec vol_id
                Intent intent = new Intent(this, PaiementActivity.class);
                intent.putExtra("vol_id", volId);
                startActivity(intent);
            }
        });
    }

    private boolean validerChamps(String prenom, String nom, String adresse, String ville,
                                  String province, String codePostal, String pays, String telephone) {
        if (TextUtils.isEmpty(prenom) || TextUtils.isEmpty(nom) || TextUtils.isEmpty(adresse)
                || TextUtils.isEmpty(ville) || TextUtils.isEmpty(province)
                || TextUtils.isEmpty(codePostal) || TextUtils.isEmpty(pays) || TextUtils.isEmpty(telephone)) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
