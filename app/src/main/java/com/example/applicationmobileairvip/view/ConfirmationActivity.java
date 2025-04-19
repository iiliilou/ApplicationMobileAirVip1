package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.model.Vol;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView textConfirmation, textDetails;
    private Vol currentVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        textConfirmation = findViewById(R.id.textConfirmation);
        textDetails = findViewById(R.id.textDetails);
        Button btnRetourAccueil = findViewById(R.id.btnRetourAccueil);
        Button btnPayer = findViewById(R.id.btn_payer);

        int volId = getIntent().getIntExtra("vol_id", -1);
        if (volId != -1) {
            chargerDetailsVol(volId);
        } else {
            Toast.makeText(this, "Aucun ID de vol reçu", Toast.LENGTH_SHORT).show();
        }

        btnRetourAccueil.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnPayer.setOnClickListener(v -> {
            if (currentVol != null) {
                Intent intent = new Intent(this, BillingActivity.class);
                intent.putExtra("vol_id", currentVol.getVolId());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Vol non chargé", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chargerDetailsVol(int volId) {
        ApiClient.get("/vols/" + volId, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    currentVol = mapper.readValue(response, Vol.class);

                    runOnUiThread(() -> {
                        textConfirmation.setText("Vol #" + currentVol.getVolId() + " - " + currentVol.getPrix() + " $CA");
                        textDetails.setText("Départ : " + currentVol.getAeroportDepart().getVille() +
                                "\nArrivée : " + currentVol.getAeroportArrive().getVille());
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(ConfirmationActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> Toast.makeText(ConfirmationActivity.this, "Erreur API : " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
